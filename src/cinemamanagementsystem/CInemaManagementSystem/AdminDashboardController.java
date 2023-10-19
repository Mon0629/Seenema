/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class AdminDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private double x = 0;
    private double y = 0;
    @FXML
    private BorderPane mainForm;
    @FXML
    private Button dashboard_btn;
    private Button movie_btn;
    private Button feedback_btn;
    @FXML
    private Button bookings_btn;
    @FXML
    private AnchorPane dashboardFrom;
    @FXML
    private Label totalIncome;
    @FXML
    private Label totalUser;
    private AnchorPane moviesForm;
    private AnchorPane bookingHistoryForm;
    private AnchorPane feedbackForm;
    private AnchorPane aboutusForm;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button employee_btn;
    @FXML
    private Button userAccounts_btn;
    @FXML
    private AnchorPane employeeFrom;
    @FXML
    private AnchorPane useraccountFrom;
    @FXML
    private AnchorPane bookingsfrom;
    @FXML
    private AnchorPane logout;
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
    private TextField employee_search;
    @FXML
    private TableColumn<GetAccount, String> Id_Col;
    @FXML
    private TableColumn<GetAccount, String> Email_Col;
    @FXML
    private TableColumn<GetAccount, String> Username_Col;
    @FXML
    private TableColumn<GetAccount, LocalDateTime> DateTIme_Col;
    @FXML
    private TextField txtEmailEmp;
    @FXML
    private TextField txtUsernameEmp;
    @FXML
    private Button UpdateBtn;
    @FXML
    private Button DeleteBtn;
    @FXML
    private TableView<GetAccount> tableEmp;
    @FXML
    private Label totalMovie;
    @FXML
    private BarChart<String, Number> accounts_chart;
    @FXML
    private Label totalTicketSold;
    @FXML
    private BarChart<String, Number> accounts_chart2;
    @FXML
    private TextField txtPassEmp;
    @FXML
    private Button AddEmpBtn;
    @FXML
    private TableColumn<GetAccount, String> Password_Col;
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
    private TextField bookings_search;
    @FXML
    private Button CancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        table();
        tableEmp();
        tableEmp();
        try {
            bookingList();
        } catch (SQLException e) {
        }
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

