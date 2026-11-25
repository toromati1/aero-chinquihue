package controlador;

import modelo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/** Crea los objetos iniciales: destinos, vuelos diarios y clientes de ejemplo. */
public class PoblamientoController {

    private static final String[][] TARIFAS = {
        // nombre, pasaje, kg encomienda, avión (0=C310, 1=C208, 2=LET410)
        {"Cochamó", "20000", "5000", "0"},
        {"Puelo Bajo", "20000", "5000", "0"},
        {"Contao", "20000", "5000", "0"},
        {"Rio Negro", "25000", "6000", "1"},
        {"Pupelde", "25000", "6000", "1"},
        {"Chepu", "30000", "8000", "1"},
        {"Ayacara", "30000", "8000", "1"},
        {"Pillán", "40000", "12000", "1"},
        {"Reñihue", "40000", "12000", "2"},
        {"Isla Quenac", "40000", "12000", "2"},
        {"Palqui", "40000", "12000", "2"},
        {"Chaitén", "50000", "15000", "2"},
        {"Santa Bárbara", "50000", "15000", "2"}
    };

    private static final LocalTime[] HORARIOS = {LocalTime.of(8, 30), LocalTime.of(15, 0)};

    public static Aerolinea crearAerolinea() {
        Aerolinea a = new Aerolinea(
            new Gerente("11.111.111-1", "Gerente AeroChinquihue"),
            new Asistente("22.222.222-2", "Asistente de Reservas"));
        poblarDestinos(a);
        poblarVuelos(a, LocalDate.of(2026, 11, 25), 7);
        poblarClientes(a);
        return a;
    }

    public static void poblarDestinos(Aerolinea a) {
        for (String[] t : TARIFAS)
            a.getDestinos().add(new Destino(t[0], Integer.parseInt(t[1]), Integer.parseInt(t[2])));
    }

    /** Vuelos diarios: 2 horarios por destino durante {@code dias} días desde {@code inicio}. */
    public static void poblarVuelos(Aerolinea a, LocalDate inicio, int dias) {
        int n = 1;
        for (int d = 0; d < dias; d++) {
            LocalDate fecha = inicio.plusDays(d);
            for (int i = 0; i < TARIFAS.length; i++) {
                Destino dest = a.getDestinos().get(i);
                for (LocalTime h : HORARIOS) {
                    String codigo = String.format("AC-%04d", n++);
                    a.getVuelos().add(new Vuelo(codigo, dest, crearAvion(TARIFAS[i][3]),
                        LocalDateTime.of(fecha, h), false));
                }
            }
        }
    }

    private static Avion crearAvion(String tipo) {
        switch (tipo) {
            case "0": return new Cessna310();
            case "1": return new Cessna208Caravan();
            default:  return new Let410();
        }
    }

    public static void poblarClientes(Aerolinea a) {
        a.getClientes().add(new Cliente("12.345.678-9", "María Soto", "+56 9 1111 1111", 3, false));
        a.getClientes().add(new Cliente("13.456.789-0", "Pedro Alvarado", "+56 9 2222 2222", 14, false));
        a.getClientes().add(new Cliente("14.567.890-1", "Carla Núñez", "+56 9 3333 3333", 6, true));
        a.getClientes().add(new Cliente("15.678.901-2", "Luis Barría", "+56 9 4444 4444", 0, false));
    }
}
