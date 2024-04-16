package mvclinea.gui;

import mvclinea.objects.Linea;

import javax.swing.*;
import java.awt.*;

public class LineaJFrame extends JFrame {
    private LineaJPanel panel;
    private Linea model;

    public LineaJFrame() {
        init();
    }

    private void init() {
        model = new Linea();
        panel = new LineaJPanel(model);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.CENTER);
        JButton btn = new JButton("Mover");
        btn.addActionListener(e -> {
            btnMoverClicked();
        });
        this.getContentPane().add(btn, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void btnMoverClicked() {
        Thread t = new Thread(() -> {
           while (true) {
                model.mover();
           }
        });
        t.start();
    }

    public static void main(String[] args) {
        new LineaJFrame();
    }

}
