import java.util.ArrayList;

public class statement {
    if_statement ifStmt; // type = 0
    assignment_statement assignStmt; // type = 1
    while_statement whileStmt; // type = 2
    print_statement printStmt; // type = 3
    for_statement forStmt; // type = 4
    int type;

    public statement() {

    }

    public statement(int type, String expression) {
        this.type = type;
        switch (type) {
        case 1:
            assignStmt = new assignment_statement(expression);
            break;
        case 3:
            printStmt = new print_statement(expression.substring(expression.indexOf("(") + 1, expression.indexOf(")")));
            break;
        default:
            System.out.println("Statement grammar not found");
            break;
        }
    }

    public statement(int type, ArrayList<String> expressions) {
        this.type = type;
        switch (type) {
        case 0:
            ifStmt = new if_statement(expressions);
            break;
        case 1:
            assignStmt = new assignment_statement(expressions.get(0));
            break;
        case 2:
            whileStmt = new while_statement(expressions);
            break;
        case 3:
            printStmt = new print_statement(expressions.get(0));
            break;
        case 4:
            forStmt = new for_statement(expressions);
            break;
        default:
            System.out.println("Statement grammar not found");
            break;
        }
    }

    public void toGrammar() {
        switch (type) {
        case 0:
            ifStmt.toGrammar();
            break;
        case 1:
            assignStmt.toGrammar();
            break;
        case 2:
            whileStmt.toGrammar();
            break;
        case 3:
            printStmt.toGrammar();
            break;
        case 4:
            forStmt.toGrammar();
            break;
        default:
            System.out.println("Statement grammar not found");
            break;
        }
    }
}