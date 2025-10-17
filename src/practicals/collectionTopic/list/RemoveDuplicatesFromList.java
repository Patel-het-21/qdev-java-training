package practicals.collectionTopic.list;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Remove duplicate from List
 * 
 * @author Het
 * @since 17/10/25
 */
public class RemoveDuplicatesFromList {

	public static void main(String[] args) {
		List<String> letters = new ArrayList<>();
		letters.add("A");
		letters.add("B");
		letters.add("C");
		letters.add("D");
		letters.add("A");
		letters.add("C");
		letters.add("E");
		letters.add("E");
		letters.add("E");

		System.out.println("Original List : " + letters);

		/**
		 * LinkedHashSet removes duplicates and keeps insertion order
		 */
		Set<String> uniqueLetters = new LinkedHashSet<>(letters);
		/**
		 * Convert to the list
		 */
		List<String> resultList = new ArrayList<>(uniqueLetters);
		System.out.println("Result : " + resultList);
	}

}