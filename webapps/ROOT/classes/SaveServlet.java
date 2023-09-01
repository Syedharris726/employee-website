import java.io.IOException;  
import java.io.PrintWriter;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 

@WebServlet("/SaveServlet")  
public class SaveServlet extends HttpServlet {  

    //javac -cp .:/home/harris/tomcat/lib/servlet-api.jar SaveServlet.java

    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
         throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        String idStr = request.getParameter("id");
        
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String ageStr = request.getParameter("age");
        
        String role = request.getParameter("role");
        String salaryStr = request.getParameter("salary");
        
          
        int id = 0;
        int age = 0;
        BigDecimal salary = BigDecimal.ZERO;
        
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }
        
        if (ageStr != null && !ageStr.isEmpty()) {
            age = Integer.parseInt(ageStr);
        }
        
        if (salaryStr != null && !salaryStr.isEmpty()) {
            salary = new BigDecimal(salaryStr);
        }

        Employee e=new Employee(id,name,password,age,role,salary);  
        
        int status=EmpDao.save(e);  
        if(status>0){  
            out.print("<p>Record saved successfully!</p>");  
            request.getRequestDispatcher("index.html").include(request, response);  
        }else{  
            out.println("Sorry! unable to save record");  
        }  
          
        out.close();  
    }  
  
}  