// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Scanner;

// doing this to make things easier
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.ClassOrderer.Random;

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
   * After tokenizing, the tokens would be: ["the", "cat", "sat", ".", "the", "cat", "slept", ".", "the", "dog", "barked", "."]
   * 
   * The resulting map (neighborMap) would be:
   * {
   *   "the" -> ["cat", "cat", "dog"],
   *   "cat" -> ["sat", "slept"],
   *   "sat" -> ["."],
   *   "." -> ["the", "the"],
   *   "slept" -> ["."],
   *   "dog" -> ["barked"],
   *   "barked" -> ["."]
   * }
   * 
   * The order of the map and the order of each list is not important.
   * 
   * @param scanner the Scanner to read the training text from
   */
  public void train(Scanner scanner) {
    List<String> trainingWords = tokenizer.tokenize(scanner);

    // tokenizer is already breaking it down into individual tokens

    // what i need to do is to put the individual token thats already in the trainwWords 
    // list into an array/data structure of its own that will then 
    // usr that token as a key and store and array as the values for that key for each word 
    // that follows that token 
    Map<String, List<String>> predictorMap = new HashMap<>();
    
    for(int i = 0; i < trainingWords.size() - 1; i++){
      // grabbing the token to check 
      String token = trainingWords.get(i);
      // grabbing the word following the token to check
      
      // next time I need to ensure I am incrementing correct.../
      String nextToken = trainingWords.get(i + 1);

      if(predictorMap.containsKey(token)){
        // if the new map were creating contains the token as a Key already
        // then it will just add that nextToken to the list/array of strings associated
        // with that token 
        predictorMap.get(token).add(nextToken);
      }
      else{
        // if not, we need to put the next token into the list 
        // so that it can match the data type we need to put it into the hashmap
        // and later add more nextWord tokens into the array
        // then we put this in as a new entry to the hashMap 
        List<String> nextTokenList = new ArrayList<>();
        nextTokenList.add(nextToken);
        predictorMap.put(token, nextTokenList);
      }
    }



    // TODO: Convert the trainingWords into neighborMap here

    // Not sure where this is getting pull for later although I am just literally
    // taking the todo and assigning it to my product map 
    neighborMap = predictorMap;
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
   *   "the" -> ["cat", "cat", "dog"],
   *   "cat" -> ["sat", "slept"],
   *   "sat" -> ["."],
   *   "." -> ["the", "the"],
   *   "slept" -> ["."],
   *   "dog" -> ["barked"],
   *   "barked" -> ["."]
   * }
   * 
   * When predicting the next word given a context, the predictor should use 
   * the neighbor map to select a word based on the observed frequencies in 
   * the training data. For example:
   * 
   * - If the last word in the context is "the", the next word should be randomly chosen 
   *   from ["cat", "cat", "dog"]. In this case, "cat" has a 2/3 probability 
   *   of being selected, and "dog" has a 1/3 probability, reflecting the 
   *   original distribution of words following "the" in the text.
   * 
   * - If the last word in the context is "cat", the next word should be randomly chosen 
   *   from ["sat", "slept"], giving each an equal 1/2 probability.
   * 
   * - If the last word in the context is ".", the next word should be randomly chosen 
   *   from ["the", "the"], meaning "the" will always be selected 
   *   since it's the only option.
   * 
   * - If the last word in the context is "dog", the next word should be "barked" because 
   *   "barked" is the only word that follows "dog" in the training data.
   * 
   * The probabilities of selecting each word should match the relative 
   * frequencies of the words that follow in the original training data. 
   * 
   * @param context a list of words representing the current context
   * @return the predicted next word, or null if no prediction can be made
   */
  public String predictNextWord(List<String> context) {
    // TODO: Return a predicted word given the words preceding it
    // Hint: only the last word in context should be looked at

    // check if context is empty 
    if (context.isEmpty()){
      return null;
    }

    // grabbing the checkedWord
    String checkedWord = context.get(context.size() - 1);



    if(getNeighborMap().containsKey(checkedWord)){

      List<String> neighbor = getNeighborMap().get(checkedWord);

    if(neighbor != null){
      // got this from googling

      // essentially threadlocalrandom is used
      // to run mutliple threadlocalrandoms to generte
      // numbes randomly whcih would allow you to protect #s randomly

      // just have to ensure that you put the current method on it to let the class know to make its own random number on this instance similar to this. 

      // nextInt() is there to generate a random number based on nthe size of the neighbor.size()
      int randomInt = ThreadLocalRandom.current().nextInt(neighbor.size());
//cant use nextInt() so I gotta use math.rnadom instead
      return neighbor.get(randomInt);
    }
    }

    return "I have no idea how we got here!";
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
