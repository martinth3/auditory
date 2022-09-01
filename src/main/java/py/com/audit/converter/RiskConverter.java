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
import py.com.audit.entity.Risk;
import py.com.audit.model.GenericEJB;


/**
 *
 * @author crixx
 */
@FacesConverter("riskConverter")
public class RiskConverter implements Converter {
    
    GenericEJB genericEJB;
    
    public RiskConverter() {
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
                Risk risk = genericEJB.getEM().createNamedQuery("Risk.findById", Risk.class)
                        .setParameter("id", new Integer(value))
                        .getSingleResult();
                return risk;
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
            return String.valueOf(((Risk) object).getId());
        } else {
            return null;
        }
    }
}
