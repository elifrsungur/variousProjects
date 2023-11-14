package polymorphism;


class Polygon{
    public void render(){
        System.out.println("rendering polygon.");
    }
}

class Square extends Polygon{
    public void render(){
        System.out.println("rendering square");
    }
}

class Circle extends Polygon{
    public void render(){
        System.out.println("rendering circle");
    }
}

public class Main {
    public static void main(String[] args) {
        Square square = new Square();
        square.render();

        Circle circle = new Circle();
        circle.render();
    }
}
