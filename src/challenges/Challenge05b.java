package challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Challenge05b implements IChallenge {

  @Override
  public String[] translate(String[] input) {
    List<Character> characters = new ArrayList<>();
    for (Character c : input[0].toCharArray()) {
      characters.add(c);
    }
    List<List<Character>> polymers = new ArrayList<>();
    for (int i = 0; i < 26; i++) {
      polymers.add(new ArrayList<>(characters));
      char letter = (char) (i + 97);
      polymers.get(i).removeIf(c -> Character.toLowerCase(c) == letter);
      react(polymers.get(i));
    }

    Collections.sort(polymers, Comparator.comparingInt(c -> c.size()));
    int minLen = polymers.get(0).size();
    String[] str = {String.valueOf(minLen)};
    return str;
  }

  void react(List<Character> polymer) {
    if (polymer.size() < 2) {
      return;
    }

    for (int i = 0; i < polymer.size() - 1; ) {
      char current = polymer.get(i);
      char next = polymer.get(i + 1);

      if ((Character.toLowerCase(current) == Character.toLowerCase(next)) &&
          (Character.isLowerCase(current) ^ Character.isLowerCase(next))) {
        polymer.remove(i + 1);
        polymer.remove(i);
        i = Math.max(0, i - 1);
      } else {
        i++;
      }
    }
  }
}
