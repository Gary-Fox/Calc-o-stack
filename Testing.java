import static org.junit.Assert.assertEquals;

import java.util.Map;
import org.junit.Test;

/** Unit tests for Calculator class
 */
public class Testing {
    //#################################[ conversion Tests ]##################################
    @Test
    public void basicEquasion() {
        String infix = "A + B * C - D / E";
        String expectedPostfix = "ABC*+DE/-";
        String actualPostfix = Calculator.convertToPostfix(infix);
        assertEquals(expectedPostfix, actualPostfix);
    }


    @Test
    public void complexEquasion() {
        String infix = "(A + B)^C * (C - D^C) / E";
        String expectedPostfix = "AB+C^CDC^-*E/";
        String actualPostfix = Calculator.convertToPostfix(infix);
        assertEquals(expectedPostfix, actualPostfix);
    }

        @Test
    public void nestedParenthesis() {
        String infix = "(C^F * (A*(B^(D+E)+Z)))/(A+(B*(F+G)))";
        String expectedPostfix = "CF^ABDE+^Z+**ABFG+*+/";
        String actualPostfix = Calculator.convertToPostfix(infix);
        assertEquals(expectedPostfix, actualPostfix);
    }

    //#################################[ Evaluation Tests ]##################################

        @Test
    public void basicEquasionEval() {
        String Postfix = "123*+45/-";
        //String expectedPostfix = "ABC*+DE/-";
        Double expectedEval = 6.2;
        Map<Character, Double> vals = Map.of(
            '1', 1.0,
            '2', 2.0,
            '3', 3.0,
            '4', 4.0,
            '5', 5.0
        );
        double actualEval = Calculator.evaluatePostfix(Postfix, vals);

        int result = Double.compare(actualEval, expectedEval);

        assertEquals(0, result);
    }

        @Test
    public void complexEquasionEval() {
        String postFix = "AB+C^CDC^-*E/";
        Double expectedEval = -329.4;
        Map<Character, Double> vals = Map.of(
            'A', 1.0,
            'B', 2.0,
            'C', 3.0,
            'D', 4.0,
            'E', 5.0
        );
        double actualEval = Calculator.evaluatePostfix(postFix, vals);

        int result = Double.compare(actualEval, expectedEval);

        assertEquals(0, result);
    }

        @Test
    public void nestedParenthesisEval() {
        String postFix = "CF^ABDE+^Z+**ABFG+*+/";
        Double expectedEval = 19.8192435577;
        Map<Character, Double> vals = Map.of(
            'A', 2.0,
            'B', 1.5,
            'C', 1.5,
            'D', 3.0,
            'E', 5.0,
            'F', 2.0,
            'G', 1.0,
            'Z', 3.0
        );
        double actualEval = Calculator.evaluatePostfix(postFix, vals);

        int result = Double.compare(actualEval, expectedEval);
        System.out.println(actualEval);

        assertEquals(0, result);
    }

        @Test
    public void evalMixedMap() {
        String Postfix = "12Z*+4X/-";
        //String expectedPostfix = "ABC*+DE/-";
        Double expectedEval = 6.2;
        Map<Character, Double> vals = Map.of(
            '1', 1.0,
            '2', 2.0,
            'Z', 3.0,
            '4', 4.0,
            'X', 5.0
        );
        double actualEval = Calculator.evaluatePostfix(Postfix, vals);

        int result = Double.compare(actualEval, expectedEval);

        assertEquals(0, result);
    }

    //#################################[ Simotanious tests ]##################################

    @Test
    public void infixToPostfixAndEval() {
        String infix = "a * b / (c-a) + d * e";
        String postfix = Calculator.convertToPostfix(infix);
        Double expectedEval = 33.0;
        Map<Character, Double> vals = Map.of(
            'a', 2.0,
            'b', 3.0,
            'c', 4.0,
            'd', 5.0,
            'e', 6.0
        );

        double actualEval = Calculator.evaluatePostfix(postfix, vals);

        int result = Double.compare(actualEval, expectedEval);

        assertEquals(0, result);
    }

        @Test
    public void infixToPostfixAndEval2() {
        String infix = "(A + B)^C * (C - D^C) / E";
        String postfix = Calculator.convertToPostfix(infix);
        Double expectedEval = -329.4;
        Map<Character, Double> vals = Map.of(
            'A', 1.0,
            'B', 2.0,
            'C', 3.0,
            'D', 4.0,
            'E', 5.0
        );

        double actualEval = Calculator.evaluatePostfix(postfix, vals);

        int result = Double.compare(actualEval, expectedEval);

        assertEquals(0, result);
    }

    //#################################[ Error tests ]##################################

        @Test
    public void evalMissingVariable() {
        String Postfix = "AB+C*";
        Map<Character, Double> vals = Map.of(
            'A', 1.0,
            'B', 2.0
        );
        try {
            double actualEval = Calculator.evaluatePostfix(Postfix, vals);
        } catch (IllegalArgumentException e) {
            assertEquals("Missing value for: C", e.getMessage());
        }

    }

}
