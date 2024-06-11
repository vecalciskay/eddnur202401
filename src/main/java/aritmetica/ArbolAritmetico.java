package aritmetica;

import arboles.Arbol;
import cadenas.Lista;

import java.awt.*;

public class ArbolAritmetico {
    private Nodo raiz;
    private static final int ANCHO_NODO = 80;
    private static final int ESPACIO_VERTICAL = 130;
    private static final int ESPACIO_HORIZONTAL = 30;
    //private HashMap<String,Nodo<E>> nodos;
    public ArbolAritmetico() {
        raiz = null;
        //nodos = new HashMap<>();
    }

    public ArbolAritmetico(String expresion) {
        raiz = new Nodo(expresion);
    }

    @Override
    public String toString() {
        if (raiz == null) {
            return "[VACIO]";
        }
        return raiz.toString();
    }

    public void add(ElementoAritmetico o, String padre) {
        add(o, o.toString(), padre);
    }
    public void add(ElementoAritmetico o, String id, String idPadre) {

    }

    public void add(LadoSigno lado, ElementoAritmetico o, String id, String idPadre) {
        if (idPadre == null) {
            raiz = new Nodo(o, id);
            //nodos.put(id, raiz);
            return;
        }

        Nodo padre = buscar(idPadre); // nodos.get(idPadre);
        if (padre == null) {
            // error
            return;
        }
        if (lado == LadoSigno.Izquierda) {
            Nodo hijo = new Nodo(o, id);
            padre.setIzquierda(hijo);
        }
        if (lado == LadoSigno.Derecha) {
            Nodo hijo = new Nodo(o, id);
            padre.setDerecha(hijo);
        }
        //nodos.put(id, hijo);
    }
    private Nodo buscar(String id) {
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

    public double evaluar() {
        return raiz.evaluar();
    }

    class Nodo {
        private String id;
        private ElementoAritmetico contenido;
        private Nodo izquierda;
        private Nodo derecha;

        public Nodo(ElementoAritmetico o) {
            this(o, o.toString());
        }
        public Nodo(ElementoAritmetico o, String identificador) {
            id = identificador;
            contenido = o;
        }

        public Nodo(String expresion) {
            int npa = 0;
            try {
                int n = Integer.parseInt(expresion);
                contenido = new Numero(n);
                return;
            } catch(NumberFormatException q) {
                ;
            }
            for (int i = 0; i < expresion.length(); i++) {
                char c = expresion.charAt(i);
                if (c == '(')
                {
                    npa++;
                    continue;
                }
                if (c == ')')
                {
                    npa--;
                    continue;
                }
                if (npa == 0 && esSigno(c)) {
                    contenido = new Operacion(c);
                    setIzquierda(new Nodo(expresion.substring(0,i)));
                    setDerecha(new Nodo(expresion.substring(i+1)));
                    break;
                }
            }
        }

        private boolean esSigno(char c) {
            try {
                new Operacion(c);
                return true;
            } catch (IllegalArgumentException q) {
                return false;
            }
        }

        public ElementoAritmetico getContenido() {
            return contenido;
        }

        public Nodo getIzquierda() {
            return izquierda;
        }

        public void setIzquierda(Nodo izquierda) {
            this.izquierda = izquierda;
        }

        public Nodo getDerecha() {
            return derecha;
        }

        public void setDerecha(Nodo derecha) {
            this.derecha = derecha;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();

            if (izquierda == null && derecha == null) {
                result.append(contenido);
                return result.toString();
            }

            result.append("( ");
            result.append(izquierda);
            result.append(contenido.toString());
            result.append(derecha);
            result.append(")");

            return result.toString();
        }

        public Nodo buscar(String id) {
            Nodo resultado = null;
            if (this.id.equals(id)) {
                return this;
            }

            // buscar izquierda y si no derecha
            if (izquierda != null) {
                Nodo buscarEnHijo = izquierda.buscar(id);
                resultado = buscarEnHijo;
            }
            if (derecha != null && resultado == null) {
                Nodo buscarEnHijo = derecha.buscar(id);
                resultado = buscarEnHijo;
            }
            return resultado;
        }

        public void dibujar(Graphics g, int x, int y) {
            int ancho = calcularAncho();

            int yHijo = y + ESPACIO_VERTICAL;
            int xHijo = x;

            // izquierda
            int anchoHijo = izquierda.calcularAncho();
            g.drawLine(x + ancho / 2, y + ANCHO_NODO / 2,
                    xHijo + anchoHijo / 2, yHijo + ANCHO_NODO / 2);
            izquierda.dibujar(g, xHijo, yHijo);
            xHijo += (ESPACIO_HORIZONTAL + anchoHijo);
            // derecha
            anchoHijo = derecha.calcularAncho();
            g.drawLine(x + ancho / 2, y + ANCHO_NODO / 2,
                    xHijo + anchoHijo / 2, yHijo + ANCHO_NODO / 2);
            derecha.dibujar(g, xHijo, yHijo);

            this.dibujarNodo(g, x + ancho / 2 - ANCHO_NODO / 2, y);

        }

        private void dibujarNodo(Graphics g, int x, int y) {
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
            if (izquierda == null) {
                return ANCHO_NODO;
            }

            int resultado = 0;
            int espacio = 0;

            resultado += (espacio + izquierda.calcularAncho());
            espacio = ESPACIO_HORIZONTAL;
            resultado += (espacio + derecha.calcularAncho());

            return resultado;
        }

        public double evaluar() {
            if (izquierda == null && derecha == null) {
                Numero n = (Numero)contenido;
                return n.getValor();
            }

            Operacion op = (Operacion)contenido;

            double izq = izquierda.evaluar();
            double der = derecha.evaluar();

            return op.evaluar(izq, der);
        }
    }
}
