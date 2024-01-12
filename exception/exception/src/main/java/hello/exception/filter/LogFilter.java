package hello.exception.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();


        try{
            log.info("REQUEST [{}][{}][{}]", uuid, servletRequest.getDispatcherType(), requestURI);
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("RESPONSE [{}][{}][{}]", uuid, servletRequest.getDispatcherType(), requestURI);
        }
    }
}
