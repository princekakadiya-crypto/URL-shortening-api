package com.tss.URL_Shortening.security;

import com.tss.URL_Shortening.service.TokenBlacklistRedisService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;
    private UserDetailsService userDetailsService;
    private final TokenBlacklistRedisService tokenBlacklistRedisService;
    public JwtAuthenticationFilter (JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService,TokenBlacklistRedisService tokenBlacklistRedisService) {
        super();
        this.jwtTokenProvider= jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.tokenBlacklistRedisService=tokenBlacklistRedisService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // get JWT token from http request
        String token = getTakenFromRequest(request);
        if (StringUtils.hasText(token)) {

            // Check Redis blacklist
            if (tokenBlacklistRedisService.isBlacklisted(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token has been logged out");
                return;
            }

            // Validate JWT
            if (jwtTokenProvider.validateToken(token)) {

                // Get username from token
                String username = jwtTokenProvider.getUsername(token);

                // Load user details
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);

    }

    private String getTakenFromRequest (HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        System.out.println("----------------> "+bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.split(" ")[1].trim();
            // substring(7, bearerToken.length());
        }
        return null;
    }
}
