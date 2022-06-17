package com.example.demo.util;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;

import java.util.UUID;

public class SenderReceiverUtil {

    private SenderReceiverUtil(){}

    static Logger logger = LoggerFactory.getLogger(SenderReceiverUtil.class);
    public static Publisher<Void> receiveAndSend(NettyInbound inbound, NettyOutbound outbound) {
        return inbound.receive().asString().flatMap( receivedData -> {
            logger.info("Received Message: {}", receivedData);
            final String sentMessage = receivedData.concat(UUID.randomUUID().toString());
            logger.info("Sent data: {}", sentMessage);
            return outbound.sendString(Mono.just(sentMessage)).then();
        });
    }
}
