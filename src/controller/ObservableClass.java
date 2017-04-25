package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import controller.Database;

public class ObservableClass {

    //Creating observablelists
    static Database db = new Database();

    public static ObservableList<Customer> customerObservableList = FXCollections.observableArrayList(db.getCustomers());

    public static ObservableList<Events> eventsObservableList = FXCollections.observableArrayList();

    public static ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList(db.getEmployee());

    public static ObservableList<Events> ordersObservableList = FXCollections.observableArrayList(db.getEventOrdersSingelTon());

    public static ObservableList<Employee> employeeObservableListForTableview = FXCollections.observableArrayList();

    //Getters and Setters

    //Employee
    public static void addEmployee(Employee employee) {
        employeeObservableList.add(employee);
    }

    public static ObservableList<Employee> getEmployeeObservableList() {
        return employeeObservableList;
    }

    public static void setEmployeeObservableList(ObservableList<Employee> employeeObservableList) {
        ObservableClass.employeeObservableList = employeeObservableList;
    }

    //List of employee first names for AddOrder combobox
    public static ObservableList<String> getEmployeeFirstName() {
        ObservableList<String> employeeFirstName = FXCollections.observableArrayList();

        for (Employee employee : employeeObservableList) {
            employeeFirstName.add(employee.getFirstName());
        }
        return employeeFirstName;
    }

    public static void setEmployeeObservableListForTableview(ObservableList<Employee> employeeObservableListForTableview) {
        ObservableClass.employeeObservableListForTableview = employeeObservableListForTableview;
    }

    //Lists for employeeObservableListTableView
    public static ObservableList<Employee> getEmployeeObservableListForTableview() {
        return employeeObservableListForTableview;
    }

    //Customer
    public static ObservableList<Customer> getCustomerObservableList() {
        return customerObservableList;
    }

    public static void setCustomerObservableList(ObservableList<Customer> customerObservableList) {
        ObservableClass.customerObservableList = customerObservableList;
    }

    //Events
    public static ObservableList<Events> getEventsObservableList() {
        return eventsObservableList;
    }

    public static void setEventsObservableList(ObservableList<Events> eventsObservableList) {
        ObservableClass.eventsObservableList = eventsObservableList;
    }

    //Orders
    public static ObservableList<Events> getOrdersObservableList() {
        return ordersObservableList;
    }

    public static void setOrdersObservableList(ObservableList<Events> ordersObservableList) {
        ObservableClass.ordersObservableList = ordersObservableList;
    }

    //Add and remove methods
    public static void addToEmployeeTableView(Employee employee){
        ObservableClass.employeeObservableListForTableview.add(employee);
    }

    public static void removeFromEmployeeTableView(Employee employee){
        ObservableClass.employeeObservableListForTableview.remove(employee);
    }

}
