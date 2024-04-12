package mvclista.gui;

import mvclista.objects.ListaModel;

import javax.swing.*;
import java.awt.*;

public class ListaJFrame extends JFrame {
    private ListaJPanel panel;
    private ListaModel model;

    public ListaJFrame() {
        init();
    }

    private void init() {
        model = new ListaModel(10);
        panel = new ListaJPanel(model);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.CENTER);
        JButton btn = new JButton("Agregar elemento");
        btn.addActionListener(e -> {
            btnAgregarClicked();
        });
        this.getContentPane().add(btn, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    private void btnAgregarClicked() {
        model.agregarElemento();
    }

    public static void main(String[] args) {
        new ListaJFrame();
    }

}
