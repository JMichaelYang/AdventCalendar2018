import utilities.FileHandler;
import challenges.IChallenge;
import challenges.*;

/**
 * A class that reads challenge inputs from a file, translates them to outputs, and then outputs the
 * values to a corresponding output file.
 */
public class Main {

  // The relative location of the files.
  private static final String fileName = "advent03b";
  private static final String inputFile = "inputs\\" + fileName;
  private static final String outputFile = "outputs\\" + fileName;

  // The challenge object to use to run this challenge.
  private static final IChallenge challenge = new Challenge03b();

  /**
   * Reads the inputs from the specified location, translates them, and outputs them.
   *
   * @param args the command line arguments for this program
   */
  public static void main(String[] args) {
    String[] input = FileHandler.ReadInputs(inputFile);
    String[] output = challenge.translate(input);
    FileHandler.WriteOutput(outputFile, output);
  }
}
