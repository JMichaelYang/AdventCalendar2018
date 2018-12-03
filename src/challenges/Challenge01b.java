package challenges;

import java.util.ArrayList;

public class Challenge01b implements IChallenge {

  @Override
  public String[] translate(String[] input) {
    ArrayList<Integer> seen = new ArrayList<>();
    int total = 0;

    while (true) {
      for (String s : input) {
        total += Integer.valueOf(s);
        if (seen.contains(total)) {
          String[] ret = {String.valueOf(total)};
          return ret;
        } else {
          seen.add(total);
        }
      }
    }
  }
}
