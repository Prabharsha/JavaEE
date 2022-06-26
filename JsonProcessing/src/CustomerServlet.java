import sun.dc.pr.PRError;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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

        String option = req.getParameter("option");

        resp.setContentType("application/json"); //set the response media type
        try {
            //Initialize the connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "1234");
            PrintWriter writer = resp.getWriter();

            switch (option) {
                case "SEARCH":
                    //for search customer

                case "GETALL":
                    System.out.println("case getAll");
                    ResultSet resultSet = connection.prepareStatement("SELECT * FROM customer").executeQuery();
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();//json array

                    //Access thereports andgenerate a Json object
                    while (resultSet.next()) {
                        System.out.println("inside while");
                        String tempId = resultSet.getString(1);
                        String tempName = resultSet.getString(2);
                        String tempAddress = resultSet.getString(3);
                        double tempSalary = resultSet.getDouble(4);

                        //create a Json object and set values
                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("id", tempId);
                        objectBuilder.add("name", tempName);
                        objectBuilder.add("address", tempAddress);
                        objectBuilder.add("salary", tempSalary);

                        //add Json object to Json array
                        arrayBuilder.add(objectBuilder.build());
                    }
                    //Generate a custom response with JSon
                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", 200);
                    response.add("Message", "Done");
                    response.add("data", arrayBuilder.build());
                    writer.print(response.build());
                    break;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost method invoked");
        String customerId = req.getParameter("customerId");
        String customerName = req.getParameter("customerName");
        String customerAddress = req.getParameter("customerAddress");
        String customerSalary = req.getParameter("customerSalary");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");
            preparedStatement.setObject(1, customerId);
            preparedStatement.setObject(2, customerName);
            preparedStatement.setObject(3, customerAddress);
            preparedStatement.setObject(4, customerSalary);

            if (preparedStatement.executeUpdate()>0){
                JsonObjectBuilder responseObject = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_CREATED); //201
                responseObject.add("status",200);
                responseObject.add("message","Customer Saved Successfully !");
                responseObject.add("data","");
                writer.print(responseObject.build());
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
