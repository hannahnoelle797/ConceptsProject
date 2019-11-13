//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

import java.util.ArrayList;

/**
 * The for statement class contains the entire contents of the for loop. It
 * stores the id used for the counter, an iterator object, and a block object
 * which contains the body of the for loop. The boolean variable 'nested' is
 * used to determine if the body of the for loop contains another loop or
 * conditional statement. The integer variable nestedCount is used to count how
 * many nested statements are within the encapsulating for loop.
 */
public class for_statement {
    private String id;
    private iter iterator;
    private block statement;

    private boolean nested = false;
    private int nestedCount = 0;

    /**
     * Constructor Reads in the entire body of the for loop, including the header of
     * the for loop. The first element in the expressions parameter is the for loop
     * header. A substring is taken from if which removes the word for from the
     * beginning. It isn't needed anymore, so there's no need to store it. The
     * remainder of the header is split on the '=' sign. The first element is the id
     * of the counter variable used in the loop. The second element is the iterator
     * which is passed to the iter class. The rest of the expressions array is the
     * body of the for loop. Each line is checked to determine if it contains a
     * nested loop or conditional statement. If it does, the boolean nested is set
     * to true and nested count is incremented. If the loop encounters a line that
     * contains end but the boolean nested is set to true, that means the line is
     * not the end of the for loop. The line is added to the forBody array, nested
     * count is decremented and parsing continues. The boolean nested is reset to
     * false if nested count is zero.
     * 
     * @param expressions
     */
    public for_statement(ArrayList<String> expressions) {
        String it = expressions.get(0).substring(3).trim();
        String[] condition = it.split("=");
        this.id = condition[0].trim();
        iterator = new iter(condition[1]);
        ArrayList<String> forBody = new ArrayList<>();
        for (int i = 1; i < expressions.size(); i++) {
            if (!expressions.get(i).contains("end")) {
                isNested(expressions.get(i));
                forBody.add(expressions.get(i));
            } else {
                if (nested) {
                    forBody.add(expressions.get(i));
                    nestedCount--;
                    if (nestedCount == 0)
                        nested = false; // nested is reset to false if there are no remaining end statements
                }
            }
        }
        statement = new block(forBody);
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
     * Returns the id the for loop uses.
     * 
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * toGrammar prints out the grammar of this class. Each class represents the LHS
     * abstraction of the Julia grammar. It then calls the toGrammar for each
     * non-terminal piece of the grammar.
     * 
     * Full Grammar: <for_statement> -> for id = <iter> <block> end
     */
    public void toGrammar() {
        System.out.println("<for_statement> -> for id = <iter> <block> end");
        printContents();
        iterator.toGrammar();
        iterator.printContents();
        statement.toGrammar();
        statement.printContents();
    }

    /**
     * The method prints out the id that is being used to store the counter for the
     * for loop.
     */
    public void printContents() {

    }
}