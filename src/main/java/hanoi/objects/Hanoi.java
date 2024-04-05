package hanoi.objects;

public class Hanoi {
    private Torre[] torres;

    public Hanoi(int n) {
        torres = new Torre[3];
        torres[0] = new Torre(n);
        torres[1] = new Torre();
        torres[2] = new Torre();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            result.append(torres[i]).append("\n");
        }
        return result.toString();
    }

    public void hacer(int de, int a, int pp, int n) {
        if (n == 1) {
            Anillo obj = torres[de].getAnillos().pop();
            torres[a].getAnillos().push(obj);
            System.out.println(this);
            return;
        }
        hacer(de, pp, a, n-1);
        hacer(de,a,pp,1);
        hacer(pp,a,de,n-1);
    }
}
