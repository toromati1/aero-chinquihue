package modelo;

public class Destino {
    private final String nombre;
    private final int precioPasaje;       // por persona
    private final int precioKgEncomienda; // por kg

    public Destino(String nombre, int precioPasaje, int precioKgEncomienda) {
        this.nombre = nombre;
        this.precioPasaje = precioPasaje;
        this.precioKgEncomienda = precioKgEncomienda;
    }
    public String getNombre() { return nombre; }
    public int getPrecioPasaje() { return precioPasaje; }
    public int getPrecioKgEncomienda() { return precioKgEncomienda; }
    @Override public String toString() { return nombre; }
}
