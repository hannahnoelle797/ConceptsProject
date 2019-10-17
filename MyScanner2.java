import java.util.Arrays;
import java.util.ArrayList;

public class MyScanner2 {

    // private String[] reserved_words = { "do", "else", "elseif", "end", "false",
    // "for", "function", "if", "print", "return","true","while"};
    // private String[] braces = { "(", ")", "{", "}", "[", "]" };
    // private String[] operators = { "+", "-", "*", "/", "=" };
    // private String[] comparison = { "<", ">", "<=", ">=", "==", "!=" };
    private ArrayList<String> lexemes = new ArrayList<>(Arrays.asList("do", "else", "elseif", "end", "false", "for",
            "function", "if", "print", "return", "true", "while", "(", ")", "{", "}", "[", "]", "\"", "+", "-", "*",
            "/", "%", "=", "<", ">", "<=", ">=", "==", "!="));
    private ArrayList<String> tokens = new ArrayList<>(Arrays.asList("do_statement", "else_statement",
            "else_if_statement", "end_statement", "boolean_false", "for_statement", "function_statment", "if_statement",
            "print_statement", "return_statement", "boolean_true", "while_statement", "parenthesis_open",
            "parenthesis_closed", "curly_open", "curly_closed", "square_open", "square_closed", "back_slash",
            "add_operator", "sub_operator", "mul_operator", "div_operator", "mod_operator", "eq_operator",
            "lt_operator", "gt_operator", "le_operator", "ge_operator", "eqal_operator", "ne_operator"));

    String[] lexeme = { "DNE", "DNE" };

    public MyScanner2() {
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
}