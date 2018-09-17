package com.randall.proxy.loader;

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
 * @version 2018-09-14 14:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class CommentLoaderTest {
    
    @Autowired
    private CommentLoader commentLoader;

    @Test
    public void loadSongs() throws Exception {
        commentLoader.loadSongs(32317208, 32317208);
    }
}