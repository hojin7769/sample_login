package com.example.sample_login.config.filter;

import com.example.sample_login.util.MDCUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.Enumeration;

public class LogFilter implements Filter {

    /**
     * Request, Response가 필터를 거칠 때 수행되는 메소드
     * LogFilter에서는 Request 정보로 MDC 정보를 생성하기 위해서 사용
     *
     * @param request - 사용자 요청 정보
     * @param response - 응답 정보
     * @param chain - chain
     * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration<String> str = req.getHeaderNames();

        try {
            MDCUtil.put( "requestUID" , RandomStringUtils.randomAscii( 8 ) );
            MDCUtil.put( "requestUserAgent" , req.getHeaderNames().toString() );

            chain.doFilter( request, response );
        }
        finally {
            MDCUtil.clear();
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private String getHeader( HttpServletRequest req ) {

        return req.getHeader( "X-Forwarded-For" );
    }
}
