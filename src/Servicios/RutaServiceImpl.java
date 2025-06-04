package servicios;

import modelo.Ruta;
import Repositorio.RutaRepository;

import java.util.List;

public class RutaServiceImpl {

    private final RutaRepository rutaRepository;

    public RutaServiceImpl(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

    public void registrarRuta(Ruta ruta) {
        rutaRepository.save(ruta);
    }

    public void modificarRuta(int id, Ruta ruta) {
        rutaRepository.update(id, ruta);
    }

    public void eliminarRuta(int id) {
        rutaRepository.delete(id);
    }

    public Ruta buscarRuta(int id) {
        return rutaRepository.findById(id);
    }

    public List<Ruta> listarRutas() {
        return rutaRepository.findAll();
    }
}
