package com.eventify.security;

import com.eventify.controller.LoginBean;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nehem
 */
@WebFilter("/*")
public class SecurityFilter implements Filter {

    @Inject
    private LoginBean loginBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        if (path.contains("admin") && !loginBean.hasRol("ADMIN")) {
            res.sendRedirect(req.getContextPath());
        } else if (path.contains("eventos") && !loginBean.isLoggedIn()) {
            res.sendRedirect(req.getContextPath());
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
