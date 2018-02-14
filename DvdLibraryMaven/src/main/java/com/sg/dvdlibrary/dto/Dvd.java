package com.sg.dvdlibrary.dto;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

public class Dvd {

    private String title;
    private LocalDate releaseDate;
    private String ratingMPAA;
    private String directorName;
    private String studio;
    private String userRating;


    // constructors
    public Dvd(String title){
        this.title = title;
    }


    public Dvd(String title, LocalDate releaseDate, String ratingMPAA, String directorName, String studio, String userRating) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.ratingMPAA = ratingMPAA;
        this.directorName = directorName;
        this.studio = studio;
        this.userRating = userRating;
    }


    /* Getters and Setters ******************/

    public String getTitle() {
        return title;
    }

    // COMMENTED OUT TO MAKE TITLE READ ONLY SINCE IT IS THE KEY IN HASHMAP
//    public void setTitle(String title) {
//        this.title = title;
//    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRatingMPAA() {
        return ratingMPAA;
    }

    public void setRatingMPAA(String ratingMPAA) {
        this.ratingMPAA = ratingMPAA;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dvd dvd = (Dvd) o;
        return Objects.equals(title, dvd.title) &&
                Objects.equals(releaseDate, dvd.releaseDate) &&
                Objects.equals(ratingMPAA, dvd.ratingMPAA) &&
                Objects.equals(directorName, dvd.directorName) &&
                Objects.equals(studio, dvd.studio) &&
                Objects.equals(userRating, dvd.userRating);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, releaseDate, ratingMPAA, directorName, studio, userRating);
    }


    @Override
    public String toString() {
        return "Dvd{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", ratingMPAA='" + ratingMPAA + '\'' +
                ", directorName='" + directorName + '\'' +
                ", studio='" + studio + '\'' +
                ", userRating='" + userRating + '\'' +
                '}';
    }
}
