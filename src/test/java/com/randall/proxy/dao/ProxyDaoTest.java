package com.randall.proxy.dao;

import com.randall.proxy.entity.Proxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Description:
 *
 * @author damon4u
 * @version 2018-09-14 13:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class ProxyDaoTest {
    
    @Autowired
    private ProxyDao proxyDao;

    @Test
    public void save() {
        int ret = proxyDao.save(new Proxy("127.0.0.1", 80, "http"));
        assertEquals(1, ret);
    }
}