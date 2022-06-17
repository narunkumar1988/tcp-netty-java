package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("tcp-client")
public class TcpClientProperties {

    private String hostName;

    private int port;

    private TcpConfig config;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public TcpConfig getConfig() {
        return config;
    }

    public void setConfig(TcpConfig config) {
        this.config = config;
    }
}
