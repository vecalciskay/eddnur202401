package arboles.gui;

import arboles.Arbol;

import javax.swing.*;
import java.awt.*;

public class ArbolPanel extends JPanel {

    private Arbol<String> modelo;

    public ArbolPanel(Arbol<String> m) {
        modelo = m;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (modelo != null)
            modelo.dibujar(g,0,0);
    }
}
