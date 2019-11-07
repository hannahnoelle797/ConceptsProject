import java.util.InputMismatchException;

public class arithmetic_ex {
    String id;
    int literal_integer;
    binary_ex binary;
    int type; // 0 for id, 1 for int, 2 for binary expression

    public arithmetic_ex(String expression) {
        if (expression.length() == 1) {
            try {
                literal_integer = Integer.parseInt(expression);
                type = 1;
            } catch (InputMismatchException e) {
                id = expression;
                type = 0;
            }
        } else {
            binary = new binary_ex(expression);
            type = 2;
        }
    }

    public int getType() {
        return type;
    }

    public String getId() {
        if (type == 0)
            return id;
        else
            return null;
    }

    public int getInt() {
        if (type == 1)
            return literal_integer;
        else
            return -1;
    }

    public String getBinaryEx() {
        if (type == 2)
            return binary.toString();
        else
            return null;
    }

    public void toGrammar() {
        System.out.print("<arithmetic_expression> -> ");
        switch (type) {
        case 0:
            System.out.println("<id>");
            break;
        case 1:
            System.out.println("<literal_integer>");
            break;
        case 2:
            System.out.println("<binary_expression>");
            binary.toGrammar();
            break;
        default:
            System.out.println("Failed to get grammar");
        }

    }
}