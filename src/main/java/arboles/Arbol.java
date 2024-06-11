package arboles;

import cadenas.Lista;

import java.awt.*;
import java.util.HashMap;

public class Arbol<E> {
    private Nodo<E> raiz;
    private static final int ANCHO_NODO = 80;
    private static final int ESPACIO_VERTICAL = 130;
    private static final int ESPACIO_HORIZONTAL = 30;
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

    public E nodoEnPixel(int x, int y) {
        if (raiz == null)
            return null;
        return raiz.nodoEnPixel(x, y);
    }

    class Nodo<E> {
        private String id;
        private E contenido;
        private Lista<Nodo<E>> hijos;
        private int ultimaPosicionX;
        private int ultimaPosicionY;

        public Nodo(E o) {
            this(o, o.toString());
        }
        public Nodo(E o, String identificador) {
            id = identificador;
            contenido = o;
            hijos = new Lista<>();
            ultimaPosicionY = -1;
            ultimaPosicionX = -1;
        }

        public E getContenido() {
            return contenido;
        }

        public Lista<Nodo<E>> getHijos() {
            return hijos;
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

        public E nodoEnPixel(int x, int y) {
            if (ultimaPosicionX < 0 || ultimaPosicionY < 0)
                return null;
            if(x > ultimaPosicionX &&
               x < (ultimaPosicionX + ANCHO_NODO) &&
               y > ultimaPosicionY &&
               y < (ultimaPosicionY + ANCHO_NODO)) {
                return contenido;
            }
            for (Nodo<E> hijo : hijos) {
                E contenidoHijo = hijo.nodoEnPixel(x,y);
                if (contenidoHijo != null)
                    return contenidoHijo;
            }
            return null;
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
            int ancho = calcularAncho();

            int yHijo = y + ESPACIO_VERTICAL;
            int xHijo = x;
            for(Nodo<E> hijo : hijos) {
                int anchoHijo = hijo.calcularAncho();

                g.drawLine(x + ancho/2, y + ANCHO_NODO/2,
                        xHijo + anchoHijo/2, yHijo + ANCHO_NODO/2);
                hijo.dibujar(g, xHijo, yHijo);

                xHijo += (ESPACIO_HORIZONTAL + anchoHijo);
            }
            this.dibujarNodo(g, x + ancho/2 - ANCHO_NODO/2, y);

        }

        private void dibujarNodo(Graphics g, int x, int y) {
            ultimaPosicionX = x;
            ultimaPosicionY = y;

            g.setColor(Color.white);
            g.fillOval(x, y, ANCHO_NODO, ANCHO_NODO);
            g.setColor(Color.black);
            g.drawOval(x, y, ANCHO_NODO, ANCHO_NODO);

            g.setFont(new Font("Serif", Font.ITALIC, 22));
            g.drawString(contenido.toString(),
                    x + ANCHO_NODO / 2 - 8,
                    y + ANCHO_NODO/2 + 7);

        }

        private int calcularAncho() {
            if (hijos.getTamano() == 0) {
                return ANCHO_NODO;
            }

            int resultado = 0;
            int espacio = 0;
            for(Nodo<E> hijo : hijos) {
                resultado += (espacio + hijo.calcularAncho());
                espacio = ESPACIO_HORIZONTAL;
            }

            return resultado;
        }
    }
}
