package tries;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @author harish.kumar-mbp
 * createdOn 18/12/23
 */
public class AutoComplete {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("hello", "dog", "hell", "cat", "a", "hel","help","helps","helping");
        Trie trie = new Trie(words);

        System.out.println(trie.suggest("hel"));
    }
}
