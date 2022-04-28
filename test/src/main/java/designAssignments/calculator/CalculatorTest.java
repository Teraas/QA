package designAssignments.calculator;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Stack;

import static org.junit.Assert.*;

 class Calculator {
    /**
     * Implement calculator for single digits
     * */
    public static int calculator(String str) {

        int result = 0;
        int sign = 1;
        final Stack<Integer> stackVals = new Stack<Integer>();
        final Stack<Character> stackOps = new Stack<Character>();

        for (int i = 0; i < str.length(); i++) {
            final char curr = str.charAt(i);
            if (curr == ' ') {
                continue;
            } else if (curr == '+') {
                sign = 1;
            } else if (curr == '-') {
                sign = -1;
            } else if (curr >= '0' && curr <= '9') {
                String num = Character.toString(curr);
                int val = Integer.parseInt(num);
                stackVals.push(val);
            } else if (curr == '(') {
                stackOps.push(curr);

            } else if (curr == ')') {
                // calculate whole expression for brace
            }
        }
        return result;
    }
}

public class CalculatorTest {

    // tests

    @org.junit.jupiter.api.Test
    public void simpleExpressionTest() {
        assertEquals(Calculator.calculator("2 + 2"), 4);
    }

    @Test
    public void expressionWithParenthesisTest() {
        assertEquals(Calculator.calculator("((2+2) - 3)"), 1);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CalculatorTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

}
