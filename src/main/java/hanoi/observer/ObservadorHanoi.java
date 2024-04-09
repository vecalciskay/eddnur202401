package hanoi.observer;

import hanoi.objects.Hanoi;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ObservadorHanoi implements PropertyChangeListener, Volador {

    public ObservadorHanoi() {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Hanoi h = (Hanoi)(evt.getSource());
        System.out.println(h);
    }

    @Override
    public void volar() {
        System.out.println("Vuelo");
    }
}
