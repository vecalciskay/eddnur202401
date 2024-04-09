package hanoi.objects;

import java.awt.*;

public class Anillo {
    public static final int ANCHOANILLO = 20;
    private int tamano;
    public Anillo(int t) {
        tamano = t;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    @Override
    public String toString() {
        return String.valueOf(tamano);
    }

    public void dibujar(int x, int y, Graphics g) {
        g.fillRect(x, y, ANCHOANILLO * tamano, ANCHOANILLO);
    }
}
