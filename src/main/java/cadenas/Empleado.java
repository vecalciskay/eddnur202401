package cadenas;

public class Empleado implements Comparable<Empleado> {
    private String nombres;
    private String apellidos;
    private int salario;
    private int edad;

    public Empleado(String nombres, String apellidos, int salario, int edad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.salario = salario;
        this.edad = edad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "(" + nombres + " " + apellidos + ", Salario: " + salario + ", Edad: " + edad + ")";
    }

    @Override
    public int compareTo(Empleado o) {
        int comparacion = apellidos.compareTo(o.apellidos);
        if (comparacion == 0) {
            return nombres.compareTo(o.nombres);
        }
        return comparacion;
//        if (this.edad > o.edad) {
//            return 1;
//        }
//        if (this.edad < o.edad) {
//            return -1;
//        }
//        return 0;
    }
}
