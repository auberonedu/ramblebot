import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

/**
 * A class for predicting the next word in a sequence using a unigram model.
 * The model is trained on input text and maps each word to a list of 
 * words that directly follow it in the text.
 */
public class UnigramWordPredictor implements WordPredictor {
    private Map<String, List<String>> neighborMap;
    private Tokenizer tokenizer;
    private Random random;

    /**
     * Constructs a UnigramWordPredictor with the specified tokenizer.
     * 
     * @param tokenizer the tokenizer used to process the input text
     */
    public UnigramWordPredictor(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        this.neighborMap = new HashMap<>();
        this.random = new Random(); // Initialize Random object
    }

    /**
     * Trains the predictor using the text provided by the Scanner.
     * The method tokenizes the text and builds a map where each word 
     * is associated with a list of words that immediately follow it 
     * in the text. The resultant map is stored in the neighborMap
     * instance variable.
     * 
     * @param scanner the Scanner to read the training text from
     */
    @Override
    public void train(Scanner scanner) {
        List<String> trainingWords = tokenizer.tokenize(scanner); // Correct variable name used
        for (int i = 0; i < trainingWords.size() - 1; i++) { // Fixing reference to trainingWords
            String currentWord = trainingWords.get(i);
            String nextWord = trainingWords.get(i + 1);
            neighborMap.computeIfAbsent(currentWord, k -> new ArrayList<>()).add(nextWord);
        }
    }

    /**
     * Predicts the next word based on the given context.
     * The prediction is made by randomly selecting from all words 
     * that follow the last word in the context in the training data.
     * 
     * @param context a list of words representing the current context
     * @return the predicted next word, or null if no prediction can be made
     */
    public String predictNextWord(List<String> context) {
        String lastWord = context.get(context.size() - 1);
        List<String> possibleNextWords = neighborMap.get(lastWord);

        // Check if there are possible next words, otherwise return null
        if (possibleNextWords == null || possibleNextWords.isEmpty()) {
            return null;
        }
        
        // Randomly select a next word from the list of possible next words
        return possibleNextWords.get(random.nextInt(possibleNextWords.size()));
    }

    /**
     * Returns a copy of the neighbor map. The neighbor map is a mapping 
     * from each word to a list of words that have followed it in the training data.
     * 
     * @return a copy of the neighbor map
     */
    public Map<String, List<String>> getNeighborMap() {
        Map<String, List<String>> copy = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : neighborMap.entrySet()) {
            List<String> newList = new ArrayList<>(entry.getValue());
            copy.put(entry.getKey(), newList);
        }
        return copy;
    }
}
