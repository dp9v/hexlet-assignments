package exercise;

import java.util.Map;

// BEGIN
public class Circle {
    private final Point center;
    private final int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException();
        }

        return Math.PI * radius * radius;
    }
}
// END
