/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import java.time.LocalDateTime;
import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class bookinglist {
    
    private final StringProperty UserID;
    private final StringProperty TicketID;
    private final StringProperty IDMovies;
    private final StringProperty MovieName;
    private final StringProperty ViewingDate;
    private final StringProperty quantity;
    private final StringProperty price;
    private final StringProperty total;
     private final ObjectProperty<LocalDateTime> BookingDate;
    
    public bookinglist() {
        this.UserID = new SimpleStringProperty();
        this.TicketID = new SimpleStringProperty();
        this.IDMovies = new SimpleStringProperty();
        this.MovieName = new SimpleStringProperty();
        this.ViewingDate = new SimpleStringProperty();
        this.quantity = new SimpleStringProperty();
        this.price = new SimpleStringProperty();
        this.total = new SimpleStringProperty();
        this.BookingDate = new SimpleObjectProperty<>();
    }
    
    public String getUserID(){
        return UserID.get(); 
    }
    
    public void setUserId(String UserID){
        this.UserID.set(UserID);
    } 
    
    public StringProperty bookinglistUserID(){
        return UserID;
    }
    
    public String getTicketID(){
        return TicketID.get(); 
    }
    
    public void setTicketId(String TicketID){
        this.TicketID.set(TicketID);
    } 
    
    public StringProperty bookinglistTicketID(){
        return TicketID;
    }
    
    public String getIDMovies(){
        return IDMovies.get(); 
    }
    
    public void setIDMovies(String IDMovies){
        this.IDMovies.set(IDMovies);
    } 
    
    public StringProperty bookinglistIDMovies(){
        return IDMovies;
    }
    
    public String getMovieName(){
        return MovieName.get(); 
    }
    
    public void setMovieName(String MovieName){
        this.MovieName.set(MovieName);
    } 
    
    public StringProperty bookinglistMovieName(){
        return MovieName;
    }
    
    public String getViewingDate(){
        return ViewingDate.get(); 
    }
    
    public void setViewingDate(String ViewingDate){
        this.ViewingDate.set(ViewingDate);
    } 
    
    public StringProperty bookinglistViewingDate(){
        return ViewingDate;
    }
    
    public String getQuantity(){
        return quantity.get(); 
    }
    
    public void setQuantity(String quantity){
        this.quantity.set(quantity);
    } 
    
    public StringProperty bookinglistQuantity(){
        return quantity;
    }
    
    public String getPrice(){
        return price.get(); 
    }
    
    public void setPrice(String price){
        this.price.set(price);
    } 
    
    public StringProperty bookinglistPrice(){
        return price;
    }
    
    public String getTotal(){
        return total.get(); 
    }
    
    public void setTotal (String total){
        this.total.set(total);
    } 
    
    public StringProperty bookinglistTotal(){
        return total;
    }
    
    public ObjectProperty<LocalDateTime> BookingDateProperty() {
    return BookingDate;
    }

    public LocalDateTime getBookingDate() {
        return BookingDate.get();
    }

    public void setBookingDate(LocalDateTime newBookingDate) {
        BookingDate.set(newBookingDate);
    }
}
