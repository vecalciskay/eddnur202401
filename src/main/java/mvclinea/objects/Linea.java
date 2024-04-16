package mvclinea.objects;

import hanoi.observer.Dibujable;
import mvclinea.gui.LineaJPanel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Linea {
    private int posX = 100;
    private int posY = 100;
    private int ancho = 500;
    private PropertyChangeSupport observado;

    public Linea() {
        observado = new PropertyChangeSupport(this);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void dibujar(int x, int y, int anchoPantalla, Graphics g) {
        //grosor de la línea
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));

        if (this.posX >= anchoPantalla) {
            this.posX = 0;
        }
        g2.drawLine(this.posX, this.posY, this.posX + ancho, this.posY);
        if (this.posX + ancho > anchoPantalla) {
            int diff = (this.posX + ancho) - anchoPantalla;
            System.out.println(diff);
            g.drawLine(0, this.posY, diff, this.posY);
        }

//        g2.drawArc(200, 200, 100, 300, 0, -90);
//        g2.setColor(Color.BLUE);
//        g2.drawArc(200, 200, 100, 300, 90, -90);
//        g2.setColor(Color.RED);
//        g2.drawArc(200, 200, 100, 300, 180, -90);
//        g2.setColor(Color.GREEN);
//        g2.drawArc(200, 200, 100, 300, 270, -90);
//        g2.setColor(Color.CYAN);
//        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
//                0, new float[]{9}, 0);
//        g2.setStroke(dashed);
//        g2.drawRect(200, 200, 100, 300);
    }

    public void mover() {
        this.posX = this.posX + 20;
        observado.firePropertyChange("Linea", true, false);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void addObservador(PropertyChangeListener listener) {
        observado.addPropertyChangeListener(listener);
    }
}
