package cadenas;

public class TestListaDoble {
    public static void main(String[] args) {
        ListaDoble<String> a = new ListaDoble<>();
        a.add("Hugo");
        a.add("Paco");
        a.add("Luis");
        a.add("McPato");
        a.add("Donald");
        a.add("Daisy");
        System.out.println(a);
        System.out.println(a.get(0));
        a.remove("McPato");
        System.out.println(a);
        a.delete(0);
        System.out.println(a);
        a.delete(3);
        System.out.println(a);
        a.delete(1);
        System.out.println(a);
        a.delete(0);
        System.out.println(a);
        a.delete(0);
        System.out.println(a);
    }
}
