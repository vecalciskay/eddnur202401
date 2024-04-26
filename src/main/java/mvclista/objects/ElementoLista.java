package mvclista.objects;

import java.awt.*;

public class ElementoLista {
    private Integer valor;
    private Color color;

    public ElementoLista(int valor, Color color) {
        this.valor = valor;
        this.color = color;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor.toString();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
