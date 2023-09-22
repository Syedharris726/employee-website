Employee Website Documentation

Introduction

    The Employee Website is a web application that enables users to manage employee records. This documentation provides a detailed guide on how to set up, use, and customize the application.

Technologies Used

    HTML for the user interface
    Java for server-side logic
    Java Servlets for handling HTTP requests
    PostgreSQL for the database
    Apache Tomcat for local hosting

Installation for ubuntu users

    1.Install java:
        sudo apt update
        sudo apt install openjdk-17-jdk

        here 17 is just version we can use other versions

    2.Install tomcat:
        download tomcat with desired version in tomcat.apache.org

        in my case, i downloaded tomcat 10

    3.Install postgresql:
        download postgresql with desired version in postgresql.org

        in my case, i downloaded postgresql 15

    4.Install pgadmit4:
        download pgadmit-4 in pgadmin.org


Code Structure
    The code for the Employee CRUD Operations Website is organized as follows:

    src/main/java: Contains Java source code.
        Employee.java: Defines the Employee entity.
        EmpDAO.java: Manages data access for employees.
        SaveServlet.java: Handles HTTP requests related to adding and saving employee details.
        ViewServlet.java: Handles HTTP requests related to viewing employee details.
        EditServlet.java & EditServlet2.java: Handles HTTP requests related to updating employee details.
        DeleteServlet.java: Handles HTTP requests related to deleteing employee details.

    src/main/webapp: Contains HTML templates and web resources.
        index.html: Home page for the application.

    WEB-INF: Configuration files.
        web.xml: Servlet configuration and database connection details.

