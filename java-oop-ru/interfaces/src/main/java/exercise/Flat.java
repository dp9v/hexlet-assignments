package exercise;

// BEGIN
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home another) {
        var dif = this.getArea() - another.getArea();
        return dif > 0 ? 1 : dif < 0 ? -1 : 0;
    }

    @Override
    public String toString() {
        return "Квартира площадью %.1f метров на %d этаже".formatted(getArea(), floor).replace(",", ".");
    }
}
// END
