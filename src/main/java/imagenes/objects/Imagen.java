package imagenes.objects;

import hanoi.observer.Dibujable;

import java.awt.*;

public class Imagen implements Dibujable {
    private int ancho;
    private int alto;
    private int[][] pixeles;

    public Imagen(int ancho, int alto) {
        this.alto = alto;
        this.ancho = ancho;
        this.pixeles = new int[ancho][alto];
    }

    public void imagenCombinada() {
//         #F25022
        for (int i = 0; i < ancho / 2; i++) {
            for (int j = 0; j < alto / 2; j++) {
                pixeles[i][j] = 0x00F25022;
            }
        }
        // #00A4EF
        for (int i = ancho / 2; i < ancho; i++) {
            for (int j = alto / 2; j < alto; j++) {
                pixeles[i][j] = 0x0000A4EF;
            }
        }
        // #7FBA00
        for (int i = ancho / 2; i < ancho; i++) {
            for (int j = 0; j < alto / 2; j++) {
                pixeles[i][j] = 0x007FBA00;
            }
        }
        //  #FFB900
        for (int i = 0; i < ancho / 2; i++) {
            for (int j = alto / 2; j < alto; j++) {
                pixeles[i][j] = 0x00FFB900;
            }
        }
    }

    public void imagenRoja() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                pixeles[i][j] = 0x00FF0000;
            }
        }
    }

    public void imagenAzul() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                pixeles[i][j] = 0x000000FF;
            }
        }
    }

    @Override
    public void dibujar(int x, int y, Graphics g) {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                g.setColor(new Color(pixeles[i][j]));
                g.drawLine(i, y + j, x + i, y + j);
            }
        }
    }

    public void lineaEnElMedio() {
        for (int i = 10; i < (ancho ) - 10; i++) {
            for (int j = alto / 2; j < (alto / 2) + 3; j++) {
                pixeles[i][j] = 0x00FFFFFF;
            }
        }
        for (int i = ancho/2; i < (ancho /2) +3; i++) {
            for (int j = 10; j < (alto) -10; j++) {
                pixeles[i][j] = 0x00FFFFFF;
            }
        }
    }
}
