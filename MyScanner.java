import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MyScanner {

    // private String[] reserved_words = { "do", "else", "elseif", "end", "false",
    // "for", "function", "if", "print", "return","true","while"};
    // private String[] braces = { "(", ")", "{", "}", "[", "]" };
    // private String[] operators = { "+", "-", "*", "/", "=" };
    // private String[] comparison = { "<", ">", "<=", ">=", "==", "!=" };

    private String input_file, output_file;
    private FileWriter fw;
    private int lineNum = 0;
    private String[] l;
    private String[] newInt;

    private ArrayList<String> lexemes = new ArrayList<>(Arrays.asList("do", "else", "elseif", "end", "false", "for",
            "function", "if", "print", "return", "true", "while", "(", ")", "{", "}", "[", "]", "\\", "+", "-", "*",
            "/", "%", "=", "<", ">", "<=", ">=", "==", "!=", "^", ":"));
    private ArrayList<String> tokens = new ArrayList<>(
            Arrays.asList("do_statement", "else_statement", "else_if_statement", "end_statement", "boolean_false",
                    "for_statement", "function_statment", "if_statement", "print_statement", "return_statement",
                    "boolean_true", "while_statement", "parenthesis_open", "parenthesis_closed", "curly_open",
                    "curly_closed", "square_open", "square_closed", "back_slash", "add_operator", "sub_operator",
                    "mul_operator", "div_operator", "mod_operator", "eq_operator", "lt_operator", "gt_operator",
                    "le_operator", "ge_operator", "eqal_operator", "ne_operator", "exp_operator", "iter_operator"));

    String[] lexeme = { "DNE", "DNE" };

    public MyScanner(String input, String output) {
        input_file = input;
        output_file = output;
    }

    public void Scan() {
        try {
            File file = new File(input_file);
            Scanner fileRead = new Scanner(file);
            fw = new FileWriter(output_file);

            lineNum = 0;

            while (fileRead.hasNextLine()) {
                lineNum++;
                String line = fileRead.nextLine();
                String[] parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].length() > 0) {
                        if (if_exists(parts[i])) {
                            String[] currLex = new String[2];
                            currLex = returnLexeme();
                            addData(currLex);
                        } else {
                            if (isInt(parts[i])) {
                                String[] data = new String[2];
                                data = setUserVariable(parts[i], "Integer");
                                addData(data);
                            } else {
                                if (parts[i].length() == 1) {
                                    String[] data = new String[2];
                                    data = setUserVariable(parts[i], "Variable");
                                    addData(data);
                                } else {
                                    String part = parts[i];
                                    int start = 0;
                                    char first = part.charAt(0);
                                    String sub = Character.toString(first);
                                    for (int j = 0; j < part.length(); j++) {
                                        sub = part.substring(start, (j + 1));
                                        if (if_exists(sub)) {
                                            String[] data = new String[2];
                                            data = returnLexeme();
                                            addData(data);
                                            start = j + 1;
                                        } else {
                                            String last = Character.toString(sub.charAt(sub.length() - 1));
                                            if (if_exists(last)) {
                                                String newSub = sub.substring(0, sub.length() - 1);
                                                if (isInt(newSub)) {
                                                    newInt = new String[2];
                                                    newInt = setUserVariable(newSub, "Integer");
                                                    addData(newInt);
                                                } else {
                                                    l = new String[2];
                                                    l = setUserVariable(newSub, "Variable");
                                                    addData(l);
                                                }
                                                l = new String[2];
                                                l = returnLexeme();
                                                addData(l);
                                                start = j + 1;
                                            }
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

    public boolean if_exists(String lex) {
        // Returns string array containing token type and index
        resetLexeme();

        if (lexemes.contains(lex)) {

            int index = lexemes.indexOf(lex);
            lexeme[0] = lexemes.get(index);
            lexeme[1] = tokens.get(index);
            return true;
        }
        return false;

    }

    public String[] returnLexeme() {
        return lexeme;
    }

    private void resetLexeme() {
        lexeme[0] = "DNE";
        lexeme[1] = "DNE";
    }

    public String[] setUserVariable(String lex, String tok) {
        lexemes.add(lex);
        tokens.add(tok);
        String[] lexeme = { lex, tok };
        return lexeme;
    }

    public boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void addData(String[] data) {
        try {
            String lex = data[0];
            String type = data[1];
            String out = "Line Number: " + lineNum + "\tLexeme: " + lex + "\tToken: " + type + "\n";
            System.out.print(out);
            fw.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
