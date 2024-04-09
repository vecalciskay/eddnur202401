package hanoi.objects;

import java.awt.*;
import java.util.Stack;

public class Torre {
    private Stack<Anillo> anillos;

    public Torre() {
        anillos = new Stack<>();
    }

    public Torre(int n) {
        anillos = new Stack<>();
        for (int i = 0; i < n; i++) {
            Anillo obj = new Anillo(n-i);
            anillos.push(obj);
        }
    }

    public Stack<Anillo> getAnillos() {
        return anillos;
    }

    public void setAnillos(Stack<Anillo> anillos) {
        this.anillos = anillos;
    }

    /**
     * Si la torre vacia entonces |-
     * Si tiene anillos, por ejemplo 3: |-3-2-1-
     * @return
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("|-");
        for (Anillo a :
                anillos) {
            result.append(a).append("-");
        }
        return result.toString();
    }

    public void dibujar(int x, int y, Graphics g) {
        int ancho = Anillo.ANCHOANILLO;
        int alto = Anillo.ANCHOANILLO;

        g.setColor(Color.red);
        g.fillRect(x, y, 10, 250);
        g.setColor(Color.black);

        for (Anillo a :
                anillos) {
            a.dibujar(x, y, g);
            y -= Anillo.ANCHOANILLO + 5;
        }
    }
}
