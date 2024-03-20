package stacks;

import java.util.Stack;

/**
 * @author harish.kumar-mbp
 * createdOn 09/03/24
 */
public class ValidBrackets {
    public static void main(String[] args){
        boolean res = balancedBrackets("([])(){}(())()()");
        System.out.println(res);
    }

    public static boolean balancedBrackets(String str) {
        // Write your code here.
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();

        for(char ch : chars){

            switch (ch) {
                case '(' : {
                    stack.push('(');
                    break;
                }
                case '[' : {
                    stack.push('[');
                    break;

                }
                case '{' : {
                    stack.push('{');
                    break;

                }
                case ')' : {
                    //char c = stack.pop();
                    if(stack.isEmpty() || stack.pop() != '('){
                        return false;
                    }
                    break;
                }
                case '}' : {
                    if(stack.isEmpty() || stack.pop() != '{'){
                        return false;
                    }
                    break;

                }
                case ']' : {
                    if(stack.isEmpty() || stack.pop() != '['){
                        return false;
                    }
                    break;

                }
                default : {
                    // do nothing
                    break;
                }
            }
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }
}
