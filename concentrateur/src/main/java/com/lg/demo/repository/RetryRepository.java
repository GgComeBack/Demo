package com.lg.demo.repository;

import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.Logger;
import reactor.util.retry.Retry;

import java.time.Duration;

public interface RetryRepository {

    long getRetryMax();

    long getMinDuration();

    String serviceName();

    Logger getLogger();

    default Retry manageRetry() {
        return Retry.backoff(getRetryMax(), Duration.ofMillis(getMinDuration())).filter(exception -> !(exception instanceof WebClientResponseException))
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                    getLogger().error(String.format("les service %s est en erreur aprés %d tentatives", serviceName(), getRetryMax()));
                    throw new RuntimeException(String.format("les service %s est en erreur aprés %d tentatives"));
                });
    }
}
