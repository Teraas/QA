/**
 * 
 */
package src.myTests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kirti
 *
 */
public class Assignment2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		 
	       while(in.hasNextLine()){
	    	   
	    	   String S1=in.next();
	    	   if(!in.hasNext()){
	    	   return;
	    	   }
	    	   else {
	    		 String L=in.next();
	    		 System.out.println(getType1(S1,L) +" "+ getType2(S1,L) +" "+getType3(S1,L));
	    	   }
	       }
	}
	
	public static int getType1(String S, String L){
		
		
		Pattern pattern = Pattern.compile(L);
        Matcher matcher = pattern.matcher(S);
        int count=0;
        System.out.println("type1");
        while(matcher.find())
        	count++;
		
		return count;
	}
public static int getType2(String S, String L){
		
	
	
	ArrayList<String> b=new ArrayList<String>();
		char[] a=S.toCharArray();
		for(Character c:a)
			b.add(c.toString());
		
	    HashSet<String> set=new HashSet<String>();
	    
	    for(Character c:a){
	    	ArrayList<String> temp=b;
	    	temp.remove(c.toString());
	    	set.add(temp.toString());
	    	
	    }
	    int count=0;
	    Iterator value=set.iterator();
	    System.out.println("type2");
	    while(value.hasNext()) {
	    	String l=value.next().toString();
		Pattern pattern = Pattern.compile(L);
        Matcher matcher = pattern.matcher(l);
        
        while(matcher.find())
        	count++;
	    }
		return count;
	}

public static int getType3(String S, String L){
	
	char[] d = {'S','T','G','A'};
	ArrayList<String> b=new ArrayList<String>();
	char[] a=S.toCharArray();
	for(Character c:a){
			b.add(c.toString());
			}
       HashSet<String> set=new HashSet<String>();
   
    for(Iterator<String> c=b.iterator();c.hasNext();){
    	ArrayList<String> temp=b;
    	for(int j=0;j<a.length;j++){
    	temp.add(j,c.toString());
    	set.add(temp.toString());
    	}
    }
    int count=0;
    System.out.println("type3");
    Iterator value=set.iterator();
    while(value.hasNext()) {
    	String l=value.next().toString();
	Pattern pattern = Pattern.compile(L);
    Matcher matcher = pattern.matcher(l);
    
    while(matcher.find())
    	count++;
    }
	
	return count;
}

}