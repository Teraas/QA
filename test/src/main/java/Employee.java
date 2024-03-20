import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author harish.kumar-mbp
 * createdOn 17/02/24
 */
public class Employee {

    // Junit 4 test
    // Datapro
    //

    // SOLID
    // liskov -
    //

    public static void main(String[] args){
        Employee e1 = new Employee("a", "Math", 100, 2001);
        Employee e2 = new Employee("b", "Chem", 100, 2002);
        Employee e3 = new Employee("c", "Math", 100, 2003);

        List<Employee> lst = new ArrayList<>();
        lst.add(e1);lst.add(e2);lst.add(e3);
        //1. Find the total number of employees in each department.
        //2. Find the average salary for each department.
        Map map = lst.stream().collect(Collectors.groupingBy( Employee::getDepartment  ));
        System.out.println(map);

    }

    public Employee(String name, String department, double salary, int joiningYear) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.joiningYear = joiningYear;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getJoiningYear() {
        return joiningYear;
    }

    public void setJoiningYear(int joiningYear) {
        this.joiningYear = joiningYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
        private String department;
        private double salary;
        private int joiningYear;

    //public class Employee {
    //    private String name;
    //    private String department;
    //    private double salary;
    //    private int joiningYear;
    //
    //    // Constructors, getters, and setters
    //
    //    // Assume they are implemented
    //}
    //```
    //
    //Write a Java program to perform the following tasks using the Stream API:
    //
    //1. Find the total number of employees in each department.
    //2. Find the average salary for each department.
}
