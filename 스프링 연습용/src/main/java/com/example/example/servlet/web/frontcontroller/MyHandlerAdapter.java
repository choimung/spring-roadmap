package com.example.example.servlet.web.frontcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.servlet.ModelAndView;

public interface MyHandlerAdapter {
    boolean support(Object handler);
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handle) throws ServletException, IOException;
}
