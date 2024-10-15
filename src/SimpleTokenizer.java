import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleTokenizer implements Tokenizer {
    
    @Override
    public List<String> tokenize(Scanner scanner) {
        List<String> tokens = new ArrayList<>();

        while (scanner.hasNext()) {
            String token = scanner.next().toLowerCase();

            //Checks if token is a word or a period
            if (token.matches("[a-z]+") || token.matches(token +".")) {
                tokens.add(token);
            }
        }

        return tokens;
    }
}
