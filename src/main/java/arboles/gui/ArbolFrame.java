package arboles.gui;

import arboles.Arbol;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ArbolFrame extends JFrame
    implements PropertyChangeListener {
    private ArbolPanel panel;
    private JLabel msg;
    private Escena escena;

    public ArbolFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.getContentPane().setLayout(new BorderLayout());
        Arbol<String> arbol = new Arbol<>();
        construirArbol(arbol);

        escena = new Escena(arbol);
        escena.addObserver(this);

        panel = new ArbolPanel(escena);
        this.getContentPane().add(panel, BorderLayout.CENTER);

        msg = new JLabel();
        msg.setFont(new Font("Serif", Font.ITALIC, 22));
        this.getContentPane().add(msg, BorderLayout.SOUTH);

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        msg.setText(escena.getMensaje());
    }
}
