public class boolean_ex {
    relative_op operator;
    arithmetic_ex left;
    arithmetic_ex right;

    public boolean_ex(String expression) {
        if (expression.contains("<="))
            splitExpression(expression, "<=");
        else if (expression.contains(">="))
            splitExpression(expression, ">=");
        else if (expression.contains(">"))
            splitExpression(expression, ">");
        else if (expression.contains("<"))
            splitExpression(expression, "<");
        else if (expression.contains("=="))
            splitExpression(expression, "==");
        else if (expression.contains("!="))
            splitExpression(expression, "!=");
        else
            System.out.println("No operator found. Invalid expression");
    }

    public void splitExpression(String expression, String op) {
        operator = new relative_op(op);
        int index = expression.indexOf(op);
        if (index != 0) { // format x < 5
            left = new arithmetic_ex((expression.substring(0, index)).trim());
            if (op.length() == 2)
                index++;
            right = new arithmetic_ex((expression.substring(index + 1)).trim());
        } else { // format < x 5
            String[] expressSplit = expression.split(" ");
            left = new arithmetic_ex(expressSplit[1]);
            right = new arithmetic_ex(expressSplit[2]);
        }
    }

    public void toGrammar() {
        System.out.print("<boolean_expression> -> <arithmetic_expression> <relative_op> <arithmetic_expression>");
        left.toGrammar();
        operator.toGrammar();
        right.toGrammar();
    }
}
