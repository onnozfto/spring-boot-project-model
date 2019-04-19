package com.self.project.configurer.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description
 * @Author will
 * @Date 2019/04/17
 */
public class URLSplitFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        if (requestURI.indexOf("/api") != -1) {
            String replace = requestURI.replace("/api", "");
            request.getRequestDispatcher(replace).forward(request, servletResponse);
        } else {
            filterChain.doFilter(request, servletResponse);
        }

    }
}
