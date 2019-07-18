package ru.job4j.servlets.http.servlet;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*
@WebFilter(urlPatterns = {"*"})
*/

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (request.getRequestURI().contains("signin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        if (session.getAttribute("role") == null) {
        //    ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/signin", request.getContextPath()));
            request.getRequestDispatcher("/WEB-INF/views/loginView.jsp").forward(servletRequest, servletResponse);
        }
       filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
