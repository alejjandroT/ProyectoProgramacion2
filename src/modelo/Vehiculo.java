package modelo;

import interfaces.Arrancable;
import interfaces.Detenible;

public class Vehiculo implements Arrancable, Detenible {

    private String placa;
    private final Motor motor;
    private Conductor conductor;

    public Vehiculo(String placa, int potencia, Conductor conductor) {
        this.placa = placa;
        this.motor = new Motor(potencia);
        this.conductor = conductor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getPotencia() {
        return motor.isActivo() ? motor.getPotencia() : 0;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public Motor getMotor() {
        return motor;
    }

    public void destruir() {
        motor.desactivar();
    }

    @Override
    public String toString() {
        return placa + " - Potencia: " + getPotencia() + " HP - Conductor: " + (conductor != null ? conductor.getIdentificacion() : "Sin conductor");
    }

    @Override
    public void arrancar() {
        if (motor.isActivo()) {
            System.out.println("El vehículo con placa " + placa + " ha arrancado.");
        } else {
            System.out.println("No se puede arrancar: el motor está inactivo.");
        }
    }

    @Override
    public void detener() {
        if (motor.isActivo()) {
            System.out.println("El vehículo con placa " + placa + " se ha detenido.");
        }
    }
}
