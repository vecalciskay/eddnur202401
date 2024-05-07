package cadenas;

import java.util.Iterator;

public class TestLista {
    public static void main(String[] args) {
        Lista<String> a = new Lista();
        a.insert("Hugo");
        a.insert("Paco");
        a.insert("Luis");
        a.insert("McPato");
        a.insert("Donald");
        a.insert("Daisy");

        System.out.println(a);

        System.out.println("Test por indices");
        System.out.println("a[4] = " + a.get(4));
/**
        Iterator<String> iter = a.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }*/

        for(String s: a) {
            System.out.println(s);
        }
    }
}
