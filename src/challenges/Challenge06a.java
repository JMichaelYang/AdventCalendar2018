package challenges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Challenge06a implements IChallenge {

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

    int[][] distances = new int[maxX][maxY];

    for (int i = 0; i < maxX; i++) {
      for (int j = 0; j < maxY; j++) {
        int[] dist = new int[points.size()];
        Point p = new Point(i, j);

        for (int k = 0; k < points.size(); k++) {
          int man = p.getManhattanDistance(points.get(k));
          dist[k] = man;
        }

        int minDist = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int k = 0; k < points.size(); k++) {
          if (dist[k] < minDist) {
            minDist = dist[k];
            minIndex = k;
          } else if (dist[k] == minDist) {
            minIndex = -1;
          }
        }

        distances[i][j] = minIndex;
      }
    }

    List<Integer> dqInts = new ArrayList<>();
    for (int i = 0; i < maxX; i++) {
      int index = distances[i][0];
      if (!dqInts.contains(index)) {
        dqInts.add(index);
      }
      index = distances[i][maxY - 1];
      if (!dqInts.contains(index)) {
        dqInts.add(index);
      }
    }
    for (int i = 0; i < maxY; i++) {
      int index = distances[0][i];
      if (!dqInts.contains(index)) {
        dqInts.add(index);
      }
      index = distances[maxX - 1][0];
      if (!dqInts.contains(index)) {
        dqInts.add(index);
      }
    }

    HashMap<Integer, Integer> areas = new HashMap<>();
    for (int i = 0; i < maxX; i++) {
      for (int j = 0; j < maxY; j++) {
        int index = distances[i][j];
        if (!dqInts.contains(index)) {
          if (!areas.containsKey(index)) {
            areas.put(index, 1);
          } else {
            int z = areas.get(index);
            z++;
            areas.put(index, z);
          }
        }
      }
    }

    int largest = -1;
    for (int i : areas.keySet()) {
      if (areas.get(i) > largest) {
        largest = areas.get(i);
      }
    }

    String[] ret = {String.valueOf(largest)};
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
