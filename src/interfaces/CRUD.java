package interfaces;

import java.util.List;

public interface CRUD<T> {

    void registrar(T entidad);

    void modificar(int index, T entidad);

    void eliminar(int index);

    List<T> listar();
}
