/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Administrator
 */
public class getFeedback {
    private final StringProperty id;
    private final StringProperty overall;
    private final StringProperty customer;
    private final StringProperty transparency;
    private final StringProperty service;
    private final StringProperty pickup;
    private final StringProperty suggestion;
    private final StringProperty selectedValue;

    public getFeedback() {
        this.id = new SimpleStringProperty();
        this.overall = new SimpleStringProperty();
        this.customer = new SimpleStringProperty();
        this.transparency = new SimpleStringProperty();
        this.service = new SimpleStringProperty();
        this.pickup = new SimpleStringProperty();
        this.suggestion = new SimpleStringProperty();
        this.selectedValue = new SimpleStringProperty();
    }
    
    public String getDataId(){
        return id.get();
    }
    
    public void setDataId(String id){
        this.id.set(id);
    }
    
    public StringProperty getDatabaseId(){
        return id;
    }
    
    public String getDataOverall(){
        return overall.get();
    }
    
    public void setDataOverall(String overall){
        this.overall.set(overall);
    }
    
    public StringProperty getDatabaseOverall(){
        return overall;
    }
    
    public String getDataCustomer(){
        return customer.get();
    }
    
    public void setDataCustomer(String customer){
        this.customer.set(customer);
    }
    
    public StringProperty getDatabaseCustomer(){
        return customer;
    }
    
    public String getDataTransparency(){
        return transparency.get();
    }
    
    public void setDataTransparency(String transparency){
        this.transparency.set(transparency);
    }
    
    public StringProperty getDatabaseTransparency(){
        return transparency;
    }
    
    public String getDataService(){
        return service.get();
    }
    
    public void setDataService(String service){
        this.service.set(service);
    }
    
    public StringProperty getDatabaseService(){
        return service;
    }
    
    public String getDataPickup(){
        return pickup.get();
    }
    
    public void setDataPickup(String pickup){
        this.pickup.set(pickup);
    }
    
    public StringProperty getDatabasePickup(){
        return pickup;
    }
    
    public String getDataSuggestion(){
        return suggestion.get();
    }
    
    public void setDataSuggestion(String suggestion){
        this.suggestion.set(suggestion);
    }
    
    public StringProperty getDatabaseSuggestion(){
        return suggestion;
    }
    
    public String getDataSelectedValuen(){
        return selectedValue.get();
    }
    
    public void setDataSelectedValue(String selectedValue){
        this.selectedValue.set(selectedValue);
    }
    
    public StringProperty getDatabaseSelectedValue(){
        return selectedValue;
    }

    
}
