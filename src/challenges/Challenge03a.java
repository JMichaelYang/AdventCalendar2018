package challenges;

import java.util.ArrayList;
import utilities.Section;

public class Challenge03a implements IChallenge {

  @Override
  public String[] translate(String[] input) {
    ArrayList<Section> sections = new ArrayList<>();

    for (String s : input) {
      String num = s.split("@")[0];
      String part = s.split("@")[1];
      String loc = part.split(":")[0];
      String dim = part.split(":")[1];
      int id = Integer.parseInt(num.substring(1));
      int left = Integer.parseInt(loc.split(",")[0].substring(1));
      int top = Integer.parseInt(loc.split(",")[1]);
      int right = left + Integer.parseInt(dim.split("x")[0].substring(1)) - 1;
      int bottom = top + Integer.parseInt(dim.split("x")[1]) - 1;
      sections.add(new Section(id, left, right, top, bottom));
    }

    int xMax = -1;
    int yMax = -1;

    for (Section s : sections) {
      if (s.right > xMax) {
        xMax = s.right;
      }
      if (s.bottom > yMax) {
        yMax = s.bottom;
      }
    }

    int[][] fabric = new int[xMax + 1][yMax + 1];

    for(Section s : sections) {
      Section.setPoints(fabric, s);
    }

    int total = 0;

    for(int i = 0; i <= xMax; i++) {
      for(int j = 0; j <= yMax; j++) {
        if(fabric[i][j] > 1) {
          total++;
        }
      }
    }

    String[] ret = { String.valueOf(total) };
    return ret;
  }
}
