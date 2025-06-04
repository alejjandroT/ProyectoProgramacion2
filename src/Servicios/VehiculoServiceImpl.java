package Servicios;

import interfaces.VehiculoService;
import interfaces.ConductorService;
import interfaces.Repository;
import modelo.Vehiculo;
import modelo.Conductor;

import java.util.List;

public class VehiculoServiceImpl implements VehiculoService {
    private Repository<Vehiculo> repository;
    private  ConductorService conductorService;

    public VehiculoServiceImpl(Repository<Vehiculo> repository, ConductorService conductorService) {
        this.repository = repository;
        this.conductorService = conductorService;
    }
    
    @Override
    public void registrarVehiculo(Vehiculo vehiculo) {
        repository.save(vehiculo);
    }

    @Override
    public void registrarVehiculo(String placa, int potencia, String idConductor) {
        Conductor conductor = conductorService.buscarConductor(idConductor);
        if (conductor != null) {
            Vehiculo vehiculo = new Vehiculo(placa, potencia, conductor);
            repository.save(vehiculo);
        }
    }

    @Override
    public void modificarVehiculo(Vehiculo vehiculo) {
        repository.update("",vehiculo);
    }

    @Override
    public void eliminarVehiculo(String placa) {
        repository.delete(placa);
    }

    @Override
    public List<Vehiculo> listarVehiculos() {
        return repository.findAll();
    }

    @Override
    public List<String> listarPlacas() {
        return repository.findAll().stream()
            .map(Vehiculo::getPlaca)
            .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public ConductorService getConductorService() {
        return conductorService;
    }

  @Override
    public Vehiculo buscarVehiculo(String placa) {
        return repository.findAll().stream()
            .filter(vehiculo -> vehiculo.getPlaca().equals(placa))
            .findFirst()
            .orElse(null);
    }
}