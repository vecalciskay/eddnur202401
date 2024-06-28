package basedatos.dao.mysql;

import basedatos.dao.FactoryDao;
import basedatos.dao.PersonaDao;

public class FactoryDaoMysql extends FactoryDao {
    public FactoryDaoMysql() {

    }
    @Override
    public PersonaDao newPersonaDao() {
        return new PersonaDaoMysql();
    }
}
