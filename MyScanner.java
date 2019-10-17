import java.util.Arrays;

public class MyScanner {

    // private String[] reserved_words = { "do", "else", "elseif", "end", "false",
    // "for", "function", "if", "print", "return","true","while"};
    // private String[] braces = { "(", ")", "{", "}", "[", "]" };
    // private String[] operators = { "+", "-", "*", "/", "=" };
    // private String[] comparison = { "<", ">", "<=", ">=", "==", "!=" };
    private String[][] tokens = {
            /* reserved words */ { "do", "else", "elseif", "end", "false", "for", "function", "if", "print", "return",
                    "true", "while" },
            /* braces */ { "(", ")", "{", "}", "[", "]", "\"" }, /* operators */ { "+", "-", "*", "/", "=" },
            /* comparison */ { "<", ">", "<=", ">=", "==", "!=" }, /* user variables */ { " " } };

    public MyScanner() {
    }

    public String[][] if_exists(String lex) {
        // Returns string array containing token type and index
        String[][] lexeme = { { "type" }, { "0", "0" } };

        for (int i = 0; i < tokens.length; i++) {
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
                    lexeme[0][0] = "DNE";
                    break;
                }
                if (!lexeme[0][0].equals("DNE")) {
                    lexeme[1][0] = Integer.toString(i);
                    lexeme[1][1] = Integer.toString(Arrays.asList(tokens[i]).indexOf(lex));
                    return lexeme;
                }
            } else {
                lexeme[0][0] = "DNE";
                lexeme[1][0] = "0";
                lexeme[1][1] = "0";
            }
        }

        return lexeme;
    }

    public String[][] setUserVariable(String var, String type) {
        if (tokens[4][0].equals(" "))
            tokens[4][0] = var;
        else
            tokens[4] = addVariable(tokens[4], var);
        int idx2 = Arrays.asList(tokens[4]).indexOf(var);
        String[][] lexeme = { { type }, { "4", Integer.toString(idx2) } };
        return lexeme;
    }

    public String[] addVariable(String[] arr, String var) {
        String[] newArr = new String[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[newArr.length - 1] = var;
        return newArr;
    }

    public String getLexeme(String[][] lex) {
        int index1 = Integer.parseInt(lex[1][0]);
        int index2 = Integer.parseInt(lex[1][1]);
        return tokens[index1][index2];
    }
}