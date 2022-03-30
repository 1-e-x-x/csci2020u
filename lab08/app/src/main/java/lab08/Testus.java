package lab08;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Testus extends Application{
    public static String currentFile = new String("C:\\tmp\\unnamed.txt");
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Lab 08 Test File");
        stage.setAlwaysOnTop(true); //added this line to speed up prototyping, I'm tired of alt-tabbing
        stage.setWidth(600);
        stage.setHeight(500);
        GridPane gridPane = new GridPane();
        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();
        
        
        //make menu
        Menu fileMenu = new Menu("File");
        MenuItem fileMenuNew = new MenuItem("New");
        MenuItem fileMenuOpen = new MenuItem("Open");
        MenuItem fileMenuSave = new MenuItem("Save");
        MenuItem fileMenuSaveAs = new MenuItem("Save As");
        MenuItem fileMenuExit = new MenuItem("Exit");
        fileMenu.getItems().addAll(fileMenuNew, fileMenuOpen, fileMenuSave, fileMenuSaveAs, fileMenuExit);
        MenuBar optionsBar = new MenuBar();
        optionsBar.getMenus().add(fileMenu);

        //make table
        TableView tableView = new TableView<>();//i know I could have made a loop, but i'm lazy and this works
        TableColumn<StudentRecord, String> col1 = new TableColumn<>("Student ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("SID"));
        TableColumn<StudentRecord, String> col2 = new TableColumn<>("Midterm");
        col2.setCellValueFactory(new PropertyValueFactory<>("Midterm"));
        TableColumn<StudentRecord, String> col3 = new TableColumn<>("Assignments");
        col3.setCellValueFactory(new PropertyValueFactory<>("Assignments"));
        TableColumn<StudentRecord, String> col4 = new TableColumn<>("Final Exam");
        col4.setCellValueFactory(new PropertyValueFactory<>("Exam"));
        TableColumn<StudentRecord, String> col5 = new TableColumn<>("Final Mark");
        col5.setCellValueFactory(new PropertyValueFactory<>("Mark"));
        TableColumn<StudentRecord, String> col6 = new TableColumn<>("Letter Grade");
        col6.setCellValueFactory(new PropertyValueFactory<>("Grade"));
        tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6);
        
        //make addEntry assets
        Label descLabel = new Label("Make new student record:");
        TextField sidTextField = new TextField();
        sidTextField.setPromptText("SID");
        TextField assignmentsTextField = new TextField();
        assignmentsTextField.setPromptText("Assignments");
        TextField midtermTextField = new TextField();
        midtermTextField.setPromptText("Midterm");
        TextField examTextField = new TextField();
        examTextField.setPromptText("Final Exam");
        Button addButton = new Button("Add");
        
        
        

        //add to gridpane
        gridPane.add(optionsBar, 0, 0);
        gridPane.add(tableView, 0, 1);
        gridPane.add(descLabel, 0, 2);
        gridPane.add(sidTextField, 0, 3);
        gridPane.add(assignmentsTextField, 0, 4);
        gridPane.add(midtermTextField, 0, 5);
        gridPane.add(examTextField, 0, 6);
        gridPane.add(addButton, 0, 7);

        //new event
        EventHandler<ActionEvent> newHandler = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                System.out.println("pressed new menuButton");
                try {
                    marks.clear();
                    currentFile = "C:\\Documents\\unnamed";
                    tableView.setItems(marks);
                    //tableView.refresh();
                } catch (Exception error) {
                    error.printStackTrace();
                }
                
            }
        };
        fileMenuNew.setOnAction(newHandler);
        //open event
        EventHandler<ActionEvent> openHandler = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                System.out.println("pressed open menuButton");
                try {
                    marks.setAll(load(stage));
                    System.out.println("Loaded file, current filename: " + currentFile);
                    tableView.setItems(marks);
                    //tableView.refresh();
                } catch (Exception error) {
                    error.printStackTrace();
                }
                
            }
        };
        fileMenuOpen.setOnAction(openHandler);
        //save event
        EventHandler<ActionEvent> saveHandler = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                System.out.println("pressed save menuButton");
                try {
                    File toWrite = new File(currentFile);
                    FileWriter fileWriter = new FileWriter(toWrite);
                    //System.out.println(marks.get(marks.size()));
                    if(0 >= marks.size()){
                        System.out.println("Empty File");
                        fileWriter.close();
                    }
                    else{
                        for (int i = 0; i < marks.size()-1; i++){
                            fileWriter.write(marks.get(i).getSID()+",");
                            fileWriter.write(marks.get(i).getMidterm()+",");
                            fileWriter.write(marks.get(i).getAssignments()+",");
                            fileWriter.write(marks.get(i).getExam()+","+"\n");

                        }
                        fileWriter.write(marks.get(marks.size()-1).getSID()+",");
                        fileWriter.write(marks.get(marks.size()-1).getMidterm()+",");
                        fileWriter.write(marks.get(marks.size()-1).getAssignments()+",");
                        fileWriter.write(marks.get(marks.size()-1).getExam());
                        fileWriter.close();
                    }
                    System.out.println("Saved file to: "+ currentFile);
                    
                } catch (Exception error) {
                    error.printStackTrace();
                }
                
            }
        };
        fileMenuSave.setOnAction(saveHandler);
        //save as event
        EventHandler<ActionEvent> saveAsHandler = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                System.out.println("pressed save as menuButton");
                try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Save as");
                    File toWrite = fileChooser.showSaveDialog(stage);
                    FileWriter fileWriter = new FileWriter(toWrite);
                    //System.out.println(marks.get(marks.size()));
                    if(0 >= marks.size()){
                        System.out.println("Empty File");
                        fileWriter.close();
                    }
                    else{
                        for (int i = 0; i < marks.size()-1; i++){
                            fileWriter.write(marks.get(i).getSID()+",");
                            fileWriter.write(marks.get(i).getMidterm()+",");
                            fileWriter.write(marks.get(i).getAssignments()+",");
                            fileWriter.write(marks.get(i).getExam()+","+"\n");

                        }
                        fileWriter.write(marks.get(marks.size()-1).getSID()+",");
                        fileWriter.write(marks.get(marks.size()-1).getMidterm()+",");
                        fileWriter.write(marks.get(marks.size()-1).getAssignments()+",");
                        fileWriter.write(marks.get(marks.size()-1).getExam());
                        fileWriter.close();
                    }
                    System.out.println("Saved file to: " + toWrite.getAbsolutePath());
                    
                } catch (Exception error) {
                    error.printStackTrace();
                }
                
            }
        };
        fileMenuSaveAs.setOnAction(saveAsHandler);
        //exit event
        EventHandler<ActionEvent> exitHandler = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                System.out.println("pressed exit menuButton");
                stage.close();
            }
        };
        fileMenuExit.setOnAction(exitHandler);
        
        //add record event
        EventHandler<ActionEvent> addHandler = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                System.out.println("pressed add button");
                try {
                    marks.add(new StudentRecord(sidTextField.getText(),
                    Float.parseFloat(assignmentsTextField.getText()),
                    Float.parseFloat(midtermTextField.getText()),
                    Float.parseFloat(examTextField.getText())));
                    tableView.setItems(marks);
                    //tableView.refresh();
                } catch (Exception error) {
                    error.printStackTrace();
                }
                
            }
        };
        addButton.setOnAction(addHandler);


        
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        
        stage.show();
    }
    public static ObservableList<StudentRecord> load(Stage stage) throws Exception{
        //Open fileChooser, select file, and print file contents to console for debug.
        //Package each line of the file into a StudentRecord, then an ObservableList of type StudentRecord
        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open text file");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        File dataFile = fileChooser.showOpenDialog(stage);
        if (null != dataFile){
            System.out.println(dataFile.getAbsolutePath());
            Scanner scanner = new Scanner(dataFile);
            scanner.useDelimiter(",");
            while(scanner.hasNext()){
                String SID = scanner.next();
                Float Midterm = Float.parseFloat(scanner.next());
                Float Assignments = Float.parseFloat(scanner.next());
                Float Exam =Float.parseFloat(scanner.next());
                System.out.println(SID + " " + Midterm + " " + Assignments + " " + Exam);
                marks.add(new StudentRecord(SID, Midterm, Assignments, Exam));
              
            }
            currentFile = dataFile.getAbsolutePath();
            scanner.close();      
        }
        return marks;    
    }

}
