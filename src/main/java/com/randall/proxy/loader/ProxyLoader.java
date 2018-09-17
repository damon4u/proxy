package com.randall.proxy.loader;

import com.randall.proxy.client.GitProxyClient;
import com.randall.proxy.constant.HttpConfig;
import com.randall.proxy.dao.ProxyDao;
import com.randall.proxy.entity.Proxy;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author damon4u
 * @version 2018-08-28 10:57
 */
@Component
public class ProxyLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyLoader.class);

    @Autowired
    private ProxyDao proxyDao;
    
    @Autowired
    private GitProxyClient gitProxyClient;
    
    @Autowired
    private CommentLoader commentLoader;
    
    @Autowired
    private HttpConfig httpConfig;
    
    public void loadProxy() throws Exception {
        loadFromGit();
    }
    
    private void loadFromGit() throws Exception {
        LOGGER.info("loadFromGit start.");
        Response<List<Proxy>> response = gitProxyClient.loadProxy().execute();
        List<Proxy> proxyList = response.body();
        filterProxy(proxyList);
        LOGGER.info("loadFromGit done.");
    }

    private void filterProxy(List<Proxy> proxyList) {
        if (CollectionUtils.isNotEmpty(proxyList)) {
            final CountDownLatch latch = new CountDownLatch(proxyList.size());
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            proxyList.forEach(proxy -> executorService.execute(new FilterJob(proxy, latch)));
            try {
                latch.await();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
            executorService.shutdownNow();
        }
    }

    class FilterJob implements Runnable {
        
        private Proxy proxy;
        
        private CountDownLatch latch;

        FilterJob(Proxy proxy, CountDownLatch latch) {
            this.proxy = proxy;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                // 先只要http类型的
                if (proxy.getProto().startsWith("http")
                        && commentLoader.getSongInfo(209996, proxy, httpConfig.getProxyValidateTimeout()) != null) {
                    LOGGER.info("================> {}", proxy);
                    proxyDao.save(new Proxy(proxy.getIp(), proxy.getPort(), proxy.getProto()));
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            } finally {
                latch.countDown();
            }
        }
    }

    public void validateFromDb() {
        List<Proxy> allProxy = proxyDao.getAllProxy();
        if (CollectionUtils.isNotEmpty(allProxy)) {
            final CountDownLatch latch = new CountDownLatch(allProxy.size());
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            allProxy.forEach(proxy -> executorService.execute(new ValidateJob(proxy, latch)));
            for (Proxy proxy : allProxy) {
                executorService.execute(new ValidateJob(proxy, latch));
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
            executorService.shutdownNow();
        }
    }

    class ValidateJob implements Runnable {

        private Proxy proxy;

        private CountDownLatch latch;

        ValidateJob(Proxy proxy, CountDownLatch latch) {
            this.proxy = proxy;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                if (commentLoader.getSongInfo(209996, proxy, httpConfig.getProxyValidateTimeout()) == null) {
                    proxyDao.delete(proxy.getId());
                    LOGGER.error("disable proxy={}", proxy.getProxyStr());
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            } finally {
                latch.countDown();
            }
        }
    }
    
}
