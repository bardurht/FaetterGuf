package model;

import javafx.beans.property.*;
import java.util.Calendar;

public class Events {

    //Fields
    //We need SimpleProperty to update tableView's
    private SimpleStringProperty customer_first_name;
    private SimpleStringProperty customer_last_name;
    private SimpleStringProperty customer_email_address;
    private SimpleStringProperty customer_phone;
    private SimpleStringProperty eventAddress;
    private SimpleStringProperty eventZipCode;
    private SimpleStringProperty eventCity;
    private SimpleStringProperty eventType;
    private SimpleDoubleProperty price;
    private SimpleStringProperty startEventTime;
    private SimpleStringProperty endEventTime;
    private SimpleStringProperty eventDate;
    private SimpleObjectProperty<Customer> customers;
    private SimpleStringProperty product;
    private SimpleIntegerProperty eventCountdown;
    private SimpleStringProperty comments;
    private long dateOfCreation;

    //orders constructor
    public Events(String event_date, String start_event_time, String end_event_time, String event_type, String product, String customer_first_name, String customer_last_name) {
        this.eventDate = new SimpleStringProperty(event_date);
        this.startEventTime = new SimpleStringProperty(start_event_time);
        this.endEventTime = new SimpleStringProperty(end_event_time);
        this.eventType = new SimpleStringProperty(event_type);
        this.product = new SimpleStringProperty(product);
        this.customer_first_name = new SimpleStringProperty(customer_first_name);
        this.customer_last_name = new SimpleStringProperty(customer_last_name);
    }

    //Main constructor
    public Events(String eventAddress, String eventZipCode, String eventCity, String eventType, String startEventTime, String endEventTime, String eventDate,
                  double price, String product, Customer customers, String comments) {
        this.eventAddress = new SimpleStringProperty(eventAddress);
        this.eventZipCode = new SimpleStringProperty(eventZipCode);
        this.eventCity = new SimpleStringProperty(eventCity);
        this.eventType = new SimpleStringProperty(eventType);
        this.startEventTime = new SimpleStringProperty(startEventTime);
        this.endEventTime = new SimpleStringProperty(endEventTime);
        this.eventDate = new SimpleStringProperty(eventDate);
        this.price = new SimpleDoubleProperty(price);
        this.product = new SimpleStringProperty(product);
        this.customers = new SimpleObjectProperty<>(customers);
        this.comments = new SimpleStringProperty(comments);
        this.eventCountdown = new SimpleIntegerProperty(14);
        this.dateOfCreation = Calendar.getInstance().getTimeInMillis();
    }

    //Constructor for Events to tableview in FollowUp class
    public Events(String eventDate, String startEventTime, String end_event_time, String eventType, String product, long date_of_creation, String customer_first_name, String customer_last_name) {
        this.eventDate = new SimpleStringProperty(eventDate);
        this.startEventTime = new SimpleStringProperty(startEventTime);
        this.endEventTime = new SimpleStringProperty(end_event_time);
        this.eventType = new SimpleStringProperty(eventType);
        this.product = new SimpleStringProperty(product);
        this.eventCountdown = new SimpleIntegerProperty(14);
        this.customer_first_name = new SimpleStringProperty(customer_first_name);
        this.customer_last_name = new SimpleStringProperty(customer_last_name);
    }

    //Constructor for onlyArrayList in DatabaseConnection class
    public Events(String customer_first_name, String customer_last_name, String customer_email_address, String customer_phone, String eventAddress, String eventZipCode, String eventCity, String eventType, String startEventTime, String endEventTime, String eventDate,
                  double price, String product, String comments, long dateOfCreation) {
        this.customer_first_name = new SimpleStringProperty(customer_first_name);
        this.customer_last_name = new SimpleStringProperty(customer_last_name);
        this.customer_email_address = new SimpleStringProperty(customer_email_address);
        this.customer_phone = new SimpleStringProperty(customer_phone);
        this.eventAddress = new SimpleStringProperty(eventAddress);
        this.eventZipCode = new SimpleStringProperty(eventZipCode);
        this.eventCity = new SimpleStringProperty(eventCity);
        this.eventType = new SimpleStringProperty(eventType);
        this.startEventTime = new SimpleStringProperty(startEventTime);
        this.endEventTime = new SimpleStringProperty(endEventTime);
        this.eventDate = new SimpleStringProperty(eventDate);
        this.price = new SimpleDoubleProperty(price);
        this.product = new SimpleStringProperty(product);
        this.comments = new SimpleStringProperty(comments);
        this.dateOfCreation = dateOfCreation;
    }

    //Constructor for updateDatabase
    public Events (String customer_first_name, String customer_last_name, String customer_email_address, String customer_phone, String eventAddress, String eventZipCode, String eventCity, String eventType, String startEventTime, String endEventTime, String eventDate,
                   double price, String product, String comments){
        this.customer_first_name = new SimpleStringProperty(customer_first_name);
        this.customer_last_name = new SimpleStringProperty(customer_last_name);
        this.customer_email_address = new SimpleStringProperty(customer_email_address);
        this.customer_phone = new SimpleStringProperty(customer_phone);
        this.eventAddress = new SimpleStringProperty(eventAddress);
        this.eventZipCode = new SimpleStringProperty(eventZipCode);
        this.eventCity = new SimpleStringProperty(eventCity);
        this.eventType = new SimpleStringProperty(eventType);
        this.startEventTime = new SimpleStringProperty(startEventTime);
        this.endEventTime = new SimpleStringProperty(endEventTime);
        this.eventDate = new SimpleStringProperty(eventDate);
        this.price = new SimpleDoubleProperty(price);
        this.product = new SimpleStringProperty(product);
        this.comments = new SimpleStringProperty(comments);
    }

