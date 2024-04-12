package mvclista.gui;

import mvclista.objects.ListaModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ListaJPanel extends JPanel implements PropertyChangeListener {
    private ListaModel model;

    public ListaJPanel(ListaModel model) {
        this.model = model;
        model.addObservador(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.model != null) {
            this.model.dibujar(10, 10, getWidth(), g);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.repaint();
    }
}
