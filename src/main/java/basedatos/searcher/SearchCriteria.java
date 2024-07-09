package basedatos.searcher;

import cadenas.Lista;

public class SearchCriteria {
    private Lista<SearchColumn> columns;

    public SearchCriteria() {
        columns = new Lista<>();
    }

    public SearchCriteria add(ColumnType col, String expression) {
        columns.add(new SearchColumn(col, expression));
        return this;
    }

    public Lista<SearchColumn> getColumns() {
        return columns;
    }

    public String toSqlString() {
        if (columns.getTamano() == 0) {
            return " 1=1 ";
        }
        StringBuilder sb = new StringBuilder();
        String firstAnd = "";
        for (SearchColumn col : columns) {
            if (sb.length() > 0) {
                sb.append(firstAnd);
            }
            sb.append(col.getColumnType().getColumn());
            sb.append(" ");
            sb.append(col.getColumnOperator().getOperator());
            sb.append(" ");
            String arg = col.getArgument();
            if (arg != null) {
                if (col.getColumnOperator() == ColumnOperator.LIKE) {
                    sb.append("'%");
                    sb.append(arg);
                    sb.append("%'");
                } else if (col.getColumnOperator() == ColumnOperator.IN || col.getColumnOperator() == ColumnOperator.NOT_IN) {
                    sb.append("(");
                    sb.append(arg);
                    sb.append(")");
                } else {
                    sb.append("'");
                    sb.append(arg);
                    sb.append("'");
                }
            }

            firstAnd = " AND ";
        }
        return sb.toString();
    }
}
