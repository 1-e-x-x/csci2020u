package lab05;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
    //setup
        stage.setTitle("Lab 05 Solution");
        stage.setAlwaysOnTop(true); //added this line to speed up prototyping, I'm tired of alt-tabbing
        stage.setWidth(600);
        stage.setHeight(500);

        TableView tableView = new TableView<>();


        //make assets
        TableColumn<StudentRecord, String> col1 = new TableColumn<>("Student ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("SID"));

        TableColumn<StudentRecord, String> col2 = new TableColumn<>("Assignments");
        col2.setCellValueFactory(new PropertyValueFactory<>("Assignments"));

        TableColumn<StudentRecord, String> col3 = new TableColumn<>("Midterm");
        col3.setCellValueFactory(new PropertyValueFactory<>("Midterm"));

        TableColumn<StudentRecord, String> col4 = new TableColumn<>("Final Exam");
        col4.setCellValueFactory(new PropertyValueFactory<>("Exam"));

        TableColumn<StudentRecord, String> col5 = new TableColumn<>("Final Mark");
        col5.setCellValueFactory(new PropertyValueFactory<>("Mark"));

        TableColumn<StudentRecord, String> col6 = new TableColumn<>("Letter Grade");
        col6.setCellValueFactory(new PropertyValueFactory<>("Grade"));


        //add to stage

        

        //StudentRecord testus = new StudentRecord("100100102", 100.0f, 97.0f, 92.5f);

        tableView.getColumns().add(col1);
        tableView.getColumns().add(col2);
        tableView.getColumns().add(col3);
        tableView.getColumns().add(col4);
        tableView.getColumns().add(col5);
        tableView.getColumns().add(col6);

        tableView.setItems(DataSource.getAllMarks());
        
        
        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);
    //display
        stage.setScene(scene);
        stage.show();
    }
}