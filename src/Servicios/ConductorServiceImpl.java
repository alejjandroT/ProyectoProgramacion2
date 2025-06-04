package Servicios;

import Repositorio.ConductorRepository;
import interfaces.ConductorService;
import interfaces.Repository;
import modelo.Conductor;

import java.util.List;
import java.util.stream.Collectors;

public class ConductorServiceImpl implements ConductorService {
    private ConductorRepository conductorRepository;

  public ConductorServiceImpl(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

  @Override
    public void registrarConductor(Conductor conductor) {
        conductorRepository.save(conductor);
    }

  @Override
    public void modificarConductor(Conductor conductor) {
        conductorRepository.update("",conductor);
    }

    @Override
    public void eliminarConductor(String identificacion) {
        conductorRepository.delete(identificacion);
    }

    

    @Override
    public List<Conductor> listarConductores() {
        return conductorRepository.findAll();
    }

    @Override
    public List<String> listarIdentificaciones() {
        return conductorRepository.findAll().stream()
            .map(Conductor::getIdentificacion)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<String> listarNombresCompletos() {
        return conductorRepository.findAll().stream()
            .map(conductor -> conductor.getNombre() + " " + conductor.getApellido())
            .collect(Collectors.toList());
    }

   @Override
    public Conductor buscarConductor(String identificacion) {
        return conductorRepository.findAll().stream()
            .filter(conductor -> conductor.getIdentificacion().equals(identificacion))
            .findFirst()
            .orElse(null);
    }
}
