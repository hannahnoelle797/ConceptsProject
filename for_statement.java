public class for_statement {
    String id;
    iter iterator;
    block statement;

    public for_statement(String fullLoop) {

    }

    public void toGrammar() {
        System.out.println("<for_statement> -> for id = <iter> <block> end");
        iterator.toGrammar();
        statement.toGrammar();
    }
}