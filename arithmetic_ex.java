//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * The arithemtic expression class contains all the parts of an arithmetic
 * expression in the form detailed in the Julia grammar provided. An arithmetic
 * expression is either an id, a literal integer, or a binary expression. The
 * type integer variable is the indicator for with type of data is stored within
 * the arithmetic expression instance.
 */
public class arithmetic_ex {
    String id;
    int literal_integer;
    binary_ex binary;
    int type; // id type = 0, integer type = 1, binary expression type = 2

    /**
     * Constructor The constructor determines the type of the expression passed in.
     * It first tries to parse the expression to an integer. If if can parse to an
     * integer, it stores it in the integer variable literal_integer and sets the
     * type to 0. If this throws an exception, meaning the expression is not an
     * integer, then the catch block catches the exception. If an exception is
     * caught, the length is checked. Because all IDs are a single character, if the
     * length of the expression is 1 then we know the expression is an id. The
     * String variable id is set to 1. Otherwise, the expression is sent to the
     * binary expression constructor and the type is set to 2.
     * 
     * @param expression
     */
    public arithmetic_ex(String expression) { // 5 + 10
        expression = expression.trim(); // remove any white space on the left or right side
        try {
            literal_integer = Integer.parseInt(expression); // if does not throw exception, expression is an integer
            type = 1;
        } catch (InputMismatchException e) {
            idOrBinary(expression); // calls method for modularity
        } catch (NumberFormatException e) {
            idOrBinary(expression); // calls method for modularity
        }
    }

    /**
     * Id or Binary Expression method determines if the expression is an id or a
     * binary expression. Created to increase modularity of class so that same code
     * does not appear under both catch expressions above.
     * 
     * @param expression
     */
    public void idOrBinary(String expression) {
        if (expression.length() == 1) { // if length = 1, then expression is id
            id = expression;
            type = 0;
        } else { // else, binary expression
            binary = new binary_ex(expression);
            type = 2;
        }
    }

    /**
     * getType method returns the integer variable 'type'
     * 
     * @return int type
     */
    public int getType() {
        return type;
    }

    /**
     * getId method returns the String variable 'id' if variable type is set to 0
     * 
     * @return String id
     */
    public String getId() {
        if (type == 0)
            return id;
        else
            return null;
    }

    /**
     * getInt method returns the int variable 'literal_integer' if variable type is
     * set to 1
     * 
     * @return int literal_integer
     */
    public int getInt() {
        if (type == 1)
            return literal_integer;
        else
            return -1;
    }

    /**
     * getBinaryEx method returns the toString String value of the binary_ex binary
     * if variable type is set to 2
     * 
     * @return String returned by binary_ex toString method
     */
    public String getBinaryEx() {
        if (type == 2)
            return binary.toString();
        else
            return null;
    }

    /**
     * toGrammar prints out the grammar of this class. Each class represents the LHS
     * abstraction of the Julia grammar.
     * 
     * Full Grammar: <arithmetic_expression> -> id | literal_integer |
     * <binary_expression>
     */
    public void toGrammar() {
        System.out.print("<arithmetic_expression> -> "); // printed here b/c it will always be printed
        switch (type) {
        case 0:
            System.out.println("id");
            printContents();
            break;
        case 1:
            System.out.println("literal_integer");
            printContents();
            break;
        case 2:
            System.out.println("<binary_expression>");
            binary.toGrammar();
            break;
        default:
            System.out.println("Failed to get grammar");
        }

    }

    /**
     * Prints the contents of the id, literal integer, or binary epxression based on
     * which object is stored.
     */
    public void printContents() {
        switch (type) {
        case 0:
            System.out.println("id = " + id);
            break;
        case 1:
            System.out.println("literal_integer = " + literal_integer);
            break;
        default:
            System.out.println();
            break;
        }
    }

    /**
     * Prints the expression in prefix notation if contents is a binary expression.
     * Calls binary expression prefix method to do so.
     * 
     */
    public void prefix() {
        switch (type) {
        case 0:
            System.out.print(id + " ");
            break;
        case 1:
            System.out.print(literal_integer + " ");
            break;
        case 2:
            binary.prefix();
            break;
        }
    }

    public int compute(ArrayList<variable> variables) {
        int value = -999;
        if (type == 0) {
            value = -999;
        } else if (type == 1) {
            return literal_integer;
        } else if (type == 2) {
            return binary.compute(variables);
        }

        if (value == -999) {
            for (int i = 0; i < variables.size(); i++) {
                if (variables.get(i).getId().equals(id))
                    return variables.get(i).getValue();
            }
        }
        return -1;
    }
}