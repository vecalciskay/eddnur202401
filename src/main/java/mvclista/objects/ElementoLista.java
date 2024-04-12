package mvclista.objects;

public class ElementoLista {
    private Integer valor;

    public ElementoLista(int valor) {
        this.valor = valor;
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
}
