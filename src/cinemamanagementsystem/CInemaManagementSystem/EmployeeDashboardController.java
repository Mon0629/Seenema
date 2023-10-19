/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import java.net.URL;
import com.mysql.cj.jdbc.Blob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class EmployeeDashboardController implements Initializable {

    @FXML
    private BorderPane mainForm;
    @FXML
    private Button dashboard_btn;
    @FXML
    private Button movie_btn;
    @FXML
    private Button userAccounts_btn;
    @FXML
    private Button feedbacksUser_btn;
    @FXML
    private Button bookings_btn;
    @FXML
    private AnchorPane logout;
    @FXML
    private AnchorPane dashboardFrom;
    @FXML
    private AnchorPane moviesForm;
    @FXML
    private AnchorPane bookingHistoryForm;
    @FXML
    private AnchorPane userAccountsFrom;
    @FXML
    private AnchorPane feedbacksfrom;
    @FXML
    private Button close;
    @FXML
    private Button minimize;

    private double x = 0;
    private double y = 0;

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
    private TableColumn<movieListData, String> movies_col_ViewDate;
    @FXML
    private TableColumn<movieListData, String> movies_col_NextViewDate;
    @FXML
    private TableView<movieListData> movies_inventoryTV;

    @FXML
    private TextField txt_IDMovie;
    @FXML
    private TextField txt_Title;
    @FXML
    private TextField txt_Genre;
    @FXML
    private TextField txt_Sypnosis;
    @FXML
    private TextField txt_Duration;
    @FXML
    private TextField txt_Director;
    @FXML
    private TextField txt_Cast;
    @FXML
    private ImageView ImportImageView;
    @FXML
    private Button ImportBtn;
    @FXML
    private Button AddBtn;
    @FXML
    private Button UpdateBtn;
    @FXML
    private Button ClearBtn;
    @FXML
    private Button DeleteBtn;
    @FXML
    private ComboBox<String> ChooseTime;
    @FXML
    private ComboBox<String> ChooseRate;
    @FXML
    private TextField txt_Price;
    @FXML
    private TextField txt_Time;
    @FXML
    private TextField txt_Rate;
    @FXML
    private TableView<getFeedback> tableFeedback;

    @FXML
    private TableColumn<getFeedback, String> table_customer;

    @FXML
    private TableColumn<getFeedback, String> table_id;

    @FXML
    private TableColumn<getFeedback, String> table_overall;

    @FXML
    private TableColumn<getFeedback, String> table_pickup;

    @FXML
    private TableColumn<getFeedback, String> table_selectedvalue;

    @FXML
    private TableColumn<getFeedback, String> table_service;

    @FXML
    private TableColumn<getFeedback, String> table_suggestion;

    @FXML
    private TableColumn<getFeedback, String> table_transparency;
    @FXML
    private ImageView ImportImageBG;
    @FXML
    private Button ImportBtnBG;
    @FXML
    private Label totalMovie;
    @FXML
    private Label totalUser;
    @FXML
    private Label totalIncome;
    @FXML
    private BarChart<String, Number> accounts_chart;
    @FXML
    private Label totalTicketSold;
    @FXML
    private BarChart<String, Number> accounts_chart2;
    @FXML
    private TableView<bookinglist> EmpBookingTable;
    @FXML
    private TableColumn<bookinglist, String> EmpBookingUserID;
    @FXML
    private TableColumn<bookinglist, String> EmpBookingTicketID;
    @FXML
    private TableColumn<bookinglist, String> EmpBookingMovieID;
    @FXML
    private TableColumn<bookinglist, String> EmpBookingMovieName;
    @FXML
    private TableColumn<bookinglist, String> EmpBookingVIewDate;
    @FXML
    private TableColumn<bookinglist, String> EmpBookingQuantity;
    @FXML
    private TableColumn<bookinglist, String> EmpBookingPrice;
    @FXML
    private TableColumn<bookinglist, String> EmpBookingTotal;
    @FXML
    private TableColumn<bookinglist, LocalDateTime> EmpBookingDate;
    @FXML
    private Button CancelBtn;
    @FXML
    private TextField bookings_search;
    @FXML
    private DatePicker txt_ViewDate;
    @FXML
    private DatePicker txt_NextView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        table();
        showData();
        try {
            tableView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            bookingList();
        } catch (SQLException e) {
        }
        timeCombobox();
        rateCombobox();
        getDataCountFromTable_TotalUser();
        getDataCountFromTable_Totalticket();
        getDataCountFromTable_TotalSale();
        getDataCountFromTable_TotalMovies();
        getDataFromTable_SalesChart();
        getDataFromTable_TotalUserChart();
    }
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;
    private Alert alert;

    private ObservableList<bookinglist> listbookings;

    public void bookingList() throws SQLException {
        listbookings = FXCollections.observableArrayList(); // Update class-level variable
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement("SELECT UserID, TicketID, IDMovies, MovieName, ViewingDate, quantity, price, total, BookingDate FROM bookings");
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                bookinglist bl = new bookinglist();
                bl.setUserId(result.getString("UserID"));
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

            EmpBookingUserID.setCellValueFactory(f -> f.getValue().bookinglistUserID());
            EmpBookingTicketID.setCellValueFactory(f -> f.getValue().bookinglistTicketID());
            EmpBookingMovieID.setCellValueFactory(f -> f.getValue().bookinglistIDMovies());
            EmpBookingMovieName.setCellValueFactory(f -> f.getValue().bookinglistMovieName());
            EmpBookingVIewDate.setCellValueFactory(f -> f.getValue().bookinglistViewingDate());
            EmpBookingQuantity.setCellValueFactory(f -> f.getValue().bookinglistQuantity());
            EmpBookingPrice.setCellValueFactory(f -> f.getValue().bookinglistPrice());
            EmpBookingTotal.setCellValueFactory(f -> f.getValue().bookinglistTotal());
            EmpBookingDate.setCellValueFactory(f -> f.getValue().BookingDateProperty());  // Use the correct getter method
            EmpBookingTable.setItems(listbookings);

        } catch (SQLException e) {
            e.printStackTrace();  // Print the stack trace for debugging purposes
        }
    }

    @FXML
    private void setupSearchBox() {
        bookings_search.setOnKeyReleased(event -> {
            String input = bookings_search.getText().toLowerCase();

            ObservableList<bookinglist> filteredList = FXCollections.observableArrayList();

            if (input.isEmpty()) {
                filteredList.addAll(listbookings); // Add all rows when the input is empty
            } else {
                for (bookinglist booking : listbookings) {
                    if (booking.getTicketID().toLowerCase().equals(input)) {
                        filteredList.add(booking);
                    }
                }
            }

            EmpBookingTable.setItems(filteredList);
        });

        // Perform initial search on system load
        String input = bookings_search.getText().toLowerCase();
        if (!input.isEmpty()) {
            bookings_search.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.ENTER, false, false, false, false));
        }
    }

    @FXML
    private void onDeleteButtonClicked(ActionEvent event) {
        bookinglist selectedBooking = EmpBookingTable.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            // Prompt user for confirmation before deleting the row
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Booking");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this booking?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                deleteBooking(selectedBooking); // Delete the selected row from the database
                EmpBookingTable.getItems().remove(selectedBooking); // Remove the row from the TableView
            }
        }
    }

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

    @FXML
    private void inventorySelectData(MouseEvent event) {
        movieListData movData = movies_inventoryTV.getSelectionModel().getSelectedItem();
        int num = movies_inventoryTV.getSelectionModel().getSelectedIndex();

        if (num - 1 < -1) {
            return;
        }

        txt_IDMovie.setText(movData.getIdMovies());
        txt_Title.setText(movData.getTitle());
        txt_Genre.setText(movData.getGenre());
        txt_Price.setText(Double.toString(movData.getPrice()));
        txt_Sypnosis.setText(movData.getSypnosis());
        txt_Duration.setText(movData.getDuration());

        // Set the selected value of ChooseTime and ChooseRate
        txt_Time.setText(movData.getTime());

        txt_Director.setText(movData.getDirector());
        txt_Rate.setText(movData.getRating());

        txt_Cast.setText(movData.getCast());

        try {
            Blob imageBlob = movData.getImage();
            InputStream inputStream = imageBlob.getBinaryStream();
            image = new Image(inputStream, 134, 165, false, true);
            ImportImageView.setImage(image);
        } catch (SQLException e) {
            e.getCause().printStackTrace();
        }

        try {
            Blob imageBlob = movData.getImageBG();
            InputStream inputStream = imageBlob.getBinaryStream();
            image = new Image(inputStream, 171, 116, false, true);
            ImportImageBG.setImage(image);
        } catch (SQLException e) {
            e.getCause().printStackTrace();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate viewingDate = LocalDate.parse(movData.getViewingDate(), formatter);
        LocalDate nextViewingDate = LocalDate.parse(movData.getNextViewingDate(), formatter);

        txt_ViewDate.setValue(viewingDate);
        txt_NextView.setValue(nextViewingDate);

    }

    @FXML
    private void insertImage(ActionEvent event) {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());

        if (file != null) {
            data.path = file.getAbsolutePath();
            System.out.println("IMAGE PATH: " + file.getAbsolutePath());
            image = new Image(file.toURI().toString(), 134, 161, false, true);
            ImportImageView.setImage(image);
        } else {
            System.out.println("DATA DOESN'T EXIST");
        }
    }

    @FXML
    private void insertImageBG(ActionEvent event) {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());

        if (file != null) {
            data.pathpath = file.getAbsolutePath();
            System.out.println("IMAGE BG PATH: " + data.pathpath);
            image = new Image(file.toURI().toString(), 171, 116, false, true);
            ImportImageBG.setImage(image);
        } else {
            System.out.println("DATA DOESN'T EXIST");
        }
    }

    @FXML
    private void insert(ActionEvent event) {
        if (txt_IDMovie.getText().isEmpty()
                || txt_Title.getText().isEmpty()
                || txt_Genre.getText().isEmpty()
                || txt_Price.getText().isEmpty()
                || txt_Sypnosis.getText().isEmpty()
                || txt_Duration.getText().isEmpty()
                || ChooseTime.getSelectionModel().getSelectedItem() == null
                || txt_Director.getText().isEmpty()
                || ChooseRate.getSelectionModel().getSelectedItem() == null
                || txt_Cast.getText().isEmpty()
                || data.path == null
                || data.pathpath == null
                || txt_ViewDate.getValue() == null // Check if DatePicker is empty
                || txt_NextView.getValue() == null) // Check if DatePicker is empty) 
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {

            String checkMovID = "SELECT id_movies FROM movielist WHERE id_movies = '"
                    + txt_IDMovie.getText() + "'";

            connect = database.getConnection();

            try {
                statement = connect.createStatement();

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText(txt_IDMovie.getText() + " is already taken");
                    alert.showAndWait();
                } else {
                    String insertData = "INSERT INTO movielist "
                            + "(id_movies, title, genre, price, sypnosis, duration, time, director, cast, rating, image, imagebg, ViewingDate, NextViewingDate)"
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, txt_IDMovie.getText());
                    prepare.setString(2, txt_Title.getText());
                    prepare.setString(3, txt_Genre.getText());
                    prepare.setDouble(4, Double.parseDouble(txt_Price.getText()));
                    prepare.setString(5, txt_Sypnosis.getText());
                    prepare.setString(6, txt_Duration.getText());
                    prepare.setString(7, txt_Time.getText());
                    prepare.setString(8, txt_Director.getText());
                    prepare.setString(9, txt_Rate.getText());
                    prepare.setString(10, txt_Cast.getText());

                    FileInputStream fileInputStream = new FileInputStream(data.path);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, bytesRead);
                    }

                    byte[] imageBytes = byteArrayOutputStream.toByteArray();
                    prepare.setBytes(11, imageBytes);

                    FileInputStream imageBGInputStream = new FileInputStream(data.pathpath);
                    ByteArrayOutputStream imageBGByteArrayOutputStream = new ByteArrayOutputStream();

                    byte[] imageBGBuffer = new byte[1024];
                    int imageBGBytesRead;
                    while ((imageBGBytesRead = imageBGInputStream.read(imageBGBuffer)) != -1) {
                        imageBGByteArrayOutputStream.write(imageBGBuffer, 0, imageBGBytesRead);
                    }

                    byte[] imageBGBytes = imageBGByteArrayOutputStream.toByteArray();
                    prepare.setBytes(12, imageBGBytes);
                    prepare.setDate(13, java.sql.Date.valueOf(txt_ViewDate.getValue()));
                    prepare.setDate(14, java.sql.Date.valueOf(txt_NextView.getValue()));

                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    showData();
                    clear();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        connect = database.getConnection();
        String sql = "INSERT INTO movielist VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, txt_IDMovie.getText());
            prepare.setString(2, txt_Title.getText());
            prepare.setString(3, txt_Genre.getText());
            prepare.setDouble(4, Double.parseDouble(txt_Price.getText()));
            prepare.setString(5, txt_Sypnosis.getText());
            prepare.setString(6, txt_Duration.getText());
            prepare.setString(7, (String) ChooseTime.getSelectionModel().getSelectedItem());
            prepare.setString(8, txt_Director.getText());
            prepare.setString(9, (String) ChooseRate.getSelectionModel().getSelectedItem());
            prepare.setString(10, txt_Cast.getText());
            String path = data.path;
            String pathpath = data.pathpath;

            path = path.replace("\\", "\\\\");
            pathpath = pathpath.replace("\\", "\\\\");

            prepare.setString(11, path);
            prepare.setString(12, pathpath);

            prepare.executeUpdate();
            showData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //para mashow yung total number of movies AHAHAHAAHHAHAAHHAHAHAHAHHAHAHHAHAHAHAHAHAHAHAHHHAH
    private int getDataCountFromTable_TotalUser() {
        int count = 0;

        try {
            //ikonek sa deyta beys
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seenema", "root", "");

            Statement statement = connection.createStatement();

            // iexecute ang kwery
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM user");

            // Retrieve the count from the result set
            if (resultSet.next()) {
                String total;
                total = resultSet.getString("COUNT(*)");
                totalUser.setText(total);

            }

            // isarado ang mga nakabukas
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
        }

        return count;
    }

    private int getDataCountFromTable_TotalMovies() {
        int count = 0;

        try {
            //ikonek sa deyta beys
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seenema", "root", "");

            Statement statement = connection.createStatement();

            // iexecute ang kwery
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM movielist");

            // Retrieve the count from the result set
            if (resultSet.next()) {
                String total;
                total = resultSet.getString("COUNT(*)");
                totalMovie.setText(total);

            }

            // isarado ang mga nakabukas
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
        }

        return count;
    }

    //para mashow yung total number of BOOKINGS, GUMAWA MUNA AKO NG EME EME NA TABLE NA MAY NAME NA bookings AHAHAHAAHHAHAAHHAHAHAHAHHAHAHHAHAHAHAHAHAHAHAHHHAH
    private int getDataCountFromTable_Totalticket() {
        int count = 0;

        try {
            //ikonek sa deyta beys
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seenema", "root", "");

            Statement statement = connection.createStatement();

            // iexecute ang kwery
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM bookings");

            // Retrieve the count from the result set
            if (resultSet.next()) {
                String total;
                total = resultSet.getString("COUNT(*)");
                totalTicketSold.setText(total);
            }

            // isarado ang mga nakabukas
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
        }

        return count;
    }

    //para mashow ang total INCOME NA NASA LABEL keme keme// palitan anlng ng table ng booking HAHAHAHAHAHHAHAHAHAAHHAHHAHHA
    private int getDataCountFromTable_TotalSale() {
        int count = 0;

        try {
            // SYEMPRE DAPAT MAY KONEKSYON KAYO DIBA
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seenema", "root", "");

            Statement statement = connection.createStatement();

            // Execute THE KWERY SYEMPRE, PARA KANG DI NAG GRAD 2
            ResultSet resultSet = statement.executeQuery("SELECT SUM(Total) FROM bookings");

            // Retrieve the count from the result set
            if (resultSet.next()) {
                String total;
                total = resultSet.getString("SUM(Total)");
                totalIncome.setText("$" + total);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
        }

        return count;
    }

    // THIS ONE EH SA ANALYTICS NG INCOME REPORT
    private XYChart.Series<String, Number> getDataFromTable_SalesChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        try {
            // Establish a connection to your SQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seenema", "root", "");

            // Create a statement to execute SQL queries
            Statement statement = connection.createStatement();

            // Execute the query to retrieve the data
            ResultSet resultSet = statement.executeQuery("SELECT DATE(BookingDate), SUM(Total) FROM bookings GROUP BY BookingDate");

            // Iterate over the result set and add data to the series
            while (resultSet.next()) {
                String date = resultSet.getString("DATE(BookingDate)");
                double price = resultSet.getDouble("SUM(Total)");

                series.getData().add(new XYChart.Data<>(date, price));

            }
            accounts_chart2.getData().add(series);
            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return series;
    }

    // THIS ONE EH SA ANALYTICS NG INCOME REPORT
    private XYChart.Series<String, Number> getDataFromTable_TotalUserChart() {
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
        return null;
    }





    @FXML
    private void UPDATEPUTAENA(ActionEvent event) {
        if (txt_IDMovie.getText().isEmpty()
                || txt_Title.getText().isEmpty()
                || txt_Genre.getText().isEmpty()
                || txt_Price.getText().isEmpty()
                || txt_Sypnosis.getText().isEmpty()
                || txt_Duration.getText().isEmpty()
                || txt_Time.getText().isEmpty()
                || txt_Director.getText().isEmpty()
                || txt_Rate.getText().isEmpty()
                || txt_Cast.getText().isEmpty()
                || ImportImageView.getImage() == null
                || ImportImageBG.getImage() == null
                || txt_ViewDate.getValue() == null // Check if DatePicker is empty
                || txt_NextView.getValue() == null) // Check if DatePicker is empty
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            try {
                connect = database.getConnection();

                // First, check if the given movie ID already exists in the database
                String checkMovID = "SELECT id_movies FROM movielist WHERE id_movies = ?";
                prepare = connect.prepareStatement(checkMovID);
                prepare.setString(1, txt_IDMovie.getText());
                result = prepare.executeQuery();

                if (!result.next()) {
                    // If the given movie ID doesn't exist, show an error message
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText("ID Movies cannot be edited! Try creating a new movie.");
                    alert.showAndWait();
                } else {
                    // If the given movie ID exists, update the corresponding row
                    String updateData;
                    if (data.path != null && data.pathpath != null) {
                        updateData = "UPDATE movielist SET title = ?, genre = ?, price = ?, sypnosis = ?, duration = ?, time = ?, director = ?, cast = ?, rating = ?, image = ?, imagebg = ?, ViewingDate = ?, NextViewingDate = ? WHERE id_movies = ?";
                    } else if (data.path != null) {
                        updateData = "UPDATE movielist SET title = ?, genre = ?, price = ?, sypnosis = ?, duration = ?, time = ?, director = ?, cast = ?, rating = ?, image = ?, ViewingDate = ?, NextViewingDate = ? WHERE id_movies = ?";
                    } else if (data.pathpath != null) {
                        updateData = "UPDATE movielist SET title = ?, genre = ?, price = ?, sypnosis = ?, duration = ?, time = ?, director = ?, cast = ?, rating = ?, imagebg = ?, ViewingDate = ?, NextViewingDate = ? WHERE id_movies = ?";
                    } else {
                        updateData = "UPDATE movielist SET title = ?, genre = ?, price = ?, sypnosis = ?, duration = ?, time = ?, director = ?, cast = ?, rating = ?, ViewingDate = ?, NextViewingDate = ? WHERE id_movies = ?";
                    }

                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, txt_Title.getText());
                    prepare.setString(2, txt_Genre.getText());
                    prepare.setDouble(3, Double.parseDouble(txt_Price.getText()));
                    prepare.setString(4, txt_Sypnosis.getText());
                    prepare.setString(5, txt_Duration.getText());
                    prepare.setString(6, txt_Time.getText());
                    prepare.setString(7, txt_Director.getText());
                    prepare.setString(8, txt_Cast.getText());
                    prepare.setString(9, txt_Rate.getText());

                    int parameterIndex = 10; // Start index for additional parameters

                    byte[] existingImageBytes = null;
                    byte[] existingImageBGBytes = null;

                    if (data.path != null) {
                        File file = new File(data.path);
                        if (file.exists()) {
                            FileInputStream fileInputStream = new FileInputStream(file);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                                byteArrayOutputStream.write(buffer, 0, bytesRead);
                            }
                            byte[] imageBytes = byteArrayOutputStream.toByteArray();
                            prepare.setBytes(parameterIndex, imageBytes);
                        } else {
                            // Retrieve existing image data from the database
                            String retrieveImageDataQuery = "SELECT image FROM movielist WHERE id_movies = ?";
                            PreparedStatement retrieveImageDataStatement = connect.prepareStatement(retrieveImageDataQuery);
                            retrieveImageDataStatement.setString(1, txt_IDMovie.getText());
                            ResultSet imageDataResult = retrieveImageDataStatement.executeQuery();
                            if (imageDataResult.next()) {
                                existingImageBytes = imageDataResult.getBytes("image");
                            }
                            prepare.setBytes(parameterIndex, existingImageBytes);
                        }
                        parameterIndex++;
                    }

                    if (data.pathpath != null) {
                        File imageBGFile = new File(data.pathpath);
                        if (imageBGFile.exists()) {
                            FileInputStream imageBGInputStream = new FileInputStream(imageBGFile);
                            ByteArrayOutputStream imageBGByteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] imageBGBuffer = new byte[1024];
                            int imageBGBytesRead;
                            while ((imageBGBytesRead = imageBGInputStream.read(imageBGBuffer)) != -1) {
                                imageBGByteArrayOutputStream.write(imageBGBuffer, 0, imageBGBytesRead);
                            }
                            byte[] imageBGBytes = imageBGByteArrayOutputStream.toByteArray();
                            prepare.setBytes(parameterIndex, imageBGBytes);
                        } else {
                            // Retrieve existing image background data from the database
                            String retrieveImageBGDataQuery = "SELECT imagebg FROM movielist WHERE id_movies = ?";
                            PreparedStatement retrieveImageBGDataStatement = connect.prepareStatement(retrieveImageBGDataQuery);
                            retrieveImageBGDataStatement.setString(1, txt_IDMovie.getText());
                            ResultSet imageBGDataResult = retrieveImageBGDataStatement.executeQuery();
                            if (imageBGDataResult.next()) {
                                existingImageBGBytes = imageBGDataResult.getBytes("imagebg");
                            }
                            prepare.setBytes(parameterIndex, existingImageBGBytes);
                        }
                        parameterIndex++;
                    }

                    prepare.setDate(parameterIndex++, java.sql.Date.valueOf(txt_ViewDate.getValue()));
                    prepare.setDate(parameterIndex++, java.sql.Date.valueOf(txt_NextView.getValue()));
                    prepare.setString(parameterIndex, txt_IDMovie.getText());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully updated!");
                    alert.showAndWait();

                    showData();
                    clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void clear() {
        txt_IDMovie.setText("");
        txt_Title.setText("");
        txt_Genre.setText("");
        txt_Price.setText("");
        txt_Sypnosis.setText("");
        txt_Duration.setText("");
        ChooseTime.getSelectionModel().clearSelection();
        txt_Time.setText("");
        txt_Director.setText("");
        ChooseRate.getSelectionModel().clearSelection();
        txt_Rate.setText("");
        txt_Cast.setText("");
        data.path = "";
        ImportImageView.setImage(null);
        data.pathpath = "";
        ImportImageBG.setImage(null);
        txt_ViewDate.setValue(null); // Clear the DatePicker
        txt_NextView.setValue(null); // Clear the DatePicker
    }

    @FXML
    private void deleteData(ActionEvent event) {
        movieListData movData = movies_inventoryTV.getSelectionModel().getSelectedItem();
        if (movData != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you want to delete " + movData.getTitle() + "?");
            alert.setContentText("This action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    connect = database.getConnection();
                    String deleteData = "DELETE FROM movielist WHERE id_movies = ?";
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, movData.getIdMovies());
                    prepare.executeUpdate();
                    showData();
                    clear();
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Dialog");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText(movData.getTitle() + " has been deleted successfully.");
                    successAlert.showAndWait();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to delete.");
            alert.showAndWait();
        }
    }
    private String timeData[] = {"10:00 AM", "5:00 PM"};

    @FXML
    private void timeCombobox() {
        List<String> timeD = new ArrayList<>();
        for (String data : timeData) {
            timeD.add(data);
        }
        ObservableList<String> listData = FXCollections.observableArrayList(timeD);
        ChooseTime.setItems(listData);
        ChooseTime.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            txt_Time.setText(newValue);
            ChooseTime.setValue(newValue);
        });
    }

    private String[] rateData = {"0.0", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1.0",
        "1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9", "2.0",
        "2.1", "2.2", "2.3", "2.4", "2.5", "2.6", "2.7", "2.8", "2.9", "3.0",
        "3.1", "3.2", "3.3", "3.4", "3.5", "3.6", "3.7", "3.8", "3.9", "4.0",
        "4.1", "4.2", "4.3", "4.4", "4.5", "4.6", "4.7", "4.8", "4.9", "5.0",
        "5.1", "5.2", "5.3", "5.4", "5.5", "5.6", "5.7", "5.8", "5.9", "6.0",
        "6.1", "6.2", "6.3", "6.4", "6.5", "6.6", "6.7", "6.8", "6.9", "7.0",
        "7.1", "7.2", "7.3", "7.4", "7.5", "7.6", "7.7", "7.8", "7.9", "8.0",
        "8.1", "8.2", "8.3", "8.4", "8.5", "8.6", "8.7", "8.8", "8.9", "9.0",
        "9.1", "9.2", "9.3", "9.4", "9.5", "9.6", "9.7", "9.8", "9.9", "10.0"};

    @FXML
    private void rateCombobox() {
        List<String> rateD = new ArrayList<>();

        for (String data : rateData) {
            rateD.add(data);

        }
        ObservableList<String> listData = FXCollections.observableArrayList(rateD);
        ChooseRate.setItems(listData);
        ChooseRate.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            txt_Rate.setText(newValue);
            ChooseRate.setValue(newValue);
        });
    }

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
        movies_col_ViewDate.setCellValueFactory(new PropertyValueFactory<>("viewingDate"));
        movies_col_NextViewDate.setCellValueFactory(new PropertyValueFactory<>("nextViewingDate"));

        movies_inventoryTV.setItems(inventoryListData);
    }

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

    // Create a confirmation dialog
    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.setTitle("Confirmation");
    confirmationDialog.setHeaderText("Delete Confirmation");
    confirmationDialog.setContentText("Are you sure you want to delete this data?");

    Optional<ButtonType> result = confirmationDialog.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
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
            ex.printStackTrace();
        }
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
            setButtonColor(userAccounts_btn, false);
            setButtonColor(feedbacksUser_btn, false);

            dashboardFrom.setVisible(true);
            moviesForm.setVisible(false);
            bookingHistoryForm.setVisible(false);
            userAccountsFrom.setVisible(false);
            feedbacksfrom.setVisible(false);

        } else if (clickedButton == movie_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(movie_btn, true);
            setButtonColor(bookings_btn, false);
            setButtonColor(userAccounts_btn, false);
            setButtonColor(feedbacksUser_btn, false);

            dashboardFrom.setVisible(false);
            moviesForm.setVisible(true);
            bookingHistoryForm.setVisible(false);
            userAccountsFrom.setVisible(false);
            feedbacksfrom.setVisible(false);

        } else if (clickedButton == bookings_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(movie_btn, false);
            setButtonColor(bookings_btn, true);
            setButtonColor(userAccounts_btn, false);
            setButtonColor(feedbacksUser_btn, false);

            dashboardFrom.setVisible(false);
            moviesForm.setVisible(false);
            bookingHistoryForm.setVisible(true);
            userAccountsFrom.setVisible(false);
            feedbacksfrom.setVisible(false);

        } else if (clickedButton == userAccounts_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(movie_btn, false);
            setButtonColor(bookings_btn, false);
            setButtonColor(userAccounts_btn, true);
            setButtonColor(feedbacksUser_btn, false);

            dashboardFrom.setVisible(false);
            moviesForm.setVisible(false);
            bookingHistoryForm.setVisible(false);
            userAccountsFrom.setVisible(true);
            feedbacksfrom.setVisible(false);

        } else if (clickedButton == feedbacksUser_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(movie_btn, false);
            setButtonColor(bookings_btn, false);
            setButtonColor(userAccounts_btn, false);
            setButtonColor(feedbacksUser_btn, true);

            dashboardFrom.setVisible(false);
            moviesForm.setVisible(false);
            bookingHistoryForm.setVisible(false);
            userAccountsFrom.setVisible(false);
            feedbacksfrom.setVisible(true);
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
    private void logout(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save before loging out?");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully logged out");

            logout.getScene().getWindow().hide();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("employeeLogin.fxml"));
                Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setWidth(370);
                stage.setHeight(511);

                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
                double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
                stage.setX(centerX - 185);
                stage.setY(centerY - 255.5);

                Scene scene = new Scene(root, 370, 511);

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

    @FXML
    public void tableView() throws SQLException {
        connect = database.getConnection();
        ObservableList<getFeedback> getDatabase = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement("select id,overall,customer,transparency,service,pickup,suggestion,selectedValue from feedback");
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                getFeedback gd = new getFeedback();
                gd.setDataId(result.getString("id"));
                gd.setDataOverall(result.getString("overall"));
                gd.setDataCustomer(result.getString("customer"));
                gd.setDataTransparency(result.getString("transparency"));
                gd.setDataService(result.getString("service"));
                gd.setDataPickup(result.getString("pickup"));
                gd.setDataSuggestion(result.getString("suggestion"));
                gd.setDataSelectedValue(result.getString("selectedValue"));
                getDatabase.add(gd); // add the retrieved data to the ObservableList
            }

            tableFeedback.setItems(getDatabase);
            table_id.setCellValueFactory(f -> f.getValue().getDatabaseId());
            table_overall.setCellValueFactory(f -> f.getValue().getDatabaseOverall());
            table_customer.setCellValueFactory(f -> f.getValue().getDatabaseCustomer());
            table_transparency.setCellValueFactory(f -> f.getValue().getDatabaseTransparency());
            table_service.setCellValueFactory(f -> f.getValue().getDatabaseService());
            table_pickup.setCellValueFactory(f -> f.getValue().getDatabasePickup());
            table_suggestion.setCellValueFactory(f -> f.getValue().getDatabaseSuggestion());
            table_selectedvalue.setCellValueFactory(f -> f.getValue().getDatabaseSelectedValue());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
