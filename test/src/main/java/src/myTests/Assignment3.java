package src.myTests;

import java.util.Scanner;

public class Assignment3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		 
	       int count=in.nextInt();
	       for(int i=0;i<count;i++){
	    	   in.nextLine();
	    	   System.out.println(in.nextInt() +" " + printUniqueParts(in.nextInt()));
	       }
	}

	
	
	static int printUniqueParts(int n) 
    { 
        int[] p = new int[n]; 
        int k = 0;  
        p[k] = n;  
   
        int count=0; 
        while (true) 
        { 
           count++; 
            int rem_val = 0; 
            while (k >= 0 && p[k] == 1) 
            { 
                rem_val += p[k]; 
                k--; 
            } 
    
            if (k < 0)  return count; 
   
            
            p[k]--; 
            rem_val++; 
    
            while (rem_val > p[k]) 
            { 
                p[k+1] = p[k]; 
                rem_val = rem_val - p[k]; 
                k++; 
            } 
   
            // Copy rem_val to next position and increment position 
            p[k+1] = rem_val; 
            k++; 
        } 
    }
}