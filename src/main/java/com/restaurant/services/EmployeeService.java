package src.main.java.com.restaurant.services;

import src.main.java.com.restaurant.model.Admin;
import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.repository.RestaurantDataStore;
import src.main.java.com.restaurant.util.ValidationUtils;

public class EmployeeService {
    private final RestaurantDataStore data;

    public EmployeeService(RestaurantDataStore data) {
        this.data = data;
    }

    public void addEmployee(Employee employee) {
        if (!isValidEmployee(employee)) { return; }

        data.getEmployees().add(employee);
    }

    public Employee searchEmployeeByID(int id) {
        if (!isValidEmployeeID(id)) { return null; }

        for (Employee employee : data.getEmployees()) {
            if (employee.getID() == id) {
                return employee;
            }
        }

        return null;
    }

    private boolean isEmployeeExists(int id) {
        return searchEmployeeByID(id) != null;
    }

    private boolean isValidEmployeeID(int id) {
        if (!ValidationUtils.isValidID(id) || id >= data.getEmployees().size()) {
            System.out.println("id out of range.");
            return false;
        }

        return true;
    }

    private boolean isValidEmployee(Employee employee) {
        if (employee == null) {
            System.out.println("null employee.");
            return false;
        }

        return true;
    }
}
