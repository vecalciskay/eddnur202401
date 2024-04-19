package tp2;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Spiral {
    private static final float goldenRatio = (1 + (float) Math.sqrt(5)) / 2;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    int clickedY = 0;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void goldenSpiral(Graphics2D g, float size, int depth, int clickX, int clickY) {
        if (depth <= 0) {
            return;
        }
        clickedY = clickY - (int) size;
        g.setStroke(new BasicStroke(2.0f));
        g.setColor(Color.CYAN);
        g.drawRect(clickX, clickedY, (int) size, (int) size);
        g.drawArc(clickX, clickedY, (int) (2 * size - 1), (int) (2 * size - 1), 90, 90);
        g.translate((clickX * 2) + size * goldenRatio, 0);
        g.rotate(Math.PI / 2);
        goldenSpiral(g, size / goldenRatio, depth - 1, clickedY, clickX + (int) (size / goldenRatio));
    }
}
