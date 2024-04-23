package imagenes.gui;

import imagenes.objects.Imagen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

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

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenu menuEdicion = new JMenu("Edición");
        JMenuItem menuItem = new JMenuItem();
        menuItem.setText("Cargar imagen");
        menuItem.addActionListener((e) -> {
            menuItemClicked();
        });
        JMenuItem menuAgrandar = new JMenuItem("Agrandar");
        menuAgrandar.addActionListener((e) -> {
            menuAgrandarClicked();
        });
        JMenuItem menuAchicar = new JMenuItem("Achicar");
        menuAchicar.addActionListener((e) -> {
            menuAchicarClicked();
        });
        JMenuItem menuEscalaGrises = new JMenuItem("Escala de Grises");
        menuEscalaGrises.addActionListener((e) -> {
            menuEscalagrises();
        });
        menuArchivo.add(menuItem);
        menuArchivo.add(menuAgrandar);
        menuArchivo.add(menuAchicar);
        menuArchivo.add(menuEscalaGrises);

        JMenuItem menuDeshacer = new JMenuItem("Deshacer");
        menuDeshacer.addActionListener((e -> {
            menuDeshacerClicked();
        }));
        menuDeshacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        menuEdicion.add(menuDeshacer);

        menuBar.add(menuArchivo);
        menuBar.add(menuEdicion);
        this.setJMenuBar(menuBar);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    private void menuDeshacerClicked() {
        this.model.deshacer();
    }

    private void menuEscalagrises() {
        this.model.gris();
    }

    private void menuAchicarClicked() {
        this.model.achicar();
    }

    private void menuAgrandarClicked() {
        this.model.agrandar();
    }

    private void menuItemClicked() {
        JFileChooser chooser = new JFileChooser();
        int respuesta = chooser.showDialog(this, "Seleccionar");
        if (respuesta == 0) {
            File f = chooser.getSelectedFile();
            this.model.leerArchivo(f);
        }
    }

    public static void main(String[] args) {
        new ImagenJFrame();
    }
}
