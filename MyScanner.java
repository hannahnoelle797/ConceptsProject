//Hannah Duncan, Colleen Hynes, Mary Le
//KSU CS 4308 Concepts of Programming Languages
//Deepa Muralidhar
//Fall 2019

import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The MyScanner class scans through the entire file and finds all the different
 * parts of the file.
 */
public class MyScanner {
    private String input_file, output_file;
    private FileWriter fw;
    private int lineNum = 0;
    private String[] l;
    private String[] newInt;

    // reserved words and operators and brackets
    private ArrayList<String> lexemes = new ArrayList<>(
            Arrays.asList("else", "end", "false", "for", "function", "if", "print", "true", "while", "(", ")", "+", "-",
                    "*", "/", "%", "=", "<", ">", "<=", ">=", "==", "!=", "^", ":"));
    // token version of reserved words, placed in angle brackets
    private ArrayList<String> tokens = new ArrayList<>(Arrays.asList("<else>", "<end>", "<false>", "<for>",
            "<function>", "<if>", "<print>", "<true>", "<while", "<(>", "<)>", "<add_operator>", "<sub_operator>",
            "<mul_operator>", "<div_operator>", "<mod_operator>", "<eq_operator>", "<lt_operator>", "<gt_operator>",
            "<le_operator>", "<ge_operator>", "<eqal_operator>", "<ne_operator>", "<exp_operator>", "<colon>"));

    // identifer keys
    private ArrayList<String> identifier = new ArrayList<>(Arrays.asList("5000", "5001", "5002", "5003", "5004", "5005",
            "5006", "5007", "5008", "5009", "5010", "5011", "5012", "5013", "5014", "5015", "5016", "5017", "5018",
            "5019", "5020", "5021", "5022", "5023", "5024", "5025"));

    // used to hold lexeme, token, and identifier to be printed to screen/output
    // file
    private String[] lexeme = { "DNE", "DNE", "DNE" };
    private int intCount = 6000;
    private int idCount = 8000;

    /**
     * Constructor.
     * 
     * @param input  name of input file
     * @param output name of output file
     */
    public MyScanner(String input, String output) {
        input_file = input;
        output_file = output;
    }

