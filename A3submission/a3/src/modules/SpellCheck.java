package modules;


import util.Trie;
/**
 * A mutable collection of correctly-spelled Strings that can check if another string is contained within it.
 */
public class SpellCheck implements SpellCheckModule{
	Trie dictionary = new Trie();
	/**
    * Adds {@code word} to the set of words that can be returned by
    * {@link #isValidWord(String)}.
    */
	public void addWord(String word) {
		dictionary.insert(word);
		
	}
	/**
    * Returns true if {@code word} has been added to the list of known words
    * through a call to {@link #addWord(String)}.
    */
	public boolean isValidWord(String word) {
		return dictionary.contains(word);
	}
	

}
