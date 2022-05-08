package Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class StreamAndFilters {

    public static void main(String[] agrs){
        List<String> list = new ArrayList<>();
        String[] array = {"A","B","C","D"};
        System.out.println(array.length);
        list = Arrays.asList("A","A","A","A");
        System.out.println(list);
        String s = "A";

        filterUsingStream(list);
        filterMapUsingStream(list);
    }

    private static void filterMapUsingStream(List<String> list) {
        Integer l =list.stream().filter( name -> name !="A").map(String :: hashCode).findAny().orElse(1);
        System.out.println(l);
        list.stream().filter( name -> name =="A").map(String :: hashCode).forEach( value -> System.out.println(value));
    }

    private static void filterUsingStream(List<String> list) {
        list.stream().filter( name -> name !="A").forEach( value -> System.out.println(value));
    }
}
