package com.example.demo.controller;

import com.example.demo.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class TcpClientController {

    private final ClientService clientService;

    public TcpClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/data")
    public Mono<String> getDataFromServer(final ServerWebExchange exchange){
        return this.clientService.getData(exchange);
    }
}
