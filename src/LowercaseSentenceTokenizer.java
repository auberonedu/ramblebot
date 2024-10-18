import java.util.ArrayList;
import java.util.LinkedList;
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

    // Scanner input = new Scanner(System.in);

    ArrayList<String> tokenizedList = new ArrayList<>();

    while (scanner.hasNext()) {

      // The regex "(?=[,.])|(?<=[,.])" means that it is going to make the incisions
      // for the word before and after by 'looking' and createing that specific word
      // as a token, while also including the split criteria as a token as well!
      // String[] test = scanner.next().split("(?=[,.])|(?<=[,.])");

      String tokenHolder = scanner.next();
      int periodIndex = tokenHolder.indexOf('.');

      // Running the logic to ID if the period is within the token 
      if (periodIndex > -1 && periodIndex < tokenHolder.length() - 1 &&
          Character.isLetter(tokenHolder.charAt(periodIndex - 1)) &&
          Character.isLetter(tokenHolder.charAt(periodIndex + 1))) {

        // the portion before the period to be add
       tokenizedList.add(tokenHolder.toLowerCase());

        // add after the period - For some reason getting rid of this makes it work
        // except the last one
        // tokenizedList.add(tokenHolder.substring(0, periodIndex + 1).toLowerCase());

      }

      // Add the part before the period, if it exists
      // What made this work was adding a elseif.... Ready to crash out
      else if (periodIndex > 0) {
        tokenizedList.add(tokenHolder.substring(0, periodIndex).toLowerCase());

        // Add the period itself
        tokenizedList.add(".");

        // Move to the part after the period
        tokenHolder = tokenHolder.substring(periodIndex + 1);

        // Find the next period
        periodIndex = tokenHolder.indexOf('.');
      }

      else {
        tokenizedList.add(tokenHolder.toLowerCase());
      }
    }

    // Going to output a string

    return tokenizedList;
  }
}
