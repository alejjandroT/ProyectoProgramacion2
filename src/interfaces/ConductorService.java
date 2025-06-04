package interfaces;

import java.util.List;
import modelo.Conductor;

public interface ConductorService {

    void registrarConductor(Conductor conductor);
    void modificarConductor(Conductor conductor);
    void eliminarConductor(String identificacion);
    List<Conductor> listarConductores();
    List<String> listarIdentificaciones();
    List<String> listarNombresCompletos();
    Conductor buscarConductor(String identificacion);
    
    
}
