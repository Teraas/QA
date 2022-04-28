package Abstraction;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractClass {
    public static List<String> unmatchedKeyList = new ArrayList<String>();

    public abstract void print(String name);

    public void addUnmatchedKeys(String key){
        unmatchedKeyList.add(key);
    }
}
