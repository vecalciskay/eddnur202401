package aritmetica;

public class Operacion extends ElementoAritmetico {
    private Signo signo;
    public Operacion(Signo signoOperacion) {
        this.signo = signoOperacion;
    }

    public String toString() {
        switch(signo) {
            case Suma:
                return "+";
            case Resta:
                return "-";
            case Multiplicacion:
                return "*";
            case Division:
                return "/";
            default:
                return "ERROR: OP";
        }
    }

    public double evaluar(double izq, double der) {
        switch(signo) {
            case Suma:
                return izq + der;
            case Resta:
                return izq - der;
            case Multiplicacion:
                return izq * der;
            case Division:
                return izq / der;
            default:
                return Double.NaN;
        }
    }
}
