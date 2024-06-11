package arboles.gui;

import arboles.Arbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ArbolPanel extends JPanel
        implements MouseListener, PropertyChangeListener {

    private Escena modelo;

    public ArbolPanel(Escena m) {

        modelo = m;
        modelo.addObserver(this);
        this.addMouseListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (modelo != null)
            modelo.getArbol().dibujar(g,0,0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Arbol<String> arbol = modelo.getArbol();
        String contenido = arbol.nodoEnPixel(e.getX(), e.getY());
        if (contenido == null)
            modelo.setMensaje("Nada");
        else
            modelo.setMensaje("Nodo seleccionado (" + e.getX() +
                ", " + e.getY() + ": " + contenido);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
