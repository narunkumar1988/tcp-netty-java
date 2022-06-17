package com.example.demo.config;

public class TcpConfig {

    private boolean keepAlive = true;
    private boolean noDelay = true;
    private int connectionTimeoutMillis = 250;
    private int idleTimeout = 30;
    private int maxLifeTime = 120;
    private int maxConnections = 50;

    public TcpConfig(){}

    public TcpConfig(boolean keepAlive, boolean noDelay, int connectionTimeoutMillis, int idleTimeout, int maxLifeTime, int maxConnections) {
        this.keepAlive = keepAlive;
        this.noDelay = noDelay;
        this.connectionTimeoutMillis = connectionTimeoutMillis;
        this.idleTimeout = idleTimeout;
        this.maxLifeTime = maxLifeTime;
        this.maxConnections = maxConnections;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean isNoDelay() {
        return noDelay;
    }

    public void setNoDelay(boolean noDelay) {
        this.noDelay = noDelay;
    }

    public int getConnectionTimeoutMillis() {
        return connectionTimeoutMillis;
    }

    public void setConnectionTimeoutMillis(int connectionTimeoutMillis) {
        this.connectionTimeoutMillis = connectionTimeoutMillis;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public int getMaxLifeTime() {
        return maxLifeTime;
    }

    public void setMaxLifeTime(int maxLifeTime) {
        this.maxLifeTime = maxLifeTime;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }
}
