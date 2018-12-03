package challenges;

import java.util.ArrayList;
import java.util.HashMap;
import utilities.Section;

public class Challenge03b implements IChallenge {

  @Override
  public String[] translate(String[] input) {
    HashMap<Section, Integer> sections = new HashMap<>();

    for (String s : input) {
      String num = s.split("@")[0];
      String part = s.split("@")[1];
      String loc = part.split(":")[0];
      String dim = part.split(":")[1];
      int id = Integer.parseInt(num.substring(1, num.length() - 1));
      int left = Integer.parseInt(loc.split(",")[0].substring(1));
      int top = Integer.parseInt(loc.split(",")[1]);
      int right = left + Integer.parseInt(dim.split("x")[0].substring(1)) - 1;
      int bottom = top + Integer.parseInt(dim.split("x")[1]) - 1;
      sections.put(new Section(id, left, right, top, bottom), 0);
    }

    int xMax = -1;
    int yMax = -1;

    for (Section s : sections.keySet()) {
      if (s.right > xMax) {
        xMax = s.right;
      }
      if (s.bottom > yMax) {
        yMax = s.bottom;
      }
    }

    int[][] fabric = new int[xMax + 1][yMax + 1];

    for(Section s : sections.keySet()) {
      Section.setPoints(fabric, s);
    }

    Section fin = null;

    for(Section s : sections.keySet()) {
      if(Section.setPoints(fabric, s)) {
        fin = s;
      }
    }

    if(fin == null) {
      throw new IllegalStateException("No single section was found.");
    }

    String[] ret = { String.valueOf(fin.id) };
    return ret;
  }
}