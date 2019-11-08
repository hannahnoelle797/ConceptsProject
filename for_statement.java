import java.util.ArrayList;

public class for_statement {
    String id;
    iter iterator;
    block statement;

    public for_statement(String fullLoop) {

    }

    public for_statement(ArrayList<String> expressions) {

    }

    public void toGrammar() {
        System.out.println("<for_statement> -> for id = <iter> <block> end");
        iterator.toGrammar();
        statement.toGrammar();
    }
}