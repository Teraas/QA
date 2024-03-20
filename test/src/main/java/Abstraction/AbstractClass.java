package Abstraction;

import java.util.ArrayList;
import java.util.List;
// can not be instatiated. Can have abstract and non-abstract method. static as well. Need atleast 1 abstract method.
//
public abstract class AbstractClass {
    public static List<String> unmatchedKeyList = new ArrayList<String>(); // can have variables as well

    public abstract void print(String name);

    public static void  addUnmatchedKeys(String key){
        unmatchedKeyList.add(key);
    }
}
