import java.util.ArrayList;

//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

/**
 * The assignment statement class contains two parts: the id where the data is
 * being stored and the arithmetic expression on the right hand side of the
 * equals sign.
 */
public class assignment_statement {
    private String id;
    private arithmetic_ex expression;

    /**
     * Constructor Takes in a String and splits it by the equals sign. The first
     * element of the array is the id. The second element is the arithmetic
     * expressioin. This value is passed to the arithmetic expression constructor.
     * 
     * @param expression
     */
    public assignment_statement(String expression) {
        String[] stringSplit = expression.split("=");
        id = stringSplit[0];
        this.expression = new arithmetic_ex(stringSplit[1]);
    }

    /**
     * getId returns the String value stored in the String variable id
     * 
     * @return String id
     */
    public String getId() {
        return id;
    }

    public arithmetic_ex getExpression() {
        return expression;
    }

    /**
     * toGrammar prints out the grammar of this class. Each class represents the LHS
     * abstraction of the Julia grammar. It then calls the toGrammar for each
     * non-terminal piece of the grammar. <assignment_operator> does not have a
     * toGrammar as <assignment_operator> is always '='.
     * 
     * Full Grammar: <assignment_statement> -> id <assignment_operator>
     * <arithmetic_expression>
     */
    public void toGrammar() {
        System.out.println("<assignment_statement> -> id <assignment_operator> <arithmetic_expression>");
        printContents();
        expression.toGrammar();
        prefix();
    }

    /**
     * The method prints out the id that is being used to store data and the
     * contents of the arithmetic epxression.
     */
    public void printContents() {
        System.out.println("id = " + id);
    }

    /**
     * Method prints the assignment statement in prefix format
     * 
     * i.e. = id arithemetic_ex
     */
    public void prefix() {
        System.out.print("= " + id + " ");
        expression.prefix();
        System.out.println();
    }

    public int compute(ArrayList<variable> variables) {
        int value = expression.compute(variables);
        if (value == -999) {
            for (int i = 0; i < variables.size(); i++) {
                if (variables.get(i).id.equals(id))
                    return variables.get(i).value;
            }
        }
        return value;
    }
}