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

    // List of words containing input from scanner
    List<String> trainingWords = tokenizer.tokenize(scanner);
    // Create empty hashmap with String as keys and a list of strings as value
    Map<String, List<String>> neighborMap = new HashMap<>();

    // TODO: Convert the trainingWords into neighborMap here

    // for-loop to loop through each token. does not go to final index since it
    // will throw an IndexOutOfBoundsExceptions
    for (int i = 0; i < trainingWords.size() - 1; i++) {
      // obtain the string at index i and convert to lowercase
      String currentString = trainingWords.get(i).toLowerCase();
      // obtain the next string after index i and convert to lowercase
      String nextString = trainingWords.get(i + 1).toLowerCase();

      // if currentString is not found within neighborMap
      // puts currentString as key and creates a new list
      if (!neighborMap.containsKey(currentString)) {
        neighborMap.put(currentString, new ArrayList<>());
      }
      // add nextString to the collection of neighbors for currentString
      neighborMap.get(currentString).add(nextString);
    }
    // Allow neighborMap to be used throughout the class
    // and not just within the train method
    // or will throw NullPointerException
    this.neighborMap = neighborMap;
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
    // TODO: Return a predicted word given the words preceding it

    // As stated in the hint, only the last word in context should be evaluated.
    String lastWord = context.get(context.size() - 1);
    // Create empty list for scope purposes
    List<String> nextPredictedWords = null;

    // check if neighbormap contains lastWord derived from the context
    if (neighborMap.containsKey(lastWord)) {

      // if lastWord found is true, retrieve list of words that can follow the
      // last word in the context
      nextPredictedWords = neighborMap.get(lastWord);
    }
    // create instance of random for generating a random output from
    // nextPredictedWords List
    Random random = new Random();
    // Hint: only the last word in context should be looked at

    // Used this link to support with the syntaxing of using the random class
    // https://www.geeksforgeeks.org/generating-random-numbers-in-java/

    // Using the Random class, it will use the size of the nextPredictedWords,
    // and select a random integer value within the indices of nextPredictedWords.
    // If the list is ["cat", "cat", "dog"], cat will still have a 2/3 chance
    // to be selected since cat is given an index twice. In this case, it is 0 =
    // cat, 1 = cat,
    // and 2 = dog.
    return nextPredictedWords.get(random.nextInt(nextPredictedWords.size()));
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
