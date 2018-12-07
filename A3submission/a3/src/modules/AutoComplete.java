package modules;

import util.Trie;
/**
 * A mutable collection of strings that can check for a shortest string contained within it that starts with a given prefix.
 */
public class AutoComplete implements AutoCompleteModule {
	Trie dictionary = new Trie();
	 /**
	* Adds {@code word} to the set of words that can be returned by
	* {@link #getWordForPrefix(String)}.
	*/
	   public void addWord(String word) {
		   dictionary.insert(word);
	   }
	   
	   /**
	* Returns a word of minimal length that has {@code prefix} as a (not
	* necessarily proper) prefix.
	*/
	   public String getWordForPrefix(String prefix) {
		   return dictionary.closestWordToPrefix(prefix);
	   }
}
