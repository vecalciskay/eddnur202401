package imagenes.gui;

import imagenes.objects.Imagen;
import mvclista.gui.ListaJPanel;
import mvclista.objects.ListaModel;

import javax.swing.*;
import java.awt.*;

public class ImagenJFrame extends JFrame {
    private ImagenJPanel panel;
    private Imagen model;

    public ImagenJFrame() {
        init();
    }

    private void init() {
        model = new Imagen(1000, 800);
        panel = new ImagenJPanel(model);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        new ImagenJFrame();
    }
}
