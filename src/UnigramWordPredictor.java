import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * A class for predicting the next word in a sequence using a unigram model.
 * The model is trained on input text and maps each word to a list of
 * words that directly follow it in the text.
 */
public class UnigramWordPredictor implements WordPredictor {
  private Map<String, List<String>> neighborMap;
  private Tokenizer tokenizer;

  /**
   * Constructs a UnigramWordPredictor with the specified tokenizer.
   * 
   * @param tokenizer the tokenizer used to process the input text
   */
  public UnigramWordPredictor(Tokenizer tokenizer) {
    this.tokenizer = tokenizer;
  }

  /**
   * Trains the predictor using the text provided by the Scanner.
   * The method tokenizes the text and builds a map where each word
   * is associated with a list of words that immediately follow it
   * in the text. The resultant map is stored in the neighborMap
   * instance variable.
   * 
   * For example:
   * If the input text is: "The cat sat. The cat slept. The dog barked."
   * After tokenizing, the tokens would be: ["the", "cat", "sat", ".", "the",
   * "cat", "slept", ".", "the", "dog", "barked", "."]
   * 
   * The resulting map (neighborMap) would be:
   * {
   * "the" -> ["cat", "cat", "dog"],
   * "cat" -> ["sat", "slept"],
   * "sat" -> ["."],
   * "." -> ["the", "the"],
   * "slept" -> ["."],
   * "dog" -> ["barked"],
   * "barked" -> ["."]
   * }
   * 
   * The order of the map and the order of each list is not important.
   * 
   * @param scanner the Scanner to read the training text from
   */
  public void train(Scanner scanner) {
    List<String> trainingWords = tokenizer.tokenize(scanner);

    // Completed: TODO: Convert the trainingWords into neighborMap here
    // Instantiates a new HashMap of neighborMap to store the currentWord and the
    // words that follow
    neighborMap = new HashMap<>();

    // Loops through the trainingWords String List, stopping one word/token before
    // the end of the list
    for (int i = 0; i < trainingWords.size() - 1; i++) {
      // Saves the current word of index i and the word that follows it
      String currentWord = trainingWords.get(i);
      String nextWord = trainingWords.get(i + 1);

      // Checkes whether neighborMap does not already contain the current word
      if (!neighborMap.containsKey(currentWord)) {
        // Adds the current word as a key and an empty ArrayList as the value inside of
        // neighborMap
        neighborMap.put(currentWord, new ArrayList<>());
      }
      // Adds the next word that follows currentWord into the ArrayList if the current
      // word has already been added as a key in neighborMap
      neighborMap.get(currentWord).add(nextWord);
    }
  }

  /**
   * Predicts the next word based on the given context.
   * The prediction is made by randomly selecting from all words
   * that follow the last word in the context in the training data.
   * 
   * For example:
   * If the input text is: "The cat sat. The cat slept. The dog barked."
   * 
   * The resulting map (neighborMap) would be:
   * {
   * "the" -> ["cat", "cat", "dog"],
   * "cat" -> ["sat", "slept"],
   * "sat" -> ["."],
   * "." -> ["the", "the"],
   * "slept" -> ["."],
   * "dog" -> ["barked"],
   * "barked" -> ["."]
   * }
   * 
   * When predicting the next word given a context, the predictor should use
   * the neighbor map to select a word based on the observed frequencies in
   * the training data. For example:
   * 
   * - If the last word in the context is "the", the next word should be randomly
   * chosen
   * from ["cat", "cat", "dog"]. In this case, "cat" has a 2/3 probability
   * of being selected, and "dog" has a 1/3 probability, reflecting the
   * original distribution of words following "the" in the text.
   * 
   * - If the last word in the context is "cat", the next word should be randomly
   * chosen
   * from ["sat", "slept"], giving each an equal 1/2 probability.
   * 
   * - If the last word in the context is ".", the next word should be randomly
   * chosen
   * from ["the", "the"], meaning "the" will always be selected
   * since it's the only option.
   * 
   * - If the last word in the context is "dog", the next word should be "barked"
   * because
   * "barked" is the only word that follows "dog" in the training data.
   * 
   * The probabilities of selecting each word should match the relative
   * frequencies of the words that follow in the original training data.
   * 
   * @param context a list of words representing the current context
   * @return the predicted next word, or null if no prediction can be made
   */
  public String predictNextWord(List<String> context) {
    // Completed: TODO: Return a predicted word given the words preceding it
    // Hint: only the last word in context should be looked at

    // Checks wheter context is empty or null, returning null if so
    if (context.isEmpty() | context == null) {
      return null;
    }

    // Saves the last word in context as a String
    String endingWord = context.get(context.size() - 1);

    // Instantiates a new String list to save the possible following words from neighborMap
    List<String> followUpWords = neighborMap.get(endingWord);

    // Checks wheter followUpWords is empty or null, returning null if so
    if (followUpWords.isEmpty() | followUpWords == null) {
      return null;
    }

    // Instantiaties a new Random object for generating random numbers
    Random rand = new Random();

    // Saves the randomly generated index based on the size of followUpWords
    int predictedIndex = rand.nextInt(followUpWords.size());

    // Gets the randomly predicted word and saves it in a String variable
    String predictedWord = followUpWords.get(predictedIndex);

    return predictedWord;
  }

  /**
   * Returns a copy of the neighbor map. The neighbor map is a mapping
   * from each word to a list of words that have followed it in the training data.
   * 
   * You do not need to modify this method for your project.
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
