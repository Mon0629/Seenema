/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import com.mysql.cj.jdbc.Blob;
import java.awt.FontMetrics;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;

import java.awt.FontMetrics;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.scene.text.Font;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class UserDashboardController implements Initializable {

    @FXML
    private BorderPane mainForm;
    @FXML
    private Button dashboard_btn;

    private Button movies_btn;
    private Button bookingHistory_btn;
    @FXML
    private Button feedback_btn;
    private Button aboutUs_btn;
    @FXML
    private AnchorPane dashboardFrom;
    @FXML
    private AnchorPane moviesForm;
    @FXML
    private AnchorPane bookingHistoryForm;
    @FXML
    private AnchorPane feedbackForm;
    @FXML
    private AnchorPane aboutusForm;
    @FXML
    private Button close;
    @FXML
    private Button minimize;

    private double x = 0;
    private double y = 0;
    @FXML
    private Button movie_btn;
    @FXML
    private Button aboutus_btn;
    @FXML
    private Button bookings_btn;
    @FXML
    private AnchorPane logout;
    @FXML
    private ComboBox reactionBox;
    @FXML
    private CheckBox cbOverall;
    @FXML
    private CheckBox cbCustomer;
    @FXML
    private CheckBox cbTransparency;
    @FXML
    private CheckBox cbService;
    @FXML
    private CheckBox cbPickup;
    @FXML
    private TextField fieldSuggestion;
    @FXML
    private ScrollPane book_scrollpane;
    @FXML
    private GridPane book_gridpane;
    @FXML
    private ImageView imageshow;

    /**
     * Initializes the controller class.
     */
    private ObservableList<movieListData> cardListData = FXCollections.observableArrayList();
    @FXML
    private Label genreMovie;
    @FXML
    private Label ratingMovie;
    @FXML
    private Label sypnosisMovie;

    @FXML
    private TableView<bookinglist> bookingtable;
    @FXML
    private TableColumn<bookinglist, String> bookingID;
    @FXML
    private TableColumn<bookinglist, String> bookingmovieid;
    @FXML
    private TableColumn<bookinglist, String> bookingmoviename;
    @FXML
    private TableColumn<bookinglist, String> bookingviewdate;
    @FXML
    private TableColumn<bookinglist, String> bookingquantity;
    @FXML
    private TableColumn<bookinglist, String> bookingprice;
    @FXML
    private TableColumn<bookinglist, String> bookingtotal;
    @FXML
    private Label useridlabel;
    @FXML
    private TableColumn<bookinglist, LocalDateTime> bookingDate;
    @FXML
    private Button help_btn;
    @FXML
    private Button aboutBtn;
    @FXML
    private Button bookNowBtn;
    @FXML
    private Button CancelBtn;
    @FXML
    private Button nextBtn;
    @FXML
    private Button bookMovie14;
    @FXML
    private Label directorMovie;
    @FXML
    private Label castMovie;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button filterDrama;
    @FXML
    private Button filterThriller;
    @FXML
    private Button filterTicket;
    @FXML
    private Button filterAll;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reactionBox.setItems(list);
        menuDisplayCard();
        addbookingshowlist();
    }

    public class UserSession {

        private static Integer loggedInUserID;

        public static Integer getLoggedInUserID() {
            return loggedInUserID;
        }

        public static void setLoggedInUserID(Integer userID) {
            loggedInUserID = userID;
        }
    }
    


    private Integer loggedInUserID;

    //for book button sa movie//
    @FXML
    public void bookBtn(ActionEvent event) throws SQLException {
        String selectedSynopsis = sypnosisMovie.getText();

        // Search for movies in the database based on the synopsis
        String sql = "SELECT * FROM movielist WHERE sypnosis LIKE ?";
        PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, "%" + selectedSynopsis + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<movieListData> matchedMovies = new ArrayList<>();

        // Retrieve the matched movie data from the result set
        while (resultSet.next()) {
            String IdMovies = resultSet.getString("id_movies");
            String title = resultSet.getString("title");
            String duration = resultSet.getString("duration");
            String viewingDate = resultSet.getString("ViewingDate");
            String nextViewingDate = resultSet.getString("NextViewingDate");
            String time = resultSet.getString("time");
            Double price = resultSet.getDouble("price");

            movieListData matchedMovie = new movieListData(IdMovies, title, duration, viewingDate, nextViewingDate, time, price);
            matchedMovies.add(matchedMovie);

        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookDetails.fxml"));
            Parent root = loader.load();
            BookDetailsController bookDetailsController = loader.getController();

            bookDetailsController.setLoggedInUserID(UserSession.getLoggedInUserID());
            // Pass the matched movie data to the ticket() method
            bookDetailsController.ticket(matchedMovies);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            ((Node) (event.getSource())).getScene().getWindow().hide();
            stage.setWidth(677);
            stage.setHeight(523);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
            double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
            stage.setX(centerX - 338.5);
            stage.setY(centerY - 261.5);

            Scene scene = new Scene(root, 677, 523);

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
    private Button filterAction;

//***************this is is para ma set automatically yung label ng user id ayon sa user id na corresponding sa ginamit na username***
    public void setLoggedInUserID(Integer userID) {
        UserSession.setLoggedInUserID(userID);
        // Set the user ID in the label
        useridlabel.setText(userID.toString());
        addbookingshowlist();
    }
    
    

// eto po is dun sa table ng booking*********************************
    public ObservableList<bookinglist> bookingList() {
        ObservableList<bookinglist> listbookings = FXCollections.observableArrayList();
        String usedid = useridlabel.getText();
        int pars = Integer.parseInt(usedid);
        String sql = "SELECT * FROM bookings JOIN user ON UserID = id WHERE id = ?";
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, pars); // Set the value of the placeholder
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                bookinglist bl = new bookinglist();
                bl.setTicketId(result.getString("TicketID"));
                bl.setIDMovies(result.getString("IDMovies"));
                bl.setMovieName(result.getString("MovieName"));
                bl.setViewingDate(result.getString("ViewingDate"));
                bl.setQuantity(result.getString("quantity"));
                bl.setPrice(result.getString("price"));
                bl.setTotal(result.getString("total"));
                LocalDateTime bookingDate = result.getTimestamp("BookingDate").toLocalDateTime();
                bl.setBookingDate(bookingDate);
                listbookings.add(bl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listbookings;
    }

    private ObservableList<bookinglist> showbookinglist;

    private void addbookingshowlist() {
        try {
            showbookinglist = bookingList();
            bookingID.setCellValueFactory(f -> f.getValue().bookinglistTicketID());
            bookingmovieid.setCellValueFactory(f -> f.getValue().bookinglistIDMovies());
            bookingmoviename.setCellValueFactory(f -> f.getValue().bookinglistMovieName());
            bookingviewdate.setCellValueFactory(f -> f.getValue().bookinglistViewingDate());
            bookingquantity.setCellValueFactory(f -> f.getValue().bookinglistQuantity());
            bookingprice.setCellValueFactory(f -> f.getValue().bookinglistPrice());
            bookingtotal.setCellValueFactory(f -> f.getValue().bookinglistTotal());
            bookingDate.setCellValueFactory(f -> f.getValue().BookingDateProperty());

            //to handle the row selection
            bookingtable.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) { // Detect single click
                    handleRowSelection();
                }
            });

            bookingtable.setItems(showbookinglist);
        } catch (Exception e) {
            e.printStackTrace(); // Print the error message to the console
        }

    }

    //eto po yung handle row selection
    private void handleRowSelection() {
        bookinglist selectedBooking = bookingtable.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            // Enable the delete button when a row is selected
            CancelBtn.setDisable(false);
        } else {
            // Disable the delete button when no row is selected
            CancelBtn.setDisable(true);
        }
    }

