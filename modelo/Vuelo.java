package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    /** Peso promedio supuesto por pasajero, para calcular la carga disponible. */
    public static final int PESO_PROMEDIO_PASAJERO_KG = 80;

    private final String codigo;
    private final Destino destino;
    private final Avion avion;
    private LocalDateTime fechaHora;
    private final boolean extraordinario;
    private boolean enServicio = true;
    private final List<Asiento> asientos = new ArrayList<>();
    private final List<Encomienda> encomiendas = new ArrayList<>();

    public Vuelo(String codigo, Destino destino, Avion avion, LocalDateTime fechaHora, boolean extraordinario) {
        this.codigo = codigo;
        this.destino = destino;
        this.avion = avion;
        this.fechaHora = fechaHora;
        this.extraordinario = extraordinario;
        for (int i = 1; i <= avion.getCapacidadPasajeros(); i++) asientos.add(new Asiento(i));
    }

    public int asientosLibres() {
        int n = 0;
        for (Asiento a : asientos) if (!a.isOcupado()) n++;
        return n;
    }

    public int asientosOcupados() { return asientos.size() - asientosLibres(); }

    public Asiento buscarAsiento(int numero) {
        for (Asiento a : asientos) if (a.getNumero() == numero) return a;
        return null;
    }

    /** Ocupa el asiento indicado. Retorna false si no existe, ya está ocupado o el vuelo no está en servicio. */
    public boolean reservarAsiento(int numero) {
        Asiento a = buscarAsiento(numero);
        if (a == null || a.isOcupado() || !enServicio) return false;
        a.setOcupado(true);
        return true;
    }

    public void liberarAsiento(int numero) {
        Asiento a = buscarAsiento(numero);
        if (a != null) a.setOcupado(false);
    }

    public double cargaUsadaKg() {
        double total = asientosOcupados() * PESO_PROMEDIO_PASAJERO_KG;
        for (Encomienda e : encomiendas) total += e.getPesoKg();
        return total;
    }

    public double cargaDisponibleKg() { return avion.getPesoMaximoKg() - cargaUsadaKg(); }

    public boolean agregarEncomienda(Encomienda e) {
        if (!enServicio || e.getPesoKg() > cargaDisponibleKg()) return false;
        encomiendas.add(e);
        return true;
    }

    public String getCodigo() { return codigo; }
    public Destino getDestino() { return destino; }
    public Avion getAvion() { return avion; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public boolean isExtraordinario() { return extraordinario; }
    public boolean isEnServicio() { return enServicio; }
    public void setEnServicio(boolean enServicio) { this.enServicio = enServicio; }
    public List<Asiento> getAsientos() { return asientos; }
    public List<Encomienda> getEncomiendas() { return encomiendas; }
}
