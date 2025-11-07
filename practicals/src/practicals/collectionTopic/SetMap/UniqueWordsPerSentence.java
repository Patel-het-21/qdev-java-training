package practicals.collectionTopic.SetMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This program processes a list of sentences and counts unique words in each
 * sentence using Map and Set.
 *
 * Map<Integer, Set<String>> is used to map sentence index to a set of unique
 * words. Set<String> automatically removes duplicate words.
 * 
 * @author Het Patel
 * @since 17/10/25
 */
public class UniqueWordsPerSentence {

	/**
	 * The main method where the program execution starts.
	 * 
	 * @param args command-line arguments (not used here)
	 */
	public static void main(String[] args) {
		String[] sentences = { "hello world hello", "java is fun fun fun", "map and set example", "hello java map" };
		/**
		 * Map to store sentence index and its unique words
		 */
		Map<Integer, Set<String>> uniqueWordsMap = new HashMap<>();
		/**
		 * Loop through each sentence
		 */
		for (int i = 0; i < sentences.length; i++) {
			String sentenString = sentences[i];
			/**
			 * Split the sentence into words using space
			 */
			String[] words = sentenString.split(" ");
			Set<String> uniqueWords = new HashSet<>();
			/**
			 * Add each word to the Set
			 */
			for (String word : words) {
				uniqueWords.add(word);
			}
			/**
			 * Add the set to the map with the sentence index as key
			 */
			uniqueWordsMap.put(i, uniqueWords);
		}

		for (int index : uniqueWordsMap.keySet()) {
			System.out.println("Sentence : " + (index + 1) + ": " + uniqueWordsMap.get(index));
		}
	}

}