package src.myTests;

public class LoveRectanglesIntersection {

    public static Rectangle findRectangularOverlap(Rectangle rect1, Rectangle rect2) {

        // get the x and y overlap points and lengths
        RangeOverlap xOverlap = findRangeOverlap(rect1.getLeftX(), rect1.getWidth(),
                rect2.getLeftX(), rect2.getWidth());
        RangeOverlap yOverlap = findRangeOverlap(rect1.getBottomY(), rect1.getHeight(),
                rect2.getBottomY(), rect2.getHeight());

        // return "zero" rectangle if there is no overlap
        if (xOverlap.getLength() == 0 || yOverlap.getLength() == 0) {
            return new Rectangle();
        }

        return new Rectangle(
                xOverlap.getStartPoint(),
                yOverlap.getStartPoint(),
                xOverlap.getLength(),
                yOverlap.getLength()
        );
    }
    public static XOverlap findXOverlap(int x1, int width1, int x2, int width2) {

        // find the highest ("rightmost") start point and lowest ("leftmost") end point
        int highestStartPoint = Math.max(x1, x2);
        int lowestEndPoint = Math.min(x1 + width1, x2 + width2);

        // return empty overlap if there is no overlap
        if (highestStartPoint >= lowestEndPoint) {
            return new XOverlap(0, 0);
        }

        // compute the overlap width
        int overlapWidth = lowestEndPoint - highestStartPoint;

        return new XOverlap(highestStartPoint, overlapWidth);
    }
    public static RangeOverlap findRangeOverlap(int point1, int length1, int point2, int length2) {

        // find the highest start point and lowest end point.
        // the highest ("rightmost" or "upmost") start point is
        // the start point of the overlap.
        // the lowest end point is the end point of the overlap.
        int highestStartPoint = Math.max(point1, point2);
        int lowestEndPoint = Math.min(point1 + length1, point2 + length2);

        // return empty overlap if there is no overlap
        if (highestStartPoint >= lowestEndPoint) {
            return new RangeOverlap(0, 0);
        }

        // compute the overlap length
        int overlapLength = lowestEndPoint - highestStartPoint;

        return new RangeOverlap(highestStartPoint, overlapLength);
    }
}

class Rectangle {

    // coordinates of bottom left corner
    private int leftX;
    private int bottomY;

    // dimensions
    private int width;
    private int height;

    public Rectangle() {}

    public Rectangle(int leftX, int bottomY, int width, int height) {
        this.leftX = leftX;
        this.bottomY = bottomY;
        this.width  = width;
        this.height = height;
    }

    public int getLeftX() {
        return leftX;
    }

    public int getBottomY() {
        return bottomY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

class XOverlap {

    private int startPoint;
    private int width;

    public XOverlap(int startPoint, int width) {
        this.startPoint = startPoint;
        this.width = width;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public int getWidth() {
        return width;
    }
}

class RangeOverlap {

    private int startPoint;
    private int length;

    public RangeOverlap(int highestStartPoint, int overlapLength) {
        this.startPoint = highestStartPoint;
        this.length = overlapLength;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public int getLength() {
        return length;
    }
}