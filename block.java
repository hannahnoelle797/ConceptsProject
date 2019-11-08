import java.util.ArrayList;

public class block {
    private ArrayList<statement> blockStatements = new ArrayList<>();
    private ArrayList<String> loopStatements = new ArrayList<>();

    public block() {

    }

    public block(ArrayList<String> expressions) {
        findNextStatement(expressions);
    }

    public block(statement s) {
        blockStatements.add(s);
    }

    public void findNextStatement(ArrayList<String> lines) {
        statement s = new statement();
        int size = lines.size(), i = 0;
        while (i < size) {
            System.out.println(lines.get(i));
            if (lines.get(i).length() > 0) {
                if (lines.get(i).contains("if")) {
                    ArrayList<String> ifBody = new ArrayList<>();
                    do {
                        ifBody.add(lines.get(i));
                        i++;
                    } while (!lines.get(i).equalsIgnoreCase("end"));
                    i++;
                    s = new statement(0, ifBody);
                    blockStatements.add(s);
                } else if (lines.get(i).contains("for")) {
                    ArrayList<String> forBody = new ArrayList<>();
                    do {
                        forBody.add(lines.get(i));
                        i++;
                    } while (!lines.get(i).equalsIgnoreCase("end"));
                    i++;
                    s = new statement(4, forBody);
                    blockStatements.add(s);
                } else if (lines.get(i).contains("while")) {
                    ArrayList<String> whileBody = new ArrayList<>();
                    do {
                        whileBody.add(lines.get(i));
                        i++;
                    } while (!lines.get(i).equalsIgnoreCase("end"));
                    i++;
                    s = new statement(2, whileBody);
                    blockStatements.add(s);
                } else if (lines.get(i).contains("print")) {
                    i++;
                    s = new statement(3, lines.get(i - 1));
                    blockStatements.add(s);
                } else if (lines.get(i).contains("=")) {
                    i++;
                    s = new statement(1, lines.get(i - 1));
                    blockStatements.add(s);
                }
            }
        }

        // return s;
    }

    public void toGrammar() {
        for (statement s : blockStatements) {
            s.toGrammar();
        }
    }
}