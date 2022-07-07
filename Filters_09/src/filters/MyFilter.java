package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/item")
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
    }

    @Override
    public void destroy() {
        System.out.println("Destroy method invoked!");
    }
}
