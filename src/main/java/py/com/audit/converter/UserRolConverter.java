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
import py.com.audit.entity.User;
import py.com.audit.model.GenericEJB;


/**
 *
 * @author crixx
 */
@FacesConverter("userRolConverter")
public class UserRolConverter implements Converter {
    
    GenericEJB genericEJB;
    
    public UserRolConverter() {
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
                
                User user = genericEJB.getEM().createNamedQuery("User.findByIdUser", User.class)
                        .setParameter("idUser", new Integer(value))
                        .getSingleResult();
                return user;
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
            return String.valueOf(((User) object).getIdUser());
        } else {
            return null;
        }
    }
}
