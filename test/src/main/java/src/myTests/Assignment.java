package src.myTests;

import java.util.Scanner;

/**
 * @author kirti
 *
 */
public class Assignment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner in = new Scanner(System.in);
		 
       int count=in.nextInt();
       if (count==0)
    	   System.out.println("0");
       else {
       for(int i=0;i<count;i++)
       {
    	   int digitCount=0;
    	   int num=in.nextInt();
    	   if (num==0)
    		   digitCount=1;
    		   while(num!=0){
    			   num =num/10;
    			   digitCount++;
    	   }
    	   
    	   System.out.println(digitCount);
       }
       }
       
	}

}
