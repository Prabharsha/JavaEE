import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/items/*")
public class WildCardMapping extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Wild Card Mapping Spec Invoked");
        String method = req.getMethod();
        String servletPath = req.getServletPath();
        String contextPath = req.getContextPath();
        String pathInfo = req.getPathInfo();

        System.out.println("Method " +method);
        System.out.println("ServletPath "+servletPath);
        System.out.println("contextPath "+contextPath);
        System.out.println("Path Info "+pathInfo);

    }
}
