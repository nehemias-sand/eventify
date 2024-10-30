package com.eventify.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nehem
 */
@Embeddable
public class ReservaButacaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_reserva")
    private int idReserva;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_butaca")
    private int idButaca;

    public ReservaButacaPK() {
    }

    public ReservaButacaPK(int idReserva, int idButaca) {
        this.idReserva = idReserva;
        this.idButaca = idButaca;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdButaca() {
        return idButaca;
    }

    public void setIdButaca(int idButaca) {
        this.idButaca = idButaca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idReserva;
        hash += (int) idButaca;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaButacaPK)) {
            return false;
        }
        ReservaButacaPK other = (ReservaButacaPK) object;
        if (this.idReserva != other.idReserva) {
            return false;
        }
        return this.idButaca == other.idButaca;
    }

    @Override
    public String toString() {
        return "com.eventify.entity.ReservaButacaPK[ idReserva=" + idReserva + ", idButaca=" + idButaca + " ]";
    }
    
}
