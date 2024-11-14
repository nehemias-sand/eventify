package com.eventify.model;

import com.eventify.entity.Butaca;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.DualListModel;

/**
 *
 * @author nehem
 */
public class CategoriaButacas implements Serializable {

    private String nombre;
    private DualListModel<Butaca> butacas;

    public CategoriaButacas(String nombre, List<Butaca> butacasDisponibles) {
        this.nombre = nombre;
        this.butacas = new DualListModel<>(butacasDisponibles, new ArrayList<>());
    }

    // Getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DualListModel<Butaca> getButacas() {
        return butacas;
    }

    public void setButacas(DualListModel<Butaca> butacas) {
        this.butacas = butacas;
    }
}
