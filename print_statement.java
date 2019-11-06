public class print_statement {
    arithmetic_ex expression;

    public print_statement(String expression) {
        this.expression = new arithmetic_ex(expression);
    }

    public void print() {
        System.out.println(expression);
    }
}