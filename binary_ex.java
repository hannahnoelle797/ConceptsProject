public class binary_ex {
    arithmetic_op operator;
    arithmetic_ex left;
    arithmetic_ex right;
    String fullExpression;

    public binary_ex(String expression) {
        fullExpression = expression.trim();
        if (expression.contains("*"))
            splitExpression(expression, "*");
        else if (expression.contains("/"))
            splitExpression(expression, "/");
        else if (expression.contains("+"))
            splitExpression(expression, "+");
        else if (expression.contains("-"))
            splitExpression(expression, "-");
        else if (expression.contains("%"))
            splitExpression(expression, "%");
        else if (expression.contains("\\"))
            splitExpression(expression, "\\");
        else
            System.out.println("No operator found. Invalid expression");
    }

    public void splitExpression(String expression, String op) {
        operator = new arithmetic_op(op);
        expression = expression.trim();
        int index = expression.indexOf(op);
        if (index != 0) { // format is 2 + 5
            left = new arithmetic_ex((expression.substring(0, index)).trim());
            if (op.length() == 2)
                index++;
            right = new arithmetic_ex((expression.substring(index + 1)).trim());
        } else { // format is + 2 5

        }
    }

    public String toString() {
        return fullExpression;
    }

    public void toGrammar() {
        System.out.println("<binary_expression> -> <arithmeitc_expression> <arithmetic_op> <arithmetic_expression>");
        left.toGrammar();
        operator.toGrammar();
        right.toGrammar();
    }
}