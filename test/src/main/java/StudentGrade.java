import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author harish.kumar-mbp
 * createdOn 17/02/24
 */
public class StudentGrade {

    String name;
    Map<String, Integer> grades;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, Integer> grades) {
        this.grades = grades;
    }


    public static void main(String[] agrs){
        StudentGrade st1 = new StudentGrade();
        Map<String, Integer> val = new HashMap<>();
        val.put("Math", 90);
        val.put("Physics", 85);
        val.put("History", 78);
        Map<String, Integer> val2 = new HashMap<>();
        val2.put("Math", 92);
        val2.put("Physics", 88);
        val2.put("History", 75);
        Map<String, Integer> val3 = new HashMap<>();
        val3.put("Math", 85);
        val3.put("Physics", 95);
        val3.put("History", 80);

        StudentGrade st2 = new StudentGrade();
        StudentGrade st3 = new StudentGrade();
        st1.setName("a");st2.setName("b");st3.setName("c");

        st1.setGrades(val);st2.setGrades(val2);st3.setGrades(val3);

        List<StudentGrade> list = new ArrayList<>();

        list.add(st1);list.add(st2);list.add(st3);


        //Consider the  class representing a student's name, grade (marks out of 100) in different subjects:
        //Now, assume you have a list of `StudentGrade` objects, each containing a student's name and their grades in various subjects.
        //Your task is to find the average grade for each subject across all students and identify the subject with the highest average grade
        //

        //List lst = list.stream().map( s -> s.getGrades()).collect(Collectors.groupingBy(Map::keySet, Collectors.toList()));
        //System.out.println(lst);




    }
}
