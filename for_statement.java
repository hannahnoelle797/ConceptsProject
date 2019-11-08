import java.util.ArrayList;

public class for_statement {
    private String id;
    private iter iterator;
    private block statement;
    private boolean nested = false;

    public for_statement(ArrayList<String> expressions) {
        String it = expressions.get(0).substring(3).trim();
        String[] condition = it.split("=");
        this.id = condition[0].trim();
        iterator = new iter(condition[1]);
        ArrayList<String> forBody = new ArrayList<>();
        for (int i = 1; i < expressions.size(); i++) {
            if (!expressions.get(i).contains("end")) {
                if (expressions.get(i).contains("if") || expressions.get(i).contains("while")
                        || expressions.get(i).contains("for")) {
                    nested = true;
                }
                forBody.add(expressions.get(i));
            } else {
                if (nested) {
                    forBody.add(expressions.get(i));
                    nested = false;
                }
            }
        }
        statement = new block(forBody);
    }

    public void toGrammar() {
        System.out.println("<for_statement> -> for id = <iter> <block> end");
        iterator.toGrammar();
        statement.toGrammar();
    }
}