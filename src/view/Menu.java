package view;

import controller.Calculations;
import controller.Database;
import controller.ObservableClass;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Events;
import sun.util.resources.uk.CalendarData_uk;

public class Menu extends Application {

    //Creating instances of classes
    FollowUp followUp = new FollowUp();
    Orders orders = new Orders();
    CustomerTab customerTab = new CustomerTab();
    Database db = new Database();
    Calculations calculate = new Calculations();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Creating gridpane og tabpane
        GridPane layout = new GridPane();
        TabPane mainTabpane = new TabPane();

        //Making tabs
        //Customer
        Tab customertab = new Tab();
        customertab.setText("Kunde");
        customertab.setContent(customerTab.getCustomerTab());

        //Order
        Tab orderTab = new Tab();
        orderTab.setText("Ordre");
        orderTab.setContent(orders.getOrdersClass());

        //FollowUp
        Tab followUpTab = new Tab();
        followUpTab.setText("Opfølgning");
        followUpTab.setContent(followUp.getFollowUpClass());

        //Getting data from database
        db.buildDataEvent();
        db.getOrdersFromDatabase();
        for(Events e : ObservableClass.eventsObservableList){
            calculate.countdownForOrder(e);
        }

        //Main tab
        mainTabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        mainTabpane.setSide(Side.LEFT);
        mainTabpane.setTabMinHeight(60);
        mainTabpane.setTabMinWidth(264);
        mainTabpane.getTabs().addAll(followUpTab, orderTab, customertab);

        //Making layout setting
        layout.getChildren().addAll(mainTabpane);
        Scene sceneMain = new Scene(layout);

        primaryStage.setHeight(1000);
        primaryStage.setWidth(1500);

        primaryStage.setScene(sceneMain);
        primaryStage.setTitle("Festvognen FætterGuf");
        primaryStage.getIcons().add(new Image("Fætter_guf_logo.gif"));
        primaryStage.show();

    }
}

