package cinemamanagementsystem.CInemaManagementSystem;

import com.mysql.cj.jdbc.Blob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author John Paul Uy
 */
public class MovieCardController implements Initializable {

    @FXML
    private Label moviecard_name;

    @FXML
    private ImageView moviecard_poster;

    @FXML
    private Label moviecard_rating;

    @FXML
    private Label movieDirectorLabel;

    @FXML
    private Label movieIdLabel;

    @FXML
    private Label movieTitleLabel;

    @FXML
    private TableView<Movie> booking_table;
    @FXML
    private TableColumn<Movie, String> director_column;
    @FXML
    private TableColumn<Movie, String> title_column;

    /**
     * Initializes the controller class.
     */
    private movieListData movData;
    private Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private Alert alert;

    public void setData(movieListData movData) throws SQLException {
        this.movData = movData;

        moviecard_name.setText(movData.getTitle());
        moviecard_rating.setText(movData.getRating());

        Blob imageBlob = movData.getImage();
        byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        Image image = new Image(bis, 129, 173, false, true);
        moviecard_poster.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    // view movie details
@FXML
private void bookingWindow(MouseEvent event) throws SQLException, IOException {
    // Get the current stage
    Stage currentStage = (Stage) moviecard_name.getScene().getWindow();

    // Get the existing instance of the UserDashboardController
    FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDashboard.fxml"));
    Parent root = loader.load();
    UserDashboardController dashboardController = loader.getController();

    // Create a movieListData object with the selected movie data
    movieListData selectedMovie = new movieListData(
            movData.getSypnosis(),
            movData.getGenre(),
            movData.getDirector(),
            movData.getCast(),
            movData.getRating(),
            movData.getImageBG()
    );

    // Pass the selected movie data to the UserDashboardController
    dashboardController.showmovie(selectedMovie);

    // Set the new scene on the stage
    Scene dashboardScene = new Scene(root);
    currentStage.setScene(dashboardScene);

    // Show the stage with the updated user dashboard scene
    currentStage.show();
}
}
