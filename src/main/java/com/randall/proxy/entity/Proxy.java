package com.randall.proxy.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author damon4u
 * @version 2018-08-28 10:49
 */
@Data
public class Proxy implements Serializable {

    /**
     * 主键id
     */
    private int id;

    /**
     * ip地址或者域名
     */
    private String ip;

    /**
     * 端口号
     */
    private int port;

    /**
     * 协议http、https、socks4等
     */
    private String proto;
    
    public Proxy() {}

    public Proxy(String ip, int port, String proto) {
        this.ip = ip;
        this.port = port;
        this.proto = proto.toLowerCase();
    }

    @Override
    public String toString() {
        return proto + "://" + ip + ":" + port;
    }

}
