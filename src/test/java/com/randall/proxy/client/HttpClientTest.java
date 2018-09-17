package com.randall.proxy.client;

import com.randall.proxy.entity.CommentResponseBody;
import com.randall.proxy.entity.Proxy;
import com.randall.proxy.http.HttpProxyClientFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Response;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Description:
 *
 * @author damon4u
 * @version 2018-09-14 13:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class HttpClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientTest.class);

    @Autowired
    private GitProxyClient gitProxyClient;

    @Test
    public void loadProxy() throws Exception {
        Response<List<Proxy>> response = gitProxyClient.loadProxy().execute();
        List<Proxy> proxyList = response.body();
        LOGGER.info("proxyList={}", proxyList);
    }
    
    @Test
    public void songInfo() throws Exception {
        Response<String> response = HttpProxyClientFactory
                .musicClient(new Proxy("183.88.232.207", 8080, "http"), 15)
                .songInfo(209996)
                .execute();
        assertNotNull(response);
        LOGGER.info(response.toString());
        LOGGER.info(response.body());
    }
    
    @Test
    public void comment() throws Exception {
        String params = "flQdEgSsTmFkRagRN2ceHMwk6lYVIMro5auxLK/JywlqdjeNvEtiWDhReFI+QymePGPLvPnIuVi3dfsDuqEJW204VdwvX+gr3uiRBeSFuOm1VUSJ1HqOc+nJCh0j6WGUbWuJC5GaHTEE4gcpWXX36P4Eu4djoQBzoqdsMbCwoolb2/WrYw/N2hehuwBHO4Oz";
        String encSecKey = "0263b1cd3b0a9b621a819b73e588e1cc5709349b21164dc45ab760e79858bb712986ea064dbfc41669e527b767f02da7511ac862cbc54ea7d164fc65e0359962273616e68e694453fb6820fa36dd9915b2b0f60dadb0a6022b2187b9ee011b35d82a1c0ed8ba0dceb877299eca944e80b1e74139f0191adf71ca536af7d7ec25";
        Response<CommentResponseBody> response = HttpProxyClientFactory
                .musicClient(new Proxy("183.88.232.207", 8080, "http"), 15)
                .comment(209996L, params, encSecKey)
                .execute();
        assertNotNull(response);
        LOGGER.info(response.toString());
        LOGGER.info("body={}", response.body());
    }
}