    /**
     * Creates a Scanner for the input file and File Writer for the output file.
     * Goes through each line and splits it on the spaces. Each element of the line
     * is checked to see if it is in the lexemes ArrayList. If it is, the lexeme
     * String array is updated and printed to the screen and output file. If not,
     * the element is turned into a char array The array is slowly combined back
     * together until a lexeme is found. If the last character added is a lexeme,
     * then all characters before it are checked to see if it's an integer or id.
     * The lexeme String array is then updated to contain the proper lexeme, token,
     * and identifier, and is passed to the addData method which writes the data to
     * the screen and output file.
     */
    public void Scan() {
        try {
            File file = new File(input_file); // Make File using input file name
            Scanner fileRead = new Scanner(file); // Make Scanner using File
            fw = new FileWriter(output_file); // Make File Writer using output file name

            lineNum = 0;

            while (fileRead.hasNextLine()) {
                lineNum++; // used to print the line number in the output file and on the screen
                String line = fileRead.nextLine();
                String[] parts = line.split(" "); // split the line into an array on spaces
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].length() > 0) {
                        if (if_exists(parts[i])) { // if this elements of the parts array is a lexeme
                            String[] currLex = new String[2]; // make a new string array
                            currLex = returnLexeme(); // get the values of lexeme String array
                            addData(currLex); // add array to file and print to screen
                        } else {
                            if (isInt(parts[i])) { // check if element or parts is an integer before splitting to char
                                                   // array
                                String[] data = new String[2];
                                data = setUserVariable(parts[i], "<integer_literal>", (intCount + toInt(parts[i])));
                                addData(data);
                            } else { // if not an int
                                if (parts[i].length() == 1) { // but is a single character
                                    String[] data = new String[2]; // then it's an id
                                    data = setUserVariable(parts[i], "<id>", (idCount + stringToInt(parts[i])));
                                    addData(data);
                                } else { // otherwise, it's a combo of things not separated by spaces. i.e. print(x)
                                    String part = parts[i];
                                    int start = 0;
                                    char first = part.charAt(0);
                                    String sub = Character.toString(first);
                                    for (int j = 0; j < part.length(); j++) {
                                        sub = part.substring(start, (j + 1)); // gets a substring from start to j+1
                                        if (if_exists(sub)) { // if it is a lexeme
                                            String[] data = new String[2];
                                            data = returnLexeme();
                                            addData(data); // add the data to the file and print to screen
                                            start = j + 1; // update start
                                        } else { // if not a lexeme
                                            String last = Character.toString(sub.charAt(sub.length() - 1)); // get the
                                                                                                            // last
                                                                                                            // character
                                            if (if_exists(last)) { // check if the last character is a lexeme. i.e. ) in
                                                                   // substring x)
                                                String newSub = sub.substring(0, sub.length() - 1); // get substring
                                                                                                    // minus last char
                                                if (isInt(newSub)) { // if it's an integer
                                                    newInt = new String[2];
                                                    newInt = setUserVariable(newSub, "<integer_literal>",
                                                            (intCount + toInt(newSub)));
                                                    addData(newInt); // add it to the output and print ot screen
                                                } else { // if not an integer, it's an id
                                                    l = new String[2];
                                                    l = setUserVariable(newSub, "<id>",
                                                            (idCount + stringToInt(newSub)));
                                                    addData(l); // add it to the output and print ot screen
                                                }
                                                l = new String[2];
                                                l = returnLexeme();
                                                addData(l); // add last element to output and print to screen
                                                start = j + 1;
                                            }
                                            // if the last character is not a lexeme, then there is a syntax error
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            fw.close();
            fileRead.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks to see if lex exists in the lexemes ArrayList. If it does, String
     * array lexeme is updated and return true. Otherwise, returns false;
     * 
     * @param lex
     * @return
     */
    public boolean if_exists(String lex) {
        // Returns string array containing token type and index
        resetLexeme();

        if (lexemes.contains(lex)) {

            int index = lexemes.indexOf(lex);
            lexeme[0] = lexemes.get(index);
            lexeme[1] = tokens.get(index);
            lexeme[2] = identifier.get(index);
            return true;
        }
        return false;

    }

    /**
     * Returns the value of the String array lexeme
     * 
     * @return
     */
    public String[] returnLexeme() {
        return lexeme;
    }

    /**
     * Resets all values of String array lexeme to DNE
     */
    private void resetLexeme() {
        lexeme[0] = "DNE";
        lexeme[1] = "DNE";
        lexeme[2] = "DNE";
    }

    /**
     * Converts a String to an Integer
     * 
     * @param s
     * @return
     */
    private int stringToInt(String s) {
        Character c = s.charAt(0);
        int i = (int) c;
        return i;
    }

    /**
     * Converts a String longer than 1 character to an integer
     * 
     * @param s
     * @return
     */
    private int toInt(String s) {
        return Integer.parseInt(s);
    }

    /**
     * Sets String array lexeme with the values passed in. Returns the String array
     * lexeme
     * 
     * @param lex
     * @param tok
     * @param id
     * @return
     */
    public String[] setUserVariable(String lex, String tok, int id) {

        String[] lexeme = { lex, tok, Integer.toString(id) };
        return lexeme;
    }

    /**
     * Checks to see if the String is an integer.
     * 
     * @param s
     * @return
     */
    public boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Prints the data to the screen and writes it to the file.
     * 
     * @param data
     */
    public void addData(String[] data) {
        try {
            String lex = data[0];
            String type = data[1];
            String id = data[2];
            String out = "Line Number: " + lineNum + " Lexeme: " + lex + " Identifier: " + id + " Token: " + type
                    + "\n";
            // System.out.print(out);
            fw.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
