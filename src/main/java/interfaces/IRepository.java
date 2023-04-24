package interfaces;

import java.io.Serializable;
import java.util.List;

public interface IRepository<T, TReturn>{
    TReturn save(T entity);

    TReturn update(T entity);

    TReturn delete(T entity);

    T findById(int id);

    List<T> findAll();
}