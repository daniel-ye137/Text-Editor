package modules;
/**
 * An object that can search a given string for another specified string.
 */
public class Search implements SearchModule {
	 /**
	    * Returns the starting location of the first occurrence of {@code query} in
	    * {@code text} or -1 if the query does not appear in the target text.
	    */
	 public int find(String query, String text) {
		 return text.indexOf(query);
	 }
}
