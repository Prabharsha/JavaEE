package listeners;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;


@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Context Initialized");
        // create the DBCP pool
        BasicDataSource dbcp = new BasicDataSource();
        dbcp.setDriverClassName("com.mysql.jdbc.Driver");
        dbcp.setUrl("jdbc:mysql://localhost:3306/company");
        dbcp.setUsername("root");
        dbcp.setPassword("1234");
        dbcp.setMaxTotal(5);
        dbcp.setInitialSize(5);

        ServletContext servletContext = servletContextEvent.getServletContext(); //the common place for all servlets
        servletContext.setAttribute("dbcp",dbcp); //store the pool inside the servlet context
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            System.out.println("Context Destroyed");
            ServletContext servletContext = servletContextEvent.getServletContext();
            BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("bds");
            bds.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
