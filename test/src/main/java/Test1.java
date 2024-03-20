/**
 * @author harish.kumar-mbp
 * createdOn 16/02/24
 */
public class Test1 {

    // [9,9] -> [1,0,0]
    //Explanation:
    //Input 1 - [1,2,4] -> 124 -> 124 + 1 -> 125 -> [1,2,5]
    //
    //Input 2 - [0,0,2] -> 002 -> 002 + 1 -> 003 -> [0,0,3]
    //
    //Input 3 - [1, 9,9] -> 99 -> 99 + 1 -> 100 -> [1,0,0]
    public static void main(String[] args){

        int[] arr = {0, 0, 2};

        int[] res = getResult(arr);
        System.out.println(res);
    }


    public static  int[] getResult(int[] input){
        // increment only by 1
        int len = input.length;
        int carry = 1;
        for(int i= len-1;i>=0;i--){
            int currentnNum = input[i] + carry;
            // only add 1 when index = last
//            if(i == len -1){
//                currentnNum +=1;
//            }

            if(currentnNum == 10){
                carry = 1;
                currentnNum = 0;
            }
            else {
                carry = 0;
            }
            input[i] = currentnNum;
        }
        int[] output = input;;
        if(carry>0){
            // reszie
            output = new int[len+1];
            output[0] = carry;
            for(int i =0;i<len;i++){
                output[i+1] = input[i];
            }
        }
        //int[] output =
        return output;

    }
}
