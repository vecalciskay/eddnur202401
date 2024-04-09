package hanoi.objects;

import hanoi.observer.Dibujable;
import hanoi.observer.ObservadorHanoi;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Hanoi implements Dibujable {
    private Torre[] torres;
    private PropertyChangeSupport observado;

    public Hanoi(int n) {
        torres = new Torre[3];
        torres[0] = new Torre(n);
        torres[1] = new Torre();
        torres[2] = new Torre();
        observado = new PropertyChangeSupport(this);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            result.append(torres[i]).append("\n");
        }
        return result.toString();
    }

    public void hacer(int de, int a, int pp, int n) {
        if (n == 1) {
            Anillo obj = torres[de].getAnillos().pop();
            torres[a].getAnillos().push(obj);
            observado.firePropertyChange("Hanoi", true, false);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return;
        }
        hacer(de, pp, a, n-1);
        hacer(de,a,pp,1);
        hacer(pp,a,de,n-1);
    }

    public void dibujar(int x, int y, Graphics g) {
        g.fillRect(x, y + 300, x+400, y+310);

        torres[0].dibujar(x + 10, y, g);
        torres[1].dibujar(x + 110, y, g);
        torres[2].dibujar(x + 210, y, g);
    }
    public void addObservadorHanoi(PropertyChangeListener o) {
        observado.addPropertyChangeListener(o);
    }

}
