/*
 * Name: Jinhyo Park
 * Student ID: 2018121022
 */

public class PostfixCalculator implements IPostfixCalculator
{   
    // you may declare additional variables here.
    Stack<Integer> stack;

    public PostfixCalculator()
    {
        // implement your constructor here.
        stack = new Stack<Integer>();
    }


    @Override
    public int evaluate(String exp)
    {
        /*
        * evaluate
        *
        * Evaluate the expression and return the result
        *
        * exp - String; the string representation of the postfix expression
        *
        * Note: We guarantee exp will always be a valid postfix expression of
        *       length >= 1. Only operators +, -, and * will be used and only
        *       non-negative integers will be used as terms. Terms and operators
        *       will be separated by spaces, for example "5 3 x". At no point in
        *       the evaluation will an intermediate value be large enough to
        *       overflow the Java int type. However, it can go below 0.
        */
        String[] tokens = exp.split(" ");
        for (String token : tokens) {
            switch (token) {
                case "+": {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a + b);
                    break;
                }
                case "-": {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b - a);
                    break;
                }
                case "x": {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a * b);
                    break;
                }
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        };
        return stack.pop();
    }
}