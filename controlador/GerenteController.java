package controlador;

import modelo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Operaciones exclusivas del gerente. */
public class GerenteController {
    private final Aerolinea aerolinea;

    public GerenteController(Aerolinea aerolinea) { this.aerolinea = aerolinea; }

    public List<Servicio> resumenVentas(LocalDate fecha) { return aerolinea.ventasDelDia(fecha); }

    public double totalVentas(LocalDate fecha) { return aerolinea.totalVentasDelDia(fecha); }

    public List<Vuelo> vuelosEnServicio() {
        List<Vuelo> r = new ArrayList<>();
        for (Vuelo v : aerolinea.getVuelos()) if (v.isEnServicio()) r.add(v);
        return r;
    }

    /** Agrega un vuelo extraordinario por motivos urgentes. */
    public Vuelo crearVueloExtraordinario(Destino destino, Avion avion, LocalDateTime fechaHora) {
        String codigo = String.format("EX-%04d", aerolinea.getVuelos().size() + 1);
        Vuelo v = new Vuelo(codigo, destino, avion, fechaHora, true);
        aerolinea.getVuelos().add(v);
        return v;
    }

    /** Descuento excepcional de 0 a 100%. */
    public void aplicarDescuentoExcepcional(Servicio s, int porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) throw new IllegalArgumentException("El descuento debe estar entre 0 y 100");
        s.setDescuento(porcentaje / 100.0);
    }

    public void declararEstadoEmergencia(boolean activo) { aerolinea.setEstadoEmergencia(activo); }
}
