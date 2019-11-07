public class statement {
    if_statement ifStmt; // type = 0
    assignment_statement assignStmt; // type = 1
    while_statement whileStmt; // type = 2
    print_statement printStmt; // type = 3
    for_statement forStmt; // type = 4
    int type;

    public statement(String expression) {

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