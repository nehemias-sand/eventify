/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eventify.converter;

import com.eventify.service.OrganizadorService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import com.eventify.entity.Organizador;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Membreño
 */
@FacesConverter("organizadorListConverter")
@ApplicationScoped
public class OrganizadorListConverter implements Converter{
    
    @Inject
    private OrganizadorService organizadorService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            int id = Integer.parseInt(value);
            return organizadorService.findById(id);
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "ID de organizador inválido."));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Organizador) {
            Organizador organizador = (Organizador) value;
            return String.valueOf(organizador.getId());
        }
        return "";
    }
    
}
