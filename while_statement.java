//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

import java.util.ArrayList;

/**
 * The while statement class contains the boolean expression condition for the
 * while loop and the body of the while loop. The boolean variable nested is
 * used for determining if the while loop contains another loop or conditional
 * statement within the body of the while loop. The integer variable nestedCount
 * is used to count how many nested statements are within the encapsulating
 * while loop.
 */
public class while_statement {
    boolean_ex boolEx;
    block body;

    private boolean nested;
    private int nestedCount = 0;

    /**
     * Constructor Reads in the entire body of the while loop, including the header
     * of the while loop. The first element in the expressions parameter is the
     * while loop header. A substring is taken from if which removes the word while
     * from the beginning. It isn't needed anymore, so there's no need to store it.
     * That substring is passed to the boolean_ex class. The rest of the expressions
     * array is the body of the while loop. Each line is checked to determine if it
     * contains a nested loop or conditional statement. If it does, the boolean
     * nested is set to true and the nested count is incremeneted. If the loop
     * encounters a line that contains end but the boolean nested is set to true,
     * that means the line is not the end of the for loop. The line is added to the
     * whileBody array, the nested count is decremented and parsing continues. The
     * boolean nested is reset to false if nested count is zero.
     * 
     * @param expression
     */
    public while_statement(ArrayList<String> expression) {
        String boolExpression = expression.get(0).substring(5).trim();
        boolEx = new boolean_ex(boolExpression);
        ArrayList<String> whileBody = new ArrayList<>();
        for (int i = 1; i < expression.size(); i++) {
            if (!expression.get(i).contains("end")) {
                isNested(expression.get(i));
                whileBody.add(expression.get(i));
            } else {
                if (nested) {
                    whileBody.add(expression.get(i));
                    nestedCount--;
                    if (nestedCount == 0)
                        nested = false; // nested is reset to false if there are no remaining end statements
                }
            }
        }
        body = new block(whileBody);
    }

    /**
     * Checks to see if the lines contains "if", "while", or "for". If it does,
     * there is a nested loop/conditional. If there is, nested set to true.
     * 
     * @param line
     */
    public void isNested(String line) {
        if (line.contains("if") || line.contains("while") || line.contains("for")) {
            nested = true;
            nestedCount++; // number of nested statements within encapsulating if statement
        }
    }

    /**
     * toGrammar prints out the grammar of this class. Each class represents the LHS
     * abstraction of the Julia grammar. It then calls the toGrammar for each
     * non-terminal piece of the grammar.
     * 
     * Full Grammar: <while_statement> → while <boolean_expression> <block> end
     */
    public void toGrammar() {
        System.out.println("<while_statement> -> while <boolean_expression> <block> end");
        boolEx.toGrammar();
        body.toGrammar();
        body.printContents();
    }

    public void printContents() {

    }
}