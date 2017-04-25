package view;

import controller.Calculations;
import controller.Database;
import controller.ObservableClass;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import model.Events;
import java.util.Optional;

public class Orders {

    //Creating instances
    EditOrder editOrder = new EditOrder();
    Calculations calculations = new Calculations();

    TableView ordersTableView = new TableView();

    private IntegerProperty indexOrder = new SimpleIntegerProperty();

    //Getter and Setter
    public final double getIndexOrder() {
        return indexOrder.get();
    }

    public final void setIndexOrder(Integer value) {
        indexOrder.set(value);
    }

    public IntegerProperty indexOrderProperty() {
        return indexOrder;
    }

    //Orders tab
    public BorderPane getOrdersClass() {

        BorderPane borderPaneOrders = new BorderPane();

        HBox hBoxOrder = new HBox();
        Button removeOrder = new Button("Fjern Ordre");
        removeOrder.setMinWidth(150);
        HBox hBoxHeader = new HBox();
        HBox labelHbox = new HBox();
        final Label headerOrder = new Label("Ordre Oversigt");

        //Logo
        Image logo = new Image("Fætter_guf_logo.gif");
        ImageView imageView = new ImageView();
        imageView.setImage(logo);

        //Date
        TableColumn<Events, String> dateColumn = new TableColumn<>("Dato:");
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setCellValueFactory(e -> e.getValue().eventDateProperty());

        //Time
        TableColumn<Events, String> startTimeCol = new TableColumn<>("Start:");
        startTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        startTimeCol.setCellValueFactory(e -> e.getValue().startEventTimeProperty());
        startTimeCol.setMaxWidth(2000);

        TableColumn<Events, String> endTimeCol = new TableColumn<>("Slut:");
        endTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        endTimeCol.setCellValueFactory(e-> e.getValue().endEventTimeProperty());
        endTimeCol.setMaxWidth(2000);

        TableColumn<Events, Number> timeCol = new TableColumn<>("Tidspunkt:");
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
        TableColumn<Events, String> productColumn = new TableColumn<>("Aktiviter:");
        productColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productColumn.setCellValueFactory(e -> e.getValue().productProperty());

        //Event type
        TableColumn<Events, String> eventTypeColumn = new TableColumn<>("Arrangement:");
        eventTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        eventTypeColumn.setCellValueFactory(e -> e.getValue().eventTypeProperty());

        //Layout
        ordersTableView.getColumns().addAll(dateColumn, timeCol, namecol, productColumn, eventTypeColumn);
        ordersTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //delete selected row in tableview
        indexOrderProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue o, Object oldVal,
                                Object newVal) {
            }
        });

        ordersTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
                setIndexOrder(ObservableClass.getOrdersObservableList().indexOf(newValue));
            }
        });

        //Button remove
        removeOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //Check if OrderObservableList is Empty
                if (!(ObservableClass.ordersObservableList.isEmpty())) {

                    //Makes a Alert boks with texts
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Er du sikker på at du vil slette udvalgt ordre?");
                    alert.setHeaderText(null);
                    alert.setTitle("Besked");

                    //Makes a choice if Ok is pressed and if cancel is pressed
                    Optional<ButtonType> result = alert.showAndWait();
                    //Removes selected Order from orderObservableList
                    if (result.get() == ButtonType.OK) {
                        Database update = new Database();
                        update.removeOrdersFromDatabase(ordersTableView.getSelectionModel().getSelectedItem());
                        ObservableClass.getOrdersObservableList().remove(indexOrder.get());
                        ordersTableView.getSelectionModel().clearSelection();
                    } else {
                        removeOrder.cancelButtonProperty();
                    }
                } else {
                    //do nothing, else out of bounds Arraylist -1
                }
            }
        });


        //Layout for logo, header and buttons
        hBoxHeader.setAlignment(Pos.TOP_RIGHT);
        hBoxHeader.setPadding(new Insets(20, 20, 20, 0));
        hBoxHeader.getChildren().addAll(imageView);

        headerOrder.setFont(new Font("Arial", 32));

        labelHbox.setAlignment(Pos.CENTER);
        labelHbox.getChildren().addAll(headerOrder);
        StackPane stackPaneOrders = new StackPane();

        stackPaneOrders.getChildren().addAll(labelHbox, hBoxHeader);
        stackPaneOrders.setAlignment(Pos.TOP_CENTER);

        hBoxOrder.getChildren().addAll(removeOrder);
        hBoxOrder.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxOrder.setSpacing(400);
        hBoxOrder.setPadding(new Insets(10, 10, 10, 10));
        hBoxOrder.setStyle("-fx-background-color: #cb0118");

        ordersTableView.setItems(ObservableClass.ordersObservableList);

        //Setting the layout in borderpane
        borderPaneOrders.setTop(stackPaneOrders);
        borderPaneOrders.setCenter(ordersTableView);
        borderPaneOrders.setPadding(new Insets(0, 0, 0, 70));
        borderPaneOrders.setBottom(hBoxOrder);

        //When double click on row in tableview, show editOrder with data in textfields
        ordersTableView.setRowFactory( tv -> {
            TableRow tableRow = new TableRow();
            tableRow.setOnMouseClicked(e -> {
                if(e.getClickCount() == 2 && (!tableRow.isEmpty())){

                    Events events = (Events) ordersTableView.getSelectionModel().getSelectedItem();

                    editOrder.lastNameText.setText(events.getCustomers().getLastName());
                    editOrder.firstNameText.setText(events.getCustomers().getFirstName());
                    editOrder.emailText.setText(events.getCustomers().getEmailAddress());
                    editOrder.phoneText.setText(events.getCustomers().getPhone());
                    editOrder.addressText.setText(events.getEventAddress());
                    editOrder.zipText.setText(events.getEventZipCode());
                    editOrder.cityText.setText(events.getEventCity());
                    editOrder.typeText.setText(events.getEventType());
                    editOrder.startTimeCombo.setValue(events.getStartEventTime());
                    editOrder.endTimeCombo.setValue(events.getEndEventTime());
                    editOrder.startDatePicker.setValue(calculations.stringToDate(events.getEventDate()));
                    editOrder.commentArea.setText(events.getComments());
                    editOrder.productText.setText(events.getProduct());
                    editOrder.priceText.setText(Double.toString(events.getPrice()));

                    editOrder.addEventWindow();
                }
            });
            return tableRow;
        });

        return borderPaneOrders;
    }

}