// Search
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
                totalIncome.setText(total);
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





    public void tableEmp() {
        Connect();
        ObservableList<GetAccount> getAccount = FXCollections.observableArrayList();

        try {
            pst = con.prepareStatement("select id,email,username,password,register_date from employee");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                GetAccount ga = new GetAccount();
                ga.setId(rs.getString("id"));
                ga.setEmail(rs.getString("email"));
                ga.setUsername(rs.getString("username"));
                ga.setPassword(rs.getString("password"));
                LocalDateTime registerDate = rs.getTimestamp("register_date").toLocalDateTime();
                ga.setRegisterDate(registerDate);
                getAccount.add(ga);
            }

            tableEmp.setItems(getAccount);
            Id_Col.setCellValueFactory(f -> f.getValue().idProperty());
            Email_Col.setCellValueFactory(f -> f.getValue().emailProperty());
            Username_Col.setCellValueFactory(f -> f.getValue().usernameProperty());
            Password_Col.setCellValueFactory(f -> f.getValue().passwordProperty());
            DateTIme_Col.setCellValueFactory(f -> f.getValue().registerDateProperty());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableEmp.setRowFactory(tv -> {
            TableRow<GetAccount> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableEmp.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(tableEmp.getItems().get(myIndex).getId()));
                    txtEmailEmp.setText(tableEmp.getItems().get(myIndex).getEmail());
                    txtUsernameEmp.setText(tableEmp.getItems().get(myIndex).getUsername());
                    txtPassEmp.setText(tableEmp.getItems().get(myIndex).getPassword());
                }
            });
            return myRow;
        });
    }

    @FXML
    private void searchEmp(ActionEvent event) {
        String searchText = employee_search.getText();

        if (searchText.isEmpty()) {
            showAllData(); // Call the showAllData() method
            return;
        }

        ObservableList<GetAccount> getAccount = FXCollections.observableArrayList();

        try {
            pst = con.prepareStatement("SELECT id, email, username, password, register_date FROM employee WHERE id LIKE ? OR email LIKE ? OR username LIKE ? OR password LIKE ? OR register_date LIKE ?");
            pst.setString(1, "%" + searchText + "%");
            pst.setString(2, "%" + searchText + "%");
            pst.setString(3, "%" + searchText + "%");
            pst.setString(4, "%" + searchText + "%");
            pst.setString(5, "%" + searchText + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                GetAccount ga = new GetAccount();
                ga.setId(rs.getString("id"));
                ga.setEmail(rs.getString("email"));
                ga.setUsername(rs.getString("username"));
                ga.setPassword(rs.getString("password"));
                LocalDateTime registerDate = rs.getTimestamp("register_date").toLocalDateTime();
                ga.setRegisterDate(registerDate);
                getAccount.add(ga);
            }

            tableEmp.setItems(getAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add listener to detect when search text is cleared
        employee_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                showAllData(); // Call the showAllData() method
            }
        });
    }

    private void showAllData() {
        ObservableList<GetAccount> getAccount = FXCollections.observableArrayList();

        try {
            pst = con.prepareStatement("SELECT id, email, username, password, register_date FROM employee");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                GetAccount ga = new GetAccount();
                ga.setId(rs.getString("id"));
                ga.setEmail(rs.getString("email"));
                ga.setUsername(rs.getString("username"));
                ga.setPassword(rs.getString("password"));
                LocalDateTime registerDate = rs.getTimestamp("register_date").toLocalDateTime();
                ga.setRegisterDate(registerDate);
                getAccount.add(ga);
            }

            tableEmp.setItems(getAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void resetTable() {
        // Reset the table to its original state
        tableEmp.setItems(getAccountData());
    }

    private ObservableList<GetAccount> getAccountData() {
        ObservableList<GetAccount> getAccount = FXCollections.observableArrayList();

        try {
            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, email, username, password register_date FROM employee");

            while (rs.next()) {
                GetAccount ga = new GetAccount();
                ga.setId(rs.getString("id"));
                ga.setEmail(rs.getString("email"));
                ga.setUsername(rs.getString("username"));
                ga.setPassword(rs.getString("password"));
                LocalDateTime registerDate = rs.getTimestamp("register_date").toLocalDateTime();
                ga.setRegisterDate(registerDate);
                getAccount.add(ga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getAccount;
    }

    public boolean validEmail() {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher match = pattern.matcher(txtEmailEmp.getText());
        Alert alert;

        if (match.find() && match.group().equals(txtEmailEmp.getText())) {

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
    private void AddEmp(ActionEvent event) {

        String sql = "INSERT INTO employee (email, username, password) VALUES (?, ?, ?)";
        String sqll = "SELECT username FROM employee WHERE username = ?";

        connect = database.getConnection();
        try {
            Alert alert;

            if (txtUsernameEmp.getText().isEmpty()
                    || txtEmailEmp.getText().isEmpty()
                    || txtPassEmp.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else if (txtPassEmp.getText().length() < 8) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password must be at least 8 characters long");
                alert.showAndWait();
            } else if (validEmail()) {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, txtUsernameEmp.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(txtUsernameEmp.getText() + " already exists!");
                    alert.showAndWait();
                }
            } else {
                prepare = connect.prepareStatement(sqll);
                prepare.setString(1, txtEmailEmp.getText());
                prepare.setString(2, txtUsernameEmp.getText());
                prepare.setString(3, txtPassEmp.getText());
                result = prepare.executeQuery();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully created new account!");
                alert.showAndWait();

                txtEmailEmp.setText("");
                txtUsernameEmp.setText("");
                txtPassEmp.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void UpdateEmp(ActionEvent event) {
        String email, username, password;

        myIndex = tableEmp.getSelectionModel().getSelectedIndex();
        if (myIndex == -1) {
            return; // No row is selected
        }
        id = Integer.parseInt(String.valueOf(tableEmp.getItems().get(myIndex).getId()));

        email = txtEmailEmp.getText();
        username = txtUsernameEmp.getText();
        password = txtPassEmp.getText();
        try {
            pst = con.prepareStatement("update employee set email = ?,username = ?, password = ? where id = ? ");
            pst.setString(1, email);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setInt(4, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Log in History");

            alert.setHeaderText("Log in History");
            alert.setContentText("Updated!");

            alert.showAndWait();
            tableEmp();
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void deleteEmp(ActionEvent event) {
        int selectedIndex = tableEmp.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            // No row is selected, show an error message or prompt the user to select a row
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Employee");
        alert.setContentText("Are you sure you want to delete this employee?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            GetAccount selectedAccount = tableEmp.getItems().get(selectedIndex);
            int selectedId = Integer.parseInt(selectedAccount.getId());

            try {
                pst = con.prepareStatement("DELETE FROM employee WHERE id = ?");
                pst.setInt(1, selectedId);
                pst.executeUpdate();

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Deletion Success");
                successAlert.setHeaderText("Deletion Success");
                successAlert.setContentText("The employee has been successfully deleted.");
                successAlert.showAndWait();

                tableEmp.getItems().remove(selectedIndex);
            } catch (SQLException ex) {
                // Handle SQLException
            }
        }
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

    // Show confirmation dialog
    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    confirmation.setTitle("Confirmation");
    confirmation.setHeaderText("Delete Confirmation");
    confirmation.setContentText("Are you sure you want to delete this item?");
    
    Optional<ButtonType> result = confirmation.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try {
            pst = con.prepareStatement("DELETE FROM user WHERE id = ?");
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
                Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
                Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setWidth(369);
                stage.setHeight(511);

                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
                double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
                stage.setX(centerX - 184.5);
                stage.setY(centerY - 255.5);

                Scene scene = new Scene(root, 369, 511);

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
            setButtonColor(employee_btn, false);
            setButtonColor(userAccounts_btn, false);
            setButtonColor(bookings_btn, false);

            dashboardFrom.setVisible(true);
            employeeFrom.setVisible(false);
            useraccountFrom.setVisible(false);
            bookingsfrom.setVisible(false);

        } else if (clickedButton == employee_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(employee_btn, true);
            setButtonColor(userAccounts_btn, false);
            setButtonColor(bookings_btn, false);

            dashboardFrom.setVisible(false);
            employeeFrom.setVisible(true);
            useraccountFrom.setVisible(false);
            bookingsfrom.setVisible(false);

        } else if (clickedButton == userAccounts_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(employee_btn, false);
            setButtonColor(userAccounts_btn, true);
            setButtonColor(bookings_btn, false);

            dashboardFrom.setVisible(false);
            employeeFrom.setVisible(false);
            useraccountFrom.setVisible(true);
            bookingsfrom.setVisible(false);

        } else if (clickedButton == bookings_btn) {
            setButtonColor(dashboard_btn, false);
            setButtonColor(employee_btn, false);
            setButtonColor(userAccounts_btn, false);
            setButtonColor(bookings_btn, true);

            dashboardFrom.setVisible(false);
            employeeFrom.setVisible(false);
            useraccountFrom.setVisible(false);
            bookingsfrom.setVisible(true);

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



}
