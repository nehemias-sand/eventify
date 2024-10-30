package com.eventify.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author nehem
 */
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

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
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CategoriaEvento idCategoria;
    
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstadoEvento idEstado;
    
    @JoinColumn(name = "id_instalalacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Instalacion idInstalalacion;
    
    @JoinColumn(name = "id_organizador", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Organizador idOrganizador;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvento")
    private List<Reserva> reservaList;

    public Evento() {
    }

    public Evento(Integer id) {
        this.id = id;
    }

    public Evento(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public CategoriaEvento getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CategoriaEvento idCategoria) {
        this.idCategoria = idCategoria;
    }

    public EstadoEvento getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoEvento idEstado) {
        this.idEstado = idEstado;
    }

    public Instalacion getIdInstalalacion() {
        return idInstalalacion;
    }

    public void setIdInstalalacion(Instalacion idInstalalacion) {
        this.idInstalalacion = idInstalalacion;
    }

    public Organizador getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(Organizador idOrganizador) {
        this.idOrganizador = idOrganizador;
    }

    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.eventify.entity.Evento[ id=" + id + " ]";
    }
    
}
