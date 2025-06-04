package modelo;

public class Ruta {
    private int idRuta;
    private Ciudad origen;
    private Ciudad destino;
    private double distancia;
    private double costo;

    public Ruta(Ciudad origen, Ciudad destino, double distancia, double costo) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.costo = costo;
    }

    // Getters y setters
    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
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

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}