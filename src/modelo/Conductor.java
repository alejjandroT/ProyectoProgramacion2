package modelo;

public class Conductor {
    private String nombre;
    private String apellido;
    private String identificacion;

    public Conductor(String nombre, String apellido, String identificacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    @Override
    public String toString() {
        return identificacion + " - " + nombre + " " + apellido;
    }
}