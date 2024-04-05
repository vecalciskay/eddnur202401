package hanoi.objects;

public class MainHanoi {
    public static void main(String[] args) {
        int n = 4;
        Hanoi h = new Hanoi(n);
        System.out.println(h);

        h.hacer(0,2,1,n);
    }
}
