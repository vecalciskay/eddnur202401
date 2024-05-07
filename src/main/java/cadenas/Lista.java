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

    public E get(int posicion) {
        if (posicion < 0)
            throw new IndexOutOfBoundsException("Fuera de rango");
        if (raiz == null)
            throw new IndexOutOfBoundsException("Fuera de rango");

        if (posicion == 0)
            return raiz.getContenido();

        int posicionActual = 0;
        Nodo<E> actual = raiz;
        while(actual != null && posicionActual < posicion) {

            actual = actual.getSiguiente();
            posicionActual++;
        }
        if (actual == null)
            throw new IndexOutOfBoundsException("Fuera de rango");

        return actual.getContenido();
    }

    @Override
    public String toString() {
        if(raiz == null) {
            return "[VACIA]";
        }
        StringBuilder result = new StringBuilder();
        result.append("(Tam: ").append(tamano).append(") ");
        Nodo<E> actual = raiz;
        while(actual != null) {
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
