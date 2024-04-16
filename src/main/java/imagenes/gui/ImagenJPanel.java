package imagenes.gui;

import imagenes.objects.Imagen;

import javax.swing.*;
import java.awt.*;

public class ImagenJPanel extends JPanel {
    private Imagen model;

    public ImagenJPanel(Imagen model) {
        this.model = model;
        this.model.imagenAzul();
        this.model.lineaEnElMedio();
    }
    public Dimension getPreferredSize() {
        return new Dimension(1000, 800);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.model != null) {
            this.model.dibujar(10, 10,  g);
        }
    }
}
