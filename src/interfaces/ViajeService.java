package interfaces;

import Servicios.ConductorServiceImpl;
import Servicios.VehiculoServiceImpl;
import java.util.List;
import modelo.Ciudad;
import modelo.Viaje;

public interface ViajeService {

    void registrarViaje(String idConductor, String placaVehiculo, Ciudad origen, Ciudad destino);

    void modificarViaje(String id, Viaje viaje);

    void eliminarViaje(String id);

    Viaje buscarViaje(String id);

    List<Viaje> listarViajes();

    void arrancarViaje(String id);

    void detenerViaje(String id);

    ConductorServiceImpl getConductorService();

    VehiculoServiceImpl getVehiculoService();
}
