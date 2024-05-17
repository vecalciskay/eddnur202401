package cadenas;

public class Pila<E> extends ListaDoble<E> {
    /**
     * Obtener el último elemento en la pila
     *
     * @return el ultimo elemento de la pila
     */
    public E pop() {
        Nodo<E> actual = raiz;
        int pos = 0;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
            pos++;
        }
        delete(pos);
        return actual.getContenido();
    }
}
