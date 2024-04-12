package mvclista.objects;

import hanoi.observer.Dibujable;
import mvclista.gui.ListaJPanel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ListaModel {
    private Font font = new Font("Calibri", Font.PLAIN, 20);
    private ArrayList<ElementoLista> lista = new ArrayList<>();
    private PropertyChangeSupport observado;

    public ListaModel(int n) {
        observado = new PropertyChangeSupport(this);

        for (int i = 0; i < n; i++) {
            agregarElemento();
        }
    }


    public void agregarElemento() {
        lista.add(new ElementoLista((int) (Math.random() * 100)));
        observado.firePropertyChange("Lista", true, false);
    }

    public void dibujar(int x, int y, int anchoPantalla, Graphics g) {
        g.setFont(font);
        for (ElementoLista elemento : lista) {
            g.drawRect(x, y, 80, 50);
            g.drawString(elemento.toString(), x + 30, y + 30);
            x += 90;
            if (x >= anchoPantalla) {
                x = 10;
                y += 60;
            }
        }
    }

    public void addObservador(PropertyChangeListener listener) {
        observado.addPropertyChangeListener(listener);
    }
}
