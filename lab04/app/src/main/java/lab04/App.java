package lab04;

/*
requirements:
username entry
password entry (obscured) -passwordField
name entry
email entry
phone number (formatted)
date of birth (calendar picker) - DatePicker

use borderpade layout?
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.event.*;

public class App extends Application {

    @Override
    public void start(Stage stage) {


    //asset setup

        //textfields
        TextField userEntry = new TextField();
        userEntry.setPromptText("Username");

        PasswordField passEntry = new PasswordField();
        passEntry.setPromptText("Password");
 
        TextField nameEntry = new TextField();
        nameEntry.setPromptText("Full Name");

        TextField emEntry= new TextField();
        emEntry.setPromptText("E-Mail");

        TextField phoneEntry= new TextField();
        phoneEntry.setPromptText("000-000-0000");


        //datepicker?
        DatePicker dp = new DatePicker();

        //labels
        Label user = new Label("Username:");
        Label pass = new Label("Password:");
        Label name = new Label("Full Name:");
        Label email = new Label("E-Mail:");
        Label phone = new Label("Phone #:");
        Label DOB = new Label("Date of Birth:");

        //register button
        Button register = new Button("Register");
        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println(userEntry.getText());
                System.out.println(passEntry.getText());
                System.out.println(nameEntry.getText());
                System.out.println(emEntry.getText());
                System.out.println(phoneEntry.getText());
                System.out.println(dp.getValue());
                

            }
        });

    //pane setup
        GridPane Gpane = new GridPane();
        //label setup
        Gpane.add(user, 0, 0);
        Gpane.add(pass, 0, 1);
        Gpane.add(name, 0, 2);
        Gpane.add(email, 0, 3);
        Gpane.add(phone, 0, 4);
        Gpane.add(DOB, 0, 5);

        //functional setup
        Gpane.add(userEntry, 1, 0);
        Gpane.add(passEntry, 1, 1);
        Gpane.add(nameEntry, 1, 2);
        Gpane.add(emEntry, 1, 3);
        Gpane.add(phoneEntry, 1, 4);

        Gpane.add(dp, 1, 5);

        //the button
        Gpane.add(register, 0, 6);

    //scene setup
        Scene scene = new Scene(Gpane, 640, 480);
        stage.setTitle("Lab 04 Solution");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}