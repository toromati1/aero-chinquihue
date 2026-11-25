package modelo;

public class Asiento {
    private final int numero;
    private boolean ocupado;

    public Asiento(int numero) { this.numero = numero; }
    public int getNumero() { return numero; }
    public boolean isOcupado() { return ocupado; }
    public void setOcupado(boolean ocupado) { this.ocupado = ocupado; }
}
