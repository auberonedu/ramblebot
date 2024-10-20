import java.util.List;
import java.util.ArrayList;
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
    List<String> list = new ArrayList<String>();
    
    // Loops through the provided text, ending when the text no longer has another word in it
    while(scanner.hasNext()){
      // Grabs the next word in the text
      String token = scanner.next().toLowerCase();

      // Checks to see if the word has a "." in it
      if(token.contains(".")){
        // Grabs the index after the "."
        int indexAfterPeriod = token.indexOf(".") +1;
        // Checks to make sure indexAfterPeriod is not larger than the size of the token
        // AND checks that the char after "." isn't empty. Gets added to list if both tests pass
        if(indexAfterPeriod < token.length() && token.charAt(indexAfterPeriod) != ' '){
          list.add(token);
        } else{
          // If the space after "." isn't empty, the token is broken up into before and after "."
          String[] tokens = token.split("\\.",2);
          // Adds the word before ".", and adds "." into the list
            list.add(tokens[0]);
            list.add(".");
          
        
        }
       // Adds the word if "." wasn't involved
      } else{
        list.add(token);
      }
    }
    return list;
  }
}

