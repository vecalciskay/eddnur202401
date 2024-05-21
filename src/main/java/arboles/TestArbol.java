package arboles;

public class TestArbol {
    public static void main(String[] args) {
        Arbol<String> arbol = new Arbol<>();

        arbol.add("D", null);
        arbol.add("R","D");
        arbol.add("W","D");
        arbol.add("G","R");
        arbol.add("H","R");
        arbol.add("T","R");

        System.out.println(arbol);
    }
}
