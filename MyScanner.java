import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    private ArrayList<String> lexemes = new ArrayList<>(
            Arrays.asList("else", "end", "false", "for", "function", "if", "print", "true", "while", "(", ")", "+", "-",
                    "*", "/", "%", "=", "<", ">", "<=", ">=", "==", "!=", "^", ":"));
    private ArrayList<String> tokens = new ArrayList<>(
            Arrays.asList("<else>", "<end>", "<false>", "<for>", "<function>", "<if>", "<print>", "<true>",
                    "while_statement", "<(>", "<)>", "<add_operator>", "<sub_operator>", "<mul_operator>",
                    "<div_operator>", "<mod_operator>", "<eq_operator>", "<lt_operator>", "<gt_operator>",
                    "<le_operator>", "<ge_operator>", "<eqal_operator>", "<ne_operator>", "<exp_operator>", "<colon>"));

    private ArrayList<String> identifier = new ArrayList<>(Arrays.asList("5000", "5001", "5002", "5003", "5004", "5005",
            "5006", "5007", "5008", "5009", "5010", "5011", "5012", "5013", "5014", "5015", "5016", "5017", "5018",
            "5019", "5020", "5021", "5022", "5023", "5024", "5025"));

    private String[] lexeme = { "DNE", "DNE", "DNE" };
    private int intCount = 6000;
    private int idCount = 8000;

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
                                data = setUserVariable(parts[i], "<integer_literal>", (intCount + toInt(parts[i])));
                                addData(data);
                            } else {
                                if (parts[i].length() == 1) {
                                    String[] data = new String[2];
                                    data = setUserVariable(parts[i], "<id>", (idCount + stringToInt(parts[i])));
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
                                                    newInt = setUserVariable(newSub, "<integer_literal>",
                                                            (intCount + toInt(newSub)));
                                                    addData(newInt);
                                                } else {
                                                    l = new String[2];
                                                    l = setUserVariable(newSub, "<id>",
                                                            (idCount + stringToInt(newSub)));
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
            lexeme[2] = identifier.get(index);
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
        lexeme[2] = "DNE";
    }

    private int stringToInt(String s) {
        Character c = s.charAt(0);
        int i = (int) c;
        return i;
    }

    private int toInt(String s) {
        return Integer.parseInt(s);
    }

    public String[] setUserVariable(String lex, String tok, int id) {

        String[] lexeme = { lex, tok, Integer.toString(id) };
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
            String id = data[2];
            String out = "Line Number: " + lineNum + " Lexeme: " + lex + " Identifier: " + id + " Token: " + type
                    + "\n";
            System.out.print(out);
            fw.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
