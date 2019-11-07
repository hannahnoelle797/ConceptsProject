public class arithmetic_op {
    String[] operators = { "+", "-", "*", "/", "%", "^", "\\" };
    String[] opTokens = { "add_operator", "sub_operator", "mul_operator", "div_operator", "mod_operator",
            "exp_operator", "rev_div_operator" };
    int indexOfCurrOp = 0;

    public arithmetic_op(String op) {
        switch (op) {
        case "+":
            indexOfCurrOp = 0;
            break;
        case "-":
            indexOfCurrOp = 1;
            break;
        case "*":
            indexOfCurrOp = 2;
            break;
        case "/":
            indexOfCurrOp = 3;
            break;
        case "%":
            indexOfCurrOp = 4;
            break;
        case "^":
            indexOfCurrOp = 5;
            break;
        case "\\":
            indexOfCurrOp = 6;
            break;
        default:
            indexOfCurrOp = -1;
            break;
        }
    }

    public String getArithmeticOp() {
        return operators[indexOfCurrOp];
    }

    public void toGrammar() {
        System.out.print("<arithmetic_op> -> " + opTokens[indexOfCurrOp]);

    }
}