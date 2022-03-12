package lab06;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
 
public class App extends Application {

    private static int canvasY = 500;

    //data for bar chart
    private static double[] avgHousingPricesByYear = {
        247381.0,264171.4,287715.3,294736.1,
        308431.4,322635.9,340253.0,363153.7
       };
    private static double[] avgCommercialPricesByYear = {
       1121585.3,1219479.5,1246354.2,1295364.8,
       1335932.6,1472362.0,1583521.9,1613246.3
       }; 

    //data for pie chart
    private static String[] ageGroups = {
        "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
        };
    private static int[] purchasesByAgeGroup = {
        648, 1021, 2453, 3173, 1868, 2247
        };
    private static Color[] pieColours = {
        Color.AQUA, Color.GOLD, Color.DARKORANGE,
        Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
        }; 

    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {

        stage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(900, canvasY);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.setAlwaysOnTop(true); //added this line to speed up prototyping, I'm tired of alt-tabbing
        stage.show();
    }

    private void drawShapes(GraphicsContext gc) {
    //bar chart
        //1: find maximum of data
        double maxAvgCommercialPricesByYear = 0;
        for (int i = 0; i < avgCommercialPricesByYear.length; i++) {
            if (maxAvgCommercialPricesByYear < avgCommercialPricesByYear[i]) {
                maxAvgCommercialPricesByYear =  avgCommercialPricesByYear[i];
            }
        }
        //2: express items as a percent of max, then scale with chart

        gc.setFill(Color.RED);
        //housing prices
        for(int i = 0; i < avgHousingPricesByYear.length; i++){
            gc.fillRect(10 + i * 30, 10, 10, (avgHousingPricesByYear[i]/maxAvgCommercialPricesByYear * canvasY/2));
        }

        gc.setFill(Color.BLUE);
        //commercial prices
        for(int i = 0; i < avgCommercialPricesByYear.length; i++){
            gc.fillRect(20 + i * 30, 10, 10, (avgCommercialPricesByYear[i]/maxAvgCommercialPricesByYear * canvasY/2));
        }

    //pie chart
        //1: sum all entries
        Double sumPurchasesByAgeGroup = 0.0;
        for(int i = 0; i < ageGroups.length; i++){
            sumPurchasesByAgeGroup = sumPurchasesByAgeGroup + purchasesByAgeGroup[i];
        }
        //2: find percent of 360, then draw arc
        double start = 0;
        double travel = 0;
        for(int i = 0; i < ageGroups.length; i++){
            travel =  purchasesByAgeGroup[i] / sumPurchasesByAgeGroup * 730;//ah yes, there are 730 degrees in a circle. there are no issues in my code whatsoever
            gc.setFill(pieColours[i]);
            gc.fillArc(500, 100, 200, 200, start, travel, ArcType.ROUND);
            //gc.fillArc(300, 300, 200, 200, 90, 90, ArcType.ROUND);
            start = 1 * travel;
        }
    }
}