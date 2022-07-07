import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns ="/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        BasicDataSource dbcp = (BasicDataSource) servletContext.getAttribute("dbcp");
        System.out.println("Item Servlet DoGet Method");

        try{
            Connection connection = dbcp.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                System.out.println(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
