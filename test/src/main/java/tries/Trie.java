package tries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {

    private TrieNode rootNode;
    private static final char END_OF_WORD_MARKER = '#';

    public Trie() {
        this.rootNode = new TrieNode();
    }

    public boolean addWord(String word) {

        TrieNode currentNode = this.rootNode;
        boolean isNewWord = false;

        // Work downwards through the trie, adding nodes
        // as needed, and keeping track of whether we add
        // any nodes.
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);

            if (!currentNode.hasChildNode(character)) {
                isNewWord = true;
                currentNode.makeChildNode(character);
            }

            currentNode = currentNode.getChildNode(character);
        }

        // Explicitly mark the end of a word.
        // Otherwise, we might say a word is
        // present if it is a prefix of a different,
        // longer word that was added earlier.
        if (!currentNode.hasChildNode(END_OF_WORD_MARKER)) {
            isNewWord = true;
            currentNode.makeChildNode(END_OF_WORD_MARKER);
        }

        return isNewWord;
    }

    public Trie(List<String> words) {
        rootNode = new TrieNode();
        for (String word : words)
            addWord(word);

    }
    public boolean find(String prefix, boolean exact) {
        TrieNode lastNode = rootNode;
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.getNodeChildren().get(c);
            if (lastNode == null)
                return false;
        }
        return !exact || lastNode.isWord;
    }

    public boolean find(String prefix) {
        return find(prefix, false);
    }

    public List<String> suggest(String prefix) {
        List<String> list = new ArrayList<>();
        TrieNode lastNode = rootNode;
        StringBuffer curr = new StringBuffer();
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.getNodeChildren().get(c);
            if (lastNode == null)
                return list;
            curr.append(c);
        }
        suggestHelper(lastNode, list, curr);
        return list;
    }
    public void suggestHelper(TrieNode root, List<String> list, StringBuffer curr) {
        if (root.isWord) {
            list.add(curr.toString());
        }

        if (root.getNodeChildren() == null || root.getNodeChildren().isEmpty())
            return;

        for (TrieNode child : root.getNodeChildren().values()) {
            suggestHelper(child, list, curr.append(child.c));
            curr.setLength(curr.length() - 1);
        }
    }
}
