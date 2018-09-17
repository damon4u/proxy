package com.randall.proxy.job;

import com.randall.proxy.loader.ProxyLoader;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;


/**
 * Description:
 *
 * @author damon4u
 * @version 2018-09-10 11:57
 */
@Component
public class ProxyLoadJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyLoadJob.class);
    
    @Autowired
    private ProxyLoader proxyLoader;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        LOGGER.info("ProxyLoadJob start.");
        try {
            proxyLoader.loadProxy();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("ProxyLoadJob end.");
    }
}
