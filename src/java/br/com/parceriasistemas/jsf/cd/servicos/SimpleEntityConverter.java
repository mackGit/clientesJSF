/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.servicos;

import br.com.parceriasistemas.jsf.cd.dao.CidadeDao;
import br.com.parceriasistemas.jsf.cd.model.Cidade;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author matheus
 */

// Pega a String do nome cidade pela a ID
@FacesConverter(forClass = Cidade.class)
public class SimpleEntityConverter implements Converter {

     @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Cidade) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Cidade) {
            Cidade entity= (Cidade) value;
            if (entity != null && entity instanceof Cidade && entity.getIdCidade()!= null) {
                uiComponent.getAttributes().put( entity.getIdCidade().toString(), entity);
                return entity.getIdCidade().toString();
            }
        }
        return "";
    }
}