    public Events() {

    }

    //Getters og setters

    public String getCustomer_first_name() {
        return customer_first_nameProperty().get();
    }
    public StringProperty customer_first_nameProperty() {
        if( customer_first_name == null)  customer_first_name = new SimpleStringProperty(this,"customer_first_name");
        return customer_first_name;
    }
    public void setCustomer_first_name(String customer_first_name) {
        this.customer_first_nameProperty().set(customer_first_name);
    }

    //lastname
    public String getCustomer_last_name() {
        return customer_last_nameProperty().get();
    }
    public StringProperty customer_last_nameProperty() {
        if( customer_last_name == null)  customer_last_name = new SimpleStringProperty(this,"customer_last_name");
        return customer_last_name;
    }
    public void setCustomer_last_name(String customer_last_name) {
        this.customer_last_nameProperty().set(customer_last_name);
    }

    //address
    public String getEventAddress() {
        return eventAddressProperty().get();
    }
    public StringProperty eventAddressProperty() {
        if( eventAddress == null)  eventAddress = new SimpleStringProperty(this,"event_address");
        return eventAddress;
    }
    public void setEventAddress(String eventAddress) {
        this.eventAddressProperty().set(eventAddress);
    }

    //zipcode
    public String getEventZipCode() {
        return eventZipCodeProperty().get();
    }
    public StringProperty eventZipCodeProperty() {
        if( eventZipCode == null)  eventZipCode = new SimpleStringProperty(this,"event_zipcode");
        return eventZipCode;
    }
    public void setEventZipCode(String eventZipCode) {
        this.eventZipCodeProperty().set(eventZipCode);
    }

    //city
    public String getEventCity() {
        return eventCityProperty().get();
    }
    public StringProperty eventCityProperty() {
        if(eventCity == null) eventCity = new SimpleStringProperty(this, "event_city");
        return eventCity;
    }
    public void setEventCity(String eventCity) {
        this.eventCityProperty().set(eventCity);
    }

    //event type
    public String getEventType() {
        return eventTypeProperty().get();
    }
    public StringProperty eventTypeProperty() {
        if(eventType == null) eventType = new SimpleStringProperty(this, "event_type");
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventTypeProperty().set(eventType);
    }

    //price
    public double getPrice() {
        return priceProperty().get();
    }
    public DoubleProperty priceProperty() {
        if(price == null) price = new SimpleDoubleProperty(this, "price");
        return price;
    }
    public void setPrice(double price) {
        this.priceProperty().set(price);
    }

    //start
    public String getStartEventTime() {
        return startEventTimeProperty().get();
    }
    public StringProperty startEventTimeProperty() {
        if(startEventTime == null) startEventTime = new SimpleStringProperty(this, "start_event_time");
        return startEventTime;
    }
    public void setStartEventTime(String startEventTime) {
        this.startEventTimeProperty().set(startEventTime);
    }

    //end
    public String getEndEventTime() {
        return endEventTimeProperty().get();
    }
    public StringProperty endEventTimeProperty() {
        if(endEventTime == null) endEventTime = new SimpleStringProperty(this, "end_event_time");
        return endEventTime;
    }
    public void setEndEventTime(String endEventTime) {
        this.endEventTimeProperty().set(endEventTime);
    }

    //date
    public String getEventDate() {
        return eventDateProperty().get();
    }
    public StringProperty eventDateProperty() {
        if(eventDate == null) eventDate = new SimpleStringProperty(this, "event_date");
        return eventDate;
    }
    public void setEventDate(String eventDate) {
        this.eventDateProperty().set(eventDate);
    }

    //customer
    public Customer getCustomers() {
        return customersProperty().get();
    }
    public ObjectProperty<Customer> customersProperty() {
        return customers;
    }
    public void setCustomers(Customer customers) {
        this.customersProperty().set(customers);
    }

    //product
    public String getProduct() {
        return productProperty().get();
    }
    public StringProperty productProperty() {
        if(product == null) product = new SimpleStringProperty(this, "product");
        return product;
    }
    public void setProduct(String value) {
        this.productProperty().set(value);
    }

    //countdown
    public int getEventCountdown() {
        return eventCountdownProperty().get();
    }
    public IntegerProperty eventCountdownProperty() {
        if(eventCountdown == null) eventCountdown  = new SimpleIntegerProperty(this, "event_countdown");
        return eventCountdown;
    }
    public void setEventCountdown(int eventCountdown) {
        this.eventCountdownProperty().set(eventCountdown);
    }

    //comments
    public String getComments() {
        return commentsProperty().get();
    }
    public StringProperty commentsProperty() {
        if(comments == null) comments = new SimpleStringProperty(this, "comment");
        return comments;
    }
    public void setComments(String comments) {
        this.commentsProperty().set(comments);
    }

    //customer_email_address
    public String getCustomer_email_address() {
        return customer_email_address.get();
    }

    public SimpleStringProperty customer_email_addressProperty() {
        return customer_email_address;
    }

    public void setCustomer_email_address(String customer_email_address) {
        this.customer_email_address.set(customer_email_address);
    }

    //customer_phone
    public String getCustomer_phone() {
        return customer_phone.get();
    }

    public SimpleStringProperty customer_phoneProperty() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone.set(customer_phone);
    }

    //dateOfCreation

    public long getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(long dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}