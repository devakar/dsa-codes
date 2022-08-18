import java.util.*;

class TrieTree {
	Trie root;

	public TrieTree() {
		root=new Trie(false);
	}

	public void insertIntoTrie(String word) {
		int n = word.length();
		
		Trie cur = root;

		for(int i =0;i<n;i++){
			if(cur.next[word.charAt(i)-'a'] == null)
				cur.next[word.charAt(i)-'a'] = new Trie(false);
			cur=cur.next[word.charAt(i)-'a'];
		}
		cur.isLeaf=true;
	}

	public void printTrie() {
		List<String> result = new ArrayList<>();
		printTrieUtil(root, "", result);

		for(String s : result) {
			System.out.print(s+" ");
		}
		System.out.println();
	}

	public void printTrieUtil(Trie root, String word, List<String> result) {
		
		if(root==null)
			return;

		if(root.isLeaf) {
			result.add(word);
		}

		for(int i=0;i<26;i++){
			if(root.next[i]!=null) {
				word = word+String.valueOf((char) ('a'+i));
				printTrieUtil(root.next[i], word, result);
				word = word.substring(0, word.length()-1);
			}
		}
	}

	public boolean search(String word) {
		int n = word.length();

		Trie cur = root;

		return searchUtil(cur, word);
	}

	private boolean searchUtil(Trie root, String word) {
		if(root == null && word.length()>0)
			return false;
		if(!root.isLeaf &&  word.length()==0)
			return false;
		if(root.isLeaf && word.length()==0)
			return true;

		if(root.next[word.charAt(0)-'a']==null)
			return false;

		return searchUtil(root.next[word.charAt(0)-'a'], word.substring(1));
	}
}

class Trie {
	Trie[] next;
	boolean isLeaf;

	public Trie(boolean isLeaf) {
		next =  new Trie[26];
		this.isLeaf = isLeaf;

	}
}



public class TrieSol {

	public static void main(String[] args) {

		String[] words = {"ab", "abc", "ac", "acb"};

		int n = words.length;

		TrieTree tree = new TrieTree();

		for(int i=0;i<n;i++) {
			tree.insertIntoTrie(words[i]);
		}

		tree.printTrie();
		System.out.println(tree.search("ab"));
		System.out.println(tree.search("abc"));
		System.out.println(tree.search("abd"));
		System.out.println(tree.search("ba"));
	}
}