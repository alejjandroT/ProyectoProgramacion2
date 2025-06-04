package interfaces;

import java.util.List;
import modelo.Vehiculo;

public interface VehiculoService {

  void registrarVehiculo(Vehiculo vehiculo);
    void registrarVehiculo(String placa, int potencia, String idConductor);
    void modificarVehiculo(Vehiculo vehiculo);
    void eliminarVehiculo(String placa);
    List<Vehiculo> listarVehiculos();
    List<String> listarPlacas();
    ConductorService getConductorService();
    Vehiculo buscarVehiculo(String placa);
}