//to delete the row in data base
    private void deleteBooking(bookinglist booking) {
        String sql = "DELETE FROM bookings WHERE TicketID = ?";
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, booking.getTicketID());
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// this is when you clicked the button
    private void onDeleteButtonClicked(ActionEvent event) {
        bookinglist selectedBooking = bookingtable.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            // Prompt user for confirmation before deleting the row
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Booking");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this booking?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                deleteBooking(selectedBooking); // Delete the selected row from the database
                showbookinglist.remove(selectedBooking); // Remove the row from the TableView
            }
        }
    }

    private double scrollPosition = 0.0;

    public void showmovie(movieListData movData) {
        Image image = convertBlobToImage(movData.getImageBG());
        imageshow.setImage(image);

        // Set the maximum width to 398px
        int maxWidth = 441;

        // Set the maximum width to 300px for the synopsis text
        int synopsisMaxWidth = 441;
        sypnosisMovie.setPrefWidth(synopsisMaxWidth);
        sypnosisMovie.setWrapText(true);
        sypnosisMovie.setText(movData.getSypnosis());
        genreMovie.setText(movData.getGenre());
        ratingMovie.setText(movData.getRating());
        directorMovie.setText(movData.getDirector());
        castMovie.setText(movData.getCast());

        // Store the current scroll position
        scrollPosition = book_scrollpane.getVvalue();
    }

    private static final double SCALE_FACTOR = 1.2;

    @FXML
    public void showNextMovie() {
        if (cardListData.isEmpty()) {
            return; // No movies in the database
        }

        currentMovieIndex++;
        if (currentMovieIndex >= cardListData.size()) {
            currentMovieIndex = 0; // Wrap around to the start
        }

        movieListData nextMovie = cardListData.get(currentMovieIndex);
        showmovie(nextMovie);

        // Calculate the scroll position based on the currentMovieIndex
        double scrollPosition = (double) currentMovieIndex / (cardListData.size() - 1);
        book_scrollpane.setHvalue(scrollPosition);

        // Scale the next grid within the ScrollPane
        for (Node gridNode : book_gridpane.getChildren()) {
            double scale = (gridNode == book_gridpane.getChildren().get(currentMovieIndex)) ? SCALE_FACTOR : 1.0;
            gridNode.setScaleX(scale);
            gridNode.setScaleY(scale);
        }
    }

    private Image convertBlobToImage(Blob blob) {
        try (InputStream inputStream = blob.getBinaryStream()) {
            return new Image(inputStream);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int currentMovieIndex = 0;

    public ObservableList<movieListData> menuGetData() {

        String sql = "SELECT * FROM movielist";

        ObservableList<movieListData> listData = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                Integer id = result.getInt("id");
                String idMovies = result.getString("id_movies");
                String title = result.getString("title");
                String genre = result.getString("genre");
                Double price = result.getDouble("price");
                String sypnosis = result.getString("sypnosis");
                String duration = result.getString("duration");
                String time = result.getString("time");
                String director = result.getString("director");
                String cast = result.getString("cast");
                String rating = result.getString("rating");
                Blob image = (Blob) result.getBlob("image");
                Blob imageBG = (Blob) result.getBlob("imageBG");
                String viewingDate = result.getString("ViewingDate");
                String nextViewingDate = result.getString("nextViewingDate");

                movieListData movData = new movieListData(id, idMovies, title, genre, price, sypnosis,
                        duration, time, director, cast, rating, image, imageBG, viewingDate, nextViewingDate);

                listData.add(movData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources (result, prepare, connect) if needed
        }

        return listData;
    }

    public void menuDisplayCard() {

        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        book_gridpane.getChildren().clear();
        book_gridpane.getRowConstraints().clear();
        book_gridpane.getColumnConstraints().clear();

        for (int q = 0; q < cardListData.size(); q++) {

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("movieCard.fxml"));
                AnchorPane pane = load.load();
                MovieCardController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                book_gridpane.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        currentMovieIndex = 0;
        if (!cardListData.isEmpty()) {
            movieListData firstMovie = cardListData.get(0);
            showmovie(firstMovie);
        }
    }

    @FXML
    private void logout(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save before loging out?");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully logged out");

            try {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                ((Node) (event.getSource())).getScene().getWindow().hide();
                newStage.setWidth(740);
                newStage.setHeight(470);

                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
                double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
                newStage.setX(centerX - 370);
                newStage.setY(centerY - 235);

                Scene scene = new Scene(root, 740, 470);

                newStage.setScene(scene);
                newStage.show();

                root.setOnMousePressed((mouseEvent) -> {
                    x = mouseEvent.getSceneX();
                    y = mouseEvent.getSceneY();
                });

                root.setOnMouseDragged((mouseEvent) -> {
                    newStage.setX(mouseEvent.getScreenX() - x);
                    newStage.setY(mouseEvent.getScreenY() - y);

                    newStage.setOpacity(.8);
                });

                root.setOnMouseReleased((mouseEvent) -> {
                    newStage.setOpacity(1);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
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
        Stage stage = (Stage) mainForm.getScene().getWindow();
        stage.setIconified(true);
    }

    private Button lastClickedButton = null;

    @FXML
    public void SwitchForm(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        if (clickedButton == lastClickedButton) {
            // Ignore the click if the same button was clicked twice in a row
            return;
        }

        if (clickedButton == dashboard_btn) {
            // ... (rest of the code remains the same)
        }

        // Update the last clicked button
        lastClickedButton = clickedButton;
        if (clickedButton == dashboard_btn) {
            setButtonColor(dashboard_btn, true);
            setButtonColor(movie_btn, false);
            setButtonColor(bookings_btn, false);
            setButtonColor(feedback_btn, false);
            setButtonColor(aboutus_btn, false);

            dashboardFrom.setVisible(true);
            moviesForm.setVisible(false);
            bookingHistoryForm.setVisible(false);
            feedbackForm.setVisible(false);
            aboutusForm.setVisible(false);

        } else if (clickedButton == movie_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(movie_btn, true);
            setButtonColor(bookings_btn, false);
            setButtonColor(feedback_btn, false);
            setButtonColor(aboutus_btn, false);

            dashboardFrom.setVisible(false);
            moviesForm.setVisible(true);
            bookingHistoryForm.setVisible(false);
            feedbackForm.setVisible(false);
            aboutusForm.setVisible(false);

        } else if (clickedButton == bookings_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(movie_btn, false);
            setButtonColor(bookings_btn, true);
            setButtonColor(feedback_btn, false);
            setButtonColor(aboutus_btn, false);

            dashboardFrom.setVisible(false);
            moviesForm.setVisible(false);
            bookingHistoryForm.setVisible(true);
            feedbackForm.setVisible(false);
            aboutusForm.setVisible(false);

        } else if (clickedButton == feedback_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(movie_btn, false);
            setButtonColor(bookings_btn, false);
            setButtonColor(feedback_btn, true);
            setButtonColor(aboutus_btn, false);

            dashboardFrom.setVisible(false);
            moviesForm.setVisible(false);
            bookingHistoryForm.setVisible(false);
            feedbackForm.setVisible(true);
            aboutusForm.setVisible(false);

        } else if (clickedButton == aboutus_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(movie_btn, false);
            setButtonColor(bookings_btn, false);
            setButtonColor(feedback_btn, false);
            setButtonColor(aboutus_btn, true);

            dashboardFrom.setVisible(false);
            moviesForm.setVisible(false);
            bookingHistoryForm.setVisible(false);
            feedbackForm.setVisible(false);
            aboutusForm.setVisible(true);

        } else if (clickedButton == help_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(movie_btn, false);
            setButtonColor(bookings_btn, false);
            setButtonColor(feedback_btn, true);
            setButtonColor(aboutus_btn, false);

            dashboardFrom.setVisible(false);
            moviesForm.setVisible(false);
            bookingHistoryForm.setVisible(false);
            feedbackForm.setVisible(true);
            aboutusForm.setVisible(false);
        } else if (clickedButton == bookNowBtn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(movie_btn, true);
            setButtonColor(bookings_btn, false);
            setButtonColor(feedback_btn, false);
            setButtonColor(aboutus_btn, false);

            dashboardFrom.setVisible(false);
            moviesForm.setVisible(true);
            bookingHistoryForm.setVisible(false);
            feedbackForm.setVisible(false);
            aboutusForm.setVisible(false);
        } else if (clickedButton == aboutBtn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(movie_btn, false);
            setButtonColor(bookings_btn, false);
            setButtonColor(feedback_btn, false);
            setButtonColor(aboutus_btn, true);

            dashboardFrom.setVisible(false);
            moviesForm.setVisible(false);
            bookingHistoryForm.setVisible(false);
            feedbackForm.setVisible(false);
            aboutusForm.setVisible(true);
        }
    }

    public void navigateToHome() {
        setButtonColor(dashboard_btn, true);
        setButtonColor(movie_btn, false);
        setButtonColor(bookings_btn, false);
        setButtonColor(feedback_btn, false);
        setButtonColor(aboutus_btn, false);

        dashboardFrom.setVisible(true);
        moviesForm.setVisible(false);
        bookingHistoryForm.setVisible(false);
        feedbackForm.setVisible(false);
        aboutusForm.setVisible(false);
    }

    public void navigateToBookingForm() {
        setButtonColor(dashboard_btn, false);
        setButtonColor(movie_btn, false);
        setButtonColor(bookings_btn, true);
        setButtonColor(feedback_btn, false);
        setButtonColor(aboutus_btn, false);

        dashboardFrom.setVisible(false);
        moviesForm.setVisible(false);
        bookingHistoryForm.setVisible(true);
        feedbackForm.setVisible(false);
        aboutusForm.setVisible(false);
    }

    private void setButtonColor(Button button, boolean isSelected) {
        if (isSelected) {
            button.getStyleClass().add("selected-button");
        } else {
            button.getStyleClass().remove("selected-button");
        }
    }

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    ObservableList<String> list = FXCollections.observableArrayList("Happy", "Meh", "Yuck");

    @FXML
    public void submitBtn() throws SQLException {

        connect = database.getConnection();

        // Get selected checkbox values
        String overall = cbOverall.isSelected() ? "Yes" : "No";
        String customer = cbCustomer.isSelected() ? "Yes" : "No";
        String transparency = cbTransparency.isSelected() ? "Yes" : "No";
        String service = cbService.isSelected() ? "Yes" : "No";
        String pickup = cbPickup.isSelected() ? "Yes" : "No";

        // Get textfield value
        String suggestion = fieldSuggestion.getText();

        // Get combobox value
        String selectedValue = (String) reactionBox.getValue();

        // Prepare the SQL statement to insert the data into the database
        String sql = "INSERT INTO feedback (overall, customer, transparency, service, pickup, suggestion, selectedValue) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connect.prepareStatement(sql)) {
            statement.setString(1, overall);
            statement.setString(2, customer);
            statement.setString(3, transparency);
            statement.setString(4, service);
            statement.setString(5, pickup);
            statement.setString(6, suggestion);
            statement.setString(7, selectedValue);

            // Execute the SQL statement to insert the data into the database
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Data inserted successfully!");
                alert.showAndWait();

                clearFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to insert data!");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
        } finally {
            // Close the database connection and statement objects
            connect.close();
        }
    }

    private void clearFields() {
        cbOverall.setSelected(false);
        cbCustomer.setSelected(false);
        cbTransparency.setSelected(false);
        cbService.setSelected(false);
        cbPickup.setSelected(false);
        fieldSuggestion.clear();
        reactionBox.setValue(null);
    }

    private Button lastClickedGenre = null;

    @FXML
    public void SwitchGenre(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        if (clickedButton == lastClickedGenre) {
            // Ignore the click if the same button was clicked twice in a row
            return;
        }

        if (clickedButton == filterAll) {
            // ... (rest of the code remains the same)
        }

        // Update the last clicked button
        lastClickedGenre = clickedButton;
        if (clickedButton == filterAll) {
            setButtonColor(filterAll, true);
            setButtonColor(filterAction, false);
            setButtonColor(filterDrama, false);
            setButtonColor(filterThriller, false);
            setButtonColor(filterTicket, false);

            cardListData.clear();
            cardListData.addAll(menuGetData());

            int row = 0;
            int column = 0;

            book_gridpane.getChildren().clear();
            book_gridpane.getRowConstraints().clear();
            book_gridpane.getColumnConstraints().clear();

            for (int q = 0; q < cardListData.size(); q++) {

                try {
                    FXMLLoader load = new FXMLLoader();
                    load.setLocation(getClass().getResource("movieCard.fxml"));
                    AnchorPane pane = load.load();
                    MovieCardController cardC = load.getController();
                    cardC.setData(cardListData.get(q));

                    book_gridpane.add(pane, column++, row);

                    GridPane.setMargin(pane, new Insets(10));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            currentMovieIndex = 0;
            if (!cardListData.isEmpty()) {
                movieListData firstMovie = cardListData.get(0);
                showmovie(firstMovie);
            }

        } else if (clickedButton == filterAction) {
            setButtonColor(filterAll, false);
            setButtonColor(filterAction, true);
            setButtonColor(filterDrama, false);
            setButtonColor(filterThriller, false);
            setButtonColor(filterTicket, false);

            String genreToFilter = "action";

            List<movieListData> filteredData = menuGetData().stream()
                    .filter(movie -> {
                        String genres = movie.getGenre();
                        if (genres != null) {
                            String[] genreArray = genres.split(",");
                            for (String genre : genreArray) {
                                if (genre.trim().equalsIgnoreCase(genreToFilter)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());

            cardListData.clear();
            cardListData.addAll(filteredData);

            // Clear the grid pane and re-populate it with filtered movie cards
            book_gridpane.getChildren().clear();
            book_gridpane.getRowConstraints().clear();
            book_gridpane.getColumnConstraints().clear();

            int row = 0;
            int column = 0;

            for (int q = 0; q < cardListData.size(); q++) {
                try {
                    FXMLLoader load = new FXMLLoader();
                    load.setLocation(getClass().getResource("movieCard.fxml"));
                    AnchorPane pane = load.load();
                    MovieCardController cardC = load.getController();
                    cardC.setData(cardListData.get(q));

                    book_gridpane.add(pane, column++, row);

                    GridPane.setMargin(pane, new Insets(10));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else if (clickedButton == filterDrama) {
            setButtonColor(filterAll, false);
            setButtonColor(filterAction, false);
            setButtonColor(filterDrama, true);
            setButtonColor(filterThriller, false);
            setButtonColor(filterTicket, false);

            String genreToFilter = "drama";

            List<movieListData> filteredData = menuGetData().stream()
                    .filter(movie -> {
                        String genres = movie.getGenre();
                        if (genres != null) {
                            String[] genreArray = genres.split(",");
                            for (String genre : genreArray) {
                                if (genre.trim().equalsIgnoreCase(genreToFilter)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());

            cardListData.clear();
            cardListData.addAll(filteredData);

            // Clear the grid pane and re-populate it with filtered movie cards
            book_gridpane.getChildren().clear();
            book_gridpane.getRowConstraints().clear();
            book_gridpane.getColumnConstraints().clear();

            int row = 0;
            int column = 0;

            for (int q = 0; q < cardListData.size(); q++) {
                try {
                    FXMLLoader load = new FXMLLoader();
                    load.setLocation(getClass().getResource("movieCard.fxml"));
                    AnchorPane pane = load.load();
                    MovieCardController cardC = load.getController();
                    cardC.setData(cardListData.get(q));

                    book_gridpane.add(pane, column++, row);

                    GridPane.setMargin(pane, new Insets(10));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else if (clickedButton == filterThriller) {
            setButtonColor(filterAll, false);
            setButtonColor(filterAction, false);
            setButtonColor(filterDrama, false);
            setButtonColor(filterThriller, true);
            setButtonColor(filterTicket, false);

            String genreToFilter = "thriller";

            List<movieListData> filteredData = menuGetData().stream()
                    .filter(movie -> {
                        String genres = movie.getGenre();
                        if (genres != null) {
                            String[] genreArray = genres.split(",");
                            for (String genre : genreArray) {
                                if (genre.trim().equalsIgnoreCase(genreToFilter)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());

            cardListData.clear();
            cardListData.addAll(filteredData);

            // Clear the grid pane and re-populate it with filtered movie cards
            book_gridpane.getChildren().clear();
            book_gridpane.getRowConstraints().clear();
            book_gridpane.getColumnConstraints().clear();

            int row = 0;
            int column = 0;

            for (int q = 0; q < cardListData.size(); q++) {
                try {
                    FXMLLoader load = new FXMLLoader();
                    load.setLocation(getClass().getResource("movieCard.fxml"));
                    AnchorPane pane = load.load();
                    MovieCardController cardC = load.getController();
                    cardC.setData(cardListData.get(q));

                    book_gridpane.add(pane, column++, row);

                    GridPane.setMargin(pane, new Insets(10));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else if (clickedButton == filterTicket) {
            setButtonColor(filterAll, false);
            setButtonColor(filterAction, false);
            setButtonColor(filterDrama, false);
            setButtonColor(filterThriller, false);
            setButtonColor(filterTicket, true);

            String genreToFilter = "fantasy";

            List<movieListData> filteredData = menuGetData().stream()
                    .filter(movie -> {
                        String genres = movie.getGenre();
                        if (genres != null) {
                            String[] genreArray = genres.split(",");
                            for (String genre : genreArray) {
                                if (genre.trim().equalsIgnoreCase(genreToFilter)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());

            cardListData.clear();
            cardListData.addAll(filteredData);

            // Clear the grid pane and re-populate it with filtered movie cards
            book_gridpane.getChildren().clear();
            book_gridpane.getRowConstraints().clear();
            book_gridpane.getColumnConstraints().clear();

            int row = 0;
            int column = 0;

            for (int q = 0; q < cardListData.size(); q++) {
                try {
                    FXMLLoader load = new FXMLLoader();
                    load.setLocation(getClass().getResource("movieCard.fxml"));
                    AnchorPane pane = load.load();
                    MovieCardController cardC = load.getController();
                    cardC.setData(cardListData.get(q));

                    book_gridpane.add(pane, column++, row);

                    GridPane.setMargin(pane, new Insets(10));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
