package hanoi.objects;

import hanoi.observer.ObservadorHanoi;

public class MainHanoi {
    public static void main(String[] args) {
        int n = 3;
        Hanoi h = new Hanoi(n);
        ObservadorHanoi o = new ObservadorHanoi();
        h.addObservadorHanoi(o);
        System.out.println(h);

        h.hacer(0,2,1,n);
    }
}
