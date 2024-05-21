package arboles;

import cadenas.Lista;

import java.util.HashMap;

public class Arbol<E> {
    private Nodo<E> raiz;
    private HashMap<String,Nodo<E>> nodos;
    public Arbol() {
        raiz = null;
        nodos = new HashMap<>();
    }

    @Override
    public String toString() {
        if (raiz == null) {
            return "[VACIO]";
        }
        return raiz.toString();
    }

    public void add(E o, String padre) {
        add(o, o.toString(), padre);
    }
    public void add(E o, String id, String idPadre) {
        if (idPadre == null) {
            raiz = new Nodo(o, id);
            nodos.put(id, raiz);
            return;
        }

        Nodo<E> padre = nodos.get(idPadre);
        if (padre == null) {
            // error
            return;
        }
        Nodo<E> hijo = new Nodo<>(o, id);
        padre.add(hijo);
        nodos.put(id, hijo);
    }

    class Nodo<E> {
        private String id;
        private E contenido;
        private Lista<Nodo<E>> hijos;

        public Nodo(E o) {
            this(o, o.toString());
        }
        public Nodo(E o, String identificador) {
            id = identificador;
            contenido = o;
            hijos = new Lista<>();
        }

        public void add(Nodo<E> hijo) {
            hijos.insert(hijo);
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append(contenido);

            if (hijos.getTamano() == 0) {
                return result.toString();
            }

            result.append("( ");
            String separador = "";
            for(Nodo<E> hijo : hijos) {
                result.append(separador);
                result.append(hijo);
                separador = "-";
            }
            result.append(")");

            return result.toString();
        }
    }
}
