package challenges;

import java.util.HashMap;

public class Challenge02a implements IChallenge {

  @Override
  public String[] translate(String[] input) {
    int numTwos = 0;
    int numThrees = 0;

    for (String s : input) {
      char[] chars = s.toCharArray();
      HashMap<Character, Integer> freq = new HashMap<>();

      for(char c : chars) {
        if(freq.keySet().contains(c)) {
          Integer i = freq.get(c);
          i++;
          freq.put(c, i);
        } else {
          freq.put(c, 1);
        }
      }

      boolean hasTwo = false;
      boolean hasThree = false;

      for(Character c : freq.keySet()) {
        int i = freq.get(c);
        if(i == 2) {
          hasTwo = true;
        } else if(i == 3) {
          hasThree = true;
        }
      }

      if(hasTwo) {
        numTwos++;
      }
      if(hasThree) {
        numThrees++;
      }
    }

    String[] ret = {String.valueOf(numTwos * numThrees)};
    return ret;
  }
}
