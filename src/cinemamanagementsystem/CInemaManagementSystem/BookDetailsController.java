/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import cinemamanagementsystem.CInemaManagementSystem.UserDashboardController.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class BookDetailsController implements Initializable {

    @FXML
    private AnchorPane ticketPaper;
    @FXML
    private Button backBtn;
    @FXML
    private Button printTicket;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button bookBtn;
    @FXML
    private Label ticketID;
    @FXML
    private Label bookDate;
    private Label name;
    @FXML
    private Label movieName;
    @FXML
    private Label duration;
    @FXML
    private Label screeningDate;
    @FXML
    private Label nextScreeningDate;
    @FXML
    private Label time;
    @FXML
    private TextField quantity;
    @FXML
    private Label total;
    @FXML
    private Label ticketPrice;
    @FXML
    private Label idMovie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Add a listener to the text property of the quantityTextField
        quantity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateTotal();
            }
        });
        bookdate();
    }

    private double x = 0;
    private double y = 0;

    @FXML
    private ImageView snapshotImageView;
    private final FileChooser fileChooser = new FileChooser();

    @FXML
    private void backBtn(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("userDashboard.fxml"));
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void saveWindowSnapshot() {
        // Get the current stage
        Stage stage = (Stage) ticketPaper.getScene().getWindow();

        // Set the file path for saving the snapshot in the user's download folder
        String downloadsPath = System.getProperty("user.home") + File.separator + "Downloads";
        String filePath = downloadsPath + File.separator + "snapshot.png";

        // Create a snapshot of the ticketPaper AnchorPane
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setTransform(javafx.scene.transform.Transform.scale(1, 1));
        WritableImage snapshot = ticketPaper.snapshot(parameters, null);

        // Write the snapshot image to the file using ImageIO
        File file = new File(filePath);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
            System.out.println("Snapshot saved to: " + filePath);

            // Show alert indicating successful save
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Snapshot Saved");
            alert.setHeaderText(null);
            alert.setContentText("The ticket snapshot has been saved to the Downloads folder.");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void close(ActionEvent event) {
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
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) ticketPaper.getScene().getWindow();
        stage.setIconified(true);
    }

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private ObservableList<movieListData> movieListTicket;

    //pangshow di kasama ung iba kasi magkaiba sila database table//
    public void ticket(List<movieListData> matchedMovies) {
        if (matchedMovies != null && !matchedMovies.isEmpty()) {
            movieListData movData = matchedMovies.get(0);
            idMovie.setText(movData.getIdMovies());
            movieName.setText(movData.getTitle());
            duration.setText(movData.getDuration());
            screeningDate.setText(movData.getViewingDate());
            nextScreeningDate.setText(movData.getNextViewingDate());
            time.setText(movData.getTime());
            ticketPrice.setText(Double.toString(movData.getPrice()));
        } else {
            // Handle the case where no matched movies are found
            movieName.setText("No Matched Movies");
            duration.setText("");
        }
    }

    public void bookdate() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time as a string
        String formattedDateTime = now.format(formatter);

        // Set the formatted date and time as the text of the label
        bookDate.setText(formattedDateTime);
    }

    private void updateTotal() {
        String quantityText = quantity.getText();
        int Quantity = quantityText.isEmpty() ? 0 : Integer.parseInt(quantityText);

        if (Quantity > 5) {
            // Quantity exceeds the limit, show an alert message
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Quantity Exceeded");
            alert.setHeaderText(null);
            alert.setContentText("The maximum quantity allowed is 5");
            alert.showAndWait();

            // Reset the quantity to the maximum allowed value
            Quantity = 5;
            quantity.setText(String.valueOf(Quantity));
        }

        String priceText = ticketPrice.getText();
        double price = priceText.isEmpty() ? 0.0 : Double.parseDouble(priceText);

        double Total = Quantity * price;
        total.setText(String.valueOf(Total));
    }

    int userid = 1;

    private int ticketid; // Declare the ticketid variable at the class level

    public void getTicketId() {
        String sql = "SELECT TicketID FROM bookings ORDER BY TicketID DESC LIMIT 1";

        try {
            Connection connection = database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int latestTicketId = resultSet.getInt("TicketID");
                int nextTicketId = latestTicketId + 1;
                ticketid = nextTicketId;
            } else {
                ticketid = 1; // Start with ticket ID 1 if no records exist
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Integer loggedInUserID;

    public void setLoggedInUserID(Integer userID) {
        UserSession.setLoggedInUserID(userID);
    }

    @FXML
    private void bookBtn(ActionEvent event) {
        String sql = "INSERT INTO bookings (UserID, IDMovies, MovieName, ViewingDate, Quantity, Price, Total, BookingDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Alert alert;

            if (quantity.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the Quantity");
                alert.showAndWait();
            } else {
                // Establish database connection
                Connection connect = database.getConnection();
                PreparedStatement prepare = connect.prepareStatement(sql);

                // Set parameter values for the prepared statement
                prepare.setInt(1, UserSession.getLoggedInUserID());
                prepare.setString(2, idMovie.getText());
                prepare.setString(3, movieName.getText());
                prepare.setString(4, screeningDate.getText());
                prepare.setString(5, quantity.getText());
                prepare.setString(6, ticketPrice.getText());
                prepare.setString(7, total.getText());
                prepare.setString(8, bookDate.getText());

                // Execute the SQL statement
                int rowsAffected = prepare.executeUpdate();

                if (rowsAffected > 0) {
                    // Call saveWindowSnapshot() function
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Booked!");
                    alert.showAndWait();
                    saveWindowSnapshot();

                    // Proceed to ThanksWindow.fxml
                    Parent root = FXMLLoader.load(getClass().getResource("ThanksWindow.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    stage.setWidth(554);
                    stage.setHeight(435);

                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
                    double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
                    stage.setX(centerX - 277);
                    stage.setY(centerY - 217.5);

                    Scene scene = new Scene(root, 554, 435);

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
                    // Display error message if insertion fails
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Error in inserting data into the database");
                    alert.showAndWait();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
