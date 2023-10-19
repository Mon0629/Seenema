/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Administrator
 */
public class Main extends Application {

    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) throws Exception {

        stage.initStyle(StageStyle.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("FirstWindow.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("firstWindow.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(551);
        stage.setHeight(393);

        root.setOnMousePressed((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

            stage.setOpacity(.8);
        });

        root.setOnMouseReleased((event) -> {
            stage.setOpacity(1);
        });

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - 551) / 2; 
        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - 393) / 2;
        stage.setX(centerX);
        stage.setY(centerY);

        stage.show();
        

    }

    public static void main(String[] args) {
        launch(args);
    }

}
