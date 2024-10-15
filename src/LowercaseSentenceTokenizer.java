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
     * @param scanner the Scanner to read the input text from
     * @return a list of tokens, where each token is a word or a period
     */
    @Override
    public List<String> tokenize(Scanner scanner) {
        List<String> tokens = new ArrayList<>();
        // Convert the entire input to lowercase
        String input = scanner.useDelimiter("\\A").next().toLowerCase();

        // Regex to split by spaces while preserving periods as separate tokens only at the end of words

        String[] rawTokens = input.split("\\s+");


        for (String token : rawTokens) {
            if (!token.isEmpty()) {
                // If the token ends with a period, separate the period from the word
                if (token.endsWith(".") && token.length() > 1) {
                    tokens.add(token.substring(0, token.length() - 1)); 
                    tokens.add(".");  
                } else {
                    tokens.add(token);  
                }
            }
        }
        return tokens;
    }
}
