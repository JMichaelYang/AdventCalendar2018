package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that handles the reading of inputs.
 */
public class FileHandler {

  /**
   * The location that the program was run from.
   */
  private static final String currentPath = System.getProperty("user.dir");

  /**
   * Reads the inputs from a given file using the parsing method from the input object.
   *
   * @param path the path of the input file relative to the directory that the program was run from
   * @return An array of all of the distinct lines of the input.
   */
  public static String[] ReadInputs(String path) {
    // Stores all of the input strings.
    ArrayList<String> inputList = new ArrayList<>();

    try {
      // A reference to the file in which the inputs are stored.
      File file = new File(currentPath + "\\" + path + ".in");

      // A buffered reader to read from the file.
      BufferedReader reader = new BufferedReader(new FileReader(file));

      // Read all of the inputs from the file.
      String s = null;
      do {
        s = reader.readLine();
        if (s != null) {
          inputList.add(s);
        }
      } while (s != null);

      // Close the reader.
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Convert the input string list into an array.
    String[] list = new String[inputList.size()];
    return inputList.toArray(list);
  }

  /**
   * Writes the outputs of a challenge to an output file.
   *
   * @param path the location at which the outputs should be written
   * @param output the outputs that should be written
   */
  public static void WriteOutput(String path, String[] output) {
    try {
      // A reference to the file that the outputs should be written to.
      File file = new File(currentPath + "\\" + path + ".out");

      // A buffered writer object to write the new file
      BufferedWriter outputWriter = new BufferedWriter(new FileWriter(file));

      // Write all of the outputs to the file.
      for (int i = 0; i < output.length; i++) {
        outputWriter.write(output[i] + "\n");
      }

      // Close the new file
      outputWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
