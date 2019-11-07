public class iter {
    arithmetic_ex left;
    arithmetic_ex right;

    public iter(String expression) {
        if (expression.contains(":")) {
            String[] expressSplit = expression.split(":");
            left = new arithmetic_ex(expressSplit[0]);
            right = new arithmetic_ex(expressSplit[1]);
        } else {
            System.out.println("Invalid format");
        }
    }

    public void toGrammar() {
        System.out.println("<iter> -> <arithmetic_expression> : <arithmetic_expression>");
        left.toGrammar();
        right.toGrammar();
    }

}