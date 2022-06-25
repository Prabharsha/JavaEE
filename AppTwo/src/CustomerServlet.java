import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");

            resp.addHeader("appOne", "approved");
            resp.addHeader("Browser", "Brave");

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "1234");
            ResultSet rst = connection.prepareStatement("SELECT * FROM customer").executeQuery();
            String records = "";
            while (rst.next()) {
                String id = rst.getString(1);
                String name = rst.getString(2);
                String address = rst.getString(3);
                double salary = rst.getDouble(4);

                //convert one record to Json
                String customer = "{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"address\":\"" + address + "\",\"salary\":" + salary + "},";
                records = records + customer;
            }
            //after last object "," must be removed
            String finalJson = "[" + records.substring(0, records.length() - 1) + "]";

            PrintWriter writer = resp.getWriter();
            writer.write(finalJson);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("customerId");
        String customerName = req.getParameter("customerName");
        String customerAddress = req.getParameter("customerAddress");
        String customerSalary = req.getParameter("customerSalary");

        System.out.println(customerId + " " + customerName + " " + customerAddress + " " + customerSalary);

        /*query*/
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer SET name=?,address=?,salary=? WHERE id=?");
            preparedStatement.setObject(1,customerName);
            preparedStatement.setObject(2,customerAddress);
            preparedStatement.setObject(3,customerSalary);
            preparedStatement.setObject(4,customerId);
            boolean b = preparedStatement.executeUpdate()>0;
            PrintWriter writer = resp.getWriter();
            if (b){
                writer.write("Customer Updated !");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String customerId = req.getParameter("customerId");
        String customerName = req.getParameter("customerName");
        String customerAddress = req.getParameter("customerAddress");
        String customerSalary = req.getParameter("customerSalary");

        System.out.println(customerId + " " + customerName + " " + customerAddress + " " + customerSalary);

        /*Save Customer In to database*/
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");
            preparedStatement.setObject(1, customerId);
            preparedStatement.setObject(2, customerName);
            preparedStatement.setObject(3, customerAddress);
            preparedStatement.setObject(4, customerSalary);
            PrintWriter writer = resp.getWriter();
            boolean b = preparedStatement.executeUpdate() > 0;
            if (b) {
                writer.write("Customer Saved !");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("custId");
        System.out.println("Customer ID " + customerId);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM customer WHERE id =?");
            preparedStatement.setObject(1, customerId);

            boolean b = preparedStatement.executeUpdate() > 0;
            PrintWriter writer = resp.getWriter();

            if (b) {
                writer.write("Customer Deleted");
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
