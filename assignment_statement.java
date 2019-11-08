public class assignment_statement {
    private String id;
    private arithmetic_ex expression;

    public assignment_statement(String expression) {
        String[] stringSplit = expression.split("=");
        id = stringSplit[0];
        this.expression = new arithmetic_ex(stringSplit[1]);
    }

    public void toGrammar() {
        System.out.println("<assignment_statement> -> id <assignment_operator> <arithmetic_expression>");
        expression.toGrammar();
    }

    public String getId() {
        return id;
    }

    public void setExpression(String expression) {
        String[] stringSplit = expression.split("=");
        id = stringSplit[0];
        this.expression = new arithmetic_ex(stringSplit[1]);
    }
}