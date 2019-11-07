public class if_statement {
    boolean_ex bool;
    block ifBody;
    block elseBody;
    boolean isElse;

    public if_statement(String expression) {

    }

    public if_statement(boolean_ex bool, block ifBody, block elseBody) {
        this.bool = bool;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
        isElse = true;
    }

    public if_statement(boolean_ex bool, block ifBody) {
        this.bool = bool;
        this.ifBody = ifBody;
        isElse = false;
    }

    public void toGrammar() {
        if (isElse) {
            System.out.println("<if_statement> -> if <boolean_expression> <block> else <block> end");
            bool.toGrammar();
            ifBody.toGrammar();
            elseBody.toGrammar();
        } else {
            System.out.println("<if_statement> -> if <boolean_expression> <block> end");
            bool.toGrammar();
            ifBody.toGrammar();
        }
    }
}