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
