package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Customer;
import model.Employee;

/**
 * Created by Bardur on 02/05/2016.
 */
public class AddOrder extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    //Creating comment field with TextArea
    TextArea commentArea = new TextArea();

    //Creating all the different textfields
    TextField firstNameText = new TextField();
    TextField lastNameText = new TextField();
    TextField emailText = new TextField();
    TextField phoneText = new TextField();
    TextField addressText = new TextField();
    TextField zipText = new TextField();
    TextField cityText = new TextField();
    TextField typeText = new TextField();

    //Creating Datepicker
    DatePicker startDatePicker = new DatePicker();
    DatePicker endDatePicker = new DatePicker();

    //Creating Combobox to add time
    ComboBox startTimeCombo = new ComboBox();
    ComboBox endTimeCombo = new ComboBox();

    //Creating textfield and button to calculate price
    TextField guestText = new TextField();
    TextField priceText = new TextField();

    //Creating Combobox, buttons and tableview for employeeTab
    ComboBox employeeCombo = new ComboBox();
    Button addEmployeeButton = new Button("Tilføj personale");
    Button removeEmployeeButton = new Button("Fjern personale");
    TableView employeeTableview = new TableView();

    @Override
    public void start(Stage addEventWindow) throws Exception {

        //Left side of the scene
        //Creating all the different labels
        Label nameLabel = new Label("Navn:");
        Label lastNameLabel = new Label("Efternavn:");
        Label emailLabel = new Label("Email:");
        Label phoneLabel = new Label("Tlf.:");
        Label addressLabel = new Label("Adresse:");
        Label zipLabel = new Label("Post nr:");
        Label cityLabel = new Label("By:");
        Label typeLabel = new Label("Arrangement type:");

        //Creating Gridpane to store labels and textfields
        GridPane grid = new GridPane();

        grid.add(nameLabel, 0, 0);
        grid.add(firstNameText, 1, 0);
        grid.add(lastNameLabel, 0, 1);
        grid.add(lastNameText, 1, 1);
        grid.add(emailLabel, 0, 2);
        grid.add(emailText, 1, 2);
        grid.add(phoneLabel, 0, 3);
        grid.add(phoneText, 1, 3);
        grid.add(addressLabel, 0, 4);
        grid.add(addressText, 1, 4);
        grid.add(zipLabel, 0, 5);
        grid.add(zipText, 1, 5);
        grid.add(cityLabel, 0, 6);
        grid.add(cityText, 1, 6);
        grid.add(typeLabel, 0, 7);
        grid.add(typeText, 1, 7);

        grid.setVgap(10);
        grid.setHgap(10);

        //Right side of the scene
        //Creating labels
        Label startDateLabel = new Label("Vælg startdato");
        Label endDateLabel = new Label("Vælg slutdato");
        Label startTimeLabel = new Label("Start tidspunkt");
        Label endTimeLabel = new Label("Slut tidspunkt");

        //Adding items to Time comboxes
        startTimeCombo.getItems().addAll(
                "00.00", "01.00", "02.00", "03.00", "04.00", "05.00", "06.00",
                "07.00", "08.00", "09.00", "10.00", "11.00", "12.00", "13.00",
                "14.00", "15.00", "16.00", "17.00", "18.00", "19.00", "20.00",
                "21.00", "22.00", "23.00"
        );
        startTimeCombo.setMaxWidth(Double.MAX_VALUE);

        endTimeCombo.getItems().addAll(
                "00.00", "01.00", "02.00", "03.00", "04.00", "05.00", "06.00",
                "07.00", "08.00", "09.00", "10.00", "11.00", "12.00", "13.00",
                "14.00", "15.00", "16.00", "17.00", "18.00", "19.00", "20.00",
                "21.00", "22.00", "23.00"
        );
        endTimeCombo.setMaxWidth(Double.MAX_VALUE);

        //Parring labels, Datepickers and Comboxes
        VBox startDateBox = new VBox();
        startDateBox.getChildren().addAll(startDateLabel, startDatePicker);
        startDateBox.setSpacing(2);

        VBox startTimeBox = new VBox();
        startTimeBox.getChildren().addAll(startTimeLabel, startTimeCombo);
        startTimeBox.setSpacing(2);

        VBox endDateBox = new VBox();
        endDateBox.getChildren().addAll(endDateLabel, endDatePicker);
        endDateBox.setSpacing(2);

        VBox endTimeBox = new VBox();
        endTimeBox.getChildren().addAll(endTimeLabel, endTimeCombo);
        endTimeBox.setSpacing(2);

        //Creating VBox and adding DatePicker and Combobox
        VBox dateTimeBox = new VBox();
        dateTimeBox.getChildren().addAll(startDateBox, startTimeBox, endDateBox, endTimeBox);
        dateTimeBox.setSpacing(20);

        //CommentArea layout
        commentArea.setMaxSize(500, 100);
        commentArea.setPromptText("Skriv en kommentar...");
        commentArea.setWrapText(true);

        //Creating hBox to store grid and dateTimeBox
        HBox hBox = new HBox();
        hBox.getChildren().addAll(grid, dateTimeBox);
        hBox.setSpacing(200);

        //Creating columns in TableView
        //Firstname
        TableColumn<Employee, String> firstNameCol = new TableColumn<>("Fornavn");
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setCellValueFactory(e-> e.getValue().firstNameProperty());

        //Lastname
        TableColumn<Employee, String> lastNameCol = new TableColumn<>("Efternavn");
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellValueFactory(e-> e.getValue().lastNameProperty());

        //Email address
        TableColumn<Employee, String> emailAddress = new TableColumn<>("Email Adresse");
        emailAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        emailAddress.setCellValueFactory(e -> e.getValue().emailAddressProperty());

        //Phone
        TableColumn<Employee, String> phoneCol = new TableColumn<>("Telefon nummer");
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setCellValueFactory(e-> e.getValue().phoneProperty());

        //Calculate price tab
        Label guestLabel = new Label("Antal Gæster:");
        Label amountSign = new Label("kr");
        Button calculatePriceButton = new Button("Beregn pris");

        GridPane priceTabBox = new GridPane();
        priceTabBox.add(guestLabel, 0, 0);
        priceTabBox.add(guestText, 1, 0);
        priceTabBox.add(calculatePriceButton, 0, 1);
        priceTabBox.add(priceText, 1, 1);
        priceTabBox.add(amountSign, 2, 1);

        guestText.setMaxWidth(100);
        priceText.setMaxWidth(100);

        priceTabBox.setHgap(8);
        priceTabBox.setVgap(50);
        priceTabBox.setPadding(new Insets(50, 10, 0, 50));

        //Employee tab
        GridPane employeeGrid = new GridPane();

        employeeCombo.setMinWidth(160);

        employeeGrid.add(employeeCombo, 0, 0);
        employeeGrid.add(addEmployeeButton, 1, 0);
        employeeGrid.add(removeEmployeeButton, 1, 1);

        employeeGrid.setPadding(new Insets(15, 0, 0, 15));
        employeeGrid.setVgap(8);
        employeeGrid.setHgap(8);

        employeeTableview.getColumns().addAll(firstNameCol, lastNameCol, emailAddress, phoneCol);

//        employeeTableview.setItems(Main.employeeObservableList);

        employeeTableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        StackPane employeeStack = new StackPane();
        employeeStack.getChildren().addAll(employeeTableview);

        VBox employeeBox = new VBox();
        employeeBox.getChildren().addAll(employeeGrid, employeeStack);
        employeeBox.setSpacing(20);
        employeeBox.setPadding(new Insets(0, 0, 8, 0));

        //Creating Tabpane and adding tabs
        TabPane bottomTabpane = new TabPane();
        bottomTabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        bottomTabpane.setSide(Side.TOP);
        bottomTabpane.setTabMinHeight(60);
        bottomTabpane.setTabMinWidth(185);

        Tab priceTab = new Tab();
        priceTab.setText("Pris");
        priceTab.setContent(priceTabBox);

        Tab employeeTab = new Tab();
        employeeTab.setText("Personale");
        employeeTab.setContent(employeeBox);

        bottomTabpane.getTabs().addAll(priceTab, employeeTab);

        //Creating VBox to store hBox
        VBox centerVBox = new VBox();
        centerVBox.getChildren().addAll(hBox, commentArea, bottomTabpane);
        centerVBox.setSpacing(15);
        centerVBox.setPadding(new Insets(8, 0, 0, 0));

        //Creating header
        StackPane header = new StackPane();
        Label headerLabel = new Label("Tilføj Arrangement");
        header.setPrefHeight(60);
        headerLabel.setFont(new Font("Calibri", 20));
        headerLabel.setStyle("-fx-text-fill: white");
        header.getChildren().add(headerLabel);
        header.setStyle("-fx-background-color: brown");

        //Creating "Tilføj" Button
        Button addEvent = new Button("Tilføj");
        addEvent.setDefaultButton(true);
        addEvent.setOnAction(e -> addInfoToEvent());

        //Creating cancel button
        Button cancelButton = new Button("Annuller");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(e -> addEventWindow.close());

        //Adding buttons to HBox
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(cancelButton, addEvent);
        buttonBox.setSpacing(8);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.setPadding(new Insets(5));
        buttonBox.setStyle("-fx-background-color: brown");

        //Creating Borderpane to be used as main layout
        BorderPane layout = new BorderPane();
        layout.setTop(header);
        layout.setCenter(centerVBox);
        layout.setBottom(buttonBox);
        layout.setPadding(new Insets(15, 15, 15, 15));


        //Creating scene
        Scene addOrderScene = new Scene(layout);

        //Adding scene to stage
        addEventWindow.setScene(addOrderScene);
        addEventWindow.setHeight(1000);
        addEventWindow.setWidth(860);
        addEventWindow.setTitle("Festvognen FætterGuf");
        addEventWindow.show();
    }

    //Method that adds new event to EventTable
    public void addInfoToEvent(){
        try{
            Customer newCustomer = new Customer(firstNameText.getText(), lastNameText.getText(),
                    emailText.getText(), phoneText.getText());
            //Events newEvent = new Events(addressText.getText(), zipText.getText(), cityText.getText(),
            //                            typeText.getText(), workTime, workDate);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("error when adding event");
        }
    }


}