package challenges;

public class Challenge01a implements IChallenge {

  @Override
  public String[] translate(String[] input) {
    String[] answer = new String[1];
    int total = 0;

    for (String s : input) {
      total += Integer.parseInt(s);
    }

    answer[0] = String.valueOf(total);
    return answer;
  }
}
