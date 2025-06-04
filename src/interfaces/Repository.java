package interfaces;

import java.util.List;

public interface Repository<T> {

    void save(T entity);

    void update(String id, T entity);

    void delete(String id);

    T findById(String id);

    List<T> findAll();
}
