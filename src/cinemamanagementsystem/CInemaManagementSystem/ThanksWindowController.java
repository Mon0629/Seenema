/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import cinemamanagementsystem.CInemaManagementSystem.UserDashboardController.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class ThanksWindowController implements Initializable {

    @FXML
    private Button returnHome;
    @FXML
    private Button BookingHisto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private double x = 0;
    private double y = 0;

    @FXML
    private void returnHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userDashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            UserDashboardController dashboardController = loader.getController();
            dashboardController.setLoggedInUserID(UserSession.getLoggedInUserID());

            // Set the stage size before showing the stage
            ((Node) (event.getSource())).getScene().getWindow().hide();
            stage.setWidth(1126);
            stage.setHeight(654);

            // Center the stage on the screen
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
            double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
            stage.setX(centerX - 558.5);
            stage.setY(centerY - 327);
            Scene scene = new Scene(root, 1126, 654);

            stage.setScene(scene);
            stage.show();
            root.setOnMousePressed((mouseEvent) -> {
                x = mouseEvent.getSceneX();
                y = mouseEvent.getSceneY();
            });

            root.setOnMouseDragged((mouseEvent) -> {
                stage.setX(mouseEvent.getScreenX() - x);
                stage.setY(mouseEvent.getScreenY() - y);

                stage.setOpacity(.8);
            });

            root.setOnMouseReleased((mouseEvent) -> {
                stage.setOpacity(1);
            });

            // Get the controller of the userDashboard.fxml
            

            // Call the method in the controller to navigate to the home section
            dashboardController.navigateToHome();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBookings(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userDashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            UserDashboardController dashboardController = loader.getController();
            dashboardController.setLoggedInUserID(UserSession.getLoggedInUserID());

            // Set the stage size before showing the stage
            ((Node) (event.getSource())).getScene().getWindow().hide();
            stage.setWidth(1126);
            stage.setHeight(654);

            // Center the stage on the screen
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
            double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
            stage.setX(centerX - 558.5);
            stage.setY(centerY - 327);
            Scene scene = new Scene(root, 1126, 654);

            stage.setScene(scene);
            stage.show();
            root.setOnMousePressed((mouseEvent) -> {
                x = mouseEvent.getSceneX();
                y = mouseEvent.getSceneY();
            });

            root.setOnMouseDragged((mouseEvent) -> {
                stage.setX(mouseEvent.getScreenX() - x);
                stage.setY(mouseEvent.getScreenY() - y);

                stage.setOpacity(.8);
            });

            root.setOnMouseReleased((mouseEvent) -> {
                stage.setOpacity(1);
            });

            // Get the controller of the userDashboard.fxml
          

            // Call the method in the controller to navigate to the home section
            dashboardController.navigateToBookingForm();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   
    
    
    

}
