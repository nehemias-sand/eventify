package com.eventify.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author nehem
 */
@Entity
@Table(name = "reserva_butaca")
public class ReservaButaca implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected ReservaButacaPK reservaButacaPK;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private BigDecimal precio;
    
    @JoinColumn(name = "id_butaca", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Butaca butaca;
    
    @JoinColumn(name = "id_reserva", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reserva reserva;

    public ReservaButaca() {
    }

    public ReservaButaca(ReservaButacaPK reservaButacaPK) {
        this.reservaButacaPK = reservaButacaPK;
    }

    public ReservaButaca(int idReserva, int idButaca) {
        this.reservaButacaPK = new ReservaButacaPK(idReserva, idButaca);
    }

    public ReservaButacaPK getReservaButacaPK() {
        return reservaButacaPK;
    }

    public void setReservaButacaPK(ReservaButacaPK reservaButacaPK) {
        this.reservaButacaPK = reservaButacaPK;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Butaca getButaca() {
        return butaca;
    }

    public void setButaca(Butaca butaca) {
        this.butaca = butaca;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservaButacaPK != null ? reservaButacaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaButaca)) {
            return false;
        }
        ReservaButaca other = (ReservaButaca) object;
        return !((this.reservaButacaPK == null && other.reservaButacaPK != null) || (this.reservaButacaPK != null && !this.reservaButacaPK.equals(other.reservaButacaPK)));
    }

    @Override
    public String toString() {
        return "com.eventify.entity.ReservaButaca[ reservaButacaPK=" + reservaButacaPK + " ]";
    }
    
}
