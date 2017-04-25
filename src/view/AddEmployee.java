package view;

import controller.Database;
import controller.ObservableClass;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Employee;

public class AddEmployee {

    public void createEmployee() {
        //Creating new Object of DatabaseConnection
        Database db = new Database();
        //Creating new stage
        Stage employeeStage = new Stage();

        //Insides of the scene
        //Creating Labels
        Label firstNameLabel, lastNameLabel, emailLabel, phoneLabel;

        //Creating Button
        Button createEmployeeButton = new Button("Opret");

        //Creating Layout GridPane
        GridPane createEmployeeGridPane = new GridPane();

        //Creating TextFields
        final TextField firstNameTextField = new TextField();
        final TextField lastNameTextField = new TextField();
        final TextField emailTextField = new TextField();
        final TextField phoneTextField = new TextField();

        //Adding to GridPane
        createEmployeeGridPane.add(firstNameTextField, 2, 1);
        createEmployeeGridPane.add(lastNameTextField, 2, 2);
        createEmployeeGridPane.add(emailTextField, 2, 3);
        createEmployeeGridPane.add(phoneTextField, 2, 4);
        createEmployeeGridPane.add(createEmployeeButton, 1, 5);

        //Button Action
        createEmployeeButton.setOnAction(e -> {



            //Employee constructor variables
            String employeeFirstName = firstNameTextField.getText();
            String employeeLastName = lastNameTextField.getText();
            String employeeEmail = emailTextField.getText();
            String employeePhone = phoneTextField.getText();

            //Creating new Object of Employee
            Employee employee = new Employee(employeeFirstName, employeeLastName, employeeEmail, employeePhone);
            ObservableClass.addEmployee(employee);

            db.createEmployee(employee); //adding employee to Database
            employeeStage.close();
        });

        //Add to gridpane
        createEmployeeGridPane.add(firstNameLabel = new Label("Fornavn:"), 1, 1);
        createEmployeeGridPane.add(lastNameLabel = new Label("Efternavn:"), 1, 2);
        createEmployeeGridPane.add(emailLabel = new Label("Email:"), 1, 3);
        createEmployeeGridPane.add(phoneLabel = new Label("Telefon nummer:"), 1, 4);

        createEmployeeGridPane.setHgap(8);
        createEmployeeGridPane.setVgap(8);

        Scene createEmployeeScene = new Scene(createEmployeeGridPane, 400, 400);

        employeeStage.setTitle("Opret ny medarbejder");
        employeeStage.setScene(createEmployeeScene);
        //Cannot click on another window before this window is closed
        employeeStage.initModality(Modality.APPLICATION_MODAL);
        employeeStage.showAndWait();
    }
}
