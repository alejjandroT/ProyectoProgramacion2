package modelo;

public class Motor {
    private int potencia;
    private boolean activo;

    public Motor(int potencia) {
        this.potencia = potencia;
        this.activo = true;
    }

    public int getPotencia() {
        return potencia;
    }

    public boolean isActivo() {
        return activo;
    }

    public void desactivar() {
        this.activo = false;
    }
    
    public void activar() {
        this.activo = true;
    }

    @Override
    public String toString() {
        return potencia + " HP";
    }
}