package challenges;

import java.util.ArrayList;
import java.util.List;

public class Challenge05a implements IChallenge {

  @Override
  public String[] translate(String[] input) {
    char[] ch = input[0].toCharArray();
    List<Character> chars = new ArrayList<>();
    for (char c : ch) {
      chars.add(c);
    }

    if (chars.size() < 3) {
      String[] ret = {String.valueOf(chars.size())};
      return ret;
    }

    for (int i = 0; i < chars.size() - 1; ) {
      char current = chars.get(i);
      char next = chars.get(i + 1);

      if ((Character.toLowerCase(current) == Character.toLowerCase(next)) &&
          (Character.isLowerCase(current) ^ Character.isLowerCase(next))) {
        chars.remove(i + 1);
        chars.remove(i);
        i = Math.max(0, i - 1);
      } else {
        i++;
      }
    }

    String[] ret = {String.valueOf(chars.size())};
    return ret;
  }
}
