package cadenas;

public class TestListaDoble {
    public static void main(String[] args) {
        ListaDoble<String> a = new ListaDoble<>();
        a.insert("Hugo");
        a.insert("Paco");
        a.insert("Luis");
        a.insert("McPato");
        a.insert("Donald");
        a.insert("Daisy");
        System.out.println(a);
    }
}
