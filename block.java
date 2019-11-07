import java.util.ArrayList;

public class block {
    private ArrayList<statement> blockStatements = new ArrayList<>();

    public block(String[] expressions) {
        for (String s : expressions) {
            statement state = new statement(s);
            blockStatements.add(state);
        }
    }

    public void toGrammar() {
        for (statement s : blockStatements) {
            s.toGrammar();
        }
    }
}