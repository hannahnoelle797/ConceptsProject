//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

import java.util.ArrayList;

/**
 * The statement class contains one of five types of statements: if statement,
 * assignment statement, while statement, print statement, or for statement. The
 * variable type is set based on which type of statement is being stored.
 */
public class statement {
    if_statement ifStmt; // type = 0
    assignment_statement assignStmt; // type = 1
    while_statement whileStmt; // type = 2
    print_statement printStmt; // type = 3
    for_statement forStmt; // type = 4
    int type;

    /**
     * Empty constructor.
     */
    public statement() {

    }

    /**
     * Constructor that reads in a single line. Used only for assignment statements
     * and print statements.
     * 
     * @param type
     * @param expression
     */
    public statement(int type, String expression) {
        try {
            this.type = type;
            switch (type) {
            case 1:
                assignStmt = new assignment_statement(expression);
                break;
            case 3:
                printStmt = new print_statement(
                        expression.substring(expression.indexOf("(") + 1, expression.indexOf(")")));
                break;
            default:
                throw new JuliaSyntaxException("grammar");
            }
        } catch (JuliaSyntaxException e) {

        }
    }

    /**
     * Constructor that reads in an array list of lines. Used for all types of
     * statements. The epxressions array list is passed to the constructor of its
     * corresponding statement type.
     * 
     * @param type
     * @param expressions
     */
    public statement(int type, ArrayList<String> expressions) {
        try {
            this.type = type;
            switch (type) {
            case 0:
                ifStmt = new if_statement(expressions);
                break;
            case 1:
                assignStmt = new assignment_statement(expressions.get(0));
                break;
            case 2:
                whileStmt = new while_statement(expressions);
                break;
            case 3:
                printStmt = new print_statement(expressions.get(0));
                break;
            case 4:
                forStmt = new for_statement(expressions);
                break;
            default:
                throw new JuliaSyntaxException(expressions.get(0));
            }
        } catch (JuliaSyntaxException e) {

        }
    }

    /**
     * toGrammar prints out the grammar of this class. Each class represents the LHS
     * abstraction of the Julia grammar. It then calls the toGrammar for each
     * non-terminal piece of the grammar. The statement classes only calls one
     * toGrammar method.
     * 
     * Full Grammar: <statement> → <if_statement> | <assignment_statement> |
     * <while_statement> | <print_statement> | <for_statement>
     */
    public void toGrammar() {
        try {
            switch (type) {
            case 0:
                System.out.println("<statement> -> <if_statement>");
                ifStmt.toGrammar();
                break;
            case 1:
                System.out.println("<statement> -> <assignment_statement>");
                assignStmt.toGrammar();
                break;
            case 2:
                System.out.println("<statement> -> <while_statement>");
                whileStmt.toGrammar();
                break;
            case 3:
                System.out.println("<statement> -> <print_statement>");
                printStmt.toGrammar();
                break;
            case 4:
                System.out.println("<statement> -> <for_statement>");
                forStmt.toGrammar();
                break;
            default:
                throw new JuliaSyntaxException("grammar");
            }
        } catch (JuliaSyntaxException e) {

        }
    }

    /**
     * Prints the contents of the statement based on it's type. Uses a switch
     * statement determine which printContents method to call.
     */
    public void printContents() {
        try {
            switch (type) {
            case 1:
                assignStmt.printContents();
                break;
            case 2:
            case 4:
                forStmt.printContents();
                break;
            default:
                throw new JuliaSyntaxException("grammar");
            }
        } catch (JuliaSyntaxException e) {
        }
    }
}