public class assignment_statement {
    String id;
    arithmetic_ex expression;

    public assignment_statement(String expression) {
        String[] stringSplit = expression.split("=");
        id = stringSplit[0];
        this.expression = new arithmetic_ex(stringSplit[1]);
    }

    public void toGrammar() {
        System.out.print("<assignment_statement> -> id <assignment_operator> <arithmetic_expression>");
        expression.toGrammar();
    }
}