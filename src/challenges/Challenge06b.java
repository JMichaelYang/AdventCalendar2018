
package challenges;

import java.util.ArrayList;
import java.util.List;

public class Challenge06b implements IChallenge {

  @Override
  public String[] translate(String[] input) {
    List<Point> points = new ArrayList<>();
    for (String s : input) {
      String[] parts = s.split(", ");
      points.add(new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
    }

    int maxX = -1;
    int maxY = -1;
    for (Point point : points) {
      if (point.x > maxX) {
        maxX = point.x;
      }
      if (point.y > maxY) {
        maxY = point.y;
      }
    }

    int[][] totalDistances = new int[maxX][maxY];

    for (int i = 0; i < maxX; i++) {
      for (int j = 0; j < maxY; j++) {
        Point p = new Point(i, j);
        for (Point point : points) {
          totalDistances[i][j] += point.getManhattanDistance(p);
        }
      }
    }

    int totalArea = 0;
    for (int i = 0; i < maxX; i++) {
      for (int j = 0; j < maxY; j++) {
        if (totalDistances[i][j] < 10000) {
          totalArea++;
        }
      }
    }

    String[] ret = {String.valueOf(totalArea)};
    return ret;
  }

  class Point {

    final int x;
    final int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    int getManhattanDistance(Point other) {
      int x = Math.abs(other.x - this.x);
      int y = Math.abs(other.y - this.y);
      return x + y;
    }
  }
}
