package modelo;

public class Viaje {
    private int id;
    private String idConductor;
    private String placaVehiculo;
    private Ciudad origen;
    private Ciudad destino;
    private String estado;
    private Conductor conductor;
    private Vehiculo vehiculo;

    public Viaje() {
        
    }

    public Viaje(Conductor conductor, Vehiculo vehiculo, Ciudad origen, Ciudad destino) {
        this.idConductor = conductor.getIdentificacion();
        this.placaVehiculo = vehiculo.getPlaca();
        this.origen = origen;
        this.destino = destino;
        this.conductor = conductor;
        this.vehiculo = vehiculo;
        this.estado = "pendiente";
    }

    // Getters y setters para id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(String idConductor) {
        this.idConductor = idConductor;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public String toString() {
        return conductor.getIdentificacion() + " - " + vehiculo.getPlaca() + " - " + origen + " -> " + destino;
    }
}