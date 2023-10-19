/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import java.time.LocalDateTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Administrator
 */
public class GetAccount {

    private final StringProperty id;
    private final StringProperty email;
    private final StringProperty username;
    private final StringProperty password;
    private final ObjectProperty<LocalDateTime> registerDate;

    public GetAccount() {
        id = new SimpleStringProperty(this, "id");
        email = new SimpleStringProperty(this, "email");
        username = new SimpleStringProperty(this, "username");
        password = new SimpleStringProperty(this, "password");
        registerDate = new SimpleObjectProperty<>(this, "registerDate");
    }


    public StringProperty idProperty() {
        return id;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String newId) {
        id.set(newId);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String newEmail) {
        email.set(newEmail);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String newUsername) {
        username.set(newUsername);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String newPassword) {
        password.set(newPassword);
    }

    public ObjectProperty<LocalDateTime> registerDateProperty() {
        return registerDate;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate.get();
    }

    public void setRegisterDate(LocalDateTime newRegisterDate) {
        registerDate.set(newRegisterDate);
    }

}
