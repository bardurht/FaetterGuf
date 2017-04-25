package view;

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
import model.Customer;
import java.util.Optional;

public class CustomerTab {

    //indexCustomerTab
    private IntegerProperty indexCustomerTab = new SimpleIntegerProperty();

    //Getters and Setters

    public final double getIndexCustomerTab() {
        return indexCustomerTab.get();
    }

    public final void setIndexCustomerTab(Integer value) {
        indexCustomerTab.set(value);
    }

    public IntegerProperty indexCustomerTabProperty() {
        return indexCustomerTab;
    }

    //Customer tab
    public BorderPane getCustomerTab() {

        //FX Stuff
        TableView customerTabTableView = new TableView();
        BorderPane borderPaneCustomerTab = new BorderPane();
        HBox hBoxCustomerTab = new HBox();
        Button removeCustomerTab = new Button("Fjern Kunde");
        removeCustomerTab.setMinWidth(150);
        HBox hBoxHeader = new HBox();
        HBox labelHbox = new HBox();
        final Label headerCustomerTab = new Label("Kunde historie");

        //logo
        Image logo = new Image("Fætter_guf_logo.gif");
        ImageView imageView = new ImageView();
        imageView.setImage(logo);

        //TableView
        //First name
        TableColumn<Customer, String> firstnameCol = new TableColumn<>("Fornavn:");
        firstnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstnameCol.setCellValueFactory(e -> e.getValue().firstNameProperty());

        //Last name
        TableColumn<Customer, String> lastnameCol = new TableColumn<>("Efternavn:");
        lastnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastnameCol.setCellValueFactory(e -> e.getValue().lastNameProperty());

        //Name column
        TableColumn<Customer, String> nameCol = new TableColumn<>("Kundenavn:");
        nameCol.getColumns().addAll(firstnameCol, lastnameCol);

        //Email address
        TableColumn<Customer, String> emailAddress = new TableColumn<>("Email Adresse:");
        emailAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        emailAddress.setCellValueFactory(e -> e.getValue().emailAddressProperty());

        //Phone
        TableColumn<Customer, String> tflCol = new TableColumn<>("Telefonnummer:");
        tflCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tflCol.setCellValueFactory(e -> e.getValue().phoneProperty());

        //delete selected row in tableview with Listener
        indexCustomerTabProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue o, Object oldVal,
                                Object newVal) {
            }
        });

        customerTabTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
                setIndexCustomerTab(ObservableClass.getCustomerObservableList().indexOf(newValue));
            }
        });

        //Button remove
        //removes selected event in tableView from CustomerObservableList
        removeCustomerTab.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                //check if Arraylist is empty or not
                if (!(ObservableClass.customerObservableList.isEmpty())) {

                    //makes a Alert boks with texts
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Er du sikker på at du vil slette udvalgt kunde?");
                    alert.setHeaderText(null);
                    alert.setTitle("Besked");

                    //makes a choice if Ok is pressed and if cancel is pressed
                    Optional<ButtonType> result = alert.showAndWait();

                    //removes selected Customer from CustomerList
                    if (result.get() == ButtonType.OK) {
                        Database update = new Database();
                        update.removeCustomerFromDatabase(customerTabTableView.getSelectionModel().getSelectedItem());
                        ObservableClass.getCustomerObservableList().remove(indexCustomerTab.get());
                        customerTabTableView.getSelectionModel().clearSelection();
                    } else {
                        removeCustomerTab.cancelButtonProperty();
                    }
                } else {
                    //do nothing, else out of bounds Arraylist -1
                }
            }

        });

        //layout
        customerTabTableView.getColumns().addAll(nameCol, emailAddress, tflCol);

        customerTabTableView.setItems(ObservableClass.customerObservableList);

        customerTabTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //layout for logo, header and buttons
        hBoxHeader.setAlignment(Pos.TOP_RIGHT);
        hBoxHeader.setPadding(new Insets(20, 20, 20, 0));
        hBoxHeader.getChildren().addAll(imageView);

        headerCustomerTab.setFont(new Font("Arial", 32));

        labelHbox.setAlignment(Pos.CENTER);
        labelHbox.getChildren().addAll(headerCustomerTab);
        StackPane stackPaneCustomerTab = new StackPane();

        stackPaneCustomerTab.getChildren().addAll(labelHbox, hBoxHeader);
        stackPaneCustomerTab.setAlignment(Pos.TOP_CENTER);

        hBoxCustomerTab.getChildren().addAll(removeCustomerTab);
        hBoxCustomerTab.setPadding(new Insets(10, 10, 10, 10));
        hBoxCustomerTab.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxCustomerTab.setStyle("-fx-background-color: #cb0118");

        //setting layout in borderpane
        borderPaneCustomerTab.setTop(stackPaneCustomerTab);
        borderPaneCustomerTab.setCenter(customerTabTableView);
        borderPaneCustomerTab.setPadding(new Insets(0, 0, 0, 70));
        borderPaneCustomerTab.setBottom(hBoxCustomerTab);

        return borderPaneCustomerTab;
    }

}