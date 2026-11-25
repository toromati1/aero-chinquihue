package modelo;

public class Pasaje extends Servicio {
    private final int numeroAsiento;

    public Pasaje(Cliente cliente, Vuelo vuelo, int numeroAsiento) {
        super(cliente, vuelo);
        this.numeroAsiento = numeroAsiento;
    }
    @Override public double calcularPrecioBase() { return getVuelo().getDestino().getPrecioPasaje(); }
    @Override public String getTipo() { return "Pasaje"; }
    public int getNumeroAsiento() { return numeroAsiento; }
}
