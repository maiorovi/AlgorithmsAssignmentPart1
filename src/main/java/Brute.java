import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Brute {


    public static void main(String[] args) {
        Brute brute = new Brute();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        List<Point> pointContainer = brute.readFromFile(args[0]);

        for (Point point : pointContainer) {
            point.draw();
        }

        for(int i = 0; i < pointContainer.size(); i++ ) {
            for (int j = i + 1; j < pointContainer.size(); j++ ) {
                for(int w = j + 1; w < pointContainer.size(); w++) {
                    for (int z = w + 1; z < pointContainer.size(); z++) {
                        Point[] points = new Point[4];
                        points[0] = pointContainer.get(i);
                        points[1] = pointContainer.get(j);
                        points[2] = pointContainer.get(w);
                        points[3] = pointContainer.get(z);

                        double slope = points[0].slopeTo(points[1]);
                        double slope2 = points[0].slopeTo(points[2]);
                        double slope3 = points[0].slopeTo(points[3]);



                        if (slope == slope2 && slope == slope3 && slope2 == slope3) {
                            Arrays.sort(points);
                            System.out.println(points[0] + " -> " + points[1] +" -> "+ points[2] +" -> " + points[3]);

                            points[0].drawTo(points[3]);
                        }
                    }
                }
            }
        }
    }

    private  List<Point> readFromFile(String fileName) {
        In file = new In(fileName);
        int amount = file.readInt();
        List<Point> pointContainer = new ArrayList<Point>(amount);

        while(!file.isEmpty()) {
            int x = file.readInt();
            int y = file.readInt();
            pointContainer.add(new Point(x, y));
        }
        return pointContainer;
    }
}
