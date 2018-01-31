package shapesandperimeters;

public class Square extends Shape {

    private int side;

    // constructor that ensures when a square is instantiated,
    // it must provide a side
    public Square(int side){
        this.side = side;
    }

    @Override
    public int getArea() {
        return side * side;
    }

    @Override
    public int getPerimeter() {
        return side * 4;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }
}
