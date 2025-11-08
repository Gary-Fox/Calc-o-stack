import static org.junit.Assert.assertEquals;

import java.util.Map;
import org.junit.Test;


public class Testing {
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

    //Simotanious tests

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


}
