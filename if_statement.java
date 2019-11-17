//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

import java.util.ArrayList;

/**
 * The if statement class contains the three non-terminal pieces of an if
 * statement according to the Julia grammar provided: the boolean expression,
 * the if block, and the else block. The boolean variable nested is used for
 * determining if the if statement contains another loop or conditional
 * statement within the body of the if block or else block. The integer variable
 * nestedCount is used to count how many nested statements are within the
 * encapsulating if statement.
 */
public class if_statement {
    private boolean_ex bool;
    private block ifBody;
    private block elseBody;

    private boolean nested;
    private int nestedCount = 0;

    /**
     * Constructor Reads in the entire body of the if statement, including the
     * header of the if statement. The first element in the expressions parameter is
     * the if statement header. A substring is taken from if which removes the word
     * if from the beginning. It isn't needed anymore, so there's no need to store
     * it. The substring is passed to the boolean expression constructor. The rest
     * of the expressions array is the body of the if statement. Each line is
     * checked to determine if it contains a nested loop or conditional statement.
     * If it does, the boolean nested is set to true and nested count is
     * incremented. If the loop encounters a line that contains end but the boolean
     * nested is set to true, that means the line is not the end of the if
     * statement. The line is added to the ifBody array, nested count is decremented
     * and parsing continues. The boolean nested is reset to false if nested count
     * is zero. The same is repeated for the elseBody.
     * 
     * @param expression
     */
    public if_statement(ArrayList<String> expression) {
        String ifBool = expression.get(0).substring(2).trim();
        ArrayList<String> ifBody = new ArrayList<>();
        ArrayList<String> elseBody = new ArrayList<>();
        int i = 1;
        do {
            if (!expression.get(i).contains("end")) { // if line is not end
                isNested(expression.get(i)); // returns true if line contains "if", "for", or "while"
                ifBody.add(expression.get(i)); // line added to if body array
            } else { // line is end
                if (nested) { // if nested
                    ifBody.add(expression.get(i)); // line is added anyways
                    nestedCount--;
                    if (nestedCount == 0)
                        nested = false; // nested is reset to false if there are no remaining end statements
                }
            }
            i++;
        } while (!expression.get(i).equalsIgnoreCase("else"));
        do {
            if (!expression.get(i).contains("end")) { // if line is not end
                isNested(expression.get(i)); // returns true if line contains "if", "for", or "while"
                if (!expression.get(i).contains("else")) // if line is not else
                    elseBody.add(expression.get(i)); // line added to else body array
            } else { // line is end
                if (nested) { // if nested
                    elseBody.add(expression.get(i)); // line is added anyways
                    nestedCount--;
                    if (nestedCount == 0)
                        nested = false; // nested is reset to false if there are no remaining end statements
                }
            }
            i++;
            if (i == expression.size()) // making sure the do-while ends when it's suppose to
                break;
        } while (!expression.get(i).equalsIgnoreCase("end"));
        bool = new boolean_ex(ifBool);
        this.ifBody = new block(ifBody);
        this.elseBody = new block(elseBody);
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
     * Full Grammar: <if_statement> → if <boolean_expression> <block> else <block>
     * end
     */
    public void toGrammar() {
        System.out.println("<if_statement> -> if <boolean_expression> <block> else <block> end");
        bool.toGrammar();
        ifBody.toGrammar();
        elseBody.toGrammar();
    }

}