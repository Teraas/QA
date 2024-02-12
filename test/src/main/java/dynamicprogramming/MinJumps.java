package dynamicprogramming;

/**
 * @author harish.kumar-mbp
 * createdOn 28/01/24
 */
public class MinJumps {
    public static void main(String[] args){
        int[] arr = {3,4,2,1,2,3,7,1,1,1,3};
        System.out.println(" res " + minNumberOfJumps(arr));
    }

    public static int minNumberOfJumps2(int[] array) {
        // Write your code here.
        //int[] jumps = new int[array.length];
        int maxJump = array[0];
        int steps = array[0];
        int jumps = 0;



        for(int i =1;i<array.length;i++){
            maxJump = Math.max(maxJump, array[i] + i);
            steps--;
            if(steps == 0){
                jumps++;
                steps = maxJump - i;
            }


        }
        System.out.println(jumps);
        return jumps+1;
    }
    public static int minNumberOfJumps(int[] array) {
        // Write your code here.
        int[] jumps = new int[array.length];
        jumps[0] = 0;
        for(int i =1;i<array.length;i++)
            jumps[i] = Integer.MAX_VALUE;


        for(int i =1;i<array.length;i++){
            for(int j =0;j<i;j++){
                if( (array[j] +j) >= i){
                    jumps[i] = Math.min(jumps[i], jumps[j] + 1);
                }
            }
        }
        System.out.println(jumps);
        return jumps[array.length-1];
    }
}
