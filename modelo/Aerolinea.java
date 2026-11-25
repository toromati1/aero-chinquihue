package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Raíz del modelo: guarda destinos, vuelos, clientes y registro de ventas. */
public class Aerolinea {
    public static final String ORIGEN = "Aeródromo La Paloma (Puerto Montt)";

    private final String nombre = "AeroChinquihue";
    private boolean estadoEmergencia = false;
    private final List<Destino> destinos = new ArrayList<>();
    private final List<Vuelo> vuelos = new ArrayList<>();
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Servicio> ventas = new ArrayList<>();
    private final Gerente gerente;
    private final Asistente asistente;

    public Aerolinea(Gerente gerente, Asistente asistente) {
        this.gerente = gerente;
        this.asistente = asistente;
    }

    public Destino buscarDestino(String nombre) {
        for (Destino d : destinos) if (d.getNombre().equalsIgnoreCase(nombre)) return d;
        return null;
    }

    public Cliente buscarCliente(String rut) {
        for (Cliente c : clientes) if (c.getRut().equals(rut)) return c;
        return null;
    }

    public Vuelo buscarVuelo(String codigo) {
        for (Vuelo v : vuelos) if (v.getCodigo().equals(codigo)) return v;
        return null;
    }

    public List<Vuelo> vuelosDisponibles(Destino destino, LocalDate fecha) {
        List<Vuelo> r = new ArrayList<>();
        for (Vuelo v : vuelos)
            if (v.getDestino() == destino && v.getFechaHora().toLocalDate().equals(fecha) && v.isEnServicio())
                r.add(v);
        return r;
    }

    public void registrarVenta(Servicio s) { ventas.add(s); }

    public List<Servicio> ventasDelDia(LocalDate fecha) {
        List<Servicio> r = new ArrayList<>();
        for (Servicio s : ventas) if (s.getFechaRegistro().toLocalDate().equals(fecha)) r.add(s);
        return r;
    }

    public double totalVentasDelDia(LocalDate fecha) {
        double t = 0;
        for (Servicio s : ventasDelDia(fecha)) t += s.calcularPrecioFinal();
        return t;
    }

    public String getNombre() { return nombre; }
    public boolean isEstadoEmergencia() { return estadoEmergencia; }
    public void setEstadoEmergencia(boolean e) { this.estadoEmergencia = e; }
    public List<Destino> getDestinos() { return destinos; }
    public List<Vuelo> getVuelos() { return vuelos; }
    public List<Cliente> getClientes() { return clientes; }
    public List<Servicio> getVentas() { return ventas; }
    public Gerente getGerente() { return gerente; }
    public Asistente getAsistente() { return asistente; }
}
