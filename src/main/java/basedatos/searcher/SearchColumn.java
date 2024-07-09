package basedatos.searcher;

public class SearchColumn {
    private ColumnType columnType;
    private ColumnOperator columnOperator;
    private String argument;

    /**
     *
     * @param column
     * @param expression This is a string in the form of "operator__argument"
     */
    public SearchColumn(ColumnType column, String expression) {
        this.columnType = column;
        readExpression(expression);
    }

    private void readExpression(String expression) {
        String[] parts = expression.split("__");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
        columnOperator = ColumnOperator.fromString(parts[0]);
        argument = parts[1];
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public String getArgument() {
        return argument;
    }

    public ColumnOperator getColumnOperator() {
        return columnOperator;
    }
}