Detailed code Explanation

    create a sql database using postgresql
    Open pgadmin4 give master password
    then click server->register->server
    there we have to give name as postgres, hostname as localhost, port as 5432, then give username and password. then the server will be created.
    then click on postgres->query tool.then query tool tab will be opened.
    then copy this code. then run.

            -- Create the employee table
        CREATE TABLE employee (
            id SERIAL PRIMARY KEY,
            name VARCHAR(100) NOT NULL,
            password VARCHAR(255) NOT NULL,
            age INT,
            role VARCHAR(50),
            salary DECIMAL(10, 2)
        );

        -- Insert sample data
        INSERT INTO employee (name, password, age, role, salary)
        VALUES
            ('John Doe', 'password123', 30, 'Manager', 75000.00),
            ('Jane Smith', 'securepass', 25, 'Developer', 60000.00),
            ('Michael Johnson', 'test456', 28, 'Designer', 55000.00),
            ('Emily Brown', '12345pass', 22, 'Intern', 30000.00),
            ('Robert Davis', 'secretword', 40, 'Director', 90000.00);

        -- Display the sample data
        SELECT * FROM employee;

    then a database with table employee with 5 employee records will be created.

    then employee table
    Open vscode
    file->openfolder->tomcat
    then goes to webapps-> root, inside we should create WEB-INF folder, inside we should create lib and classes folder respectively.

    first, we need to download postgresql jar file, then from tomcat->lib copy servlet-api.jar file
    then paste these two jar file in WEB-INF->lib folder.
    then copy the corresponding codes from below and paste in the order of html code should be inside root folder, 
    web.xml code should be inside WEB-INF folder, java codes should be inside classes folder.

    index.html

        <!DOCTYPE html>  
        <html>  
        <head>  
        <meta charset="ISO-8859-1">  
        <title>Employee Details</title>  
        </head>  
        <body>    
        <h1>Add New Employee</h1>  
        <form action="SaveServlet" method="post">  
        <table>  
        <tr><td>Name:</td><td><input type="text" name="name" required/></td></tr>  
        <tr><td>Password:</td><td><input type="password" name="password" required/></td></tr>  
        <tr><td>Age:</td><td><input type="number" name="age" required/></td></tr>  
        <tr><td>Role:</td><td><input type="text" name="role" required/></td></tr>  
        <tr><td>Salary:</td><td><input type="number" step="0.01" name="salary" required/></td></tr>  
        <tr><td colspan="2"><input type="submit" value="Save Employee"/></td></tr>  
        </table>  
        </form>         
        <br/>  
        <a href="ViewServlet">view Employees</a>         
        </body> 
        </html>  

    web.xml

        <?xml version="1.0" encoding="UTF-8"?>

        <web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
                            https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
        version="6.0"
        metadata-complete="true">

        <display-name>Employee Website</display-name>
        <description>
            Employee website
        </description>

            <servlet>
                <servlet-name>Employee</servlet-name>
                <servlet-class>Employee</servlet-class>
            </servlet>

            <servlet>
                <servlet-name>EmpDao</servlet-name>
                <servlet-class>EmpDoa</servlet-class>
            </servlet>

            <servlet>
                <servlet-name>SaveServlet</servlet-name>
                <servlet-class>SaveServlet</servlet-class>
            </servlet>

            <servlet>
                <servlet-name>ViewServlet</servlet-name>
                <servlet-class>ViewServlet</servlet-class>
            </servlet>

            <servlet>
                <servlet-name>DeleteServlet</servlet-name>
                <servlet-class>DeleteServlet</servlet-class>
            </servlet>

            <servlet>
                <servlet-name>EditServlet</servlet-name>
                <servlet-class>EditServlet</servlet-class>
            </servlet>

            <servlet>
                <servlet-name>EditServlet2</servlet-name>
                <servlet-class>EditServlet2</servlet-class>
            </servlet>


            <servlet-mapping>
                <servlet-name>Employee</servlet-name>
                <url-pattern>/Employee</url-pattern>
            </servlet-mapping>

            <servlet-mapping>
                <servlet-name>EmpDao</servlet-name>
                <url-pattern>/EmpDao</url-pattern>
            </servlet-mapping>

            <servlet-mapping>
                <servlet-name>SaveServlet</servlet-name>
                <url-pattern>/SaveServlet</url-pattern>
            </servlet-mapping>

            <servlet-mapping>
                <servlet-name>ViewServlet</servlet-name>
                <url-pattern>/ViewServlet</url-pattern>
            </servlet-mapping>

            <servlet-mapping>
                <servlet-name>DeleteServlet</servlet-name>
                <url-pattern>/DeleteServlet</url-pattern>
            </servlet-mapping>

            <servlet-mapping>
                <servlet-name>EditServlet</servlet-name>
                <url-pattern>/EditServlet</url-pattern>
            </servlet-mapping>

            <servlet-mapping>
                <servlet-name>EditServlet2</servlet-name>
                <url-pattern>/EditServlet2</url-pattern>
            </servlet-mapping>

            <welcome-file-list>
                <welcome-file>index.html</welcome-file>
            </welcome-file-list>

        </web-app>

    Employee.java

        import java.math.BigDecimal;

        public class Employee {
            private int id;
            private String name;
            private String password;
            private int age;
            private String role;
            private BigDecimal salary;

            public Employee(int id, String name, String password, int age, String role, BigDecimal salary) {
                this.id = id;
                this.name = name;
                this.password = password;
                this.age = age;
                this.role = role;
                this.salary = salary;
            }
            

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getPassword() {
                return password;
            }

            public int getAge() {
                return age;
            }

            public String getRole() {
                return role;
            }

            public BigDecimal getSalary() {
                return salary;
            }
        }

    run this code by cd "/home/harris/tomcat/webapps/ROOT/classes/" && javac Employee.java in terminal 

    
    EmpDao.java

        import java.io.IOException;
        import java.math.BigDecimal;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.List;


        public class EmpDao {  
        
            public static Connection getConnection(){  
                Connection con=null;  
                try{  
                    Class.forName("org.postgresql.Driver");  
                    con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1243");  
                }
                
                catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    
                    e.printStackTrace();
                } 
                return con;  
            }  
            public static int save(Employee e){  
                int status=0;  
                try{  
                    Connection con=EmpDao.getConnection();  
                    PreparedStatement ps=con.prepareStatement(  
                                "insert into employee(name,password,age,role,salary) values (?,?,?,?,?)");  
                    ps.setString(1,e.getName());
                    ps.setString(2,e.getPassword());  
                    ps.setInt(3,e.getAge());  
                    ps.setString(4,e.getRole());  
                    ps.setBigDecimal(5, e.getSalary());
                    
                    status=ps.executeUpdate();  
                    
                    con.close();  
                }catch(Exception ex){ex.printStackTrace();}  
                
                return status;  
            }  
            public static int update(Employee e){  
                int status=0;  
                try{  
                    Connection con=EmpDao.getConnection();  
                    PreparedStatement ps=con.prepareStatement(  
                                "update employee set name=?,password=?,age=?,role=?,salary=? where id=?");  
                    ps.setString(1,e.getName());  
                    ps.setString(2,e.getPassword());
                    ps.setInt(3,e.getAge());  
                    ps.setString(4,e.getRole());  
                    ps.setBigDecimal(5, e.getSalary());;  
                    ps.setInt(6,e.getId());  
                    
                    status=ps.executeUpdate();  
                    
                    con.close();  
                }catch(Exception ex){ex.printStackTrace();}  
                
                return status;  
            }  
            public static int delete(int id){  
                int status=0;  
                try{  
                    Connection con=EmpDao.getConnection();  
                    PreparedStatement ps=con.prepareStatement("delete from employee where id=?");  
                    ps.setInt(1,id);  
                    status=ps.executeUpdate();  
                    
                    con.close();  
                }catch(Exception e){e.printStackTrace();}  
                
                return status;  
            }  
            public static Employee getEmployeeById(int id){  
                Employee e=new Employee(id, null,null, id, null, null);
                
                try{  
                    Connection con=EmpDao.getConnection();  
                    PreparedStatement ps=con.prepareStatement("select * from employee where id=?");  
                    ps.setInt(1,id);  
                    ResultSet rs=ps.executeQuery();  
                    if(rs.next()){  
                        e=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getBigDecimal(6));       
                    }  
                    con.close();  
                }catch(Exception ex){ex.printStackTrace();}  
                
                return e;  
            }  
            public static List<Employee> getAllEmployees(){  
                List<Employee> list=new ArrayList<Employee>();  
                
                try{  
                    Connection con=EmpDao.getConnection();  
                    PreparedStatement ps=con.prepareStatement("select * from employee");  
                    ResultSet rs=ps.executeQuery();  
                    while(rs.next()){  
                        Employee e=new Employee(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4),rs.getString(5),rs.getBigDecimal(6)); 
                        list.add(e);  
                    }  
                    con.close();  
                }catch(Exception e){e.printStackTrace();}  
                
                return list;  
            }  
        }  
    
    run this code by cd "/home/harris/tomcat/webapps/ROOT/classes/" && javac EmpDao.java in terminal 

    SaveServlet.java

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

    run this code by cd "/home/harris/tomcat/webapps/ROOT/classes/" && javac -cp .:/home/harris/tomcat/lib/servlet-api.jar SaveServlet.java
    in terminal 

    ViewServlet.java

        import java.io.IOException;  
        import java.io.PrintWriter;  
        import java.util.List;  
        
        import jakarta.servlet.ServletException;
        import jakarta.servlet.annotation.WebServlet;
        import jakarta.servlet.http.HttpServlet;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse; 

        @WebServlet("/ViewServlet")  
        public class ViewServlet extends HttpServlet {  

            //javac -cp .:/home/harris/tomcat/lib/servlet-api.jar ViewServlet.java

            protected void doGet(HttpServletRequest request, HttpServletResponse response)   
                    throws ServletException, IOException {  
                response.setContentType("text/html");  
                PrintWriter out=response.getWriter();  
                out.println("<a href='index.html'>Add New Employee</a>");  
                out.println("<h1>Employees List</h1>");  
                
                List<Employee> list=EmpDao.getAllEmployees();  
                
                out.print("<table border='1' width='100%'");  
                out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Age</th><th>Role</th><th>Salary</th><th>Edit</th><th>Delete</th></tr>");  
                for(Employee e:list){  
                out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td><td>"+e.getPassword()+"</td><td>"+e.getAge()+"</td><td>"+e.getRole()+"</td><td>"+e.getSalary()+"</td><td><a href='EditServlet?id="+e.getId()+"'>edit</a></td>  <td><a href='DeleteServlet?id="+e.getId()+"'>delete</a></td></tr>");  
                }  
                out.print("</table>");  
                
                out.close();  
            }  
        }  

    run this code by cd "/home/harris/tomcat/webapps/ROOT/classes/" && javac -cp .:/home/harris/tomcat/lib/servlet-api.jar ViewServlet.java
    in terminal 

    EditServlet.java

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

    run this code by cd "/home/harris/tomcat/webapps/ROOT/classes/" && javac -cp .:/home/harris/tomcat/lib/servlet-api.jar EditServlet.java
    in terminal 

    EditServlet2.java

        import java.io.IOException;  
        import java.io.PrintWriter;  
        import java.math.BigDecimal;
        
        import jakarta.servlet.ServletException;
        import jakarta.servlet.annotation.WebServlet;
        import jakarta.servlet.http.HttpServlet;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;
        @WebServlet("/EditServlet2")  
        public class EditServlet2 extends HttpServlet {  

            //javac -cp .:/home/harris/tomcat/lib/servlet-api.jar EditServlet2.java

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
                
                
                int status=EmpDao.update(e);  
                if(status>0){  
                    
                    response.sendRedirect("ViewServlet");  
                }else{  
                    out.println("Sorry! unable to update record");  
                }  
                
                out.close();  
            }  
        
        }  

    run this code by cd "/home/harris/tomcat/webapps/ROOT/classes/" && javac -cp .:/home/harris/tomcat/lib/servlet-api.jar EditServlet2.java
    in terminal 

    DeleteServlet.java

        import java.io.IOException;  
        import jakarta.servlet.ServletException;
        import jakarta.servlet.annotation.WebServlet;
        import jakarta.servlet.http.HttpServlet;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse; 


        @WebServlet("/DeleteServlet")  
        public class DeleteServlet extends HttpServlet {  

            //javac -cp .:/home/harris/tomcat/lib/servlet-api.jar DeleteServlet.java

            protected void doGet(HttpServletRequest request, HttpServletResponse response)   
                    throws ServletException, IOException {  
                String sid=request.getParameter("id");  
                int id=Integer.parseInt(sid);  
                EmpDao.delete(id);  
                response.sendRedirect("ViewServlet");  
            }  
        }  

    run this code by cd "/home/harris/tomcat/webapps/ROOT/classes/" && javac -cp .:/home/harris/tomcat/lib/servlet-api.jar DeleteServlet.java
    in terminal 


Access the Application:

    Open terminal and type this command to turn on the tomcat.

    cd tomcat/bin
    ./startup.sh

    Open a web browser and navigate to http://localhost:8080

Usage

    Adding New Employee:
        Go to the home page
        Fill in the employee details and click "save employee"

    Viewing Employee List:
        Click the "view employees" button.
        All the employee buttons will be displayed
        .
    Editing Employee Details:
        Click the "edit" button next to an employee's name on the list page.
        Make changes and click "edit & save" button.
    Deleting an Employee:
        Click the "delete" button next to an employee's name on the list page.
        

Conclusion
    The Employee CRUD Operations Website simplifies employee record management. By following the installation instructions and understanding the code structure and usage examples provided in this documentation, you can effectively utilize and adapt the application to meet your specific requirements.


