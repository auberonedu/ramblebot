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
    // Completed: TODO: Implement this function to convert the scanner's input to a list of words and periods

    // Instantiates a new String ArrayList to hold all the tokens/words from Scanner
    List<String> words = new ArrayList<>();

    // Loops through the Scanners input as long as there are more lines
    while (scanner.hasNextLine()) {
      // Reads in and saves each work/token in the word variable using scanner and converting it to lowercase
      String word = scanner.next().toLowerCase();
      
      // Checks whether a word/token ends with a period using the endsWith method
      if (word.endsWith(".")) {
        // Saves the part of the word without a period in the word1 variable using substring and length
        // Found the endsWith() method at https://www.w3schools.com/java/ref_string_endswith.asp
        String word1 = word.substring(0, word.length() - 1);

        // Adds the word without the period and then adds the period as a seperate entry into the ArrayList
        words.add(word1);
        words.add(".");
      } else {
        // If the word doesn't have a period the word is added directly into the ArrayList
        words.add(word);
      }
    }
    return words;
  }
}

