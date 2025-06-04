package servicios;

import modelo.Ciudad;
import Repositorio.CiudadRepository;

import java.util.List;

public class CiudadServiceImpl {
    private final CiudadRepository ciudadRepository;

    public CiudadServiceImpl(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public void registrarCiudad(Ciudad ciudad) {
        ciudadRepository.save(ciudad);
    }

    public void modificarCiudad(int id, Ciudad ciudad) {
        ciudadRepository.update(id, ciudad);
    }

    public void eliminarCiudad(int id) {
        ciudadRepository.delete(id);
    }

    public Ciudad buscarCiudad(int id) {
        return ciudadRepository.findById(id);
    }

    public List<Ciudad> listarCiudades() {
        return ciudadRepository.findAll();
    }
}