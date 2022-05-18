package com.lab2;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",urlPatterns ={"/lab2/welcome.jsp"})
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("i am in LoginFilter-init()");
    }

    public void destroy() {
        System.out.println("i am in LoginFilter--destroy()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("i am in LoginFilter--doFilter()--request before chain");
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse) response;
        HttpSession session=req.getSession();
        if(session==null||session.getAttribute("login")==null){
            res.sendRedirect("login.jsp");
        }

        chain.doFilter(request, response);
        System.out.println("i am in LoginFilter--doFilter()--response after chain");
    }
}