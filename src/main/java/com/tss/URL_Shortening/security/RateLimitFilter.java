package com.tss.URL_Shortening.security;

import com.tss.URL_Shortening.cache.RateLimitConfigCache;
import com.tss.URL_Shortening.dto.ratelimit.RateLimitConfigDto;
import com.tss.URL_Shortening.service.RateLimitService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {
    private final RateLimitConfigCache rateLimitConfigCache;
    private final RateLimitService rateLimitService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String endpointKey = getEndpointKey(request);

        // No rate limit configured
        if (endpointKey == null) {
            filterChain.doFilter(request, response);
            return;
        }

        RateLimitConfigDto config = rateLimitConfigCache.get(endpointKey);

        // Config not found or disabled
        if (config == null || !Boolean.TRUE.equals(config.getIsActive())) {
            filterChain.doFilter(request, response);
            return;
        }

        String clientKey = getClientKey(request, endpointKey);

        boolean allowed = rateLimitService.isAllowed(clientKey, config.getMaxRequests(), config.getWindowSeconds());

        if (!allowed) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("{\"message\":\"Too many requests. Please try again later.\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getClientKey(HttpServletRequest request, String endpointKey) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            String username = authentication.getName();
            return "rate:user:" + username + ":" + endpointKey;
        }

        String ip = request.getRemoteAddr();
        return "rate:ip:" + ip + ":" + endpointKey;
    }

    private String getEndpointKey(HttpServletRequest request) {

        String method = request.getMethod();
        String uri = request.getRequestURI();

        // AUTH APIs
        if ("POST".equals(method) && "/api/v1/auth/register".equals(uri)) {
            return "AUTH_REGISTER";
        }
        if ("POST".equals(method) && "/api/v1/auth/login".equals(uri)) {
            return "AUTH_LOGIN";
        }
        if ("GET".equals(method) && "/api/v1/auth/verify-email".equals(uri)) {
            return "AUTH_VERIFY_EMAIL";
        }
        if ("POST".equals(method) && "/api/v1/auth/forgot-password".equals(uri)) {
            return "AUTH_FORGOT_PASSWORD";
        }
        if ("POST".equals(method) && "/api/v1/auth/reset-password".equals(uri)) {
            return "AUTH_RESET_PASSWORD";
        }
        if ("POST".equals(method) && "/api/v1/auth/change-password".equals(uri)) {
            return "AUTH_CHANGE_PASSWORD";
        }

        // SHORT URL APIs
        if ("POST".equals(method) && "/api/v1/urls".equals(uri)) {
            return "CREATE_SHORT_URL";
        }
        if ("PUT".equals(method) && uri.matches("/api/v1/urls/\\d+")) {
            return "UPDATE_SHORT_URL";
        }
        if ("DELETE".equals(method) && uri.matches("/api/v1/urls/\\d+")) {
            return "DELETE_SHORT_URL";
        }

        // USER APIs
        if ("POST".equals(method) && "/api/v1/users/me/profile-picture".equals(uri)) {
            return "UPLOAD_PROFILE_IMAGE";
        }

        // QR CODE
        if ("POST".equals(method) && uri.matches("/api/v1/urls/\\d+/qr-code")) {
            return "GENERATE_QR_CODE";
        }

        // PURCHASE APIs
        if ("POST".equals(method) && "/api/v1/purchases".equals(uri)) {
            return "CREATE_PURCHASE";
        }
        if ("POST".equals(method) && uri.matches("/api/v1/purchases/\\d+/confirm")) {
            return "CONFIRM_PAYMENT";
        }

        // PUBLIC REDIRECT
        if ("GET".equals(method) && uri.matches("/[^/]+")) {
            return "PUBLIC_REDIRECT";
        }

        // USER REPORTS
        if ("GET".equals(method) && "/api/v1/reports/my/urls".equals(uri)) {
            return "USER_REPORT_URLS";
        }
        if ("GET".equals(method) && "/api/v1/reports/my/purchases".equals(uri)) {
            return "USER_REPORT_PURCHASES";
        }

        // ADMIN APIs
        if ("PUT".equals(method) && uri.matches("/api/v1/admin/config/[^/]+")) {
            return "ADMIN_CONFIG_UPDATE";
        }
        if ("PUT".equals(method) && uri.matches("/api/v1/admin/rate-limits/[^/]+")) {
            return "ADMIN_RATE_LIMIT_UPDATE";
        }

        // ADMIN REPORTS
        if ("GET".equals(method) && "/api/v1/admin/reports/overview".equals(uri)) {
            return "ADMIN_REPORT_OVERVIEW";
        }
        if ("GET".equals(method) && "/api/v1/admin/reports/users".equals(uri)) {
            return "ADMIN_REPORT_USERS";
        }
        if ("GET".equals(method) && "/api/v1/admin/reports/urls".equals(uri)) {
            return "ADMIN_REPORT_URLS";
        }

        return null;
    }


}
