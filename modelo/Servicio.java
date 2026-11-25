package modelo;

import java.time.LocalDateTime;

/** Servicio vendible por la aerolínea: Pasaje o Encomienda. */
public abstract class Servicio {
    private static int contador = 1;

    private final int id;
    private final Cliente cliente;
    private final Vuelo vuelo;
    private final LocalDateTime fechaRegistro;
    private MedioPago medioPago;
    private double descuento; // entre 0.0 y 1.0

    protected Servicio(Cliente cliente, Vuelo vuelo) {
        this.id = contador++;
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.fechaRegistro = LocalDateTime.now();
    }

    public abstract double calcularPrecioBase();
    public abstract String getTipo();

    public double calcularPrecioFinal() { return calcularPrecioBase() * (1.0 - descuento); }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Vuelo getVuelo() { return vuelo; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public MedioPago getMedioPago() { return medioPago; }
    public void setMedioPago(MedioPago medioPago) { this.medioPago = medioPago; }
    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) {
        if (descuento < 0 || descuento > 1) throw new IllegalArgumentException("Descuento fuera de rango");
        this.descuento = descuento;
    }
}
