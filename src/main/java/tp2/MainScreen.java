package tp2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.logging.*;

public class MainScreen extends JFrame {
    private static final Logger logger = Logger.getLogger(MainScreen.class.getName());
    private Spiral spiral;
    private int clickX;
    private int clickY;
    private int dimension;
    private int depth;

    static {
        try {
            String logDirectory = "logs";
            File directory = new File(logDirectory);

            if (!directory.exists()) {
                directory.mkdir();
            }

            FileHandler fileHandler = new FileHandler(logDirectory + "/logs.log", true);
            logger.addHandler(fileHandler);

            logger.setLevel(Level.INFO);

            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al guardar el archivo de los logs", e);
        }
    }


    public MainScreen(Spiral spiral) {
        super("Espiral de Kanagawa");
        this.spiral = spiral;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new SpiralPanel(spiral));

        setVisible(true);
    }

    private class SpiralPanel extends JPanel {

        public SpiralPanel(Spiral spiral) {
            spiral.addPropertyChangeListener(e -> {
                if ("Depth".equals(e.getPropertyName())) {
                    repaint();
                }
            });
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickX = e.getX();
                    clickY = e.getY();

                    logger.log(Level.INFO, "Coordenada Inicial [X]: {0}", clickX);
                    logger.log(Level.INFO, "Coordenada Inicial [Y]: {0}", clickY);

                    String sizeInput = JOptionPane.showInputDialog("Ingrese el tamaño inicial del dibujo:");

                    if (!sizeInput.isEmpty()) {
                        try {
                            dimension = Integer.parseInt(sizeInput);
                            logger.log(Level.INFO, "Dimension: {0}", sizeInput);

                            String depthInput = JOptionPane.showInputDialog("Ingrese el nivel actual para el dibujo:");

                            if (!depthInput.isEmpty()) {
                                try {
                                    depth = Integer.parseInt(depthInput);
                                    logger.log(Level.INFO, "Profundidad: {0}", depth);
                                    repaint();
                                } catch (Exception ex) {
                                    logger.log(Level.WARNING, "No se ingresó ningún valor válido para la profundidad");
                                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para la profundidad.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } catch (Exception ex) {
                            logger.log(Level.WARNING, "No se ingresó ningún valor válido para las dimensiones");
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el tamaño inicial.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });
        }


        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            spiral.goldenSpiral(g2d, dimension, depth, clickX, clickY);
        }
    }
}
