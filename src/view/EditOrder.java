package view;

import controller.Calculations;
import controller.ObservableClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Employee;
import model.Events;

public class EditOrder extends AddOrder {

    public Stage addEventWindow() {

        Stage window = new Stage();

        //Setting width of textfields
        typeText.setMinWidth(199);

        //Left side of the scene
        //Creating all the different labels
        Label nameLabel = new Label("Fornavn:");
        Label lastNameLabel = new Label("Efternavn:");
        Label emailLabel = new Label("Email adresse:");
        Label phoneLabel = new Label("Telefonnummer:");
        Label addressLabel = new Label("Adresse:");
        Label zipLabel = new Label("Post nummer:");
        Label cityLabel = new Label("By:");
        Label typeLabel = new Label("Arrangement type:");
        Label productLabel = new Label("Produkt:");

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
        grid.add(productLabel, 0, 8);
        grid.add(productText, 1, 8);

        grid.setVgap(10);
        grid.setHgap(10);

        //Right side of the scene
        //Creating labels
        Label startDateLabel = new Label("Vælg dato:");
        Label startTimeLabel = new Label("Start tidspunkt:");
        Label endTimeLabel = new Label("Slut tidspunkt:");

        listForStartTimeCombo.addAll(
                "00.00", "01.00", "02.00", "03.00", "04.00", "05.00", "06.00",
                "07.00", "08.00", "09.00", "10.00", "11.00", "12.00", "13.00",
                "14.00", "15.00", "16.00", "17.00", "18.00", "19.00", "20.00",
                "21.00", "22.00", "23.00");

        //Adding items to Time comboxes
        startTimeCombo.getItems().addAll(listForStartTimeCombo);
        startTimeCombo.setMaxWidth(Double.MAX_VALUE);

        listForEndTimeCombo.addAll(
                "00.00", "01.00", "02.00", "03.00", "04.00", "05.00", "06.00",
                "07.00", "08.00", "09.00", "10.00", "11.00", "12.00", "13.00",
                "14.00", "15.00", "16.00", "17.00", "18.00", "19.00", "20.00",
                "21.00", "22.00", "23.00"
        );
        endTimeCombo.getItems().addAll(listForEndTimeCombo);
        endTimeCombo.setMaxWidth(Double.MAX_VALUE);

        //Parring labels, Datepickers and Comboxes
        VBox startDateBox = new VBox();
        startDateBox.getChildren().addAll(startDateLabel, startDatePicker);
        startDateBox.setSpacing(2);

        VBox startTimeBox = new VBox();
        startTimeBox.getChildren().addAll(startTimeLabel, startTimeCombo);
        startTimeBox.setSpacing(2);

        VBox endTimeBox = new VBox();
        endTimeBox.getChildren().addAll(endTimeLabel, endTimeCombo);
        endTimeBox.setSpacing(2);

        //Calculate price labels
        Label guestLabel = new Label("Antal Gæster:");
        Label amountSign = new Label("kr");
        Button calculatePriceButton = new Button("Beregn pris");
        calculatePriceButton.setOnAction(e -> priceText.setText(String.valueOf(Calculations.calculatePrice(guestText.getText()))));

        GridPane priceBox = new GridPane();
        priceBox.add(guestLabel, 0, 0);
        priceBox.add(guestText, 1, 0);
        priceBox.add(calculatePriceButton, 0, 1);
        priceBox.add(priceText, 1, 1);
        priceBox.add(amountSign, 2, 1);

        guestText.setMaxWidth(100);
        priceText.setMaxWidth(100);

        priceBox.setHgap(8);
        priceBox.setVgap(15);

        priceBox.setPadding(new Insets(2, 0, 0, 0));

        //Creating VBox and adding DatePicker, Combobox and calculate price
        VBox rightSide = new VBox();
        rightSide.getChildren().addAll(startDateBox, startTimeBox, endTimeBox, priceBox);
        rightSide.setSpacing(32);

        //CommentArea layout
        commentArea.setMinSize(333, 100);
        commentArea.setMaxSize(333, 100);
        commentArea.setPromptText("Skriv en kommentar...");
        commentArea.setWrapText(true);

        VBox leftSide = new VBox();
        leftSide.getChildren().addAll(grid, commentArea);
        leftSide.setSpacing(10);
        leftSide.setPadding(new Insets(8, 0, 0, 15));

        //Creating hBox to store grid and dateTimeBox
        HBox hBox = new HBox();
        hBox.getChildren().addAll(leftSide, rightSide);
        hBox.setSpacing(180);

        //Creating columns in TableView
        //Firstname
        final TableColumn<Employee, String> firstNameCol = new TableColumn<>("Fornavn:");
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setCellValueFactory(e -> e.getValue().firstNameProperty());
        counter++;

        //Lastname
        final TableColumn<Employee, String> lastNameCol = new TableColumn<>("Efternavn:");
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellValueFactory(e -> e.getValue().lastNameProperty());
        counter++;

        //Email address
        final TableColumn<Employee, String> emailAddress = new TableColumn<>("Email Adresse:");
        emailAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        emailAddress.setCellValueFactory(e -> e.getValue().emailAddressProperty());
        counter++;

        //Phone
        final TableColumn<Employee, String> phoneCol = new TableColumn<>("Telefonnummer:");
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setCellValueFactory(e -> e.getValue().phoneProperty());
        counter++;

        //Employees
        HBox employeeLabelBox = new HBox();
        HBox employeeButtonsBox = new HBox();

        Label topEmployeeLabel = new Label("Tilføj eller fjern personale fra arrangement:");

        addEmployeeButton.setOnAction(e -> {
            addEmployee.createEmployee();
        });

        employeeLabelBox.getChildren().addAll(topEmployeeLabel);
        employeeButtonsBox.getChildren().addAll(employeeCombo, addEmployeeEventButton, removeEmployeeButton);
        employeeButtonsBox.setSpacing(8);
        removeEmployeeButton.setMinWidth(160);
        employeeCombo.setMinWidth(200);
        addEmployeeEventButton.setMinWidth(160);

        VBox topEmployeeBox = new VBox();

        topEmployeeBox.getChildren().addAll(employeeLabelBox, employeeButtonsBox);
        topEmployeeBox.setSpacing(2);
        topEmployeeBox.setPadding(new Insets(15, 0, 0, 15));

        GridPane bottomEmployeeGrid = new GridPane();

        bottomEmployeeGrid.add(addEmployeeButton, 0, 0);
        bottomEmployeeGrid.add(fireEmployeeButton, 1, 0);
        addEmployeeButton.setMinWidth(162);
        fireEmployeeButton.setMinWidth(162);

        bottomEmployeeGrid.setPadding(new Insets(0, 0, 8, 15));
        bottomEmployeeGrid.setHgap(8);

        if (counter <= 4) {
            employeeTableview.getColumns().addAll(firstNameCol, lastNameCol, emailAddress, phoneCol);
        }

        employeeCombo.setItems(ObservableClass.getEmployeeFirstName());

        employeeTableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        StackPane employeeStack = new StackPane();
        employeeStack.getChildren().addAll(employeeTableview);
        employeeStack.setPadding(new Insets(0, 15, 0, 15));

        VBox employeeBox = new VBox();
        employeeBox.getChildren().addAll(topEmployeeBox, employeeStack, bottomEmployeeGrid);
        employeeBox.setSpacing(20);
        employeeBox.setPadding(new Insets(0, 0, 8, 0));

        //Creating VBox to store hBox
        VBox centerVBox = new VBox();
        centerVBox.getChildren().addAll(hBox, employeeBox);
        centerVBox.setSpacing(15);
        centerVBox.setPadding(new Insets(8, 0, 0, 0));

        //Creating header
        StackPane header = new StackPane();
        Label headerLabel = new Label("Arrangement");
        header.setPrefHeight(60);
        headerLabel.setFont(new Font("Calibri", 20));
        headerLabel.setStyle("-fx-text-fill: white");
        header.getChildren().add(headerLabel);
        header.setStyle("-fx-background-color: #cb0118");

        //Creating "Tilføj" Button
        Button editEvent = new Button("Ændre");
        editEvent.setDefaultButton(true);
        editEvent.setMinWidth(130);
        editEvent.setOnAction(e -> {
            editSelectedEvent();
            clearTextFields();
            ObservableClass.employeeObservableListForTableview.clear();
            window.close();
        });

        //Creating cancel button
        Button cancelButton = new Button("Annuller");
        cancelButton.setCancelButton(true);
        cancelButton.setMinWidth(130);
        cancelButton.setOnAction(e -> {
            clearTextFields();
            window.close();
        });

        //Adding buttons to HBox
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll();
        buttonBox.setSpacing(8);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.setPadding(new Insets(5));
        buttonBox.setStyle("-fx-background-color: #cb0118");

        //Creating Borderpane to be used as main layout
        BorderPane layout = new BorderPane();
        layout.setTop(header);
        layout.setCenter(centerVBox);
        layout.setBottom(buttonBox);

        //Creating scene
        Scene addOrderScene = new Scene(layout);

        //Adding scene to stage
        window.setScene(addOrderScene);
        window.setHeight(1000);
        window.setWidth(860);
        window.setTitle("Festvognen FætterGuf");
        window.getIcons().add(new Image("Fætter_guf_logo.gif"));
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();

        return window;

    }

    //Updates data - Doesn't work
    public void editSelectedEvent(){

        Events ev = new Events(
                firstNameText.getText(),
                lastNameText.getText(),
                emailText.getText(),
                phoneText.getText(),
                addressText.getText(),
                zipText.getText(),
                cityText.getText(),
                typeText.getText(),
                String.valueOf(startTimeCombo.getValue()),
                String.valueOf(endTimeCombo.getValue()),
                String.valueOf(startDatePicker.getValue()),
                Double.parseDouble(priceText.getText()),
                productText.getText(),
                commentArea.getText()
        );

        //db.updateEventDatabase(ev);

    }

}
