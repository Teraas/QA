package tries;

/**
 * @author harish.kumar-mbp
 * createdOn 11/01/24
 */

import java.util.*;

public class SuffixTrieSolution {
    // Do not edit the class below except for the
    // populateSuffixTrieFrom and contains methods.
    // Feel free to add new properties and methods
    // to the class.
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    }

    static class SuffixTrie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';

        public SuffixTrie(String str) {
            populateSuffixTrieFrom(str);
        }

        public void populateSuffixTrieFrom(String str) {
            // Write your code here.
            int len = str.length();
            for(int i =0;i<len;i++){
                insertFromIndex(i, str);
            }
        }

        public void insertFromIndex(int index, String str){
            TrieNode node =root;
            for(int i = index; i<str.length();i++){
                char ch = str.charAt(i);
                if(!node.children.containsKey(ch)){
                    node.children.put(ch, new TrieNode());
                }
                node = node.children.get(ch);
            }
            node.children.put(endSymbol,null);
        }

        public boolean contains(String str) {
            // Write your code here.
            TrieNode node = root;
            for(char c : str.toCharArray()){
                if(!node.children.containsKey(c)){
                    return false;
                }
                node = node.children.get(c);

            }

            return node.children.containsKey(endSymbol);
        }
    }

    public  static void main(String[] args){
        SuffixTrieSolution sol = new SuffixTrieSolution();
        SuffixTrie suffixTrie = new SuffixTrie("this is  a big string");
        String[] strings = {"this","is", "a"};
        Boolean[] res = new Boolean[strings.length];
        for(int i = 0; i<strings.length; i++){
            res[i] = suffixTrie.contains(strings[i]);
        }
        System.out.println(" result - " + res);
    }
}
