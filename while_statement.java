public class while_statement {
    boolean_ex boolEx;
    block body;

    public while_statement(String expression) {

    }

    public while_statement(boolean_ex boolEx, block body) {
        this.boolEx = boolEx;
        this.body = body;
    }
}