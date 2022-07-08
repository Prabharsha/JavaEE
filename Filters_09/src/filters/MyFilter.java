package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebFilter(urlPatterns = {"/customer","item"})
/*@WebFilter(urlPatterns = "/*")*/
public class MyFilter implements Filter {
    public MyFilter(){
        System.out.println("Object created form my filter");
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("My Filter Initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter method called");

        //Casting super type to sub type
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        filterChain.doFilter(servletRequest,servletResponse);

        PrintWriter writer = servletResponse.getWriter();
        writer.write("added from filter");

        res.addHeader("Header1","Added");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy method invoked!");
    }
}
