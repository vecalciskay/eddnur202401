package imagenes.objects;

import hanoi.observer.Dibujable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

public class Imagen implements Dibujable {
    private int ancho;
    private int alto;
    private int[][] pixeles;
    private PropertyChangeSupport observado;
    private int[][] matrizBackup;
    private int anchoBackup;
    private int altoBackup;


    public Imagen(int ancho, int alto) {
        this.alto = alto;
        this.ancho = ancho;
        this.pixeles = new int[ancho][alto];
        observado = new PropertyChangeSupport(this);

        this.matrizBackup = new int[ancho][alto];
        this.anchoBackup = ancho;
        this.altoBackup = alto;
    }

    public void leerArchivo(File f) {
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ancho = bi.getWidth();
        alto = bi.getHeight();
        this.pixeles = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                pixeles[i][j] = bi.getRGB(i, j);
            }
        }
        observado.firePropertyChange("imagen", true, false);
    }

    public void achicar() {
        this.guardarEstado();
        int[][] nuevaMatriz = new int[ancho / 2][alto / 2];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                nuevaMatriz[i / 2][j / 2] = pixeles[i][j];
            }
        }
        this.pixeles = nuevaMatriz;
        this.ancho = ancho / 2;
        this.alto = alto / 2;
        observado.firePropertyChange("imagen", true, false);
    }

    private void guardarEstado() {
        matrizBackup = pixeles.clone();
        anchoBackup = ancho;
        altoBackup = alto;
    }

    public void deshacer() {
        this.pixeles = matrizBackup.clone();
        this.ancho = anchoBackup;
        this.alto = altoBackup;
        observado.firePropertyChange("imagen", true, false);
    }


    /**
     * 200 100 155
     * 150 120 80
     * 10  15  20
     * <p>
     * 200 200 100 100 155 155
     * 200 200 100 100 155 155
     * 150 150 120 120 80  80
     * 150 150 120 120 80  80
     * 10  10  15  15 20 20
     * 10  10  15  15 20 20
     */
    public void agrandar() {
        this.guardarEstado();
        int nuevoAncho = ancho * 2;
        int nuevoAlto = alto * 2;

        int[][] nuevaMatriz = new int[nuevoAncho][nuevoAlto];
        for (int i = 0; i < this.ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int filaInicial = i * 2;
                int colInicial = j * 2;
                nuevaMatriz[filaInicial][colInicial] = pixeles[i][j];
                nuevaMatriz[filaInicial + 1][colInicial + 1] = pixeles[i][j];
                nuevaMatriz[filaInicial][colInicial + 1] = pixeles[i][j];
                nuevaMatriz[filaInicial + 1][colInicial] = pixeles[i][j];
            }
        }
        this.pixeles = nuevaMatriz;
        this.ancho = nuevoAncho;
        this.alto = nuevoAlto;
        observado.firePropertyChange("imagen", true, false);
    }

    public void gris() {
        this.guardarEstado();
        int[][] nuevaMatriz = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int rojo = (pixeles[i][j] >> 16) & 0x000000FF;
                int verde = (pixeles[i][j] >> 8) & 0x000000FF;
                int azul = (pixeles[i][j]) & 0x000000FF;
                int promedio = (rojo + verde + azul) / 3;
                nuevaMatriz[i][j] = promedio + promedio * 256 + promedio * 256 * 256;
            }
        }
        this.pixeles = nuevaMatriz;
        observado.firePropertyChange("imagen", true, false);
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
        for (int i = 10; i < (ancho) - 10; i++) {
            for (int j = alto / 2; j < (alto / 2) + 3; j++) {
                pixeles[i][j] = 0x00FFFFFF;
            }
        }
        for (int i = ancho / 2; i < (ancho / 2) + 3; i++) {
            for (int j = 10; j < (alto) - 10; j++) {
                pixeles[i][j] = 0x00FFFFFF;
            }
        }
    }

    public void addObservador(PropertyChangeListener listener) {
        observado.addPropertyChangeListener(listener);
    }
}
