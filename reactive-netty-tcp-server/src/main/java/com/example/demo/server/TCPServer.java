package com.example.demo.server;

import com.example.demo.config.TcpServerConfiguration;
import com.example.demo.util.SenderReceiverUtil;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

import javax.annotation.PreDestroy;
import java.util.Optional;
import java.util.function.Function;

@Component
public class TCPServer {

    private final DisposableServer disposableServer;

    public TCPServer(final TcpServerConfiguration tcpConfig, final DisposableServer disposableServer) {
        this.disposableServer = TcpServer.create()
                .host(tcpConfig.getHostname())
                .port(tcpConfig.getPort())
                .doOnConnection(conn -> conn.addHandlerFirst(new ReadTimeoutHandler(20)))
                .doOnChannelInit(((connectionObserver, channel, socketAddress) -> {
                    channel.pipeline().addFirst(new LoggingHandler("reactive.netty.examples"));
                    channel.pipeline().addLast("frameDecoder", new LineBasedFrameDecoder(80));
                    channel.pipeline().addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
                })).handle((nettyInbound, nettyOutbound) ->
                    SenderReceiverUtil.receiveAndSend(nettyInbound, nettyOutbound)
                ).bindNow();
    }

    @PreDestroy
    public void shutDownTcpServer() {
        Optional.ofNullable(this.disposableServer).ifPresent(DisposableServer::disposeNow);
    }
}
