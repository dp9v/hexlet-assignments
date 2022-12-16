package exercise;

// BEGIN
public class Segment {
    private Point beginPoint;
    private Point endPoint;

    public Segment(Point begin, Point end) {
        this.beginPoint = begin;
        this.endPoint = end;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        var midX = (endPoint.getX() + beginPoint.getX()) / 2;
        var midY = (endPoint.getY() + beginPoint.getY()) / 2;
        return new Point(midX, midY);
    }
}
// END
