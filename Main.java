import controlador.*;
import modelo.*;
import vista.VistaConsola;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/** Punto de entrada: crea los objetos, puebla los vuelos y ejecuta una demostración del flujo. */
public class Main {
    public static void main(String[] args) throws Exception {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        Aerolinea aero = PoblamientoController.crearAerolinea();
        VistaConsola vista = new VistaConsola();
        ReservaController reservas = new ReservaController(aero);
        GerenteController gerente = new GerenteController(aero);

        vista.mostrarTarifas(aero.getDestinos());

        // 1) Asistente revisa disponibilidad a Chaitén el 25-11-2026
        LocalDate fecha = LocalDate.of(2026, 11, 25);
        Destino chaiten = aero.buscarDestino("Chaitén");
        List<Vuelo> disponibles = aero.vuelosDisponibles(chaiten, fecha);
        vista.titulo("VUELOS DISPONIBLES A " + chaiten + " (" + fecha + ")");
        vista.mostrarVuelos(disponibles);

        // 2) Reserva de pasaje (cliente frecuente -> 10%)
        Vuelo vuelo = disponibles.get(0);
        Cliente pedro = aero.buscarCliente("13.456.789-0");
        Pasaje pasaje = reservas.reservarPasaje(pedro, vuelo, 3);
        vista.titulo("COTIZACIÓN PASAJE");
        vista.mostrarServicio(pasaje);
        reservas.confirmarPago(pasaje, MedioPago.DEBITO);

        // 3) Encomienda de 12 kg
        Cliente maria = aero.buscarCliente("12.345.678-9");
        Encomienda enc = reservas.enviarEncomienda(maria, vuelo, 12, "Repuestos de bote");
        vista.titulo("COTIZACIÓN ENCOMIENDA");
        vista.mostrarServicio(enc);
        reservas.confirmarPago(enc, MedioPago.EFECTIVO);

        // 4) Estado de emergencia: personal de emergencias no paga pasaje
        gerente.declararEstadoEmergencia(true);
        Cliente carla = aero.buscarCliente("14.567.890-1");
        Pasaje gratis = reservas.reservarPasaje(carla, vuelo, 4);
        vista.titulo("PASAJE EN ESTADO DE EMERGENCIA");
        vista.mostrarServicio(gratis);
        reservas.confirmarPago(gratis, MedioPago.EFECTIVO);
        gerente.declararEstadoEmergencia(false);

        // 5) Gerente: descuento excepcional y vuelo extraordinario
        Cliente luis = aero.buscarCliente("15.678.901-2");
        Pasaje conDcto = reservas.reservarPasaje(luis, vuelo, 5);
        gerente.aplicarDescuentoExcepcional(conDcto, 50);
        reservas.confirmarPago(conDcto, MedioPago.TRANSFERENCIA);
        Vuelo extra = gerente.crearVueloExtraordinario(aero.buscarDestino("Palqui"), new Cessna310(),
            LocalDateTime.of(2026, 11, 25, 18, 30));
        vista.titulo("VUELO EXTRAORDINARIO CREADO");
        vista.mostrarVuelos(List.of(extra));

        // 6) Resumen del gerente
        LocalDate hoy = LocalDate.now();
        vista.resumenVentas(hoy, gerente.resumenVentas(hoy), gerente.totalVentas(hoy));
        vista.mensaje("\nVuelos en servicio: " + gerente.vuelosEnServicio().size());
    }
}
