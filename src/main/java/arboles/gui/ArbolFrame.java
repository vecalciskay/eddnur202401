package arboles.gui;

import arboles.Arbol;

import javax.swing.*;
import java.awt.*;

public class ArbolFrame extends JFrame {
    private ArbolPanel panel;

    public ArbolFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.getContentPane().setLayout(new BorderLayout());
        Arbol<String> arbol = new Arbol<>();
        construirArbol(arbol);
        panel = new ArbolPanel(arbol);
        this.getContentPane().add(panel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    private void construirArbol(Arbol<String> arbol) {
        arbol.add("D", null);
        arbol.add("R","D");
        arbol.add("W","D");
        arbol.add("G","R");
        arbol.add("H","R");
        arbol.add("T","R");
    }

    public static void main(String[] args) {
        new ArbolFrame();
    }
}
