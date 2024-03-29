import java.util.ArrayList;

//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

/**
 * The binary expression class contains a relative operator object and two
 * arithmetic expression objects, left and right, containing the respective
 * sides of the expression. The full epxression is also stored for printing
 * purposes.
 */
public class binary_ex {
    arithmetic_op operator;
    arithmetic_ex left;
    arithmetic_ex right;
    String fullExpression;

    /**
     * Constructor The binary expression constructor uses a series of if statements
     * to determine the operator used. The if statements are ordered using order of
     * operations to assist in the Interpreter portion when computing the results of
     * the expressions. If a valid operator does not exist, an error is thrown.
     * 
     * @param expression
     */
    public binary_ex(String expression) {
        try {
            fullExpression = expression.trim();
            if (expression.contains("*"))
                splitExpression(expression, "*");
            else if (expression.contains("/"))
                splitExpression(expression, "/");
            else if (expression.contains("+"))
                splitExpression(expression, "+");
            else if (expression.contains("-"))
                splitExpression(expression, "-");
            else if (expression.contains("%"))
                splitExpression(expression, "%");
            else if (expression.contains("\\"))
                splitExpression(expression, "\\");
            else
                throw new JuliaSyntaxException("arith");
        } catch (JuliaSyntaxException e) {

        }
    }

    /**
     * The splitExpression method takes in a String expression and String op. The
     * variable op is passed to the constructor of the arithmetic operator class
     * which is stored in the class variable operator. The index of the first
     * instance of the operator that is found. That is used to generate two
     * substrings which are the left and right sides of the expression. The
     * substrings left are right are passed as parameters to the arithmetic
     * expression constructor and stored in their respective arthmeitic_ex object,
     * left and right.
     * 
     * @param expression
     * @param op
     */
    public void splitExpression(String expression, String op) {
        operator = new arithmetic_op(op);
        expression = expression.trim();
        int index = expression.indexOf(op);
        left = new arithmetic_ex((expression.substring(0, index)).trim());
        right = new arithmetic_ex((expression.substring(index + 1)).trim());
    }

    /**
     * toString method returns the full expression originally passed to the binary
     * expression instance before splitting occured.
     * 
     * @return String fullExpression
     */
    public String toString() {
        return fullExpression;
    }

    /**
     * toGrammar prints out the grammar of this class. Each class represents the LHS
     * abstraction of the Julia grammar. It then calls the toGrammar for each
     * non-terminal piece of the grammar.
     * 
     * Full Grammar: <binary_expression> -> <arithmeitc_expression> <arithmetic_op>
     * <arithmetic_expression>
     */
    public void toGrammar() {
        System.out.println("<binary_expression> -> <arithmeitc_expression> <arithmetic_op> <arithmetic_expression>");
        left.toGrammar();
        operator.toGrammar();
        right.toGrammar();
        prefix();
    }

    /**
     * Prints the binary expression in prefix notation. Calls prefix method for left
     * and right. Calls operator's getArithmeticOp method to get the operator.
     * 
     * i.e. operator left right
     */
    public void prefix() {
        System.out.print(operator.getArithmeticOp() + " ");
        left.prefix();
        right.prefix();
        System.out.println();
    }

    /**
     * Method computes the variable of the expression
     * 
     * @param variables
     * @return
     */
    public int compute(ArrayList<variable> variables) {
        int l = left.compute(variables); // get value of left side
        int r = right.compute(variables); // get value of right side
        if (l == -999) { // if left side is an id
            l = getValue(variables, left); // get the value from the variables array list
        }
        if (r == -999) { // if right side is an id
            r = getValue(variables, right); // get the value from the variables array list
        }
        if (operator.getArithmeticOp().equals("*")) {
            return (int) l * r;
        } else if (operator.getArithmeticOp().equals("/")) {
            return (int) l / r;
        } else if (operator.getArithmeticOp().equals("+")) {
            return (int) l + r;
        } else if (operator.getArithmeticOp().equals("-")) {
            return (int) l - r;
        } else if (operator.getArithmeticOp().equals("%")) {
            return (int) l % r;
        } else if (operator.getArithmeticOp().equals("\\")) {
            return (int) r / l;
        } else {
            return -999;
        }
    }

    /**
     * If the arithemtic expression is an id, the method finds that id in the
     * variable array list. The value of that id is then returned. If it is not
     * found, an error is thrown.
     * 
     * @param variables
     * @param e
     * @return
     */
    public int getValue(ArrayList<variable> variables, arithmetic_ex e) {
        String id = e.getId(); // get id of arithmetic expression
        try {
            for (int i = 0; i < variables.size(); i++) {
                if ((variables.get(i).getId()).trim().equals(id)) // if variable id matches expression id
                    return variables.get(i).getValue(); // return the value from the variable array list
            }
            throw new JuliaSyntaxException("compute"); // hasn't returned a value yet, so throw an error
        } catch (JuliaSyntaxException jse) {

        }
        return -1;
    }
}