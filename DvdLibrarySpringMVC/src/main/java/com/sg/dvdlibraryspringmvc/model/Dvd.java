package com.sg.dvdlibraryspringmvc.model;

import java.time.LocalDate;
import java.util.Objects;

public class Dvd {

    private int dvdId;
    private String title;
    private String releaseYear;
    private String director;
    private String rating;
    private String notes;

    public int getDvdId() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dvd dvd = (Dvd) o;
        return dvdId == dvd.dvdId &&
                Objects.equals(title, dvd.title) &&
                Objects.equals(releaseYear, dvd.releaseYear) &&
                Objects.equals(director, dvd.director) &&
                Objects.equals(rating, dvd.rating);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dvdId, title, releaseYear, director, rating);
    }
}
