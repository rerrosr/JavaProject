package com.example.univerproject.service;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;

/** The type Counter service. */
@Data
public class CounterService {
    private CounterService() {}

    private static AtomicInteger requestCount = new AtomicInteger(0);

    /** Increment request count. */
    public static synchronized void incrementRequestCount() {
        requestCount.incrementAndGet();
    }

    /**
     * Gets request count.
     *
     * @return the request count
     */
    public static synchronized int getRequestCount() {
        return requestCount.get();
    }
}
