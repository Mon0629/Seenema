/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemamanagementsystem.CInemaManagementSystem;

import static cinemamanagementsystem.CInemaManagementSystem.data.id;
import com.mysql.cj.jdbc.Blob;
import javafx.scene.image.Image;

/**
 *
 * @author Administrator
 */
public class movieListData {

    private Integer id;
    private String IdMovies;
    private String title;
    private String genre;
    private Double price;
    private String sypnosis;
    private String duration;
    private String time;
    private String director;
    private String cast;
    private String rating;
    private Blob image;
    private Blob imageBG;
    private String viewingDate;
    private String nextViewingDate;

    public movieListData(Integer id, String IdMovies, String title,
            String genre, Double price, String sypnosis, String duration,
            String time, String director, String cast, String rating, Blob image, Blob imagebg, String viewingDate, String nextViewingDate) {
        this.id = id;
        this.IdMovies = IdMovies;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.sypnosis = sypnosis;
        this.duration = duration;
        this.time = time;
        this.director = director;
        this.cast = cast;
        this.rating = rating;
        this.image = image;
        this.imageBG = imagebg;
        this.viewingDate = viewingDate;
        this.nextViewingDate = nextViewingDate;
    }

    public movieListData(String sypnosis, String genre, String director, String cast, String rating, Blob imageBG) {

        this.sypnosis = sypnosis;
        this.genre = genre;
        this.director = director;
        this.cast = cast;
        this.rating = rating;
        this.imageBG = imageBG;
    }

    public movieListData(String title, Double price, String duration, String time, String viewingDate, String nextViewingDate) {
        this.title = title;
        this.price = price;
        this.duration = duration;
        this.time = time;
        this.viewingDate = viewingDate;
        this.nextViewingDate = nextViewingDate;
    }

    public movieListData(String IdMovies, String title, String duration, String viewingDate, String nextViewingDate, String time, Double price) {
        this.IdMovies = IdMovies;
        this.title = title;
        this.duration = duration;
        this.viewingDate = viewingDate;
        this.nextViewingDate = nextViewingDate;
        this.time = time;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdMovies() {
        return IdMovies;
    }

    public void setIdMovies(String IdMovies) {
        this.IdMovies = IdMovies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    public String getSypnosis() {
        return sypnosis;
    }

    public void setSypnosis(String sypnosis) {
        this.sypnosis = sypnosis;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Blob getImageBG() {
        return imageBG;
    }

    public void setImageBG(Blob imageBG) {
        this.imageBG = imageBG;
    }

    public String getViewingDate() {
        return viewingDate;
    }

    public void setViewingDate(String viewingDate) {
        this.viewingDate = viewingDate;
    }

    public String getNextViewingDate() {
        return nextViewingDate;
    }

    public void setNextViewingDate(String nextViewingDate) {
        this.nextViewingDate = nextViewingDate;
    }
}
