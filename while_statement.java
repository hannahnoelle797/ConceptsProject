import java.util.ArrayList;

public class while_statement {
    boolean_ex boolEx;
    block body;

    public while_statement(String expression) {

    }

    public while_statement(ArrayList<String> expression) {
        String boolExpression = expression.get(0).substring(5).trim();
        boolEx = new boolean_ex(boolExpression);
        ArrayList<String> whileBody = new ArrayList<>();
        for (int i = 1; i < expression.size(); i++) {
            if (!expression.get(i).equalsIgnoreCase("end"))
                whileBody.add(expression.get(i));
        }
        body = new block(whileBody);
    }

    public while_statement(boolean_ex boolEx, block body) {
        this.boolEx = boolEx;
        this.body = body;
    }

    public void toGrammar() {
        System.out.println("<while_statement> -> while <boolean_expression> <block> end");
        boolEx.toGrammar();
        body.toGrammar();
    }
}