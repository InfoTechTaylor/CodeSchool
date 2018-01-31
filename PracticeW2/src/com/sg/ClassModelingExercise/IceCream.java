package com.sg.ClassModelingExercise;

public class IceCream {
    // Model ice cream as if the class were to be part of the control
    // system at the dairy that makes the ice cream.

    private String name;
    private String[] ingredients;
    private String[] flavorComponents;
    private String size;
    private String milkType;

    public IceCream(String name){
        this.name = name;
    } // end constructor

    public void addIngredient(String newIngredient){

    }

    public void deleteIngredient(String existingIngredient){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getFlavorComponents() {
        return flavorComponents;
    }

    public void setFlavorComponents(String[] flavorComponents) {
        this.flavorComponents = flavorComponents;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMilkType() {
        return milkType;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
    }
}
