import java.util.ArrayList;

public class while_statement {
    boolean_ex boolEx;
    block body;

    public while_statement(String expression) {

    }

    public while_statement(ArrayList<String> expressions) {

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