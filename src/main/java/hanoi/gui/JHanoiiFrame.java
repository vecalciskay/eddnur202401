package hanoi.gui;

import hanoi.objects.Hanoi;

import javax.swing.*;
import java.awt.*;

public class JHanoiiFrame extends JFrame {
    private JHanoiPanel panel;
    private Hanoi modelo;

    public JHanoiiFrame() {
        init();
    }

    public void init() {
        Hanoi h = new Hanoi(3);
       panel = new JHanoiPanel(h);
       modelo = h;

       this.getContentPane().setLayout(new BorderLayout());
       this.getContentPane().add(panel, BorderLayout.CENTER);

       JButton btn = new JButton("Hacer");
       btn.addActionListener(e -> {
           btnHacer_clicked();

       });

       this.getContentPane().add(btn, BorderLayout.SOUTH);

       this.pack();
       this.setVisible(true);
    }

    private void btnHacer_clicked() {
        Thread t = new Thread(() -> {
            modelo.hacer(0,2,1,3);
        });

        t.start();
    }

    public static void main(String[] args) {
        new JHanoiiFrame();
    }
}
