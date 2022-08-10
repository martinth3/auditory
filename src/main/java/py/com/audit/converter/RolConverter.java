/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.audit.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import py.com.audit.model.AuditorModel;
import py.com.audit.entity.Company;
import py.com.audit.entity.Rol;
import py.com.audit.entity.User;
import py.com.audit.model.GenericEJB;


/**
 *
 * @author crixx
 */
@FacesConverter("rolConverter")
public class RolConverter implements Converter {
    
    GenericEJB genericEJB;
    
    public RolConverter() {
        super();
        try {
            InitialContext ic = new InitialContext();
            genericEJB = (GenericEJB) ic.lookup("java:module/GenericEJB");
        } catch (NamingException e) {
            e.printStackTrace(System.err);
        }
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0 && !value.equals("null")) {
            try {
                System.out.println("valor --> " + value);
                System.out.println("auditorModel --> " + genericEJB);
                
                Rol rol = genericEJB.getEM().createNamedQuery("Rol.findById", Rol.class)
                        .setParameter("id", new Integer(value))
                        .getSingleResult();
                return rol;
            } catch (NumberFormatException e) {
                e.printStackTrace(System.err);
            }
        } else {
            return null;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((Rol) object).getId());
        } else {
            return null;
        }
    }
}
