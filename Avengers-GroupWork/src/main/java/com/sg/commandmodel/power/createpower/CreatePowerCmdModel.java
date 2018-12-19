package com.sg.commandmodel.power.createpower;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CreatePowerCmdModel {

    @NotEmpty(message = "Field cannot be empty.")
    @Length(max = 45, message = "No more than 45 characters allowed")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
