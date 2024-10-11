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
    // DONE: Implement this function to convert the scanner's input to a list of words and periods
    List <String> tokens = new ArrayList<>(); // creating an Arraylist

    // reading the input until there's no text left to read from the Scanner
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine(); // saving the read input line in the variable
      String words[] = line.split(" "); // adding spaces

      // looping through an array "words", and populating each word into the "tokens" ArrayList
      for (String word: words) {
        // checking if the word is empty
        if (!word.isEmpty()) {
          String lowerCaseWord = word.toLowerCase(); // converting to lowercase

          // checking if a word is a period itself
          if (lowerCaseWord.equals(".")) {
              tokens.add(lowerCaseWord); // adding a period as a seperate token 
          } else {
            // empty string
            String cleanedWord = "";
            // iterating through each character
            for (char c : lowerCaseWord.toCharArray()) {
              if (Character.isLetter(c) || c == '.' || c == '\'')  {
                  cleanedWord+= c; // adding valid characters to the word
              }
          }
          // seperating the word if it ends with a period
          if (cleanedWord.endsWith(".")) {
            tokens.add(cleanedWord.substring(0, cleanedWord.length() - 1)); // adding the word without the period
            tokens.add("."); // adding period as a seperate token
          } else {
            // if not empty
            if (!cleanedWord.isEmpty()) 
            tokens.add(cleanedWord); // adding the cleaned word
          } 
        }
      }
    }
  }
    return tokens;
 } 
}

