package basedatos.searcher;

public enum ColumnOperator {
    EQUALS("="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUALS(">="),
    LESS_THAN_OR_EQUALS("<="),
    NOT_EQUALS("!="),
    LIKE("LIKE"),
    IN("IN"),
    NOT_IN("NOT IN");

    private String operator;

    ColumnOperator(String operator) {
        this.operator = operator;
    }

    public static ColumnOperator fromString(String part) {
        for (ColumnOperator op : ColumnOperator.values()) {
            if (op.getOperator().equals(part)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Invalid operator: " + part);
    }

    public String getOperator() {
        return operator;
    }
}
