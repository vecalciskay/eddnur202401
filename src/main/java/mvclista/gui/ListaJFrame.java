package mvclista.gui;

import mvclista.objects.ListaModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListaJFrame extends JFrame {
    private ListaJPanel panel;
    private ListaModel model;
    private int cantHilos = 0;
    private boolean hiloContinua = false;
    private ArrayList<Color> listaColores = new ArrayList<>();

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
        JButton btnDetenerHilo = new JButton("Detener Hilo");
        btnDetenerHilo.addActionListener(e -> {
            btnDetenerHiloClicked();
        });

        JButton btnAgregarHilo = new JButton("Agregar Hilo");
        btnAgregarHilo.addActionListener(e -> {
            inicializarThread();
        });

        JPanel panelBottom = new JPanel();
        panelBottom.add(btn);
        panelBottom.add(btnAgregarHilo);
        panelBottom.add(btnDetenerHilo);
        this.getContentPane().add(panelBottom, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
//        inicializarThread();
        inicializarColores();
    }

    private void btnDetenerHiloClicked() {
        if (hiloContinua) {
            hiloContinua = false;
        }
    }


    private void inicializarColores() {
        listaColores.add(Color.BLACK);
        listaColores.add(Color.BLUE);
        listaColores.add(Color.RED);
        listaColores.add(Color.GREEN);
        listaColores.add(Color.CYAN);
        listaColores.add(Color.MAGENTA);
        listaColores.add(Color.ORANGE);
    }

    private void inicializarThread() {
        Thread t = new Thread(() -> {
            hiloContinua = true;
            Color colorHilo = obtenerColorAleatorio();
            while (hiloContinua) {
                model.agregarElemento(colorHilo);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        cantHilos++;
        System.out.println("Cantidad de hilos: " + cantHilos);
        t.start();
    }

    private Color obtenerColorAleatorio() {
        int index = (int) (Math.random() * listaColores.size());
        if (index >= listaColores.size()) {
            index = listaColores.size() - 1;
        }
        Color c = listaColores.get(index);
        listaColores.remove(index);
        return c;
    }

    private void btnAgregarClicked() {
        model.agregarElemento(obtenerColorAleatorio());
    }

    public static void main(String[] args) {
        new ListaJFrame();
    }

}
