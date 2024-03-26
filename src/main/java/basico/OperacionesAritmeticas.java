package basico;

public class OperacionesAritmeticas {
    public static void main(String[] args) {
        OperacionesAritmeticas obj =
                new OperacionesAritmeticas();

        int suma100 = obj.sumarArregloEnteros(100);
        System.out.println("La suma de los primeros 100 es: " + suma100);
    }

    /**
     * Este metodo crea un arreglo de n enteros. Carga en el
     * arreglo la secuencia de enteros desde 1 a n. Y luego
     * devuelve la suma de todos estos enteros.
     *
     * n(n+1) / 2
     * @param n
     * @return
     */
    public int sumarArregloEnteros(int n) {
        int[] arreglo = new int[n];
        for (int i=0; i<n; i++) {
            arreglo[i] = i+1;
        }
        int resultado = 0;
        for (int i = 0; i < n; i++) {
            resultado += arreglo[i];
        }
        return resultado;
    }
}
