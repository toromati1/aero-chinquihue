package modelo;

public abstract class Avion {
    private final String modelo;
    private final int capacidadPasajeros;
    private final int pesoMaximoKg;

    protected Avion(String modelo, int capacidadPasajeros, int pesoMaximoKg) {
        this.modelo = modelo;
        this.capacidadPasajeros = capacidadPasajeros;
        this.pesoMaximoKg = pesoMaximoKg;
    }
    public String getModelo() { return modelo; }
    public int getCapacidadPasajeros() { return capacidadPasajeros; }
    public int getPesoMaximoKg() { return pesoMaximoKg; }
    @Override public String toString() { return modelo; }
}
