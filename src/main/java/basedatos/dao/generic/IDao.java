package basedatos.dao.generic;

import basedatos.searcher.SearchCriteria;
import cadenas.Lista;

/**
 *
 * @param <E> Este es el tipo de objeto DTO
 * @param <T> Este es el tipo de la llave primaria del objeto
 */
public interface IDao<E,T> {
    public E getById(T id);
    public E insert(E o);
    public E update(E o);
    public int delete(T id);
    public Lista<E> get();
    public Lista<E> get(SearchCriteria criteria);
}
