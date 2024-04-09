package hanoi.gui;

import hanoi.objects.Hanoi;
import hanoi.observer.ObservadorHanoi;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class JHanoiPanel extends JPanel implements PropertyChangeListener {

    private Hanoi hanoi;

    public JHanoiPanel(Hanoi h) {
        super();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        hanoi = h;

        hanoi.addObservadorHanoi(this);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (hanoi != null)
            hanoi.dibujar(50,50,g);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.repaint();
    }
}
