David Siher (das568)
Daniel Ye (dzy3)
Consulted (http://www.cs.cornell.edu/courses/cs3110/2008fa/lectures/lec21.html) for additional information on multiplicative hashing for BloomFilter
No data structure was needed to create the search module, only indexOf(), and the other two modules were created with just Trie (could have potentially merged autocomplete and spellcheck into just one class and used only one Trie for dictionary.)
HashTable was used for the creation of Trie, but was not used by itself in any modules. 
Testing for Trie and BloomFilter were mainly creating random strings and stress testing the two structures to see if they could handle large amounts of random data.
HashTable testing was more specific and aimed at trying to see if each individual function worked as intended.