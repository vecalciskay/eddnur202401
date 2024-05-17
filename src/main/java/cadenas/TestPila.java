package cadenas;

public class TestPila {
    public static void main(String[] args) {
        Pila<Integer> a = new Pila<>();
        a.add(1);
        a.add(5);
        a.add(4);
        a.add(10);
        System.out.println(a);
        a.pop();
        System.out.println(a);
    }
}
