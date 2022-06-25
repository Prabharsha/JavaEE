import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/json")
public class JSONServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //Create and returning a single Json file
        resp.setContentType("application/json");

/*        JsonObjectBuilder objectBuild = Json.createObjectBuilder();
        objectBuild.add("id","C001");
        objectBuild.add("name","Kamal");
        objectBuild.add("address","Galle");
        objectBuild.add("salary",45000);

        JsonObject build = objectBuild.build();
        PrintWriter writer = resp.getWriter();
        writer.print(build);*/

        //Create and returning Json array
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); //create a json array

        JsonObjectBuilder object1 = Json.createObjectBuilder(); //create object1

        object1.add("id","C001");
        object1.add("name","Kamal");
        object1.add("address","Galle");
        object1.add("salary",45000);

        JsonObjectBuilder object2 = Json.createObjectBuilder(); //create object2

        object2.add("id","C002");
        object2.add("name","Nimal");
        object2.add("address","Matara");
        object2 .add("salary",5000);

        arrayBuilder.add(object1.build()); //add json object1 into the json array
        arrayBuilder.add(object2.build()); //add json object2 into the json array

        PrintWriter writer = resp.getWriter();
        writer.print(arrayBuilder.build()); //pront the json arrya as the response
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

/*       ServletInputStream inputStream = req.getInputStream();
        int read ;
        while ((read = inputStream.read() )!= -1){
            System.out.print((char)read);
        }*/

        //How to work with JSON Processing
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        String salary = jsonObject.getString("salary");

        System.out.println("ID -"+id+" name-"+name+" Address-"+address+" Salary-"+salary );
    }
}
