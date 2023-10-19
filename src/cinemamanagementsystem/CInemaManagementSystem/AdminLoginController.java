/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import static com.mysql.cj.util.SaslPrep.prepare;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import static javax.management.remote.JMXConnectorFactory.connect;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class AdminLoginController implements Initializable {

    @FXML
    private Button backbtn;
    @FXML
    private Button minimize;
    @FXML
    private Button close;
    @FXML
    private AnchorPane adminLogin_form;
    @FXML
    private TextField si_username;
    @FXML
    private PasswordField si_password;
    @FXML
    private Button si_loginBtn;

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
    private void back(ActionEvent event) {
        backbtn.getScene().getWindow().hide();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("selectRole.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            ((Node) (event.getSource())).getScene().getWindow().hide();
            stage.setWidth(766);
            stage.setHeight(539);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
            double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
            stage.setX(centerX - 383);
            stage.setY(centerY - 269.5);

            Scene scene = new Scene(root, 766, 539);

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

        } catch (Exception e) {
        }
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) adminLogin_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void close(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save before exiting?");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully logged out");
            stage.close();
        }
    }

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    private void login(ActionEvent event) {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";

        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, si_username.getText());
            prepare.setString(2, si_password.getText());
            result = prepare.executeQuery();

            Alert alert;

            if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    Parent root = FXMLLoader.load(getClass().getResource("adminDashboard.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    stage.setWidth(1126);
                    stage.setHeight(654);

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

                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }

            }

        } catch (Exception e) {

        }
    }

}
