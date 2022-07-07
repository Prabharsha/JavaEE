import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ="/hello")
public class MyServlet extends HttpServlet {

    //life cycle of a servlet
    public MyServlet(){
        System.out.println("Object Created !");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Became a servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Got the Request !");
    }

    @Override
    public void destroy() {
        System.out.println("Destroyed !");
    }
}
