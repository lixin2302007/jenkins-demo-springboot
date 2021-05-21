package com.elane.learning.bucket4j;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;

public class Bucket4jTest {

    private static ConcurrentMap<String, RateLimits> perTenantLimits = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 40; i++) {
//            execRateLimit("001", "2:30");
            testRateLimit();
            Thread.sleep(2000);
        }


    }

    public static boolean execRateLimit(String tenantId, String perTenantLimitsConfiguration) {
        RateLimits rateLimits = perTenantLimits.computeIfAbsent(tenantId, id -> new RateLimits(perTenantLimitsConfiguration));
        if (!rateLimits.tryConsume()) {
            System.out.println("tryConsume false, tenantId=" + tenantId + ",leftToken=" + rateLimits.getAvailableTokens());
            return false;
        } else {
            System.out.println("tryConsume true, tenantId=" + tenantId + ",leftToken=" + rateLimits.getAvailableTokens());
            return true;
        }
    }

    public static void testRateLimit() {
        Bandwidth limit = Bandwidth.simple(1, Duration.ofSeconds(20));
        Bucket bucket = Bucket4j.builder().addLimit(limit).build();
        IntStream.rangeClosed(1, 5).forEach(i -> {
            GuavaListeningExecutorService.executor.submit(() -> {
                if (bucket.tryConsume(1)) {
                    System.out.println("acquired");
                } else {
                    System.out.println("blocked");
                }
            });
        });
    }
}