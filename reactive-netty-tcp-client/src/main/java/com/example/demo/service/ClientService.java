package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.NettyOutbound;
import reactor.netty.tcp.TcpClient;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ClientService {

    final Logger logger = LoggerFactory.getLogger(ClientService.class);

    private final RequestCorrelator requestCorrelator;
    private final TcpClient tcpClient;

    public ClientService(final RequestCorrelator requestCorrelator, final TcpClient tcpClient) {
        this.requestCorrelator = requestCorrelator;
        this.tcpClient = tcpClient;
    }

    public Mono<String> getData(ServerWebExchange exchange) {
        final String apiId = Optional.ofNullable(exchange.getRequest())
                .map(HttpMessage::getHeaders)
                .map(headers -> headers.getFirst("apiId"))
                .orElse(null);
        final String uuid = UUID.randomUUID().toString();
        final String message = "Api Id is "+apiId;
        logger.info("Request sent: {}", message);
        final CompletableFuture<String> future = new CompletableFuture<>();
        this.requestCorrelator.addResponseFuture(uuid, future);
        return this.tcpClient
                .connect()
                .flatMap(connection -> getOutBound(message, connection, uuid)
                        .then()
                        .then(Mono.fromFuture(future)));

    }

    private NettyOutbound getOutBound(final String message, final Connection connection, final String uuid) {
        return connection
                .outbound()
                .sendString(Mono.just(message))
                .then(connection.inbound().receive().asString().flatMap(response -> {
                    logger.info("Received response: {}", response);
                    this.requestCorrelator.getResponseFutures().remove(uuid).complete(response);
                    return Mono.empty();
                }));
    }
}
