package basedatos.searcher;

public enum ColumnType {
    personas_id("id"),
    personas_nombre("nombre"),
    personas_altura("alturacm"),
    personas_peso("pesokg");

    private String column;

    ColumnType(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
