/**
 * 
 */


/**
 * @author kirti
 *
 */
public class Assignment {

	/**
	 * @param args
	 */
	public static void main(int[] args) {
		// TODO Auto-generated method stub
       int count=args[0];
       
       for(int i=0;i<args.length-1;i++){
    	   int digitCount=0;
    	   int num=args[i];
    	   if (num==0)
    		   System.out.println("1");
    		   while(num!=0){
    			   num =num/10;
    			   count++;
    	   }
    	   
    	   System.out.println(count);
       }
       
	}

}
