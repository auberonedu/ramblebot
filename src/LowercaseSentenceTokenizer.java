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

    // create new ArrayList to hold scanner input
    List<String> wordList = new ArrayList<>();

    // create a while loop to keep running until there are no more tokens
    while (scanner.hasNext()) {
      // read next token and assign to variable word
      // set to lowercase
      String word = scanner.next();
     
      if (word.endsWith(".")) {
        // If it ends with a period, add the word without the period
        //substring starts at index 0, and goes to index before the period is found
        //convert to lowercase
        String withoutPeriod = word.substring(0, word.length() - 1).toLowerCase();
        //ensure non-empty strings are added to ArrayList
        if (!withoutPeriod.isEmpty()) {
            wordList.add(withoutPeriod); 
        }
         // add the period as a separate token
        wordList.add(".");
    } else {
        // if no period found, add word and convert to lowercase
        wordList.add(word.toLowerCase());
    }
}
//return wordList ArrayList
return wordList;
  }
}
