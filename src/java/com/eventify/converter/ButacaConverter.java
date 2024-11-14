package com.eventify.converter;

import com.eventify.entity.Butaca;
import com.eventify.service.ButacaService;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author nehem
 */
@FacesConverter(value = "butacaConverter")
public class ButacaConverter implements Converter {

    private ButacaService butacaService;
    
    public ButacaConverter() {
        this.butacaService = CDI.current().select(ButacaService.class).get();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return butacaService.findById(Integer.parseInt(value)).get();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Butaca) {
            return String.valueOf(((Butaca) value).getId());
        } else {
            throw new IllegalArgumentException("El valor no es una instancia de Butaca.");
        }
    }
}
