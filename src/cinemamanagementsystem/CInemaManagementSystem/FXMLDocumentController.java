/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Administrator
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private AnchorPane sub_form;
    @FXML
    private Button sub_signupBtn;
    @FXML
    private Button sub_loginBtn;
    @FXML
    private AnchorPane login_form;
    @FXML
    private TextField si_username;
    @FXML
    private PasswordField si_password;
    @FXML
    private Button si_loginBtn;
    @FXML
    private AnchorPane signup_form;
    @FXML
    private TextField su_email;
    @FXML
    private PasswordField su_password;
    @FXML
    private Button su_signupBtn;
    @FXML
    private TextField su_username;
    @FXML
    private Button close;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private double x = 0;
    private double y = 0;
    @FXML
    private Button backbtn;
    @FXML
    private Button minimize;
    @FXML
    private ImageView loginImage;
    @FXML
    private ImageView login_image;
    @FXML
    private ImageView signin_image;

    public boolean validEmail() {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher match = pattern.matcher(su_email.getText());
        Alert alert;

        if (match.find() && match.group().equals(su_email.getText())) {

            return true;
        } else {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email");
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    public void login(ActionEvent event) throws SQLException {

        String sql = "SELECT * FROM user WHERE username = ? and password = ?";

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
                    int userID = result.getInt("id");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("userDashboard.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    UserDashboardController dashboardController = loader.getController();
                    dashboardController.setLoggedInUserID(userID);

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

    @FXML
    public void signup() {
        String sql = "INSERT INTO user (email, username, password) VALUES (?, ?, ?)";
        String sqll = "SELECT username FROM user WHERE username = ?";

        connect = database.getConnection();

        try {
            Alert alert;

            if (su_email.getText().isEmpty() || su_username.getText().isEmpty() || su_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else if (su_password.getText().length() < 8 || !su_password.getText().matches("^(?=.*[a-zA-Z])(?=.*\\d).{8,}$")) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password must be at least 8 characters long and contain at least one letter and one number");
                alert.showAndWait();
            } else if (validEmail()) {
                prepare = connect.prepareStatement(sqll);
                prepare.setString(1, su_username.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(su_username.getText() + " already exists!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, su_email.getText());
                    prepare.setString(2, su_username.getText());
                    prepare.setString(3, su_password.getText());
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully created new account!");
                    alert.showAndWait();

                    su_email.setText("");
                    su_username.setText("");
                    su_password.setText("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void signupSlider() {
        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sub_form);
        slider1.setToX(370);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();

        slider1.setOnFinished((event) -> {
            sub_signupBtn.setVisible(false);
            sub_loginBtn.setVisible(true);
            signin_image.setVisible(true);
            login_image.setVisible(false);
        });
    }

    @FXML
    public void loginSlider() {
        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sub_form);
        slider1.setToX(0);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();

        slider1.setOnFinished((event) -> {
            sub_signupBtn.setVisible(true);
            sub_loginBtn.setVisible(false);
            signin_image.setVisible(false);
            login_image.setVisible(true);
        });
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

    @FXML
    public void minimize() {
        Stage stage = (Stage) signup_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

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

}
