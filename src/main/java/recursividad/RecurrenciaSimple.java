package recursividad;

public class RecurrenciaSimple {
    public static void main(String[] args) {
        RecurrenciaSimple obj = new RecurrenciaSimple();
        //obj.recursivo();

        int s = obj.suma(5);
        System.out.println("La suma de los primeros 5 numeros es " + s);
    }

    /**
     * Prueba de escritorio para numero n = 5
     * PE - suma(5)
     *     return 5 + (4 + (3 + (2 + 1)))
     *
     * PE - suma(4)
     *    return 4 + (3 + (2 + 1))
     *
     * PE - suma(3)
     *    return 3 + (2 + 1)
     *
     * PE - suma(2)
     *    return 2 + 1
     *
     * PE - suma(1)
     *    return 1
     * @param n
     * @return
     */
    private int suma(int n) {
        if (n == 1)
            return 1;
        return n + suma(n-1);
    }

    private void recursivo() {
        recursivo();
    }
}
