import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fast {

    public static void main(String[] args) {
        Fast fast = new Fast();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        Point[] pointContainer = fast.readFromFile(args[0]);
//        Point startPoint = pointContainer[0];
//        startPoint.draw();
//        Double[] slopes = new Double[pointContainer.length-1];

//        for (int i = 1; i < pointContainer.length; i++) {
//            Point currentPoint = pointContainer[i];
//            slopes[i-1] = startPoint.slopeTo(currentPoint);
//            currentPoint.draw();
//        }
//
//        Arrays.sort(slopes);
        for(int t = 0; t < pointContainer.length; t++) {
            Arrays.sort(pointContainer, pointContainer[t].SLOPE_ORDER);
            Point startPoint = pointContainer[t];

            Double currentSlope = Double.NEGATIVE_INFINITY;

            for (int i = 1, counter = 1; i < pointContainer.length; i++) {
                if (currentSlope.equals(startPoint.slopeTo(pointContainer[i]))) {
                    counter++;
                } else if (counter >= 3) {
                    Point[] points = new Point[counter + 1];

                    for (int j = i - counter, u = 0; j < i; j++, u++) {
                        points[u] = pointContainer[j];
                    }
                    points[points.length - 1] = startPoint;
                    Arrays.sort(points);
                    String s = "";
                    for (Point point : points) {
                        s += " -> " + point;
                    }

                    s = s.replaceFirst(" -> ", " ");
                    System.out.println(s);
                    points[0].drawTo(points[counter - 1]);
                    currentSlope = startPoint.slopeTo(pointContainer[i]);
                    counter = 1;
                } else {
                    currentSlope = startPoint.slopeTo(pointContainer[i]);
                    counter = 1;
                }
            }
        }

    }

    private Point[] readFromFile(String fileName) {
        In file = new In(fileName);
        int amount = file.readInt();
        Point[] pointContainer = new Point[amount];
        int i = 0;

        while(!file.isEmpty()) {
            int x = file.readInt();
            int y = file.readInt();
            pointContainer[i++] = new Point(x, y);
        }
        return pointContainer;
    }
}
