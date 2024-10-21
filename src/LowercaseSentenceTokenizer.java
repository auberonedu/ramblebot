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
   * 
   * For example:
   * If the input text is: "Hello world. This is an example."
   * The tokenized output should be: ["hello", "world", ".", "this", "is", "an", "example", "."]
   * 
   * Notice that the text is converted to lowercase, and each period is treated as a separate token.
   * 
   * However, a period should only be considered a separate token if it occurs at the end
   * of a word. For example:
   * 
   * If the input text is: "Hello world. This is Dr.Smith's example."
   * The tokenized output should be: ["hello", "world", ".", "this", "is", "dr.smith's", "example", "."]
   * 
   * The internal period in Dr.Smith's is not treated as its own token because it does not occur at the end of the word.
   * 
   * @param scanner the Scanner to read the input text from
   * @return a list of tokens, where each token is a word or a period
   */
  public List<String> tokenize(Scanner scanner) {
    List<String> tokens = new ArrayList<>();
    
    // Iterate through each word in the scanner
    while (scanner.hasNext()) {
      String word = scanner.next().toLowerCase();
      
      // Check if the word ends with a period and is not an internal period
      if (word.endsWith(".") && word.length() > 1 && !isInternalPeriod(word)) {
        // Add the word without the period
        tokens.add(word.substring(0, word.length() - 1));
        // Add the period as a separate token
        tokens.add(".");
      } else {
        // Add the word as is (including internal periods)
        tokens.add(word);
      }
    }
    
    return tokens;
  }

  /**
   * Helper method to check if a period is internal (e.g., in "Dr.Smith").
   * It ensures the period is inside the word, not at the end.
   *
   * @param word the word to check
   * @return true if the period is internal, false if it is at the end of the word
   */
  private boolean isInternalPeriod(String word) {
    return word.matches("[a-zA-Z]+\\.[a-zA-Z]+");  // Detects a word with a period in the middle (e.g., "Dr.Smith")
  }
}