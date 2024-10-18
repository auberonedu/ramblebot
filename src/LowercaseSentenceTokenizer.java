import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A tokenizer that converts text input to lowercase and splits it
 * into a list of tokens, where each token is either a word or a period.
 */
public class LowercaseSentenceTokenizer implements Tokenizer {
  /**
   * Tokenizes the text from the given Scanner. The method should
   * convert the text to lowercase and split it into words and periods.
   * Words are separated by spaces, and periods are treated as separate tokens.
   * 
   * For example:
   * If the input text is: "Hello world. This is an example."
   * The tokenized output should be: ["hello", "world", ".", "this", "is", "an",
   * "example", "."]
   * 
   * Notice that the text is converted to lowercase, and each period is treated as
   * a separate token.
   * 
   * However, a period should only be considered a separate token if it occurs at
   * the end
   * of a word. For example:
   * 
   * If the input text is: "Hello world. This is Dr.Smith's example."
   * The tokenized output should be: ["hello", "world", ".", "this", "is",
   * "dr.smith's", "example", "."]
   * 
   * The internal period in Dr.Smith's is not treated as its own token because it
   * does not occur at the end of the word.
   * 
   * @param scanner the Scanner to read the input text from
   * @return a list of tokens, where each token is a word or a period
   */
  public List<String> tokenize(Scanner scanner) {
    // TODO: Implement this function to convert the scanner's input to a list of
    // words and periods

    // we want to intialize a new ArrayList, process scanner line by line, split
    // each line by white
    // space (and periods at end of words) then add each word (and standalone
    // periods) to List and finally return the list
    List<String> tokens = new ArrayList<>();

    // System.out.println("Got called"); //for debugging purpose, debug console

    // process each line in scanner
    while (scanner.hasNextLine()) {
      // read next line and convert to lowercase
      String line = scanner.nextLine().toLowerCase();

      // split line by spaces (will handle punctuation later - wave 1)
      // \\s+: matches any sequence of whitespace characters and splits words by
      // whitespace
      String[] words = line.split("\\s+");

      for (String word : words) {
        if (word.isEmpty()) {
          continue; // skip empty tokens
        }

        // handle periods-if word ends with period add word and add period as separate
        // tokens
        // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_expressions
        // adding the punctuation function required me to read the mozilla
        // JS guide (URL provided above) - wave 3
        if (word.endsWith(".")) {
          // add word without period
          tokens.add(word.substring(0, word.length() - 1)); //cuts off period
          // add period as its own token
          tokens.add(".");
        } else {
          // add word (includes words with internal periods like Dr.Smith)
          tokens.add(word);
        }
      }
    }
    return tokens;
  }
}
