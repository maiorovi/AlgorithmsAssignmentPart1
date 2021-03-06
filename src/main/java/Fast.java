import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Fast {

    public static void main(String[] args) {
        Fast fast = new Fast();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        Point[] pointContainer = fast.readFromFile(args[0]);
        Point[] tempPoint = new Point[pointContainer.length];

        for (int i = 0; i < pointContainer.length; i++) {
            pointContainer[i].draw();
            tempPoint[i] = pointContainer[i];
        }

        HashSet usedLines = new HashSet();

        for(int t = 0; t < pointContainer.length; t++) {
            Arrays.sort(pointContainer, tempPoint[t].SLOPE_ORDER);
            Point startPoint = tempPoint[t];

            Double currentSlope = Double.NEGATIVE_INFINITY;

            for (int i = 1, counter = 1; i <= pointContainer.length; i++) {
                if ( i!= pointContainer.length && currentSlope.equals(startPoint.slopeTo(pointContainer[i]))) {
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

                    if (!usedLines.contains(s)) {
                        System.out.println(s);
                        points[0].drawTo(points[counter]);
                        usedLines.add(s);
                    }

                    if (i == pointContainer.length)
                        break;
                    currentSlope = startPoint.slopeTo(pointContainer[i]);
                    counter = 1;
                } else {
                    if (i == pointContainer.length)
                        break;
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
