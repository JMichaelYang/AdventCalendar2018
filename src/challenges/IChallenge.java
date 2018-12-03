package challenges;

/**
 * An interface for an object that performs the work of converting a challenge input to output.
 */
public interface IChallenge {

  /**
   * Translates the challenge input to its output.
   *
   * @param input the input that will be used for this translation.
   * @return The results of the translation.
   */
  String[] translate(String[] input);
}
