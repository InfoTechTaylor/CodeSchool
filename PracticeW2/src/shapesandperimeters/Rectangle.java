package shapesandperimeters;

public class Rectangle extends Shape{

    private int length;
    private int width;

    // constructor requiring length and width when instantiating a rectangle object
    public Rectangle(int length, int width){
        this.length = length;
        this.width = width;
        super.setColor("Orange");
    }

    @Override
    public int getArea() {
        return length * width;
    }

    @Override
    public int getPerimeter() {
        return (length + width) * 2;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
