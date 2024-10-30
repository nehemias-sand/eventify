package com.eventify.service;

import com.eventify.dao.CatalogoDao;
import com.eventify.dao.UsuarioDao;
import com.eventify.entity.Rol;
import com.eventify.entity.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author nehem
 */
@ApplicationScoped
public class UsuarioService {
    
    @Inject
    private UsuarioDao usuarioDAO;
    
    @Inject
    private CatalogoDao catalogoDAO;
    
    public boolean login(String email, String password) {
        try {
            Optional<Usuario> usuario = findByEmail(email);
            return usuario.isPresent() && usuario.get().getPassword().equals(hashPassword(password));
        } catch (Exception e) {
            return false;
        }
    }
    
    public Optional<Usuario> findByEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }
    
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
    
    public List<Usuario> findAll() {
        return usuarioDAO.findAll();
    }
    
    public Optional<Usuario> findById(int id) {
        return usuarioDAO.findById(id);
    }
    
    public void create(Usuario usuario) {     
        Optional<Rol> rol  = catalogoDAO.findRolByNombre("ADMIN");
        usuario.setIdRol(rol.get());
        
        usuarioDAO.save(usuario);
    }
    
    public void update(Usuario usuario) {
        usuarioDAO.update(usuario);
    }
    
    public void changeEstado(int id) {
        Optional<Usuario> usuarioOpt = findById(id);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setEstado(!usuario.getEstado());
            usuarioDAO.update(usuario);
        }
    }
}
