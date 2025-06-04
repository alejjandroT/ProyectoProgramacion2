package servicios;

import Repositorio.ViajeRepository;
import Servicios.ConductorServiceImpl;
import Servicios.VehiculoServiceImpl;
import interfaces.ViajeService;
import modelo.Viaje;
import modelo.Ciudad;

import java.util.List;
import modelo.Conductor;
import modelo.Vehiculo;

public class ViajeServiceImpl implements ViajeService {
    private final ViajeRepository repository;
    private final ConductorServiceImpl conductorService;
    private final VehiculoServiceImpl vehiculoService;

    public ViajeServiceImpl(ViajeRepository repository, ConductorServiceImpl conductorService, VehiculoServiceImpl vehiculoService) {
        this.repository = repository;
        this.conductorService = conductorService;
        this.vehiculoService = vehiculoService;
    }

    @Override
    public void registrarViaje(String idConductor, String placaVehiculo, Ciudad origen, Ciudad destino) {
        Conductor conductor = conductorService.buscarConductor(idConductor);
        Vehiculo vehiculo = vehiculoService.buscarVehiculo(placaVehiculo);
        if (conductor != null && vehiculo != null && vehiculo.getMotor().isActivo()) {
            Viaje viaje = new Viaje(conductor, vehiculo, origen, destino);
            repository.save(viaje);
        } else {
            throw new IllegalStateException("No se puede registrar el viaje: conductor o vehículo no encontrado, o motor inactivo.");
        }
    }

    @Override
    public void modificarViaje(String id, Viaje viaje) {
        repository.update(id, viaje);
    }

    @Override
    public void eliminarViaje(String id) {
        repository.delete(id);
    }

    @Override
    public Viaje buscarViaje(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Viaje> listarViajes() {
        return repository.findAll();
    }

    @Override
    public void arrancarViaje(String id) {
        Viaje viaje = buscarViaje(id);
        if (viaje != null && "pendiente".equals(viaje.getEstado())) {
            viaje.setEstado("en curso");
            repository.update(id, viaje);
        } else {
            throw new IllegalStateException("El viaje no puede arrancar: no existe o no está pendiente.");
        }
    }

    @Override
    public void detenerViaje(String id) {
        Viaje viaje = buscarViaje(id);
        if (viaje != null && "en curso".equals(viaje.getEstado())) {
            viaje.setEstado("finalizado");
            repository.update(id, viaje);
        } else {
            throw new IllegalStateException("El viaje no puede detenerse: no existe o no está en curso.");
        }
    }

    @Override
    public ConductorServiceImpl getConductorService() {
        return conductorService;
    }

    @Override
    public VehiculoServiceImpl getVehiculoService() {
        return vehiculoService;
    }
}