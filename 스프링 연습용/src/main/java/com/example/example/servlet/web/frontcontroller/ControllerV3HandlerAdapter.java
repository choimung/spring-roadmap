package com.example.example.servlet.web.frontcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.servlet.ModelAndView;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter{
    @Override
    public boolean support(Object handler) {
        return handler instanceof ControllerV3;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handle) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handle;
        Map<String, String> paramMap = createParamMap(request);
        ModelAndView mv = controller.process(paramMap);
        return mv;
    }
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }
}
