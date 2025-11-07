package practicals.collectionTopic.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This program adds string values to a list and prints them using an Iterator.
 * 
 * @author Het Patel
 * @since 16/10/25
 * 
 */
public class StringListIterator {

	public static void main(String[] args) {
		List<String> stringList = new ArrayList<String>();
		/**
		 * Add String value to list
		 */
		stringList.add("Hello");
		stringList.add("World");
		stringList.add("Het");
		stringList.add("Java");
		stringList.add("Iterator");

		/**
		 * Print all values using the iterator
		 */
		Iterator<String> iterator = stringList.iterator();
		while (iterator.hasNext()) {
			String value = iterator.next();
			System.out.println(value);
		}
	}

}