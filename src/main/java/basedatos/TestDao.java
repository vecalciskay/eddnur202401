package basedatos;

import basedatos.dao.PersonaDao;
import basedatos.dto.PersonaDto;
import cadenas.Lista;

public class TestDao {
    public static void main(String[] args) {

        PersonaDao dao = new PersonaDao();
        Lista<PersonaDto> personas = dao.getPersonas();
        System.out.println(personas);
        /*
        PersonaDto nuevo = new PersonaDto(2,"PAco",169,45.8);
        dao.insert(nuevo);

        PersonaDto dto = dao.getPersonaById(nuevo.getId());

        dto.setNombre("Luis");
        dao.update(dto);

        dao.delete(2);*/
    }
}
