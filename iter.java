//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

/**
 * The iterator class contains the start and end values of the for loop. The
 * left and right sides of the iterator are usually integer, but can be an id or
 * binary expression, which is why there are arithmetic_ex objects.
 */
public class iter {
    arithmetic_ex left;
    arithmetic_ex right;

    /**
     * Constructor. The constructor checks to make sure the expression contains a
     * colon. If it does not, an error is thrown. If it does then the expression is
     * split on the colon. The first element is the left arithmetic expression and
     * the second element is the right arithmetic expression.
     * 
     * @param expression
     */
    public iter(String expression) {
        try {
            if (expression.contains(":")) {
                String[] parts = expression.split(":");
                left = new arithmetic_ex(parts[0]);
                right = new arithmetic_ex(parts[1]);
            } else {
                throw new JuliaSyntaxException("for iterator");
            }
        } catch (JuliaSyntaxException e) {

        }
    }

    /**
     * toGrammar prints out the grammar of this class. Each class represents the LHS
     * abstraction of the Julia grammar. It then calls the toGrammar for each
     * non-terminal piece of the grammar.
     * 
     * Full Grammar: <iter> → <arithmetic_expression> : <arithmetic_expression>
     */
    public void toGrammar() {
        System.out.println("<iter> -> <arithmetic_expression> : <arithmetic_expression>");
        left.toGrammar();
        right.toGrammar();
    }

    public void printContents() {
    }

}