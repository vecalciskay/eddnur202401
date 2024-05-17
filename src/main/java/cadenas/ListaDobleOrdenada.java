package cadenas;

public class ListaDobleOrdenada<E extends Comparable<E>> {

    private Nodo<E> raiz;
    private int tamano;

    public ListaDobleOrdenada() {
        raiz = null;
        tamano = 0;
    }

    public void insert(E ele) {
        Nodo<E> actual = raiz;
        Nodo<E> nuevo = new Nodo<>(ele);
        if (raiz == null) {
            raiz = nuevo;
            tamano++;
            return;
        }
        if (nuevo.compareTo(raiz) < 0) { //nuevo < raiz
            raiz.setAnterior(nuevo);
            nuevo.setSiguiente(raiz);
            raiz = nuevo;
            tamano++;
            return;
        }
        Nodo<E> siguiente = actual.getSiguiente();

        while (nuevo.compareTo(actual) > 0 // nuevo > actual
                && siguiente != null
                && nuevo.compareTo(siguiente) > 0 // nuevo > siguiente
        ) {
            actual = actual.getSiguiente();
            siguiente = actual.getSiguiente();
        }
        //conexiones con el siguiente
        if (siguiente != null) {
            siguiente.setAnterior(nuevo);
            nuevo.setSiguiente(siguiente);
        }
        //conexiones con el actual
        actual.setSiguiente(nuevo);
        nuevo.setAnterior(actual);

        tamano++;
    }

    public void delete(E ele) {
        if (raiz.getContenido().compareTo(ele) == 0) { //queremos eliminar la raiz?
            if (tamano == 1) { //el elemento a eliminar es la raiz, y no hay nada mas
                raiz = null;
                tamano--;
                return;
            }
            //vamos a eliminar el primer elemento de la lista
            raiz = raiz.getSiguiente();
            raiz.setAnterior(null);
            tamano--;
            return;
        }

        Nodo<E> actual = raiz;
        while (actual.getSiguiente() != null && actual.getContenido().compareTo(ele) != 0) {
            actual = actual.getSiguiente();
        }
        if (actual.getSiguiente() == null) {// es el último elemento
            Nodo<E> anterior = actual.getAnterior();
            anterior.setSiguiente(null);
        } else { //el nodo está entre el medio
            Nodo<E> anterior = actual.getAnterior();
            Nodo<E> siguiente = actual.getSiguiente();
            anterior.setSiguiente(siguiente);
            siguiente.setAnterior(anterior);
        }
        tamano--;

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

    static class Nodo<E extends Comparable<E>> implements Comparable<Nodo<E>> {
        private E contenido;
        private Nodo<E> siguiente;
        private Nodo<E> anterior;

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

        public Nodo<E> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<E> siguiente) {
            this.siguiente = siguiente;
        }

        public Nodo<E> getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo<E> anterior) {
            this.anterior = anterior;
        }

        /**
         * @param o the object to be compared.
         * @return positivo cuando this > o, negativo cuando this < o, 0 cuando this == o
         */
        @Override
        public int compareTo(Nodo<E> o) {
            return this.getContenido().compareTo(o.getContenido());
        }

        @Override
        public String toString() {
            return contenido.toString();
        }
    }
}
