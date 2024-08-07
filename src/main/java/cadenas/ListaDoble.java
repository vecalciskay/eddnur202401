package cadenas;

public class ListaDoble<E> {
    protected ListaDoble.Nodo<E> raiz;
    private int tamano;

    public ListaDoble() {
        raiz = null;
        tamano = 0;
    }

    public int getTamano() {
        return tamano;
    }

    /**
     * Agregar elementos al principio
     *
     * @param o elemento a agregar
     */
    public void insert(E o) {
        Nodo<E> nuevo = new Nodo(o);
        nuevo.setSiguiente(raiz);
        if (raiz != null) {
            raiz.setAnterior(nuevo);
        }
        raiz = nuevo;
        tamano++;
    }

    /**
     * Agregar elementos al final
     *
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
        nuevo.setAnterior(actual);
        tamano++;
    }

    public E get(int posicion) {
        if (tamano < posicion + 1) {
            throw new IndexOutOfBoundsException("Fuera de rango");
        }
        if (posicion < 0) {
            throw new IndexOutOfBoundsException("Fuera de rango");
        }
        Nodo<E> actual = raiz;
        int posActual = 0;
        while (posActual < posicion && actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
            posActual++;
        }
        return actual.getContenido();
    }

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
        while (actual.getSiguiente() != null && posActual < posicion) {
            actual = actual.getSiguiente();
            posActual++;
        }
        Nodo<E> anterior = actual.getAnterior();
        Nodo<E> siguiente = actual.getSiguiente();
        if (anterior != null) {
            anterior.setSiguiente(siguiente);
        }
        if (siguiente != null) {
            siguiente.setAnterior(anterior);
        }
        tamano--;
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
            result.append("  <-->  ");
            actual = actual.getSiguiente();
        }

        return result.toString();
    }

    static class Nodo<E> {
        private E contenido;
        private ListaDoble.Nodo<E> siguiente;
        private ListaDoble.Nodo<E> anterior;

        public Nodo(E o) {
            contenido = o;
            siguiente = null;
            anterior = null;
        }

        public E getContenido() {
            return contenido;
        }

        public void setContenido(E contenido) {
            this.contenido = contenido;
        }

        public ListaDoble.Nodo<E> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(ListaDoble.Nodo<E> siguiente) {
            this.siguiente = siguiente;
        }

        public Nodo<E> getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo<E> anterior) {
            this.anterior = anterior;
        }
    }
}
