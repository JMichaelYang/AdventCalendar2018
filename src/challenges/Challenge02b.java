package challenges;

public class Challenge02b implements IChallenge {

  @Override
  public String[] translate(String[] input) {
    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < i; j++) {
        if (difference(input[i], input[j]) == 1) {
          String[] ret = {findSimilar(input[i], input[j])};
          return ret;
        }
      }
    }

    throw new IllegalStateException("Could not find two strings with one difference.");
  }

  private int difference(String one, String two) {
    char[] oneChars = one.toCharArray();
    char[] twoChars = two.toCharArray();
    int totalDiff = 0;

    for (int i = 0; i < oneChars.length && i < twoChars.length; i++) {
      if (oneChars[i] != twoChars[i]) {
        totalDiff++;
      }
    }

    return totalDiff;
  }

  private String findSimilar(String one, String two) {
    char[] oneChars = one.toCharArray();
    char[] twoChars = two.toCharArray();
    int index = -1;

    for (int i = 0; i < oneChars.length && i < twoChars.length; i++) {
      if (oneChars[i] != twoChars[i]) {
        index = i;
        break;
      }
    }

    if (index < 0) {
      throw new IllegalStateException("Strings messed up.");
    }

    return one.substring(0, index) + one.substring(index + 1);
  }
}
