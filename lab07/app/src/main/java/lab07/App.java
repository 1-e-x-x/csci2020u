package lab07;

import java.io.*;
import org.apache.commons.csv.*;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
 
public class App extends Application{

    private static int canvasY = 500;
    private static int canvasX = 500;

    //data for pie chart
    private static String[] stormTypes = {
        "FLASH FLOOD", "SEVERE THUNDERSTORM", "SPECIAL MARINE", "TORNADO"
        };
    private static int[] dataFetcher(){
        //repurposing code from lab 3, because I am exeptionally lazy.
        //this is poorly done and would get me fired instantly if I did it in a workplace
        //if I actually was working with big data though, I wouldn't be using java. I'd be using python or SQL or something.
        //praise be to dataframes, I wish I could use them for this

        int flashFloods = 0;
        int severeThunderstorms = 0;
        int specialMarines = 0;
        int tornados = 0;

        try {
            Reader in = new FileReader("weatherwarnings-2015.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader("Start", "End", "gunk", "gunk2", "gunk3", "type","coords").parse(in);
            for (CSVRecord record: records) {
    
                String start = record.get("Start");
                String end = record.get("End");
                String garbo = record.get("gunk");
                String garbo2 = record.get("gunk2");
                String garbo3 = record.get("gunk3");
                //the part I care about
                String stormType = record.get("type");
                //
                String location = record.get("coords");
    
                if ("FLASH FLOOD".equals(stormType)){
                    flashFloods += 1;
                }
                else if("SEVERE THUNDERSTORM".equals(stormType)){
                    severeThunderstorms += 1;
                }
                else if("SPECIAL MARINE".equals(stormType)){
                    specialMarines += 1;
                }
                else if("TORNADO".equals(stormType)){
                    tornados += 1;
                }
                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }
            int[] toReturn = {flashFloods, severeThunderstorms, specialMarines, tornados};
            return toReturn;
        
    }
    
    private static int[] data = dataFetcher();
    
    private static Color[] pieColours = {
        Color.AQUA, Color.GOLD, Color.DARKORANGE,
        Color.DARKSALMON}; 

    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Text tex1 = new Text();
        Text tex2 = new Text(); 
        Text tex3 = new Text(); 
        Text tex4 = new Text();       
       
        tex1.setText("FLASH FLOOD"); 
        tex1.setX(80); 
        tex1.setY(190); 

        tex2.setText("SEVERE THUNDERSTORM"); 
        tex2.setX(80); 
        tex2.setY(230);

        tex3.setText("SPECIAL MARINE"); 
        tex3.setX(80); 
        tex3.setY(270);

        tex4.setText("TORNADO"); 
        tex4.setX(80); 
        tex4.setY(310);
           

        stage.setTitle("Lab 06");
        Group root = new Group(tex1, tex2, tex3, tex4);
        Canvas canvas = new Canvas(canvasX, canvasY);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.setAlwaysOnTop(true); //added this line to speed up prototyping, I'm tired of alt-tabbing
        stage.show();
    }

    private void drawShapes(GraphicsContext gc) {
    //pie chart
        //1: sum all entries
        Double sumStorms = 0.0;
        for(int i = 0; i < stormTypes.length; i++){
            sumStorms = sumStorms + data[i];
        }
        //2: find percent of 360, then draw arc
        double start = 0;
        double travel = 0;
        for(int i = 0; i < stormTypes.length; i++){
            travel =  data[i] / sumStorms * 360;
            gc.setFill(pieColours[i]);
            gc.fillArc(250, 150, 200, 200, start, travel, ArcType.ROUND);
            gc.fillRect(50, 180 + i * 40, 20, 15);
            start = start + travel;
        }
        
        /*
        Text tex1 = new Text();
        tex1.setText("FLASH FLOOD");
        tex1.setX(100);
        tex1.sety(180);
        
        Text tex2 = new Text();
        tex2.setText("SEVERE THUNDERSTORM");

        Text tex3 = new Text();
        tex3.setText("SPECIAL MARINE");

        Text tex4 = new Text();
        tex4.setText("TORNADO");
        */

    }
}