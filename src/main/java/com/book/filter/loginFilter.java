package com.book.filter;

import com.book.dao.UserDao;
import com.book.rest.UserResource;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lixuy on 2016/5/24.
 */
public class LoginFilter extends HttpServlet implements  Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response  =(HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        String uri = request.getRequestURI();

        if(uri.endsWith("bookList.html") || uri.endsWith("login.html") || uri.endsWith("register.html")
                || uri.endsWith("user/user_name") || uri.endsWith("car/list") || uri.endsWith("bookType")
                || uri.endsWith("login") || uri.endsWith("valid") || uri.endsWith("register")
                || uri.startsWith("/book/book/")|| uri.endsWith("/book/book")
                || uri.endsWith(".js") || uri.endsWith(".css")){
            filterChain.doFilter(request, response);
            return;

        }else if(StringUtils.isNotBlank((String) session.getAttribute(UserServiceImpl.USERID))){
            filterChain.doFilter(request, response);
            return;
        }
        // 不符合，跳转到登陆页面
        response.sendRedirect(request.getContextPath() + "/html/login.html");
        return ;
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
