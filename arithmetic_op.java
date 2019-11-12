//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

/**
 * The arithmetic operator class stores the operator used in a binary expression
 * instance. The operators array stores the possible operators and is used to
 * return the operator currently in use in the getOperator method. The opTokens
 * array is used for printing the grammar. The indexOfCurrOp stores the index
 * value of the operator in use for the arithmetic_op instance to be used when
 * returning the operator or printing the grammar.
 */
public class arithmetic_op {
    String[] operators = { "+", "-", "*", "/", "%", "^", "\\" };
    String[] opTokens = { "add_operator", "sub_operator", "mul_operator", "div_operator", "mod_operator",
            "exp_operator", "rev_div_operator" };
    int indexOfCurrOp = 0;

    /**
     * Constructor Receives a string containing the operator found in the binary
     * expression instance it's being called from. Uses a switch statement to
     * determine the operator and to set the indexOfCurrOp. If not found, throws an
     * error.
     * 
     * @param op
     */
    public arithmetic_op(String op) {
        switch (op) {
        case "+":
            indexOfCurrOp = 0;
            break;
        case "-":
            indexOfCurrOp = 1;
            break;
        case "*":
            indexOfCurrOp = 2;
            break;
        case "/":
            indexOfCurrOp = 3;
            break;
        case "%":
            indexOfCurrOp = 4;
            break;
        case "^":
            indexOfCurrOp = 5;
            break;
        case "\\":
            indexOfCurrOp = 6;
            break;
        default:
            indexOfCurrOp = -1;
            // TODO: Throw an error here
            break;
        }
    }

    /**
     * Method returns the operator as a String that's being used in the binary
     * expression instance.
     * 
     * @return String
     */
    public String getArithmeticOp() {
        return operators[indexOfCurrOp];
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