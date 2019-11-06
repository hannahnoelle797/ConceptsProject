public class if_statement {
    boolean_ex bool;
    block ifBody;
    block elseBody;

    public if_statement(String expression) {

    }

    public if_statement(boolean_ex bool, block ifBody, block elseBody) {
        this.bool = bool;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }
}