public class relative_op {
    String[] operators = { "<=", "<", ">=", ">", "==", "!=" };
    String[] opTokens = { "le_operator", "lt_operator", "ge_operator", "gt_operator", "eq_operator", "ne_operator" };
    int indexOfCurrOp = 0;

    public relative_op(String op) {
        switch (op) {
        case "<=":
            indexOfCurrOp = 0;
            break;
        case "<":
            indexOfCurrOp = 1;
            break;
        case ">=":
            indexOfCurrOp = 2;
            break;
        case ">":
            indexOfCurrOp = 3;
            break;
        case "==":
            indexOfCurrOp = 4;
            break;
        case "!=":
            indexOfCurrOp = 5;
            break;
        default:
            indexOfCurrOp = -1;
            break;
        }
    }

    public String getRelativeOp() {
        return operators[indexOfCurrOp];
    }

    public void toGrammar() {
        System.out.println("<relative_op> -> " + opTokens[indexOfCurrOp]);
    }
}