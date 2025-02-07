package com.zerobase.table_reserve.security.filter;


import com.zerobase.table_reserve.security.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberFilter extends OncePerRequestFilter {
    private final TokenProvider provider;

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // "/member/**" 경로는 필터를 통과시키도록 설정
        if (requestURI.startsWith("/member/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = resolveTokenFromRequest(request);


        if (!provider.validateToken(token)) {
            log.info("유효하지 않은 토큰 값" + token);
            throw new ServletException("Invalid Access");
        }


        Authentication auth = provider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.info("auth 데이터 " + SecurityContextHolder.getContext().getAuthentication());

        filterChain.doFilter(request, response);
    }

    private String resolveTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
            return token.substring(TOKEN_PREFIX.length());
        }
        return null;
    }


}
