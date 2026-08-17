package com.tss.URL_Shortening.service;

public interface RateLimitService {
    boolean isAllowed(String key, int maxRequests, int windowSeconds);
}
