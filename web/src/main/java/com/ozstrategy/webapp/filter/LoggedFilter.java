package com.ozstrategy.webapp.filter;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoggedFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String username = null;
        try {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) req.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
            username = securityContextImpl.getAuthentication().getName();
        } catch (NullPointerException e) {
        }

        if (req.getHeader("x-requested-with") != null
                && req.getHeader("x-requested-with")
                .equalsIgnoreCase("XMLHttpRequest")) {
            if (username == null) {
                res.addHeader("sessiontimeout", "true");
            } else {
                chain.doFilter(req, res);

            }
//      if(((HttpServletRequest)request).getSession().isNew())  {
////        ((HttpServletResponse)response).setStatus(500,"sessiontimeout");
//        System.out.println("timeout");
//        res.addHeader("sessionstatus", "timeout");
//      }  else {
//        chain.doFilter(request,response);
//      }
        } else {
            chain.doFilter(request, response);
        }

    }

    public void destroy() {
    }

}
