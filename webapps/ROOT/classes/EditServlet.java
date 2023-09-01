import java.io.IOException;  
import java.io.PrintWriter;  
  
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 
@WebServlet("/EditServlet")  
public class EditServlet extends HttpServlet {  

    //javac -cp .:/home/harris/tomcat/lib/servlet-api.jar EditServlet.java

    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
           throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        out.println("<h1>Update Employee</h1>");  
        String sid=request.getParameter("id");  
        int id=Integer.parseInt(sid);  
          
        Employee e=EmpDao.getEmployeeById(id);  
          
        out.print("<form action='EditServlet2' method='post'>");  
        out.print("<table>");  
        out.print("<tr><td>id:</td><td><input type='number' name='id' value='"+e.getId()+"'/></td></tr>");   
        out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+e.getName()+"'/></td></tr>");  
        out.print("<tr><td>Password:</td><td><input type='password' name='password' value='"+e.getPassword()+"'/></td></tr>");  
        out.print("<tr><td>Age:</td><td><input type='number' name='age' value='"+e.getAge()+"'/></td></tr>");  
        out.print("<tr><td>Role:</td><td><input type='text' name='role' value='"+e.getRole()+"'/></td></tr>");   
        out.print("<tr><td>Salary:</td><td><input type='number' name='salary' step='0.01' value='"+e.getSalary()+"'/></td></tr>"); 
       
        out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
        out.print("</table>");  
        out.print("</form>");   
        out.close();  
    }  
}  