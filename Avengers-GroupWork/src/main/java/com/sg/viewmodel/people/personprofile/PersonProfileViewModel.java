package com.sg.viewmodel.people.personprofile;

import java.util.List;
import java.util.Map;

public class PersonProfileViewModel {

    private Long id;
    private String name;
    private String description;
    private String type;
    private List<OrgViewModel> organizations;
    private List<PowerViewModel> powers;
    private Long imageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrgViewModel> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<OrgViewModel> organizations) {
        this.organizations = organizations;
    }

    public List<PowerViewModel> getPowers() {
        return powers;
    }

    public void setPowers(List<PowerViewModel> powers) {
        this.powers = powers;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
