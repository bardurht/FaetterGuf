package controller;

import model.Customer;
import model.Employee;
import model.Events;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    public Statement stmt;
    public PreparedStatement preS;
    public ResultSet rs;
    public String sqlString;

    public static final String URL = "jdbc:mysql://localhost:3306/fætterguf?autoReceonnect=true&useSSL=false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";


    private Connection connection = null;


    //removes event from database
    public void removeEventFromDatabase(Object events){

        //Cast object to an Event object
        Events selectedEvent = (Events) events;

        //Removes from event from event tabel
        //MySQL statement
        String sql = "DELETE FROM event WHERE event_type = ? AND start_event_time = ? AND event_date = ? limit 1;";

        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preS = connection.prepareStatement(sql);
            preS.setString(1,selectedEvent.getEventType());
            preS.setString(2,selectedEvent.getStartEventTime());
            preS.setString(3,selectedEvent.getEventDate());
            preS.execute();

            preS.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Removes customer from database
    public void removeCustomerFromDatabase(Object customer){
        Customer selectedCustomer = (Customer) customer;

        String sql = "DELETE FROM customer WHERE customer_email_address = ?";

        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            preS = connection.prepareStatement(sql);
            preS.setString(1,selectedCustomer.getEmailAddress());
            preS.execute();

            preS.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Remove employee from database
    public void removeEmployeeFromDatabase(Employee employee){

        String sql = "DELETE FROM employee WHERE employee_email_address = ?";

        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            preS = connection.prepareStatement(sql);
            preS.setString(1,employee.getEmailAddress());
            preS.execute();

            preS.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Remove orders from database
    public void removeOrdersFromDatabase(Object orders){

        //Cast object to an Event object
        Events selectedOrders = (Events) orders;

        //Removes from event tabel
        String sql = "DELETE FROM orders WHERE event_type = ? AND start_event_time = ? AND event_date = ? limit 1;";

        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preS = connection.prepareStatement(sql);
            preS.setString(1,selectedOrders.getEventType());
            preS.setString(2,selectedOrders.getStartEventTime());
            preS.setString(3,selectedOrders.getEventDate());
            preS.execute();

            preS.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //updating databse - editOrder
    public ArrayList<Events> onlyArraylist = new ArrayList<Events>();

    public void getEventFromDatabase(){
        sqlString = "SELECT customer.customer_first_name, customer.customer_last_name, customer.customer_email_address, customer.customer_phone, event.event_address, event.event_zipcode, zipcode.event_city, event.event_type, event.comment, event.price, event.start_event_time, event.end_event_time, event.event_date, event.product, event.date_of_creation FROM event, customer, zipcode WHERE event.customer_id = customer.customer_id AND event.event_zipcode = zipcode.event_zipcode;";

        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            rs = connection.createStatement().executeQuery(sqlString);

            while (rs.next()) {

                onlyArraylist.add(new Events(
                        rs.getString("customer_first_name"),
                        rs.getString("customer_last_name"),
                        rs.getString("customer_email_address"),
                        rs.getString("customer_phone"),
                        rs.getString("event_address"),
                        rs.getString("event_zipcode"),
                        rs.getString("event_city"),
                        rs.getString("event_type"),
                        rs.getString("start_event_time"),
                        rs.getString("end_event_time"),
                        rs.getString("event_date"),
                        rs.getDouble("price"),
                        rs.getString("product"),
                        rs.getString("comment"),
                        rs.getLong("date_of_creation")));
            }
            connection.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    //Method to update in database - doesn't work
    public void updateEventDatabase(Events events){

        sqlString = "UPDATE customer, event, zipcode SET customer.customer_first_name = (?), customer.customer_last_name = (?), customer.customer_phone = (?), event.event_address = (?), event.event_zipcode = (?), zipcode.event_zipcode = (?), zipcode.event_city = (?), event.event_type = (?), event.comment = (?), event.price = (?), event.start_event_time = (?), event.end_event_time = (?), event.event_date = (?), event.product = (?) WHERE customer.customer_id = event.customer_id AND event.event_zipcode = zipcode.event_zipcode AND customer.customer_email_address = (?)";

        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            preS = connection.prepareStatement(sqlString);

            preS.setString(1, events.getCustomer_first_name());
            preS.setString(2, events.getCustomer_last_name());
            preS.setString(3, events.getCustomer_phone());
            preS.setString(4, events.getEventAddress());
            preS.setString(5, events.getEventZipCode());
            preS.setString(6, events.getEventZipCode());
            preS.setString(7, events.getEventCity());
            preS.setString(8, events.getEventType());
            preS.setString(9, events.getComments());
            preS.setDouble(10, events.getPrice());
            preS.setString(11, events.getStartEventTime());
            preS.setString(12, events.getEndEventTime());
            preS.setString(13, events.getEventDate());
            preS.setString(14, events.getProduct());
            preS.setString(15, events.getCustomer_email_address());
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/

    //write to database zipcode tabel
    public void createZipcode (Events events){
        sqlString = "insert into Zipcode VALUES (? ,?)";

        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            preS = connection.prepareStatement(sqlString);

            preS.setString(1, events.getEventZipCode());
            preS.setString(2, events.getEventCity());
            preS.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //write to database Employye tabel
    public void createEmployee(Employee employee) {
        sqlString = "INSERT INTO Employee VALUES (DEFAULT, ?, ?, ?, ?)";

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            preS = connection.prepareStatement(sqlString);

            preS.setString(1, employee.getFirstName());
            preS.setString(2, employee.getLastName());
            preS.setString(3, employee.getEmailAddress());
            preS.setString(4, employee.getPhone());
            preS.executeUpdate();

            connection.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ///write to database customer tabel
    public void createCustomer(Object customer) {
        Customer selectedcustomer = (Customer) customer;

        sqlString = "INSERT INTO Customer VALUES (DEFAULT, ?, ?, ?, ?)";
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            preS = connection.prepareStatement(sqlString);

            preS.setString(1, selectedcustomer.getFirstName());
            preS.setString(2, selectedcustomer.getLastName());
            preS.setString(3, selectedcustomer.getEmailAddress());
            preS.setString(4, selectedcustomer.getPhone());
            preS.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int counter2 = 1;
    //write to database event tabel
    public void createEvent(Events event){
        counter2 = ObservableClass.eventsObservableList.size();

        sqlString = "INSERT INTO Event ( customer_id, employee_id, event_address, event_zipcode, event_type ,comment, price, start_event_time, end_event_time, event_date, product, event_countdown, date_of_creation) VALUES ("+counter2 +", "+ counter2 + ", "+ "?, ? ,? , ?, ?, ?, ?, ?, ?,?,?)";
        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            preS = connection.prepareStatement(sqlString);

            preS.setString(1, event.getEventAddress());
            preS.setString(2, event.getEventZipCode());
            preS.setString(3, event.getEventType());
            preS.setString(4, event.getComments());
            preS.setDouble(5, event.getPrice());
            preS.setString(6, event.getStartEventTime());
            preS.setString(7, event.getEndEventTime());
            preS.setString(8, event.getEventDate());
            preS.setString(9, event.getProduct());
            preS.setInt(10, event.getEventCountdown());
            preS.setLong(11, event.getDateOfCreation());
            preS.executeUpdate();
            connection.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        counter2++;
    }

    //getting employee data from database
    public ArrayList<Employee> getEmployee() {
        ArrayList<Employee> getEmployee = new ArrayList<>();

        sqlString = "select * from employee";
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            rs = connection.createStatement().executeQuery(sqlString);
            while (rs.next()) {
                getEmployee.add(new Employee(rs.getString("employee_first_name"),
                        rs.getString("employee_last_name"),
                        rs.getString("employee_email_address"),
                        rs.getString("employee_phone")));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getEmployee;
    }

    //getting event data from database
    public void buildDataEvent(){

        sqlString = "SELECT event_date, start_event_time, end_event_time, event_type, product, date_of_creation, customer_first_name, customer_last_name FROM event, customer where event.customer_id = customer.customer_id;";
        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            rs = connection.createStatement().executeQuery(sqlString);

            ObservableClass.eventsObservableList.clear();

            while (rs.next()) {

                ObservableClass.eventsObservableList.add(new Events(
                        rs.getString("event_date"),
                        rs.getString("start_event_time"),
                        rs.getString("end_event_time"),
                        rs.getString("event_type"),
                        rs.getString("product"),
                        rs.getLong("date_of_creation"),
                        rs.getString("customer_first_name"),
                        rs.getString("customer_last_name")));

            }
            rs.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //gets customer data from Database
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> getCustomer = new ArrayList<>();

        sqlString = "SELECT * FROM Customer";

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            rs = connection.createStatement().executeQuery(sqlString);

            while (rs.next()) {
                getCustomer.add(new Customer(rs.getString("customer_first_name"),
                        rs.getString("customer_last_name"),
                        rs.getString("customer_email_address"),
                        rs.getString("customer_phone")));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getCustomer;
    }
    public void getOrdersFromDatabase() {
        // String sql = "insert into orders (customer_id, employee_id, event_address, event_zipcode, event_type ,comment, price, start_event_time, end_event_time, event_date, product)\n" +
        //            "select customer_id, employee_id, event_address, event_zipcode, event_type ,comment, price, start_event_time, end_event_time, event_date, product\n" +
        //          "from event\n" +
        //        "where customer_id = customer_id limit 1;";
        sqlString = "SELECT event_date, start_event_time, end_event_time, event_type, product, customer_first_name, customer_last_name FROM orders, customer where orders.customer_id = customer.customer_id;";

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // String sql = "SELECt event_type, start_event_time, end_event_time, product from orders, customer where orders.customer_id = customer_id";
            rs = connection.createStatement().executeQuery(sqlString);

            ObservableClass.ordersObservableList.clear();

            while (rs.next()) {

                ObservableClass.ordersObservableList.add(new Events(
                        rs.getString("event_date"),
                        rs.getString("start_event_time"),
                        rs.getString("end_event_time"),
                        rs.getString("event_type"),
                        rs.getString("product"),
                        // rs.getLong("date_of_creation"),
                        rs.getString("customer_first_name"),
                        rs.getString("customer_last_name")));
            }
            rs.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /*---------------------------------------------------------------------------------------------------
    SINGLETON
    -----------------------------------------------------------------------------------------------------*/
    public Database() {
        System.out.println("Connection created! !");

        try {
            //register JDBC driver for MySQL
            Class.forName("com.mysql.jdbc.Driver");

            //define URl of database
            String url = "jdbc:mysql://localhost:3306/fætterguf?autoReceonnect=true&useSSL=false";

            //Get connection to get database
            Connection con = DriverManager.getConnection(url, "root", "");

            //Display the URL and connection information
            System.out.println("URL: " + url);
            System.out.println("Connection: " + con);

            //Get a Statement object
            stmt = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }// end SingleTonDb

    //not working - Exception in thread "JavaFX Application Thread" java.lang.NullPointerException and cant find what the problem is but the same code is working fine i orders
    public void insertToEvent(double price, String event_address, String event_zipcode, String event_type, String comment, String start_event_time, String end_event_time, String event_date, String product){

        Connection con = null;

        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fætterguf?autoReceonnect=true&useSSL=false", "root", "");
            stmt = con.createStatement();
            sqlString = "SELECT (" +
                    "SELECT MAX(customer_id)" +
                    "from customer) as MAXId," +
                    "(SELECT max(employee_id)" +
                    "from employee) as MAXID2";
            rs = stmt.executeQuery(sqlString);
            rs.next();
            int customer_count =rs.getInt("MAXId");
            int customer_id = customer_count;
            int employee_count = rs.getInt("MAXID2");
            int employee_id = employee_count;

            sqlString = "insert into event(price, customer_id, employee_id, event_address,event_zipcode,event_type,comment,start_event_time,end_event_time,event_date,product ) values("+ price +",'" +customer_id+"','"+employee_id+"','"+event_address +"','"+ event_zipcode +"','"+ event_type +"','"+ comment +"','"+ start_event_time +"','"+ end_event_time+"','"+event_date+"','"+ product +"')";
            stmt.executeUpdate(sqlString);

            System.out.println(customer_count + " customer count ");
            System.out.println(employee_count + " employee count ");
            System.out.println(customer_id + " customeree count ");
            System.out.println(employee_id + " employee count ");

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //getting events from database
    public ArrayList<Events> getEventsSingleton(){
        ArrayList<Events> eventList = new ArrayList<>();
        try {
            sqlString = "SELECT event_date, start_event_time, end_event_time, product, date_of_creation, customer_first_name, customer_last_name,event_type FROM event, customer where event.customer_id = customer.customer_id";

            rs = stmt.executeQuery(sqlString);

            while(rs.next()){
                String event_date = rs.getString("event_date");
                String start_event_time = rs.getString("start_event_time");
                String end_event_time = rs.getString("end_event_time");
                long  date_of_creation = rs.getLong("date_of_creation");
                String customer_first_name = rs.getString("customer_first_name");
                String customer_last_name = rs.getString("customer_last_name");
                String product = rs.getString("product");
                String event_type = rs.getString("event_type");

                Events e2 = new Events();
                e2.setEventDate(event_date);
                e2.setStartEventTime(start_event_time);
                e2.setEndEventTime(end_event_time);
                e2.setDateOfCreation(date_of_creation);
                e2.setCustomer_first_name(customer_first_name);
                e2.setCustomer_last_name(customer_last_name);
                e2.setProduct(product);
                e2.setEventType(event_type);

                eventList.add(e2);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return eventList;
    }

    // orders from database
    public ArrayList<Events> getEventOrdersSingelTon(){

        ArrayList<Events> orderList = new ArrayList<>();
        try {
            sqlString = "SELECT event_date, start_event_time, end_event_time, product, customer_first_name, customer_last_name,event_type FROM orders, customer where orders.customer_id = customer.customer_id";

            rs = stmt.executeQuery(sqlString);

            while(rs.next()){
                String event_date = rs.getString("event_date");
                String start_event_time = rs.getString("start_event_time");
                String end_event_time = rs.getString("end_event_time");
                String customer_first_name = rs.getString("customer_first_name");
                String customer_last_name = rs.getString("customer_last_name");
                String product = rs.getString("product");
                String event_type = rs.getString("event_type");

                Events order = new Events();

                order.setEventDate(event_date);
                order.setStartEventTime(start_event_time);
                order.setEndEventTime(end_event_time);
                order.setCustomer_first_name(customer_first_name);
                order.setCustomer_last_name(customer_last_name);
                order.setProduct(product);
                order.setEventType(event_type);

                orderList.add(order);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return orderList;
    }

    //write data to orders tabel in database
    public void insertToOrders(double price,String event_address, String event_zipcode, String event_type, String comment, String start_event_time, String end_event_time, String event_date, String product){

        try{
            sqlString = "insert into orders(price, customer_id, employee_id, event_address,event_zipcode,event_type,comment,start_event_time,end_event_time,event_date,product ) values("+ price +",'" +counter2+"','"+counter2+"','"+event_address +"','"+ event_zipcode +"','"+ event_type +"','"+ comment +"','"+ start_event_time +"','"+ end_event_time+"','"+event_date+"','"+ product +"')";
            stmt.executeUpdate(sqlString);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //Trying alternative way to write data to database and return it - Doesn't work
  /*
    public void createOrders(ObservableList<Events> f){

        sqlString = "INSERT INTO orders (orders_id, customer_id, employee_id, event_address, event_zipcode, event_type ,comment, start_event_time, end_event_time, event_date, product) VALUES (DEFAULT, last_insert_id(), LAST_insert_id(),?, ? ,?, ?,?, ?, ?, ?)";

        try{

            String comma="";
            StringBuilder allGenres = new StringBuilder();
            for (Events g: ObservableClass.ordersObservableList) {
                allGenres.append(comma);
                allGenres.append(g);
                comma = ", ";
            }
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            preS = connection.prepareStatement(sqlString);
            preS.setString(1, String.valueOf(f.get(0).eventAddressProperty()));
            preS.setString(2, String.valueOf(f.get(0).eventZipCodeProperty()));
            preS.setString(3, String.valueOf(f.get(0).eventTypeProperty()));
            preS.setString(4, String.valueOf(f.get(0).commentsProperty()));
            //ps.setDouble(5, Double.parseDouble(String.valueOf(f.get(0).priceProperty())));
            preS.setString(5, String.valueOf(f.get(0).startEventTimeProperty()));
            preS.setString(6, String.valueOf(f.get(0).endEventTimeProperty()));
            preS.setString(7, String.valueOf(f.get(0).eventDateProperty()));
            preS.setString(8, String.valueOf(f.get(0).productProperty()));

            preS.executeUpdate();

        }

        catch (SQLException e){
            e.printStackTrace();
        }

    }*/

}