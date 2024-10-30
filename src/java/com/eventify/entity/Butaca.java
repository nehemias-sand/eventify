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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author nehem
 */
@Entity
@Table(name = "butaca")
public class Butaca implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 25)
    @Column(name = "codigo")
    private String codigo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "butaca")
    private List<ReservaButaca> reservaButacaList;
    
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CategoriaButaca idCategoria;
    
    @JoinColumn(name = "id_instalacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Instalacion idInstalacion;

    public Butaca() {
    }

    public Butaca(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<ReservaButaca> getReservaButacaList() {
        return reservaButacaList;
    }

    public void setReservaButacaList(List<ReservaButaca> reservaButacaList) {
        this.reservaButacaList = reservaButacaList;
    }

    public CategoriaButaca getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CategoriaButaca idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Instalacion getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(Instalacion idInstalacion) {
        this.idInstalacion = idInstalacion;
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
        if (!(object instanceof Butaca)) {
            return false;
        }
        Butaca other = (Butaca) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.eventify.entity.Butaca[ id=" + id + " ]";
    }
    
}
