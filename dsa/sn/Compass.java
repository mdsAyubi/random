import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 * =================================
 * Evaluate a mathematical calculation expression.
 * Given an input as a mathematical calculation expression string. 
 * Expect an output of the result of the calculation expression.
 * - No bracket. Only +, -, * and /.
 
 * Example: "-15+4*5/2" => 5
 
 1. Tokenization -- which is find the trokens based on (+-/*)
  1.1 Convert the token to numbers
 2. Convert from infix to post fix  -- usually done using a Stack
 3. Evaluate the postfix -- agin usually done through a Stack
 
 */

class Compass {
    public static void main(String[] args) throws Exception {

        Compass s = new Compass();
        s.evaluate("15+4*5/2");

    }

    private int precedence(String s) {
        switch (s) {
            case "+":
            case "-":
                return 1;

            case "/":
            case "*":
                return 2;
        }

        return -1;
    }

    public void evaluate(String str) throws Exception {
        var tokenizer = new StringTokenizer(str, "/*+-", true);
        var delimSet = Set.of("+", "-", "*", "/");

        var tokens = new ArrayList<String>();
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }

        var postfix = new ArrayList<String>();
        var stack = new java.util.Stack<String>();

        System.out.println(tokens.toString());

        tokens.forEach(t -> {
            if (!delimSet.contains(t)) {
                // operand
                postfix.add(t);
            }
            // opeator
            else {
                while (!stack.isEmpty() && precedence(t) <= precedence(stack.peek())) {
                    postfix.add(stack.pop());
                }
                stack.push(t);
            }
        });
        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }

        String postfixExpr = postfix.toString();
        System.out.println(postfixExpr);

        var expStack = new java.util.Stack<Integer>();
        postfix.forEach(t -> {
            if (!delimSet.contains(t)) {
                // operand
                expStack.push(Integer.parseInt(t));
            } else {
                Integer op1 = expStack.pop();
                Integer op2 = expStack.pop();
                switch (t) {
                    case "+":
                        expStack.push(op1 + op2);
                        break;
                    case "-":
                        expStack.push(op2 - op1);
                        break;
                    case "/":
                        expStack.push(op2 / op1);
                        break;
                    case "*":
                        expStack.push(op1 * op2);
                        break;
                }
            }
        });
        System.out.println(expStack.pop());
    }
}
