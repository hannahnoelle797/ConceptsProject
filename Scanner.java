import java.util.Arrays;

public class Scanner {

    // private String[] reserved_words = { "do", "else", "elseif", "end", "false",
    // "for", "function", "if", "print", "return","true","while"};
    // private String[] braces = { "(", ")", "{", "}", "[", "]" };
    // private String[] operators = { "+", "-", "*", "/", "=" };
    // private String[] comparison = { "<", ">", "<=", ">=", "==", "!=" };
    private String[][] tokens = {
            { "do", "else", "elseif", "end", "false", "for", "function", "if", "print", "return", "true", "while" },
            { "(", ")", "{", "}", "[", "]" }, { "+", "-", "*", "/", "=" }, { "<", ">", "<=", ">=", "==", "!=" } };

    public Scanner() {
    }

    public String[][] if_exists(String lex) {
        // Returns string array containing token type and index
        String[][] lexeme = { { "type" }, { "array 1 index", "array 2 index" } };

        for (int i = 0; i < tokens.length; i++) {
            for (int j = 0; j < tokens[i].length; j++) {
                if (Arrays.asList(tokens[i]).contains(lex)) {
                    switch (i) {
                    case 0:
                        lexeme[0][0] = "reserved word";
                        break;
                    case 1:
                        lexeme[0][0] = "bracket";
                        break;
                    case 2:
                        lexeme[0][0] = "arithmetic operator";
                        break;
                    case 3:
                        lexeme[0][0] = "relative operator";
                        break;
                    default:
                        lexeme[0][0] = "user variable";
                        break;
                    }
                    lexeme[1][0] = Integer.toString(i);
                    lexeme[1][1] = Integer.toString(j);
                }
            }
        }

        return lexeme;
    }
}