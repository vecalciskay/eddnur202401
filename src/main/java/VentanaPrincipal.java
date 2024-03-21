import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JButton botonOrdenar;
    private JPanel panelGrupos;
    private static final String[] VOCALS = {"A", "E", "I", "O", "U"};
    private JTextArea[] areasTexto;

    public VentanaPrincipal() {
        super("Ordenamiento de Palabras");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemLeer = new JMenuItem("Leer");
        itemLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: agregar la logica para leer el archivo y mostrar las palabras en pantalla respectivo a su vocal...
                JOptionPane.showMessageDialog(VentanaPrincipal.this, "Función no implementada", "Leer", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menuArchivo.add(itemLeer);
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelBotonOrdenar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonOrdenar = new JButton("Ordenar");
        botonOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarPalabras();
            }
        });
        panelBotonOrdenar.add(botonOrdenar);
        panelPrincipal.add(panelBotonOrdenar, BorderLayout.NORTH);

        panelGrupos = new JPanel();
        panelGrupos.setLayout(new GridLayout(1, VOCALS.length));
        areasTexto = new JTextArea[VOCALS.length];
        for (int i = 0; i < VOCALS.length; i++) {
            JPanel panelVocal = new JPanel(new BorderLayout());
            areasTexto[i] = new JTextArea(5, 15);
            areasTexto[i].setEditable(false);
            JScrollPane scrollPane = new JScrollPane(areasTexto[i]);
            panelVocal.add(new JLabel(VOCALS[i], SwingConstants.CENTER), BorderLayout.NORTH);
            panelVocal.add(scrollPane, BorderLayout.CENTER);
            panelGrupos.add(panelVocal);
        }
        panelPrincipal.add(panelGrupos, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private void ordenarPalabras() {
        // TODO: agregar la logica para ordenar las palabras...
        JOptionPane.showMessageDialog(this, "Función no implementada", "Ordenar", JOptionPane.INFORMATION_MESSAGE);
    }

}
