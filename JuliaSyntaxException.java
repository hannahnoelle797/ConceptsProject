public class JuliaSyntaxException extends Exception {

    private static final long serialVersionUID = 1L;

    public JuliaSyntaxException(String s) {
        super();
        if (s.contains("if")) {
            System.out.println("INVALID IF STATEMENT FORMAT. MISSING END COMMAND");
        } else if (s.contains("for")) {
            if (s.contains("end"))
                System.out.println("INVALID FOR LOOP FORMAT. MISSING END COMMAND");
            if (s.contains("iter"))
                System.out.println("INVALID IF STATEMENT FORMAT. ERROR IN ITERATOR");
        } else if (s.contains("while")) {
            System.out.println("INVALID WHILE LOOP FORMAT. MISSING END COMMAND");
        } else if (s.contains("arith")) {
            System.out.println("ARITHMETIC EXPRESSION SYNATX ERROR");
        } else if (s.contains("rela")) {
            System.out.println("RELATIVE EXPRESSION SYNTAX ERROR");
        } else if (s.contains("end")) {
            System.out.println("RANDOM END STATEMENT");
        } else if (s.contains("grammar")) {
            System.out.println("UNKNOWN ERROR. PROBLEM WITH TOGRAMMAR");
        } else if (s.contains("print")) {
            System.out.println("PRINT STATEMENT ERROR: " + s);
        } else if (s.contains("=")) {
            System.out.println("ASSIGNEMNT STATEMENT ERROR: " + s);
        }
    }
}