package com.eventify.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author nehem
 */
@Entity
@Table(name = "instalacion")
public class Instalacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "direccion")
    private String direccion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "telefono")
    private String telefono;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidad")
    private int capacidad;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "url_croquis")
    private String urlCroquis;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstalalacion")
    private List<Evento> eventoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstalacion")
    private List<Butaca> butacaList;

    public Instalacion() {
    }

    public Instalacion(Integer id) {
        this.id = id;
    }

    public Instalacion(Integer id, String nombre, String direccion, String telefono, int capacidad, String urlCroquis) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.capacidad = capacidad;
        this.urlCroquis = urlCroquis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getUrlCroquis() {
        return urlCroquis;
    }

    public void setUrlCroquis(String urlCroquis) {
        this.urlCroquis = urlCroquis;
    }

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public List<Butaca> getButacaList() {
        return butacaList;
    }

    public void setButacaList(List<Butaca> butacaList) {
        this.butacaList = butacaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instalacion)) {
            return false;
        }
        Instalacion other = (Instalacion) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.eventify.entity.Instalacion[ id=" + id + " ]";
    }
    
}
