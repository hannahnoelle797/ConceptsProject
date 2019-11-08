import java.util.ArrayList;

public class if_statement {
    boolean_ex bool;
    block ifBody;
    block elseBody;

    public if_statement(String expression) {

    }

    public if_statement(ArrayList<String> expression) {
        String ifBool = expression.get(0).substring(2).trim();
        ArrayList<String> ifBody = new ArrayList<>();
        ArrayList<String> elseBody = new ArrayList<>();
        int i = 1;
        do {
            ifBody.add(expression.get(i));
            i++;
        } while (!expression.get(i).equalsIgnoreCase("else"));
        do {
            if (!expression.get(i).contains("else")) {
                elseBody.add(expression.get(i));
            }
            i++;
            if (i == expression.size())
                break;
        } while (!expression.get(i).equalsIgnoreCase("end"));
        bool = new boolean_ex(ifBool);
        this.ifBody = new block(ifBody);
        this.elseBody = new block(elseBody);
    }

    public if_statement(boolean_ex bool, block ifBody, block elseBody) {
        this.bool = bool;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }

    public void toGrammar() {
        System.out.println("<if_statement> -> if <boolean_expression> <block> else <block> end");
        bool.toGrammar();
        ifBody.toGrammar();
        elseBody.toGrammar();
    }

}