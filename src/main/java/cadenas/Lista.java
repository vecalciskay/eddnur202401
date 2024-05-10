package cadenas;

import java.util.Iterator;

public class Lista<E> implements Iterable<E> {

    private Nodo<E> raiz;
    private int tamano;

    public Lista() {
        raiz = null;
        tamano = 0;
    }

    public int getTamano() {
        return tamano;
    }

    public void insert(E o) {
        Nodo<E> nuevo = new Nodo(o);
        nuevo.setSiguiente(raiz);
        raiz = nuevo;
        tamano++;
    }

    /**
     * Agregar elemento al final
     * @param o elemento a agregar
     */
    public void add(E o) {
        Nodo<E> nuevo = new Nodo<>(o);
        if (raiz == null) {
            raiz = nuevo;
            tamano++;
            return;
        }
        Nodo<E> actual = raiz;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);
        tamano++;
    }

    /**
     * Eliminar ultimo elemento
     */
    public void pop() {
        if (raiz == null) {
            return;
        }
        delete(tamano - 1);
    }

    /**
     * Eliminar primer elemento
     */
    public void poll() {
        if (raiz == null) {
            return;
        }
        delete(0);
    }

    public void remove(E ele) {
        if (raiz == null) {
            return;
        }
        Nodo<E> actual = raiz;
        int posActual = 0;
        while (actual.getSiguiente() != null && !actual.getContenido().equals(ele)) {
            actual = actual.getSiguiente();
            posActual++;
        }
        if (actual.getContenido().equals(ele)) {
            delete(posActual);
        }
    }

    /**
     * Eliminar en cualquier posición de la lista
     *
     * @param posicion a eliminar
     */
    public void delete(int posicion) {
        if (tamano < posicion + 1) {
            throw new IndexOutOfBoundsException("Intentó eliminar la posición " + posicion + ", La lista solo tiene " + tamano + " elementos");
        }
        if (posicion == 0) {
            raiz = raiz.getSiguiente();
            tamano--;
            return;
        }
        Nodo<E> actual = raiz;
        int posActual = 0;
        Nodo<E> anterior = null;
        while (actual.getSiguiente() != null && posActual < posicion) {
            anterior = actual;
            actual = actual.getSiguiente();
            posActual++;
        }
        Nodo<E> siguiente = actual.getSiguiente();
        anterior.setSiguiente(siguiente);
        tamano--;
    }

    public E get(int posicion) {
        if (posicion < 0)
            throw new IndexOutOfBoundsException("Fuera de rango");
        if (raiz == null)
            throw new IndexOutOfBoundsException("Fuera de rango");

        if (posicion == 0)
            return raiz.getContenido();

        int posicionActual = 0;
        Nodo<E> actual = raiz;
        while (actual != null && posicionActual < posicion) {

            actual = actual.getSiguiente();
            posicionActual++;
        }
        if (actual == null)
            throw new IndexOutOfBoundsException("Fuera de rango");

        return actual.getContenido();
    }

    @Override
    public String toString() {
        if (raiz == null) {
            return "[VACIA]";
        }
        StringBuilder result = new StringBuilder();
        result.append("(Tam: ").append(tamano).append(") ");
        Nodo<E> actual = raiz;
        while (actual != null) {
            result.append(actual.getContenido());
            result.append("  ->  ");
            actual = actual.getSiguiente();
        }

        return result.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterador(raiz);
    }

    class Iterador<E> implements Iterator<E> {

        private Nodo<E> actual;

        public Iterador(Nodo<E> primero) {
            actual = primero;
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public E next() {
            E o = actual.getContenido();
            actual = actual.getSiguiente();
            return o;
        }
    }

    class Nodo<E> {
        private E contenido;
        private Nodo<E> siguiente;

        public Nodo(E o) {
            contenido = o;
            siguiente = null;
        }

        public E getContenido() {
            return contenido;
        }

        public void setContenido(E contenido) {
            this.contenido = contenido;
        }

        public Nodo<E> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<E> siguiente) {
            this.siguiente = siguiente;
        }
    }
}
