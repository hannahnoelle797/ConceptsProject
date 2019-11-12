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
 * conditional statement.
 */
public class for_statement {
    private String id;
    private iter iterator;
    private block statement;
    private boolean nested = false;

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
     * to true. If the loop encounters are line that contains end but the boolean
     * nested is set to true, that means the line is not the end of the for loop.
     * The line is added to the forBody array and parsing continues.
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
                if (expressions.get(i).contains("if") || expressions.get(i).contains("while")
                        || expressions.get(i).contains("for")) {
                    nested = true;
                }
                forBody.add(expressions.get(i));
            } else {
                if (nested) {
                    forBody.add(expressions.get(i));
                    nested = false;
                }
            }
        }
        statement = new block(forBody);
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
     * Full Grammar: for id = <iter> <block> end
     */
    public void toGrammar() {
        System.out.println("<for_statement> -> for id = <iter> <block> end");
        iterator.toGrammar();
        statement.toGrammar();
    }
}