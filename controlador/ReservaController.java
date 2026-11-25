package controlador;

import modelo.*;

/** Gestiona reservas de pasajes y envío de encomiendas (rol Asistente). */
public class ReservaController {
    private final Aerolinea aerolinea;

    public ReservaController(Aerolinea aerolinea) { this.aerolinea = aerolinea; }

    /** Descuento automático: emergencia (pasaje gratis) o cliente frecuente (10%). */
    public double descuentoAutomatico(Cliente c, boolean esPasaje) {
        if (esPasaje && aerolinea.isEstadoEmergencia() && c.isPersonalEmergencia()) return 1.0;
        if (c.esFrecuente()) return 0.10;
        return 0.0;
    }

    /** Reserva el asiento y devuelve el pasaje con su precio; queda pendiente de pago. Null si no hay disponibilidad. */
    public Pasaje reservarPasaje(Cliente cliente, Vuelo vuelo, int numeroAsiento) {
        if (!vuelo.reservarAsiento(numeroAsiento)) return null;
        Pasaje p = new Pasaje(cliente, vuelo, numeroAsiento);
        p.setDescuento(descuentoAutomatico(cliente, true));
        return p;
    }

    /** Valida la carga disponible y devuelve la encomienda con su precio; queda pendiente de pago. Null si no cabe. */
    public Encomienda enviarEncomienda(Cliente cliente, Vuelo vuelo, double pesoKg, String descripcion) {
        Encomienda e = new Encomienda(cliente, vuelo, pesoKg, descripcion);
        if (!vuelo.agregarEncomienda(e)) return null;
        e.setDescuento(descuentoAutomatico(cliente, false));
        return e;
    }

    /** Confirma el pago e inscribe la venta en el registro. */
    public void confirmarPago(Servicio s, MedioPago medio) {
        s.setMedioPago(medio);
        aerolinea.registrarVenta(s);
        if (s instanceof Pasaje) s.getCliente().incrementarVuelos();
    }

    public void cancelarPasaje(Pasaje p) { p.getVuelo().liberarAsiento(p.getNumeroAsiento()); }
}
