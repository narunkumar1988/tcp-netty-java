package com.example.demo.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RequestCorrelator {

    private Map<String, CompletableFuture<String>> responseFutures = new ConcurrentHashMap<>(1000);

    public Map<String, CompletableFuture<String>> getResponseFutures() {
        return this.responseFutures;
    }

    public CompletableFuture<String> addResponseFuture(final String key, CompletableFuture<String> value) {
        return this.responseFutures.put(key, value);
    }
}
