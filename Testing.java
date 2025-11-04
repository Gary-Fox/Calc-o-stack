import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class Testing {
    @Test
    public void basicEquasion() {
        String infix = "A + B * C - D / E";
        String expectedPostfix = "ABC*+DE/-";
        String actualPostfix = ConvertToPostfix.convertToPostfix(infix);
        assertEquals(expectedPostfix, actualPostfix);
    }

    @Test
    public void complexEquasion() {
        String infix = "(A + B)^C * (C - D^C) / E";
        String expectedPostfix = "AB+C^CDC^-*E/";
        String actualPostfix = ConvertToPostfix.convertToPostfix(infix);
        assertEquals(expectedPostfix, actualPostfix);
    }

        @Test
    public void nestedParenthesis() {
        String infix = "(C^F * (A*(B^(D+E)+Z)))/(A+(B*(F+G)))";
        String expectedPostfix = "CF^ABDE+^Z+**ABFG+*+/";
        String actualPostfix = ConvertToPostfix.convertToPostfix(infix);
        assertEquals(expectedPostfix, actualPostfix);
    }


    
}
