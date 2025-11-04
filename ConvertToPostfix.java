public class ConvertToPostfix {

    public static String convertToPostfix(String infix) {
        LinkedStack<Character> operatorStack = new LinkedStack<>();
        StringBuilder postfix = new StringBuilder();

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
}

