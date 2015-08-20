import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.String;
import java.util.ArrayList;

/**
 * implements a trie for storing strings/string prefixes
 *
 * @author Owen Astrachan
 * @version $Id: Trie.java,v 1.1 1996/12/01 00:07:21 ola Exp ola $
 *
 */

public class Trie {
	/**
	 * create a new Trie (characters a-z)
	 */

	public Trie() {
		myLinks = new Trie[ALPH];
		myIsWord = false;
	}

	/**
	 * Add a string to the trie
	 *
	 * @param s
	 *            The string added to Trie
	 */

	public void addString(String s) {
		Trie t = this;
		int k;
		int limit = s.length();
		for (k = 0; k < limit; k++) {
			int index = s.charAt(k) - 'a';
			if (t.myLinks[index] == null) {
				t.myLinks[index] = new Trie();
			}
			t = t.myLinks[index];
		}
		t.myIsWord = true;
	}

	public void addCString(char s[]) {
		Trie t = this;
		int k = 0;
		while (s[k] != '\0') {
			int index = s[k] - 'a';
			if (t.myLinks[index] == null) {
				t.myLinks[index] = new Trie();
			}
			t = t.myLinks[index];
			k++;
		}
		t.myIsWord = true;
	}

	/**
	 * print every word in the trie, one per line
	 *
	 */

	public void Print() {
		char[] buffer = new char[BUFSIZ];
		doApply(0, buffer, this);
	}

	private void doApply(int index, char buffer[], Trie t) {
		if (t != null) {
			if (t.myIsWord) {
				// Create a stream to hold the output
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream(baos);
				PrintStream old = System.out;
				System.setOut(ps);
				System.out.println(new String(buffer, 0, index));
				System.out.flush();
				System.setOut(old);
				children.add(baos.toString());
				//System.out.println(baos.toString());
			}
			int k;
			for (k = 0; k < ALPH; k++) {
				if (t.myLinks[k] != null) {
					buffer[index] = (char) (k + 'a');
					doApply(index + 1, buffer, t.myLinks[k]);
				}
			}
		}
	}

	void DoPrint(String s, Trie t) // now superflous (see doApply)
	{
		if (t != null) {
			if (t.myIsWord) {
				System.out.println(s);
			}
			int k;
			for (k = 0; k < ALPH; k++) {
				if (t.myLinks[k] != null) {
					DoPrint(s + (char) (k + 'a'), t.myLinks[k]);
				}
			}
		}
	}

	/**
	 * determine if a word is in the trie (here or below)
	 *
	 * @param s
	 *            The string searched for
	 * @return true iff s is in trie (rooted here)
	 */

	public boolean isWord(String s) {
		Trie t = this;
		int k;
		int limit = s.length();
		for (k = 0; k < limit; k++) {
			int index = s.charAt(k) - 'a';
			if (t.myLinks[index] == null)
				return false;
			t = t.myLinks[index];
		}
		t.Print();
		return t.myIsWord;
	}

	/**
	 * determine if a word is in the trie (here or below)
	 *
	 * @param s
	 *            The string searched for
	 * @return true iff s is in trie (rooted here)
	 */

	public Trie getParentWords(String s) {
		Trie t = this;
		int k;
		int limit = s.length();
		for (k = 0; k < limit; k++) {
			int index = s.charAt(k) - 'a';
			t = t.myLinks[index];
		}
		return t;
	}

	/**
	 * @return true iff path from some root to this node is a word
	 * 
	 */

	public boolean isWord() {
		return myIsWord;
	}

	/**
	 * @param ch
	 *            Character used to index node (find child)
	 * @return Trie formed from this by indexing using ch
	 */

	Trie childAt(char ch) {
		return myLinks[ch - 'a'];
	}

	private static final int ALPH = 26;
	private static final int BUFSIZ = 1024;
	private Trie[] myLinks;
	private boolean myIsWord;
	public static ArrayList<String> children = new ArrayList<>();
}
