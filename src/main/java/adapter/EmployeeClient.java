package adapter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeClient {



    public List<Employee> getEmployeeList(){
        List<Employee> employees = new ArrayList<>();

        Employee employeeFromDB = new EmployeeDB("1234", "John", "Ben", "john@ben.com");

        employees.add(employeeFromDB);

        EmployeeLdap employeeLdap = new EmployeeLdap("chewie", "Bryan", "Mike", "bryan@mike.com");

        employees.add(new EmployeeAdapterLdap(employeeLdap));
        return employees;
    }
}
