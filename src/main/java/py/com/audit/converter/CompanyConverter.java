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


/**
 *
 * @author crixx
 */
@FacesConverter("companyConverter")
public class CompanyConverter implements Converter {
    
    AuditorModel auditorModel;
    
    public CompanyConverter() {
        super();
        try {
            InitialContext ic = new InitialContext();
            auditorModel = (AuditorModel) ic.lookup("java:module/AuditorModel");
        } catch (NamingException e) {
            e.printStackTrace(System.err);
        }
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0 && !value.equals("null")) {
            try {
                System.out.println("valor --> " + value);
                System.out.println("auditorModel --> " + auditorModel);
                
                Company company = auditorModel.getEM().createNamedQuery("Company.findById", Company.class)
                        .setParameter("id", new Integer(value))
                        .getSingleResult();
                return company;
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
            return String.valueOf(((Company) object).getId());
        } else {
            return null;
        }
    }
}
