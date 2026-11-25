package vista;

import modelo.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/** Vista por consola (Avance 1). Las GUIs llegan en el Avance 2. */
public class VistaConsola {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private String clp(double monto) { return String.format("$%,.0f", monto).replace(',', '.'); }

    public void titulo(String t) { System.out.println("\n=== " + t + " ==="); }
    public void mensaje(String m) { System.out.println(m); }

    public void mostrarTarifas(List<Destino> destinos) {
        titulo("TARIFAS DESDE " + Aerolinea.ORIGEN);
        System.out.printf("%-15s %12s %14s%n", "Destino", "Pasaje", "Encomienda/kg");
        for (Destino d : destinos)
            System.out.printf("%-15s %12s %14s%n", d.getNombre(), clp(d.getPrecioPasaje()), clp(d.getPrecioKgEncomienda()));
    }

    public void mostrarVuelos(List<Vuelo> vuelos) {
        System.out.printf("%-9s %-14s %-20s %-17s %-8s %-10s%n",
            "Código", "Destino", "Avión", "Salida", "Asientos", "Carga disp.");
        for (Vuelo v : vuelos)
            System.out.printf("%-9s %-14s %-20s %-17s %2d/%-5d %7.0f kg%n",
                v.getCodigo(), v.getDestino(), v.getAvion(), v.getFechaHora().format(FMT),
                v.asientosLibres(), v.getAvion().getCapacidadPasajeros(), v.cargaDisponibleKg());
    }

    public void mostrarServicio(Servicio s) {
        String detalle = (s instanceof Pasaje)
            ? "asiento " + ((Pasaje) s).getNumeroAsiento()
            : ((Encomienda) s).getPesoKg() + " kg";
        System.out.printf("#%d %-10s %-22s %-8s %-14s base %-10s dcto %3.0f%% total %s%n",
            s.getId(), s.getTipo(), s.getCliente().getNombre(), s.getVuelo().getCodigo(),
            detalle, clp(s.calcularPrecioBase()), s.getDescuento() * 100, clp(s.calcularPrecioFinal()));
    }

    public void resumenVentas(LocalDate fecha, List<Servicio> ventas, double total) {
        titulo("RESUMEN DE VENTAS " + fecha);
        for (Servicio s : ventas) mostrarServicio(s);
        System.out.println("TOTAL DEL DÍA: " + clp(total));
    }
}
