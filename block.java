//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

import java.util.ArrayList;

/**
 * The block class stores an array of all statement within a program, for loop,
 * while look, or if statement. It also contains the starting and ending
 * instance for each piece of the block. If there are no for loops, while loops,
 * or if statement, the blockStartsEnd array will remain empty. This array is
 * required for files with nested loops/conditional statement.
 */
public class block {
    private ArrayList<statement> blockStatements = new ArrayList<>();
    private ArrayList<int[]> blockStartsEnds = new ArrayList<>();

    /**
     * Constructor This block constructor takes in an array of expression. These
     * expressions are then passed to the findNextStatement method.
     * 
     * @param expressions
     */
    public block(ArrayList<String> expressions) {
        findNextStatement(expressions);
    }

    /**
     * The findNextStatement method goes through every line in the array 'lines'.
     * The method first finds the starting and ending indexs (line numbers) of all
     * if statements, for loops, and while loops. Each line is then checks to see if
     * it contains the Strings "for", "while", "if", "print" or "=". An occurance of
     * "=" is checked last as for loops also have "=" in the header. Depending on
     * which String occurance is found, depends on which constructor that line or
     * group of lines is passed to. If line contains "if", "for" or "while" the
     * blockStartsEnds values are used to obtain the block and to update the counter
     * 'i'.
     * 
     * @param lines
     */
    public void findNextStatement(ArrayList<String> lines) {
        statement s = new statement();
        int size = lines.size(), i = 0;
        for (int k = 0; k < lines.size(); k++) {
            if (checkForCondition(lines.get(k))) { // line contains "if", "for", or "while"
                int[] arr = { k, -1 }; // -1 is a placeholder for ending line number of loop/conditional statement
                blockStartsEnds.add(arr);
            }
            if (lines.get(k).contains("end")) { // line contains "end"
                int w = blockStartsEnds.size() - 1;
                do { // starting at the last element
                    if (blockStartsEnds.get(w)[1] == -1) { // if the second value (end index) is -1
                        blockStartsEnds.get(w)[1] = k; // update the end index to be k (for loop counter) and break
                        break;
                    }
                    w--; // otherwise, decrease w and check the previous element of the array
                } while (w >= 0);
            }
        }

        // Making sure that every if, for, or while has an end
        try {
            for (int l = 0; l < blockStartsEnds.size(); l++) {
                if (blockStartsEnds.get(l)[1] == -1)
                    throw new JuliaSyntaxException(lines.get(blockStartsEnds.get(l)[0]) + " end");
            }
        } catch (JuliaSyntaxException e) {

        }

        while (i < size) { // checking each line
            if (lines.get(i).length() > 0) {
                if (lines.get(i).contains("if")) {
                    ArrayList<String> ifBody = getSubList(lines, i);
                    i = getEnd(i); // i is updated to be the line following the end of the block
                    s = new statement(0, ifBody);
                    blockStatements.add(s);
                } else if (lines.get(i).contains("for")) {
                    ArrayList<String> forBody = getSubList(lines, i);
                    i = getEnd(i);
                    s = new statement(4, forBody);
                    blockStatements.add(s);
                } else if (lines.get(i).contains("while")) {
                    ArrayList<String> whileBody = getSubList(lines, i);
                    i = getEnd(i);
                    s = new statement(2, whileBody);
                    blockStatements.add(s);
                } else if (lines.get(i).contains("print")) {
                    i++;
                    s = new statement(3, lines.get(i - 1));
                    blockStatements.add(s);
                } else if (lines.get(i).contains("=")) {
                    i++;
                    s = new statement(1, lines.get(i - 1));
                    blockStatements.add(s);
                } else if (lines.get(i).contains("end")) {
                    i++;
                }
            }
        }
    }

    /**
     * Returns a sublist of arr from start to the value returned from getEnd.
     * Created our own because the method sublist only works on Lists, not
     * ArrayLists. Making our own eliminates the need to convert the ArrayList to a
     * List and back.
     * 
     * @param arr
     * @param start
     * @return ArrayList<String> sublist
     */
    public ArrayList<String> getSubList(ArrayList<String> arr, int start) {
        ArrayList<String> sublist = new ArrayList<>();
        int end = getEnd(start);
        for (int i = start; i < end; i++) {
            sublist.add(arr.get(i));
        }
        return sublist;
    }

    /**
     * getEnd method checks every element of the blockStartsEnds array list. If the
     * first element of the integer array within the blockStartsEnds array list then
     * the second element is returned.
     * 
     * @param start
     * @return
     */
    public int getEnd(int start) {
        int end = 0;
        for (int[] k : blockStartsEnds) {
            if (k[0] == start)
                end = k[1] + 1;
        }
        return end;
    }

    /**
     * Checks to see if the line contains "if", "for", or "while". If it does, true
     * is returned. Otherwise, false is returned.
     * 
     * @param s
     * @return boolean
     */
    public boolean checkForCondition(String s) {
        if (s.contains("if") || s.contains("for") || s.contains("while"))
            return true;
        return false;
    }

    /**
     * toGrammar prints out the grammar of this class. Each class represents the LHS
     * abstraction of the Julia grammar. It then calls the toGrammar for each
     * non-terminal piece of the grammar.
     * 
     * Full Grammar: <block> → <statement> | <statement> <block>
     */
    public void toGrammar() {
        System.out.println("<block> -> <statement>");
        for (statement s : blockStatements) {
            s.toGrammar();
        }
    }

    /**
     * Prints the contents of every statement in the blockStatements array list.
     * Uses for loop to call printContents method on every statement.
     */
    public void printContents() {
        for (statement s : blockStatements) {
            s.printContents();
        }

    }
}