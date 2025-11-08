import java.util.Map;

public class EvalPostFix
{
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

            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^')
            {
                double b = stack.pop();
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
        }

        return stack.peek();
    }
    
    public static void main(String[] args)
    {
        String postfix = "ab*ca-/de*+";
        Map<Character, Double> vals = Map.of(
            'a', 2.0,
            'b', 3.0,
            'c', 4.0,
            'd', 5.0,
            'e', 6.0
        );

        double result = evaluatePostfix(postfix, vals);
        System.out.println("Result: " + result);
    }
}
