//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The arithmetic operator class stores the operator used in a binary expression
 * instance. The operators array stores the possible operators and is used to
 * return the operator currently in use in the getOperator method. The opTokens
 * array is used for printing the grammar. The indexOfCurrOp stores the index
 * value of the operator in use for the arithmetic_op instance to be used when
 * returning the operator or printing the grammar.
 */
public class arithmetic_op {
    ArrayList<String> operators = new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "%", "^", "\\"));
    String[] opTokens = { "add_operator", "sub_operator", "mul_operator", "div_operator", "mod_operator",
            "exp_operator", "rev_div_operator" };
    int indexOfCurrOp = 0;

    /**
     * Constructor Receives a string containing the operator found in the binary
     * expression instance it's being called from. Checks to see if op is within the
     * array list operators. If found, index of that operator is used to set
     * indexOfCurrOp. If not found, throws an error.
     * 
     * @param op
     */
    public arithmetic_op(String op) {
        try {
            if (operators.contains(op)) {
                indexOfCurrOp = operators.indexOf(op);
            } else {
                throw new JuliaSyntaxException("arith");
            }
        } catch (JuliaSyntaxException e) {

        }
    }

    /**
     * Method returns the operator as a String that's being used in the binary
     * expression instance.
     * 
     * @return String
     */
    public String getArithmeticOp() {
        return operators.get(indexOfCurrOp);
    }

    /**
     * toGrammar prints out the grammar of this class. Each class represents the LHS
     * abstraction of the Julia grammar.
     * 
     * Full Grammar: <arithmetic_op> → add_operator | sub_operator | mul_operator |
     * div_operator | mod_operator | exp_operator | rev_div_operator
     */
    public void toGrammar() {
        System.out.println("<arithmetic_op> -> " + opTokens[indexOfCurrOp]);

    }
}