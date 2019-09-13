package old;

public class Line {

    private final double SLOPE, Y_INTERCEPT;

    public Line(double m, double b) {
        SLOPE = m;
        Y_INTERCEPT = b;
    }

    @Override public String toString() {
        return "y = " + SLOPE + "x" + ((Y_INTERCEPT == 0) ? "" : (Y_INTERCEPT < 0) ? " - " + Math.abs(Y_INTERCEPT) : " + " + Y_INTERCEPT);
    }

    public double getSlope() { return SLOPE; }

    public double getYIntercept() { return Y_INTERCEPT; }

    public double getY(double x) {
        return SLOPE * x + Y_INTERCEPT;
    }

    public double getX(double y) {
        return (y - Y_INTERCEPT) / SLOPE;
    }

}
