package modelo;

public abstract class Persona {
    private final String rut;
    private String nombre;

    protected Persona(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }
    public String getRut() { return rut; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    @Override public String toString() { return nombre + " (" + rut + ")"; }
}
