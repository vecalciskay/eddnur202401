package mvclinea.gui;

import mvclinea.objects.Linea;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LineaJPanel extends JPanel implements PropertyChangeListener {
    private Linea model;

    public LineaJPanel(Linea model) {
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
            this.model.dibujar(0, 0, getWidth(), g);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
