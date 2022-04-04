package com.lg.demo.repository;

import io.netty.handler.logging.LogLevel;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

public interface HttpInitRepository {

    default HttpClient logHttpAndMaxPoolsite(String namePool, int maxConnextion, int pendingAcquireMaxCount) {
        return HttpClient
                .create(ConnectionProvider.builder(namePool)
                        .maxConnections(maxConnextion)
                        .pendingAcquireMaxCount(pendingAcquireMaxCount)
                        .build())
                .wiretap("reactor.netty.http.client.HttpClient",
                        LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);
    }
}
