package basedatos;

import basedatos.dao.FactoryDao;
import basedatos.dao.PersonaDao;
import basedatos.dao.mysql.PersonaDaoMysql;
import basedatos.dto.PersonaDto;
import basedatos.searcher.ColumnType;
import basedatos.searcher.SearchCriteria;
import cadenas.Lista;

public class TestDao {
    public static void main(String[] args) {

        PersonaDao dao = FactoryDao.obtenerOCrear().newPersonaDao();
        Lista<PersonaDto> personas = dao.get();
        System.out.println(personas);

        //System.out.println("Insertamos nuevo");
        //testInsert();
        //testUpdate(2);
        //testDelete(2);

        SearchCriteria criteria = new SearchCriteria();
        criteria.add(ColumnType.personas_nombre, "LIKE__Jorg")
                .add(ColumnType.personas_peso, "<__80");
        personas = dao.get(criteria);
        System.out.println(personas);

        /*PersonaDto p = dao.getById(1);
        System.out.println(p);*/
    }

    private static void testDelete(int id) {
        PersonaDao dao = FactoryDao.obtenerOCrear().newPersonaDao();
        dao.delete(id);
    }

    private static void testUpdate(int id) {
        PersonaDao dao = FactoryDao.obtenerOCrear().newPersonaDao();
        PersonaDto p = new PersonaDto(id,"PAco",169,48.5f);
        p.setNombre("Paco");
        dao.update(p);
    }

    private static void testInsert() {
        PersonaDao dao = FactoryDao.obtenerOCrear().newPersonaDao();
        PersonaDto nuevo = new PersonaDto(2,"PAco",169,48.5f);
        dao.insert(nuevo);
    }
}
