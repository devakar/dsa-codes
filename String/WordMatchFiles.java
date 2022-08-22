Find word from multiple Document

1. Use HashMap and HashTree:

	HashMap<word, 
		TreeMap<file,
			TreeMap<page,
				TreeSet<position>>>> words

	Query Complexity:
	O(log F * log P * log T), F:Number of files, P:MAx Number of pages in a file, T:Max Number of times a word present in a page.

	If we consider worst time for HashMap query time, it will be 
	O(log W * log F * log P * log T), W:Total Number of words   

	Build Time complexity: 
	O(W * log F * log P * log T), W:Total Number of words   

	If we consider worst time for HashMap query time, it will be 
	O(W * log W * log F * log P * log T)

	Space Complexity:
	O(W * F * P * T)


2. Use Trie:
	Trie:
		- Trie[] next (size can be 26 or 256, depending on requirement)
		- boolean isWord
		- String fileId
		- int pageNumber
		- int position

	Query Complexity:
	O(log L) (as height of tree is max length of words)

	Build Time Complexity:
	O(W * log L), W:Total Number of words

	Space Complexity:
	O(256 * L * N), L:Max length of word, N: Number of nodes,
	N = 2^(L), as total number of nodes = 2^height of tree -1
	O(L* 2^L)
	O(1), if we consider length as constant



This approach will search for specific word,
to do for phrase, we have to do search for all words, 
then check from result, which are two consecutive words in phrase by checking file, page and position
	fileId should be same
	pageNumber should be same
	position should be in same order as words present in phrase

Reverse Indexing can be used to search phrase efficiently after word search:

boolean checkPhrase() {
	get file, page, position for phrase[0], by words.get(word_i).get(file).get(page)
	setFlag = true;
	for(i=1;i<n;i++){
		if(!search(file, page, position+i, phrase[i])) {
			setFlag = false;
		}
	}

	return setFlag;
}



boolean search(file, page, position+i, word_i) {
	if(words.get(word_i).get(file).get(page).get(postion+1) != null)
		return true;
	return false;
}




Find word from multiple sentences:
1. Use HashMap and HashTree:

	HAshMap<word,
		TreeMap<sentence,
			TreeSet<position>>> words

2. USe Trie:
	Trie:
		- Trie[] next
		- boolean isWord
		- String sentenceId
		- int position			




