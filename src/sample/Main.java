package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Main extends Application {
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        primaryStage.setTitle("Lab 7");
        Scene scene = new Scene(root, 800, 500);
        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        primaryStage.show();

        draw(root);
    }

    private void draw(Group root) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        TreeMap<String,Integer> wordCount = new TreeMap<>();
        wordCount = getCount();
        System.out.println("wordCount = " + wordCount);

        // Calculate total number of wordCount
        int wordCountTotal = 0;
        System.out.println(wordCount.values());
        for (int num: wordCount.values()) {
            wordCountTotal += num;
        }
        //System.out.println(wordCountTotal);

        //for (int i=0; i<Integer.parseInt(wordCount.keySet();i++) {
            //System.out.println(i);
        //}
        double startAngle = 0;
        double extentAngle = 0;
        //System.out.println(wordCount.keySet());
        gc.setStroke(Color.BLACK);
        Font font = new Font("Arial", 14);
        gc.setFont(font);
        for (String entry : wordCount.keySet()) {

            if (entry.equals("FLASH FLOOD")) {
                gc.setFill(Color.BLACK);
                gc.strokeRect(100,50,80,40);
                gc.fillText("FLASH FLOOD",190,75);
                gc.setFill(Color.AQUA);
                gc.fillRect(100,50,80,40);

            }
            else if (entry.equals("SEVERE THUNDERSTORM")) {
                gc.setFill(Color.BLACK);
                gc.strokeRect(100,130,80,40);
                gc.fillText("SEVERE THUNDERSTORM",190,155);
                gc.setFill(Color.GOLD);
                gc.fillRect(100,130,80,40);

            }
            else if (entry.equals("SPECIAL MARINE")) {
                gc.setFill(Color.BLACK);
                gc.strokeRect(100,210,80,40);
                gc.fillText("SPECIAL MARINE",190,235);
                gc.setFill(Color.DARKORANGE);
                gc.fillRect(100,210,80,40);

            }
            else if (entry.equals("TORNADO")) {
                gc.setFill(Color.BLACK);
                gc.strokeRect(100,290,80,40);
                gc.fillText("TORNADO",190,315);
                gc.setFill(Color.DARKSALMON);
                gc.fillRect(100,290,80,40);

            }
            else {
                gc.setFill(Color.BLACK);
                //System.out.println("Uhoh gotta a problem here");
            }
            extentAngle = wordCount.get(entry);
            extentAngle = extentAngle / wordCountTotal;
            extentAngle *= 360;
            System.out.println("extentAngle = " + extentAngle);


            //System.out.println("wordCount.get(entry) = " + wordCount.get(entry));
            //System.out.println("wordCountTotal = " + wordCountTotal);
            gc.fillArc(500, 200, 250, 250, startAngle, extentAngle, ArcType.ROUND);
            gc.strokeArc(500, 200, 250, 250, startAngle, extentAngle, ArcType.ROUND);
            startAngle += extentAngle;
            System.out.println("startAngle = " + startAngle);

        }



    }

    private TreeMap<String, Integer> getCount() {
        File inFile = new File("C:\\Users\\Saman\\Desktop\\SoftDevLabs\\lab7\\out\\production\\lab7\\sample\\weatherwarnings-2015.csv");
        String line;
        TreeMap<String, Integer> mymap = new TreeMap<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(inFile));
            while ((line = in.readLine()) != null) {
                String[] data = line.split(",");
                //System.out.println(data[5]);
                if (mymap.containsKey(data[5])) {
                    int newValue = mymap.get(data[5]);
                    newValue++;
                    mymap.put(data[5], newValue);
                }
                else {
                    mymap.put(data[5], 1);
                }
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
        return mymap;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
