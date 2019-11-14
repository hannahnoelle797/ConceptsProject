//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

/**
 * The boolean expression class stores the expression found in the if statement
 * or while statement condition. It stores a realtive operator object and two
 * arithmetic expression objects, the left and right side of the boolean
 * expression.
 */
public class boolean_ex {
    relative_op operator;
    arithmetic_ex left;
    arithmetic_ex right;

    /**
     * Constructor Checks to see which boolean operator is in the expression. This
     * opereator is used to split the expression. If a valid boolean operator is not
     * found then an error is thrown.
     * 
     * @param expression
     */
    public boolean_ex(String expression) {
        try {
            if (expression.contains("<="))
                splitExpression(expression, "<=");
            else if (expression.contains(">="))
                splitExpression(expression, ">=");
            else if (expression.contains(">"))
                splitExpression(expression, ">");
            else if (expression.contains("<"))
                splitExpression(expression, "<");
            else if (expression.contains("=="))
                splitExpression(expression, "==");
            else if (expression.contains("!="))
                splitExpression(expression, "!=");
            else
                throw new JuliaSyntaxException("relative");
        } catch (JuliaSyntaxException e) {

        }
    }

    /**
     * The splitExpression method takes in the full expression String and the
     * operator used in the expression. The index of the operator is found and that
     * index is used to split the expression into left and right sides which are
     * passed to the arithemtic expression class and stored in their respective
     * arithmetic_ex objects.
     * 
     * @param expression
     * @param op
     */
    public void splitExpression(String expression, String op) {
        operator = new relative_op(op);
        int index = expression.indexOf(op);
        left = new arithmetic_ex((expression.substring(0, index)).trim());
        if (op.length() == 2) // if the operator is <=, >=, ==, or != the index needs to be increased by one
                              // to adjust for this
            index++;
        right = new arithmetic_ex((expression.substring(index + 1)).trim());
    }

    /**
     * toGrammar prints out the grammar of this class. Each class represents the LHS
     * abstraction of the Julia grammar. It then calls the toGrammar for each
     * non-terminal piece of the grammar.
     * 
     * Full Grammar: <boolean_expression> -> <arithmetic_expression> <relative_op>
     * <arithmetic_expression>
     */
    public void toGrammar() {
        System.out.println("<boolean_expression> -> <arithmetic_expression> <relative_op> <arithmetic_expression>");
        left.toGrammar();
        left.printContents();
        operator.toGrammar();
        right.toGrammar();
        right.printContents();
        prefix();
    }

    /**
     * Method prints the boolean expression in prefix notation. Calls operator's
     * getRelativeOp method to get the operator.
     * 
     * i.e. operator left right
     */
    public void prefix() 
    {
        System.out.print(operator.getRelativeOp() + " ");
        left.prefix();
        right.prefix();
        

    }
}
