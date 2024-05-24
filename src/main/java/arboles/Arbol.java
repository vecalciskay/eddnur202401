package arboles;

import cadenas.Lista;

import java.awt.*;
import java.util.HashMap;

public class Arbol<E> {
    private Nodo<E> raiz;
    private static final int ANCHO_NODO = 40;
    private static final int ESPACIO_VERTICAL = 40;
    private static final int ESPACIO_HORIZONTAL = 20;
    //private HashMap<String,Nodo<E>> nodos;
    public Arbol() {
        raiz = null;
        //nodos = new HashMap<>();
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
            //nodos.put(id, raiz);
            return;
        }

        Nodo<E> padre = buscar(idPadre); // nodos.get(idPadre);
        if (padre == null) {
            // error
            return;
        }
        Nodo<E> hijo = new Nodo<>(o, id);
        padre.add(hijo);
        //nodos.put(id, hijo);
    }

    private Nodo<E> buscar(String id) {
        if (raiz == null) {
            // el arbol esta vacio y sin embargo alguien
            // nos pide un nodo... na q wer
            return null;
        }

        return raiz.buscar(id);
    }

    public void dibujar(Graphics g, int i, int i1) {
        if (raiz == null)
            return;
        raiz.dibujar(g,0,0);
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

        public Nodo<E> buscar(String id) {
            Nodo<E> resultado = null;
            if (this.id.equals(id)) {
                return this;
            }
            for (Nodo<E> hijo : hijos) {
                Nodo<E> buscarEnHijo = hijo.buscar(id);
                if (buscarEnHijo == null)
                    continue;

                resultado = buscarEnHijo;
                break;
            }
            return resultado;
        }

        public void dibujar(Graphics g, int x, int y) {
            int ancho = calcularAncho(this);
            this.dibujarNodo(g, ancho/2 - ANCHO_NODO/2, y);

            int yHijo = y + ESPACIO_VERTICAL;
            int separador = 0;
            int xHijo = x;
            for(Nodo<E> hijo : hijos) {
                xHijo += separador;

                hijo.dibujar(g, xHijo, yHijo);

                separador = ESPACIO_HORIZONTAL;
            }
        }
    }
}
