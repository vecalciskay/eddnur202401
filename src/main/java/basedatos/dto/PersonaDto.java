package basedatos.dto;

public class PersonaDto {
    private int id;
    private String nombre;
    private int alturacm;
    private float pesokg;

    public PersonaDto(int id, String nombre, int altura, float peso) {
        this.id = id;
        this.nombre = nombre;
        this.alturacm = altura;
        this.pesokg = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAlturacm() {
        return alturacm;
    }

    public void setAlturacm(int alturacm) {
        this.alturacm = alturacm;
    }

    public float getPesokg() {
        return pesokg;
    }

    public void setPesokg(float pesokg) {
        this.pesokg = pesokg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id).append(", ");
        sb.append("nombre: ").append(nombre).append(", ");
        sb.append("altura: ").append(alturacm).append(", ");
        sb.append("peso: ").append(pesokg);

        return sb.toString();
    }
}
