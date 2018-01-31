package shapesandperimeters;

public class App {
    public static void main(String[] args) {
        // we cannot instantiate abstract base classes
        // for example Shape myShape = new Shape(); is not allowed

        // create a new instance of square
        // by calling its constructor and passing
        // in the side length
        Square mySquare = new Square(2);

        // get area and perimeter of square object and print out
        System.out.println(mySquare.getArea());
        System.out.println(mySquare.getPerimeter());

        // set color, get color, print out
        mySquare.setColor("Red");
        System.out.println("Square is: " + mySquare.getColor());
        System.out.println(mySquare.getSide());

        // polymorphism below is allowed because a square is also a shape
        // we can do this because of polymorphism
        // a square is a square but it is also a shape
        Shape myShape = new Square(3);

        System.out.println(myShape.getArea());
    }
}
