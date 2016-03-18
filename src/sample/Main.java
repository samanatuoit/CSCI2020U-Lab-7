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
        Scene scene = new Scene(root, 1000, 700);
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
        System.out.println(wordCountTotal);

        //for (int i=0; i<Integer.parseInt(wordCount.keySet();i++) {
            //System.out.println(i);
        //}
        double startAngle = 0;
        double extentAngle = 0;
        //System.out.println(wordCount.keySet());
        for (String entry : wordCount.keySet()) {

            if (entry.equals("FLASH FLOOD")) {
                gc.setFill(Color.AQUA);
            }
            else if (entry.equals("SEVERE THUNDERSTORM")) {
                gc.setFill(Color.GOLD);
            }
            else if (entry.equals("SPECIAL MARINE")) {
                gc.setFill(Color.GOLD);
            }
            else if (entry.equals("TORNADO")) {
                gc.setFill(Color.DARKSALMON);
            }
            else {
                gc.setFill(Color.BLACK);
            }
            extentAngle = (wordCount.get(entry) / wordCountTotal) * 360;
            gc.fillArc(500, 350, 250, 250, startAngle, extentAngle, ArcType.ROUND);
            startAngle = extentAngle;
            System.out.println("Yo");


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
