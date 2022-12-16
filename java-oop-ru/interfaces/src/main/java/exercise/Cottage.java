package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        var dif = this.getArea() - another.getArea();
        return dif > 0 ? 1 : dif < 0 ? -1 : 0;
    }

    @Override
    public String toString() {
        return "%d этажный коттедж площадью %.1f метров".formatted(floorCount, getArea()).replace(",", ".");
    }
}
// END
