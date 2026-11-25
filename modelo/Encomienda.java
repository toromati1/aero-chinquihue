package modelo;

public class Encomienda extends Servicio {
    private final double pesoKg;
    private final String descripcion;

    public Encomienda(Cliente cliente, Vuelo vuelo, double pesoKg, String descripcion) {
        super(cliente, vuelo);
        this.pesoKg = pesoKg;
        this.descripcion = descripcion;
    }
    @Override public double calcularPrecioBase() { return pesoKg * getVuelo().getDestino().getPrecioKgEncomienda(); }
    @Override public String getTipo() { return "Encomienda"; }
    public double getPesoKg() { return pesoKg; }
    public String getDescripcion() { return descripcion; }
}
