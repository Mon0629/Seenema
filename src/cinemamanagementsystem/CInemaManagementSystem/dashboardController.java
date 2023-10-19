/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableRow;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author Administrator
 */
public class dashboardController implements Initializable {

    private double x = 0;
    private double y = 0;
    @FXML
    private BorderPane main_form;
    @FXML
    private Button accounts_btn;
    @FXML
    private Button movies_btn;
    @FXML
    private Button bookings_btn;
    @FXML
    private Button about_btn;
    @FXML
    private Button logout;
    @FXML
    private Button dashboard_btn;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private Label totalTicketSold;
    @FXML
    private Label totalIncome;
    @FXML
    private BarChart<String, Number> accounts_chart;
    @FXML
    private LineChart<?, ?> itemSold_chart;
    @FXML
    private AnchorPane account_form;
    @FXML
    private TextField account_search;
    @FXML
    private TableView<GetAccount> table;
    @FXML
    private TableColumn<GetAccount, LocalDateTime> DateTimeColmn;
    @FXML
    private TableColumn<GetAccount, String> IDColmn;
    @FXML
    private TableColumn<GetAccount, String> EmailColmn;
    @FXML
    private TableColumn<GetAccount, String> UsernameColmn;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private AnchorPane movies_form;
    @FXML
    private AnchorPane bookings_form;
    @FXML
    private AnchorPane about_form;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button AddBtn;
    @FXML
    private ComboBox<String> ChooseRate;
    @FXML
    private ComboBox<String> ChooseTime;
    @FXML
    private Button ClearBtn;
    @FXML
    private Button DeleteBtn;
    @FXML
    private Button ImportBtn;
    @FXML
    private ImageView ImportImageView;
    @FXML
    private Button UpdateBtn;
    @FXML
    private TableColumn<movieListData, String> movies_col_Cast;
    @FXML
    private TableColumn<movieListData, String> movies_col_Director;
    @FXML
    private TableColumn<movieListData, String> movies_col_Duration;
    @FXML
    private TableColumn<movieListData, String> movies_col_Genre;
    @FXML
    private TableColumn<movieListData, String> movies_col_IDMovies;
    @FXML
    private TableColumn<movieListData, String> movies_col_Rating;
    @FXML
    private TableColumn<movieListData, String> movies_col_Sypnosis;
    @FXML
    private TableColumn<movieListData, Double> movies_col_Price;
    @FXML
    private TableColumn<movieListData, String> movies_col_Time;
    @FXML
    private TableColumn<movieListData, String> movies_col_Title;
    @FXML
    private TableView<movieListData> movies_inventoryTV;
    @FXML
    private TextField txt_Cast;
    @FXML
    private TextField txt_Director;
    @FXML
    private TextField txt_Duration;
    @FXML
    private TextField txt_Genre;
    @FXML
    private TextField txt_IDMovie;
    @FXML
    private TextField txt_Sypnosis;
    @FXML
    private TextField txt_Title;
    @FXML
    private TextField txt_Price;
    @FXML
    private TextField txt_Time;
    @FXML
    private TextField txt_Rate;
    @FXML
    private Label totalTicketSold1;
    @FXML
    private Label totalUser;
    @FXML
    private BarChart<?, ?> accounts_chart1;
    @FXML
    private BarChart<?, ?> accounts_chart2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connect();
        table();
        showData();

    }
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;
    private Alert alert;

    public ObservableList<movieListData> listData() {

        ObservableList<movieListData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM movielist";

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
                com.mysql.cj.jdbc.Blob image = (com.mysql.cj.jdbc.Blob) result.getBlob("image");
                com.mysql.cj.jdbc.Blob imageBG = (com.mysql.cj.jdbc.Blob) result.getBlob("imageBG");
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

    private ObservableList<movieListData> inventoryListData;

    public void showData() {
        inventoryListData = listData();

        movies_col_IDMovies.setCellValueFactory(new PropertyValueFactory<>("IdMovies"));
        movies_col_Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        movies_col_Genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        movies_col_Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        movies_col_Sypnosis.setCellValueFactory(new PropertyValueFactory<>("sypnosis"));
        movies_col_Duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        movies_col_Time.setCellValueFactory(new PropertyValueFactory<>("time"));
        movies_col_Director.setCellValueFactory(new PropertyValueFactory<>("director"));
        movies_col_Cast.setCellValueFactory(new PropertyValueFactory<>("cast"));
        movies_col_Rating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        movies_inventoryTV.setItems(inventoryListData);
    }

    ////
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;

    public void Connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seenema", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private int totalUsers = 0;

    public void table() {
        Connect();
        ObservableList<GetAccount> getAccount = FXCollections.observableArrayList();

        try {
            pst = con.prepareStatement("select id,email,username,register_date from user");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                GetAccount ga = new GetAccount();
                ga.setId(rs.getString("id"));
                ga.setEmail(rs.getString("email"));
                ga.setUsername(rs.getString("username"));
                LocalDateTime registerDate = rs.getTimestamp("register_date").toLocalDateTime();
                ga.setRegisterDate(registerDate);
                getAccount.add(ga);
            }

            table.setItems(getAccount);
            IDColmn.setCellValueFactory(f -> f.getValue().idProperty());
            EmailColmn.setCellValueFactory(f -> f.getValue().emailProperty());
            UsernameColmn.setCellValueFactory(f -> f.getValue().usernameProperty());
            DateTimeColmn.setCellValueFactory(f -> f.getValue().registerDateProperty());

            // Count the total number of users
            totalUsers = getAccount.size();
            totalUser.setText(Integer.toString(totalUsers));

            // Create a new series to hold the data
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("User Accounts");

            // Query the database and add data to the series
            pst = con.prepareStatement("SELECT DATE(register_date), COUNT(*) FROM user GROUP BY DATE(register_date)");
            rs = pst.executeQuery();
            while (rs.next()) {
                String date = rs.getString(1);
                int count = rs.getInt(2);
                series.getData().add(new XYChart.Data<>(date, count));
            }

            // Add the series to the chart
            accounts_chart.getData().add(series);

        } catch (Exception e) {
            e.printStackTrace();
        }

        table.setRowFactory(tv -> {
            TableRow<GetAccount> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtEmail.setText(table.getItems().get(myIndex).getEmail());
                    txtUsername.setText(table.getItems().get(myIndex).getUsername());
                }
            });
            return myRow;
        });
    }

    @FXML
    private void delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        try {
            pst = con.prepareStatement("delete from user where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Log in History");

            alert.setHeaderText("Log in History");
            alert.setContentText("Deleted!");

            alert.showAndWait();
            table();
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void Update(ActionEvent event) {
        String email, username;

        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        email = txtEmail.getText();
        username = txtUsername.getText();
        try {
            pst = con.prepareStatement("update user set email = ?,username = ? where id = ? ");
            pst.setString(1, email);
            pst.setString(2, username);
            pst.setInt(3, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Log in History");

            alert.setHeaderText("Log in History");
            alert.setContentText("Updated!");

            alert.showAndWait();
            table();
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void search(ActionEvent event) {
        String searchText = account_search.getText();

        if (searchText.isEmpty()) {
            table();
            return;
        }

        ObservableList<GetAccount> getAccount = FXCollections.observableArrayList();

        try {
            pst = con.prepareStatement("SELECT id, email, username, register_date FROM user WHERE id LIKE ? OR email LIKE ? OR username LIKE ? OR register_date LIKE ?");
            pst.setString(1, "%" + searchText + "%");
            pst.setString(2, "%" + searchText + "%");
            pst.setString(3, "%" + searchText + "%");
            pst.setString(4, "%" + searchText + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                GetAccount ga = new GetAccount();
                ga.setId(rs.getString("id"));
                ga.setEmail(rs.getString("email"));
                ga.setUsername(rs.getString("username"));
                LocalDateTime registerDate = rs.getTimestamp("register_date").toLocalDateTime();
                ga.setRegisterDate(registerDate);
                getAccount.add(ga);
            }

            table.setItems(getAccount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add listener to detect when search text is cleared
        account_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                table();
            }
        });
    }

    @FXML
    public void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save before loging out?");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully logged out");

            logout.getScene().getWindow().hide();

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
            setButtonColor(accounts_btn, false);
            setButtonColor(movies_btn, false);
            setButtonColor(bookings_btn, false);
            setButtonColor(about_btn, false);

            dashboard_form.setVisible(true);
            account_form.setVisible(false);
            movies_form.setVisible(false);
            bookings_form.setVisible(false);
            about_form.setVisible(false);

        } else if (clickedButton == accounts_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(accounts_btn, true);
            setButtonColor(movies_btn, false);
            setButtonColor(bookings_btn, false);
            setButtonColor(about_btn, false);

            dashboard_form.setVisible(false);
            account_form.setVisible(true);
            movies_form.setVisible(false);
            bookings_form.setVisible(false);
            about_form.setVisible(false);

        } else if (clickedButton == movies_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(accounts_btn, false);
            setButtonColor(movies_btn, true);
            setButtonColor(bookings_btn, false);
            setButtonColor(about_btn, false);

            dashboard_form.setVisible(false);
            account_form.setVisible(false);
            movies_form.setVisible(true);
            bookings_form.setVisible(false);
            about_form.setVisible(false);

        } else if (clickedButton == bookings_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(accounts_btn, false);
            setButtonColor(movies_btn, false);
            setButtonColor(bookings_btn, true);
            setButtonColor(about_btn, false);

            dashboard_form.setVisible(false);
            account_form.setVisible(false);
            movies_form.setVisible(false);
            bookings_form.setVisible(true);
            about_form.setVisible(false);

        } else if (clickedButton == about_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(accounts_btn, false);
            setButtonColor(movies_btn, false);
            setButtonColor(bookings_btn, false);
            setButtonColor(about_btn, true);

            dashboard_form.setVisible(false);
            account_form.setVisible(false);
            movies_form.setVisible(false);
            bookings_form.setVisible(false);
            about_form.setVisible(true);

        }
    }

    private void setButtonColor(Button button, boolean isSelected) {
        if (isSelected) {
            button.getStyleClass().add("selected-button");
        } else {
            button.getStyleClass().remove("selected-button");
        }
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
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);

    }

}
