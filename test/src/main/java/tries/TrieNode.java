package tries;

import java.util.HashMap;

/**
 * @author harish.kumar-mbp
 * createdOn 18/12/23
 */
public class TrieNode {

    private HashMap<Character, TrieNode> nodeChildren;

    public HashMap<Character, TrieNode> getNodeChildren() {
        return nodeChildren;
    }

    public void setNodeChildren(HashMap<Character, TrieNode> nodeChildren) {
        this.nodeChildren = nodeChildren;
    }

    char c;
    boolean isWord;

    public TrieNode() {
        this.nodeChildren = new HashMap<>();
    }

    public boolean hasChildNode(char character) {
        return this.nodeChildren.containsKey(character);
    }

    public void makeChildNode(char character) {
        this.nodeChildren.put(character, new TrieNode());
    }

    public TrieNode getChildNode(char character) {
        return this.nodeChildren.get(character);
    }
}
