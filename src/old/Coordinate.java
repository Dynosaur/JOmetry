package old;

public class Coordinate {

    private double X, Y;

    public Coordinate(double x, double y) {
        X = x;
        Y = y;
    }

    @Override public String toString() {
        return "(" + X + ", " + Y + ")";
    }

    public double getX() { return X; }

    public double getY() { return Y; }

    public double distanceTo(Coordinate other) {
        return Math.sqrt(Math.pow(other.X - X, 2) + Math.pow(other.Y - Y, 2));
    }

}
