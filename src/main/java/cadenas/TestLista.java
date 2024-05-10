package cadenas;

import javax.swing.*;

public class TestLista {
    public static void main(String[] args) {
        Lista<String> a = new Lista();
//        System.out.println(a);
        a.add("Hugo");
//        System.out.println(a);
//        a.delete(0);
//        System.out.println(a);
//        System.out.println(a);
        a.insert("Paco");
//        System.out.println(a);
        a.add("Luis");
//        System.out.println(a);
        a.insert("McPato");
//        System.out.println(a);
        a.add("Donald");
//        System.out.println(a);
        a.insert("Daisy");
        System.out.println(a);
//        a.remove("Paco");
//        System.out.println(a);

        int seleccion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese una opción\n1.- Agregar un elemento\n2.- Eliminar un elemento\n0.- Salir"));
        while (seleccion != 0) {
            switch (seleccion) {
                case 1: //insertar
                    String elemento = JOptionPane.showInputDialog("Ingrese un texto para agregar");
                    a.add(elemento);
                    break;
                case 2: //eliminar
                    elemento = JOptionPane.showInputDialog("Ingrese el elemento para eliminar");
                    a.remove(elemento);
                    break;
            }

            System.out.println(a);
            seleccion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese una opción\n1.- Agregar un elemento\n2.- Eliminar un elemento\n0.- Salir"));
        }
//        a.delete(5);
//        System.out.println(a);
//        a.poll();
//        System.out.println(a);

//        a.delete(3);
//        System.out.println(a);
//        a.delete(7);
//        System.out.println(a);

//        System.out.println("Test por indices");
//        System.out.println("a[4] = " + a.get(4));

//        Iterator<String> iter = a.iterator();
//        while(iter.hasNext()) {
//            System.out.println(iter.next());
//        }

//        for (String s : a) {
//            System.out.println(s);
//        }
    }
}
