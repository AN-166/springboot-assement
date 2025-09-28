package com.interview.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger("REQUEST_RESPONSE");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            logRequest(wrappedRequest);
            logResponse(wrappedResponse);
            wrappedResponse.copyBodyToResponse(); // important!
        }
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        String requestBody = "";
        byte[] buf = request.getContentAsByteArray();
        if (buf.length > 0) {
            requestBody = new String(buf, StandardCharsets.UTF_8);
        }
        logger.info("REQUEST: {} {} Body: {}", request.getMethod(), request.getRequestURI(), requestBody);
    }

    private void logResponse(ContentCachingResponseWrapper response) throws IOException {
        String responseBody = "";
        byte[] buf = response.getContentAsByteArray();
        if (buf.length > 0) {
            responseBody = new String(buf, StandardCharsets.UTF_8);
        }
        logger.info("RESPONSE Status: {} Body: {}", response.getStatus(), responseBody);
    }
}
