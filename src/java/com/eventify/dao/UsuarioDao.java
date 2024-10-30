package com.eventify.dao;

import com.eventify.entity.Usuario;
import java.util.Optional;

/**
 *
 * @author nehem
 */
public interface UsuarioDao extends Dao<Usuario>{
    Optional<Usuario> findByEmail(String email);    
}
