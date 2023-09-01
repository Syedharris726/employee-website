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