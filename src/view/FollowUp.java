package view;

import controller.Calculations;
import controller.Database;
import controller.ObservableClass;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import model.Events;
import java.util.Optional;

public class FollowUp {

    //Creating instances of classes
    Database db = new Database();
    AddOrder addOrder = new AddOrder();
    EditOrder editOrder = new EditOrder();
    Calculations calculations = new Calculations();
    Orders o = new Orders();

    private IntegerProperty indexSale = new SimpleIntegerProperty();

    static TableView followUpTableView = new TableView();

    //Getters and Setters
    //indexSale
    public final double getIndexSale() {
        return indexSale.get();
    }

    public final void setIndexSale(Integer value) {
        indexSale.set(value);
    }

    public IntegerProperty indexSaleProperty() {
        return indexSale;
    }

    //followUpTableView
    public TableView getFollowUpTableView() {
        return followUpTableView;
    }

    public void setFollowUpTableView(TableView followUpTableView) {
        this.followUpTableView = followUpTableView;
    }

    //FollowUp tab
    public BorderPane getFollowUpClass() {

        //listener on Employee comboBox
        ObservableList employeeList = ObservableClass.getEmployeeObservableList();
        employeeList.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                addOrder.employeeCombo.setItems(ObservableClass.getEmployeeFirstName());
            }
        });

        //Stuff
        BorderPane borderPaneSale = new BorderPane();
        HBox hBoxSale = new HBox();
        Button addSale = new Button("Tilføj Arrangement");
        addSale.setMinWidth(150);
        addSale.setOnAction(e -> addOrder.addEventWindow());
        Button removeSale = new Button("Fjern Arrangement");
        removeSale.setMinWidth(150);
        Button confirmSale = new Button("Bekræft Ordre");
        confirmSale.setMinWidth(150);
        HBox hBoxHeader = new HBox();
        HBox labelHbox = new HBox();
        final Label headerSale = new Label("Opfølgning");

        //Logo
        Image logo = new Image("Fætter_guf_logo.gif");
        ImageView imageView = new ImageView();
        imageView.setImage(logo);

        //Dato
        TableColumn<Events, String> dateCol = new TableColumn<>("Dato:");
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dateCol.setCellValueFactory(e -> e.getValue().eventDateProperty());

        //Time
        TableColumn<Events, String> startTimeCol = new TableColumn<>("Start:");
        startTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        startTimeCol.setCellValueFactory(e -> e.getValue().startEventTimeProperty());
        startTimeCol.setMaxWidth(2000);

        TableColumn<Events, String> endTimeCol = new TableColumn<>("Slut:");
        endTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        endTimeCol.setCellValueFactory(e-> e.getValue().endEventTimeProperty());
        endTimeCol.setMaxWidth(2000);

        TableColumn<Events, String> timeCol = new TableColumn<>("Tidspunkt:");
        timeCol.getColumns().addAll(startTimeCol, endTimeCol);

        //First name
        TableColumn<Events, String> firstnameCol = new TableColumn<>("Fornavn:");
        firstnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstnameCol.setCellValueFactory(e -> e.getValue().customer_first_nameProperty());

        //Last name
        TableColumn<Events, String> lastnamecol = new TableColumn<>("Efternavn:");
        lastnamecol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastnamecol.setCellValueFactory(e -> e.getValue().customer_last_nameProperty());

        TableColumn<Events, String> namecol = new TableColumn<>("Navn på kunden:");
        namecol.getColumns().addAll(firstnameCol, lastnamecol);

        //Product
        TableColumn<Events, String> productCol = new TableColumn<>("Aktiviter:");
        productCol.setCellFactory(TextFieldTableCell.forTableColumn());
        productCol.setCellValueFactory(e -> e.getValue().productProperty());

        //Event type
        TableColumn<Events, String> eventTypeCol = new TableColumn<>("Arrangement:");
        eventTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        eventTypeCol.setCellValueFactory(e -> e.getValue().eventTypeProperty());

        //Follow up
        TableColumn<Events, Integer> eventCountdownCol = new TableColumn<>("Opfølgning:");
        eventCountdownCol.setCellValueFactory(new PropertyValueFactory<Events, Integer>("eventCountdown"));

        //layout for tableview
        followUpTableView.getColumns().addAll(dateCol, timeCol, namecol, productCol, eventTypeCol, eventCountdownCol);
        followUpTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //delete selected row in tableview
        indexSaleProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue o, Object oldVal,
                                Object newVal) {
            }
        });

        followUpTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
                setIndexSale(ObservableClass.getEventsObservableList().indexOf(newValue));
            }
        });

        //Button remove
        removeSale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //check if eventArraylist is empty or not
                if (!(ObservableClass.eventsObservableList.isEmpty())) {

                    //makes a Alert boks with texts
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Er du sikker på at du vil slette?");
                    alert.setHeaderText(null);
                    alert.setTitle("Besked");

                    //makes a choice if Ok is pressed and if cancel is pressed
                    Optional<ButtonType> result = alert.showAndWait();

                    //removes selected Event from EventList
                    if (result.get() == ButtonType.OK) {
                        Database update = new Database();
                        update.removeEventFromDatabase(followUpTableView.getSelectionModel().getSelectedItem());
                        ObservableClass.getEventsObservableList().remove(indexSale.get());
                        followUpTableView.getSelectionModel().clearSelection();

                    } else {
                        removeSale.cancelButtonProperty();
                    }
                } else {
                    //do nothing, else out of bounds Arraylist -1
                }
            }

        });

        //confirm button event
        confirmSale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!(ObservableClass.eventsObservableList.isEmpty())) {

                    // collecting selected item
                    ObservableList<Events> selectedItems = FXCollections.observableArrayList(
                            followUpTableView.getSelectionModel().getSelectedItems());

                    // Adding selected item to the target
                    ObservableClass.ordersObservableList.addAll(selectedItems);

                    // Removing selected item from source
                    ObservableClass.eventsObservableList.removeAll(selectedItems);

                    // Clearing the selection in tableView
                    followUpTableView.getSelectionModel().clearSelection();

                    // doing it one-by-one
                    selectedItems.stream().forEach(e -> {
                                o.ordersTableView.getSelectionModel().select(e);

                                db.insertToOrders(e.getPrice(),e.getEventAddress(),e.getEventZipCode(),e.getEventType(),e.getComments(),e.getStartEventTime(),e.getEndEventTime(),e.getEventDate(),e.getProduct());

                                //makes a Alert boks with texts
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setTitle("Besked");
                                alert.setContentText("Du har nu flyttet udvalgt fra opfølgning til ordre");
                                alert.showAndWait();
                            }
                    );

                }
                else {
                    //do nothing, else out of bounds Arraylist -1
                }
            }
        });

        //layout for logo, header and buttons
        hBoxHeader.setAlignment(Pos.TOP_RIGHT);
        hBoxHeader.setPadding(new Insets(20, 20, 20, 0));
        hBoxHeader.getChildren().addAll(imageView);

        headerSale.setFont(new Font("Arial", 32));

        labelHbox.setAlignment(Pos.CENTER);
        labelHbox.getChildren().addAll(headerSale);

        StackPane stackPaneSale = new StackPane();
        stackPaneSale.getChildren().addAll(labelHbox, hBoxHeader);
        stackPaneSale.setAlignment(Pos.TOP_CENTER);

        hBoxSale.getChildren().addAll(confirmSale, addSale, removeSale);
        hBoxSale.setSpacing(400);
        hBoxSale.setPadding(new Insets(10, 10, 10, 10));
        hBoxSale.setStyle("-fx-background-color: #cb0118");

        //setting layout in borderpane
        borderPaneSale.setTop(stackPaneSale);
        borderPaneSale.setCenter(followUpTableView);
        borderPaneSale.setPadding(new Insets(0, 0, 0, 70));
        borderPaneSale.setBottom(hBoxSale);

        followUpTableView.itemsProperty().setValue(ObservableClass.eventsObservableList);

        //When double click on row in tableview, show editOrder with data in textfields
        followUpTableView.setRowFactory(tv -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(e -> {
                if(e.getClickCount() == 2 && (!row.isEmpty())){

                    db.getEventFromDatabase();

                    int selectedIndex = FollowUp.followUpTableView.getSelectionModel().getSelectedIndex();

                    editOrder.firstNameText.setText(db.onlyArraylist.get(selectedIndex).getCustomer_first_name());
                    editOrder.lastNameText.setText(db.onlyArraylist.get(selectedIndex).getCustomer_last_name());
                    editOrder.emailText.setText(db.onlyArraylist.get(selectedIndex).getCustomer_email_address());
                    editOrder.phoneText.setText(db.onlyArraylist.get(selectedIndex).getCustomer_phone());
                    editOrder.addressText.setText(db.onlyArraylist.get(selectedIndex).getEventAddress());
                    editOrder.zipText.setText(db.onlyArraylist.get(selectedIndex).getEventZipCode());
                    editOrder.cityText.setText(db.onlyArraylist.get(selectedIndex).getEventCity());
                    editOrder.typeText.setText(db.onlyArraylist.get(selectedIndex).getEventType());
                    editOrder.startTimeCombo.setValue(db.onlyArraylist.get(selectedIndex).getStartEventTime());
                    editOrder.endTimeCombo.setValue(db.onlyArraylist.get(selectedIndex).getEndEventTime());
                    editOrder.startDatePicker.setValue(calculations.stringToDate(db.onlyArraylist.get(selectedIndex).getEventDate()));
                    editOrder.commentArea.setText(db.onlyArraylist.get(selectedIndex).getComments());
                    editOrder.productText.setText(db.onlyArraylist.get(selectedIndex).getProduct());
                    editOrder.priceText.setText(Double.toString(db.onlyArraylist.get(selectedIndex).getPrice()));

                    editOrder.addEventWindow();
                }
            });
            return row;
        });

        return borderPaneSale;
    }

}