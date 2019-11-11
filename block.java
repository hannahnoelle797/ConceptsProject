import java.util.ArrayList;

public class block {
    private ArrayList<statement> blockStatements = new ArrayList<>();
    private ArrayList<int[]> blockStartsEnds = new ArrayList<>();

    // private boolean found = false;

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
        for (int k = 0; k < lines.size(); k++) {
            if (checkForCondition(lines.get(k))) {
                int[] arr = { k, -1 };
                blockStartsEnds.add(arr);
            }
            if (lines.get(k).contains("end")) {
                int w = blockStartsEnds.size() - 1;
                do {
                    if (blockStartsEnds.get(w)[1] == -1) {
                        blockStartsEnds.get(w)[1] = k;
                        break;
                    }
                    w--;
                } while (w >= 0);
            }
        }
        while (i < size) {
            if (lines.get(i).length() > 0) {
                if (lines.get(i).contains("if")) {
                    ArrayList<String> ifBody = getSubList(lines, i);
                    i = getEnd(i);
                    s = new statement(0, ifBody);
                    blockStatements.add(s);
                } else if (lines.get(i).contains("for")) {
                    ArrayList<String> forBody = getSubList(lines, i);
                    i = getEnd(i);
                    s = new statement(4, forBody);
                    blockStatements.add(s);
                } else if (lines.get(i).contains("while")) {
                    ArrayList<String> whileBody = getSubList(lines, i);
                    i = getEnd(i);
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

    public ArrayList<String> getSubList(ArrayList<String> arr, int start) {
        ArrayList<String> sublist = new ArrayList<>();
        int end = getEnd(start);
        for (int i = start; i < end; i++) {
            sublist.add(arr.get(i));
        }
        return sublist;
    }

    public int getEnd(int start) {
        int end = 0;
        for (int[] k : blockStartsEnds) {
            if (k[0] == start)
                end = k[1] + 1;
        }
        return end;
    }

    public boolean checkForCondition(String s) {
        if (s.contains("if") || s.contains("for") || s.contains("while"))
            return true;
        return false;
    }

    public void toGrammar() {
        System.out.println("<block> -> <statement>");
        for (statement s : blockStatements) {
            s.toGrammar();
        }
    }
}