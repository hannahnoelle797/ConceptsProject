public class block {
    statement stmt;

    public block(String expression) {
        stmt = new statement(expression);
    }
}