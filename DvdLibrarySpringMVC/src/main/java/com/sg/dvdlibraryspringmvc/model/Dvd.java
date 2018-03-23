package com.sg.dvdlibraryspringmvc.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.Objects;

public class Dvd {

    private int dvdId;

    @NotEmpty(message="Please enter a title for the DVD.")
    private String title;

    @Length(min=4, max=4, message="Please enter a 4-digit year.")
    private LocalDate releaseDate;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseYear) {
        this.releaseDate = releaseYear;
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
                Objects.equals(releaseDate, dvd.releaseDate) &&
                Objects.equals(director, dvd.director) &&
                Objects.equals(rating, dvd.rating);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dvdId, title, releaseDate, director, rating);
    }
}
