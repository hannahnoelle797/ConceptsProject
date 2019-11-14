//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

/**
 * The print statement class holds the arithemtic expression contained within
 * the parenthesis of the print statement in the file.
 */
public class print_statement {
    arithmetic_ex expression;

    /**
     * Constructor. Passes expression to a new arithmetic expression object
     * 
     * @param expression
     */
    public print_statement(String expression) {
        this.expression = new arithmetic_ex(expression);
    }

    /**
     * toGrammar prints out the grammar of this class. Each class represents the LHS
     * abstraction of the Julia grammar. It then calls the toGrammar for each
     * non-terminal piece of the grammar.
     * 
     * Full Grammar: <print_statement> -> print ( <arithmetic_expression> )
     */
    public void toGrammar() {
        System.out.println("<print_statement> -> print ( <arithmetic_expression> )");
        expression.toGrammar();
    }

    public void printContents() {
    }
}