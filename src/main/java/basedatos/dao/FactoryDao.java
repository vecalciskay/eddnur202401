package basedatos.dao;

import basedatos.dao.mysql.FactoryDaoMysql;

public abstract class FactoryDao {
    private static FactoryDao instancia = null;

    public static FactoryDao obtenerOCrear() {
        if (instancia == null) {
            instancia = new FactoryDaoMysql();
        }
        return instancia;
    }

    public abstract PersonaDao newPersonaDao();
}
