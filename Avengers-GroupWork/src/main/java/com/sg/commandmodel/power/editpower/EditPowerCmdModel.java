package com.sg.commandmodel.power.editpower;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class EditPowerCmdModel {

    @NotNull(message = "Does not contain id")
    private Long id;
    @NotEmpty(message = "Field cannot be empty.")
    @Length(max = 50, message = "No more than 50 characters allowed")
    private String name;


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
}
