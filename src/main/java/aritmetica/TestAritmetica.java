package aritmetica;

public class TestAritmetica {
    public static void main(String[] args) {
        // (2 * 3) + ( 5 / (8 - 7))
        ArbolAritmetico a = new ArbolAritmetico();
        Operacion op = new Operacion(Signo.Suma);
        a.add(LadoSigno.Izquierda, op,"1",null);

        // 2*3
        op = new Operacion(Signo.Multiplicacion);
        a.add(LadoSigno.Izquierda, op,"2","1");
        Numero n = new Numero(2);
        a.add(LadoSigno.Izquierda, n,"3","2");
        n = new Numero(3);
        a.add(LadoSigno.Derecha, n,"4","2");

        // 5 / (8-7)
        op = new Operacion(Signo.Division);
        a.add(LadoSigno.Derecha,op,"5","1");
        n = new Numero(5);
        a.add(LadoSigno.Izquierda, n,"6","5");
        op = new Operacion(Signo.Resta);
        a.add(LadoSigno.Derecha, op,"7","5");
        n = new Numero(8);
        a.add(LadoSigno.Izquierda, n,"8","7");
        n = new Numero(7);
        a.add(LadoSigno.Derecha,n,"9","7");

        System.out.println(a + " = " + a.evaluar());

        //ArbolAritmetico b = new ArbolAritmetico("(2 * 3) + ( 5 / (8 - 7))");
        //System.out.println(b + " = " + b.evaluar());
    }
}
