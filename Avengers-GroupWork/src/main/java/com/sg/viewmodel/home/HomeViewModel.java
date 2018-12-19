package com.sg.viewmodel.home;

import java.util.List;

public class HomeViewModel {

    List<SightingViewModel> sightings;

    private List<Integer> pageNumbers;
    private Integer pageNumber;


    public List<SightingViewModel> getSightings() {
        return sightings;
    }

    public void setSightings(List<SightingViewModel> sightings) {
        this.sightings = sightings;
    }

    public List<Integer> getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(List<Integer> pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
