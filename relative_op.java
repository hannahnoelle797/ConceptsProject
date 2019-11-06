public class relative_op {
    String[] operators = { "<=", "<", ">=", ">", "==", "!=" };
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
}