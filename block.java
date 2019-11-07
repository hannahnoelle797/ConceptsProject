import java.util.ArrayList;

public class block {
    private ArrayList<statement> fileStatements = new ArrayList<>();

    public block(String expression) {

    }

    public void toGrammar() {
        for (statement s : fileStatements) {
            s.toGrammar();
        }
    }
}