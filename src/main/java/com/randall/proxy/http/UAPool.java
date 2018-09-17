package com.randall.proxy.http;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Description:
 *
 * @author damon4u
 * @version 2018-08-17 15:26
 */
public class UAPool {

    private static String[] UA_LIST = {"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50"
            ,"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50"
            ,"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)"
            ,"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)"
            ,"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)"
            ,"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1"
            ,"Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1"
            ,"Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11"
            ,"Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11"
            ,"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11"
            ,"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)"
            ,"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)"
            ,"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"
            ,"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)"
            ,"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)"
            ,"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)"
            ,"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Avant Browser)"
            ,"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36"
            ,"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"};

    public static String getUA(){
        int index = ThreadLocalRandom.current().nextInt(UA_LIST.length);
        return UA_LIST[index];
    }
}
