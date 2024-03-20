package Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAndFilters {

    public static void main(String[] agrs){
        List<String> list = new ArrayList<>();
        String[] array = {"A","B","C","D"};
        System.out.println(array.length);
        list = Arrays.asList("A","A","A","D");
        System.out.println(list);
        String s = "A";

        //filterUsingStream(list);
        //filterMapUsingStream(list);
        filterMapUsingStream();


    }

    private static void filterMapUsingStream(List<String> list) {
        Integer l =list.stream().filter( name -> name !="A").map(String :: hashCode).findAny().orElse(1);
        System.out.println(l);
        list.stream().filter( name -> name =="A").map(String :: hashCode).forEach( value -> System.out.println(value));
    }

    private static void filterUsingStream(List<String> list) {
        list.stream().filter( name -> name !="A").forEach( value -> System.out.println(value));
    }

    private static void filterMapUsingStream(){
        Person p1 = new Person("123", "a", 12);
        Person p2 = new Person("124", "ab", 13);
        Person p3 = new Person("125", "ac", 14);
        Person p4 = new Person("126", "ad", 10);
        List<Person> persons = new ArrayList<>();
        persons.add(p1);persons.add(p2);persons.add(p3);persons.add(p4);

        List<Person> persons1 = persons.stream().filter( person -> person.age>12).collect(Collectors.toList());
        System.out.println(persons1);

        Map map = persons.stream().filter(person -> person.age>12).collect(Collectors.toMap(Person::getId, Person::getAge));
        System.out.println(map);

        List lst = persons.stream().filter(person -> person.age>9).sorted((pr1, pr2) -> pr1.age.compareTo(pr2.age)).collect(Collectors.toList());
        System.out.println(lst);
        List lst2 = persons.stream().map(person -> person.age).collect(Collectors.toList());
        System.out.println(lst2);

        List lst3 = persons.stream().flatMap(person -> Stream.of(person.age)).collect(Collectors.toList());
        System.out.println(lst3);

    }

    static class Person {
        String id;
        String name;
        Integer age;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Person(String id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;

        }
    }
}
