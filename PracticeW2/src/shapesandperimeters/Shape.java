package shapesandperimeters;

public abstract class Shape {
    // every class that inherits from Shape will have a color
    private String color;

    // every class that inherits from Shape
    // will be required to implement the following two methods:
    public abstract int getArea();

    public abstract int getPerimeter();

    // every class that inherits from Shape
    // will have the getter and setter for color
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
