package com.eventify.dao;

import com.eventify.entity.CategoriaButaca;
import com.eventify.entity.CategoriaEvento;
import com.eventify.entity.EstadoEvento;
import com.eventify.entity.EstadoPago;
import com.eventify.entity.Genero;
import com.eventify.entity.Rol;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author nehem
 */
public interface CatalogoDao {
    List<Rol> findAllRoles();
    Optional<Rol> findRolByNombre(String nombre);

    List<Genero> findAllGeneros();

    List<EstadoEvento> findAllEstadosEvento();

    List<CategoriaEvento> findAllCategoriasEvento();

    List<CategoriaButaca> findAllCategoriasButaca();

    List<EstadoPago> findAllEstadosPago();
}
