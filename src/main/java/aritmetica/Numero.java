package aritmetica;

public class Numero extends ElementoAritmetico {
    private double valor;
    public Numero(int n) {
        valor = (double)n;
    }
    public Numero(double d) {
        valor = d;
    }

    public Numero(float f) {
        valor = f;
    }

    public String toString() {
        return String.valueOf(valor);
    }

    public double getValor() {
        return valor;
    }
}
