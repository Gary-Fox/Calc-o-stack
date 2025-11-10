import java.util.Map;

/** Main application for transforming in-fix expressions into post-fix, then evaluating those post-fix expressions
 */
public class Calculator 
{
    /** Converts an infix expression to postfix notation utalizing a linked stack
     * @param infix An equasion as a string
     * @return A string containing the postfix expression of infix 
     */
    public static String convertToPostfix(String infix) {
        LinkedStack<Character> operatorStack = new LinkedStack<>();
        StringBuilder postfix = new StringBuilder();

        if (infix == null || infix.isEmpty()) {
            System.out.println("Empty or null expression!");
            return "";
        }
            
        for (int i = 0; i < infix.length(); i++) {
            char nextCharacter = infix.charAt(i);

            if (Character.isWhitespace(nextCharacter)) {
                continue;
            }

            switch (nextCharacter) {
                case '^':
                    operatorStack.push(nextCharacter);
                    break;

                case '+':
                case '-':
                case '*':
                case '/':
                    while (!operatorStack.isEmpty() &&
                           precedence(nextCharacter) <= precedence(operatorStack.peek())) {
                        postfix.append(operatorStack.pop());
                    }
                    operatorStack.push(nextCharacter);
                    break;

                case '(':
                    operatorStack.push(nextCharacter);
                    break;

                case ')':
                    while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                        postfix.append(operatorStack.pop());
                    }
                    if (!operatorStack.isEmpty()) {
                        operatorStack.pop(); // remove '('
                    }
                    break;

                default:
                    if (Character.isLetterOrDigit(nextCharacter)) {
                        postfix.append(nextCharacter);
                    }
                    break;
            }
        }

        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
        }

        return postfix.toString();
    }

    /** Creates a hierarchy of priority of operations in-line with PEMDAS
     * @param operator The operator to be evaluated
     * @return An int representing the operator's priority
     */
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    /** Evaluates a postfix expression given a mapping of variable values
     * @param postfix A String postfix expression
     * @param values A map of Characters and Doubles to assign values to variables within the postfix
     * @return The top of the stack, which should contain the result of the expression
     */
     public static double evaluatePostfix(String postfix, Map<Character, Double> values)
    {
        if (postfix == null || postfix.isEmpty())
        {
            System.out.println("Empty or null expression!");
            return 0;
        }

        StackImplementation<Double> stack = new ResizableArrayStack<>();

        for (int i = 0; i < postfix.length(); i++)
        {
            char ch = postfix.charAt(i);

            if (ch == ' ')
            {
                continue;
            }

            if (Character.isLetter(ch))
            {
                Double v = values.get(ch);
                if (v == null)
                {
                    throw new IllegalArgumentException("Missing value for: " + ch);
                }
                stack.push(v);
            }

            if (Character.isDigit(ch))
            {
                // If a mapping for digit-characters is provided, prefer using that
                // (this handles expressions like "123*+" where '1','2','3' are distinct operands).
                if (values != null && values.containsKey(ch)) {
                    stack.push(values.get(ch));
                } else {
                    // Otherwise, parse a (possibly) multi-digit number from the string.
                    double num = 0;
                    while (i < postfix.length() && Character.isDigit(postfix.charAt(i)))
                    {
                        num = num * 10 + (postfix.charAt(i) - '0');
                        i++;
                    }
                    i--; // Adjust for the extra increment in the inner while loop
                    stack.push(num);
                }
            }

            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^')
            {
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Malformed postfix expression: too many operators, not enough operands");
                    }
                    double b = stack.pop();
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Malformed postfix expression: too many operators, not enough operands");
                    }
                    double a = stack.pop();
                    double r = 0;

                if (ch == '+')
                {
                    r = a + b;
                }
                else if (ch == '-')
                {
                    r = a - b;
                }
                else if (ch == '*')
                {
                    r = a * b;
                }
                else if (ch == '/')
                {
                    r = a / b;
                }
                else if (ch == '^')
                {
                    r = Math.pow(a, b);
                }

                stack.push(r);
            }
                else if (!Character.isDigit(ch) && !Character.isLetter(ch))
            {
                throw new IllegalArgumentException("Invalid character in expression: " + ch);
            }
        }

        // If evaluation produced an empty stack or has more than one value left, expression was malformed.
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Malformed postfix expression or missing operands");
        }
        
        double result = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Malformed postfix expression: too many operands");
        }

        return result;
    }

    /** Evaluates a postfix expression only given a postfix expression
     * @param postfix A String postfix expression
     * @return The top of the stack, which should contain the result of the expression
     */
    public static double evaluatePostfix(String postfix)
    {
        if (postfix == null || postfix.isEmpty())
        {
            System.out.println("Empty or null expression!");
            return 0;
        }

        StackImplementation<Double> stack = new ResizableArrayStack<>();

        for (int i = 0; i < postfix.length(); i++)
        {
            char ch = postfix.charAt(i);

            if (ch == ' ')
            {
                continue;
            }

            if (Character.isLetter(ch))
            {
                throw new IllegalArgumentException("Missing value for variable: " + ch);
            }

            if (Character.isDigit(ch))
            {
                // If a mapping for digit-characters is provided, prefer using that
                // (this handles expressions like "123*+" where '1','2','3' are distinct operands).
                    double num = 0;
                    while (i < postfix.length() && Character.isDigit(postfix.charAt(i)))
                    {
                        num = num * 10 + (postfix.charAt(i) - '0');
                        i++;
                    }
                    i--; // Adjust for the extra increment in the inner while loop
                    stack.push(num);
            }

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^')
            {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Malformed postfix expression: too many operators, not enough operands");
                }
                double b = stack.pop();
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Malformed postfix expression: too many operators, not enough operands");
                }
                double a = stack.pop();
                double r = 0;

                if (ch == '+')
                {
                    r = a + b;
                }
                else if (ch == '-')
                {
                    r = a - b;
                }
                else if (ch == '*')
                {
                    r = a * b;
                }
                else if (ch == '/')
                {
                    r = a / b;
                }
                else if (ch == '^')
                {
                    r = Math.pow(a, b);
                }

                stack.push(r);
            }
            else if (!Character.isDigit(ch) && !Character.isLetter(ch))
            {
                throw new IllegalArgumentException("Invalid character in expression: " + ch);
            }
        }

        // If evaluation produced an empty stack or has more than one value left, expression was malformed.
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Malformed postfix expression or missing operands");
        }
        
        double result = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Malformed postfix expression: too many operands");
        }

        return result;
    }

    /** A demo to show the power of the Calculator.java application
     * @param args Command line arguments
     */
    public static void main(String[] args)
    {   
        String infix = "a * b / (c-a) + d * e";
        String postfix = convertToPostfix(infix);
        System.out.println("Postfix: " + postfix);
        //String postfix = "ab*ca-/de*+";
        Map<Character, Double> vals = Map.of(
            'a', 2.0,
            'b', 3.0,
            'c', 4.0,
            'd', 5.0,
            'e', 6.0
        );

        double result = evaluatePostfix(postfix, vals);
        //double result = 33.0;
        System.out.println("Result: " + result);
    }

}
