//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The relative operator class contains the operator used in boolean
 * expressions. The operators array stores the possible operators and is used to
 * return the operator currently in use in the getRelativeOp method. The
 * opTokens array is used for printing the grammar. The indexOfCurrOp stores the
 * index value of the operator in use for the relative_op instance to be used
 * when returning the operator or printing the grammar.
 */
public class relative_op {
    ArrayList<String> operators = new ArrayList<>(Arrays.asList("<=", "<", ">=", ">", "==", "!="));
    String[] opTokens = { "le_operator", "lt_operator", "ge_operator", "gt_operator", "eq_operator", "ne_operator" };
    int indexOfCurrOp = 0;

    /**
     * Constructor. Receives a string containing the operator found in the boolean
     * expression instance it's being called from. Checks to see if op is within the
     * array list operators. If found, index of that operator is used to set
     * indexOfCurrOp. If not found, throws an error.
     * 
     * @param op
     */
    public relative_op(String op) {
        try {
            if (operators.contains(op))
                indexOfCurrOp = operators.indexOf(op);
            else {
                throw new JuliaSyntaxException("relative");
            }
        } catch (JuliaSyntaxException e) {

        }
    }

    /**
     * Returns the current operator in use
     * 
     * @return
     */
    public String getRelativeOp() {
        return operators.get(indexOfCurrOp);
    }

    /**
     * toGrammar prints out the grammar of this class. Each class represents the LHS
     * abstraction of the Julia grammar.
     * 
     * Full Grammar: <relative_op> → le_operator | lt_operator | ge_operator |
     * gt_operator | eq_operator | ne_operator
     */
    public void toGrammar() {
        System.out.println("<relative_op> -> " + opTokens[indexOfCurrOp]);
    }
}