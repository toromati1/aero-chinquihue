package modelo;

public class Cliente extends Persona {
    private String telefono;
    private int vuelosRealizados;
    private boolean personalEmergencia;

    public Cliente(String rut, String nombre, String telefono, int vuelosRealizados, boolean personalEmergencia) {
        super(rut, nombre);
        this.telefono = telefono;
        this.vuelosRealizados = vuelosRealizados;
        this.personalEmergencia = personalEmergencia;
    }
    /** Clientes con más de 10 vuelos tienen 10% de descuento. */
    public boolean esFrecuente() { return vuelosRealizados > 10; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public int getVuelosRealizados() { return vuelosRealizados; }
    public void incrementarVuelos() { vuelosRealizados++; }
    public boolean isPersonalEmergencia() { return personalEmergencia; }
    public void setPersonalEmergencia(boolean p) { this.personalEmergencia = p; }
}
