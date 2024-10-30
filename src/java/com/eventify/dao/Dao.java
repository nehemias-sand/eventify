package com.eventify.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author nehem
 * @param <T>
 */
public interface Dao<T> {   
    List<T> findAll();
    
    Optional<T> findById(int id);
    
    void save(T entity);
    
    void update(T entity);
    
    void delete(T entity);
}
