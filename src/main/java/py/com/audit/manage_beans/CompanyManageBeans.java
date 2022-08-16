package py.com.audit.manage_beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.swing.event.ChangeEvent;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import py.com.audit.model.GenericEJB;
import py.com.audit.auditexception.AuditEJBException;
import py.com.audit.entity.Company;

/**
 *
 * @author crixx
 */
@Named(value = "companyManageBeans")
@ViewScoped
public class CompanyManageBeans implements Serializable {

    @EJB
    private GenericEJB gejb;

    private Company company = new Company();
    private Company companyselect = new Company();
    private Company companyAEliminar;
    private List<Company> listaCompany;
    private List<Company> companyFiltered;
    private List<FilterMeta> filterBy;
    private List<Company> selectedCompanys;
    private List<Company> companys;
    private int digitoVerificador;

    public List<Company> getCompanys() {
        return companys;
    }

    public void setCompanys(List<Company> companys) {
        this.companys = companys;
    }

    public int getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(int digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public List<Company> getSelectedCompanys() {
        return selectedCompanys;
    }

    public void setSelectedCompanys(List<Company> selectedCompanys) {
        this.selectedCompanys = selectedCompanys;
    }

    public Company getCompanyselect() {
        return companyselect;
    }

    public void setCompanyselect(Company companyselect) {
        this.companyselect = companyselect;
    }

    public List<FilterMeta> getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(List<FilterMeta> filterBy) {
        this.filterBy = filterBy;
    }

    public List<Company> getselectedCompanys() {
        return selectedCompanys;
    }

    public void setselectedCompanys(List<Company> selectedCompanys) {
        this.selectedCompanys = selectedCompanys;
    }

    public List<Company> getCompanyFiltered() throws AuditEJBException {
        try {
            companyFiltered = gejb.getEM().createNamedQuery("Company.findAll", Company.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return companyFiltered;
    }

    public void setCompanyFiltered(List<Company> companyFiltered) {
        this.companyFiltered = companyFiltered;
    }

    public List<Company> getListaCompany() {
        if (listaCompany == null || listaCompany.isEmpty()) {
            try {
                listaCompany = gejb.getEM().createNamedQuery("Company.findAll", Company.class).getResultList();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return listaCompany;
    }

    public void setListaCompany(List<Company> listaCompany) {
        this.listaCompany = listaCompany;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompanyAEliminar() {
        return companyAEliminar;
    }

    public void setCompanyAEliminar(Company companyAEliminar) {
        this.companyAEliminar = companyAEliminar;
    }

    @PostConstruct
    public void init() {
        filterBy = new ArrayList<>();
        listaCompany = new ArrayList<>();
        getListaCompany();
    }

    public void guardarCompany() throws AuditEJBException {
        try {
            company.setDateCreate(new Date());
            company.setStatus(true);
            company.setRuc(company.getRuc()+"-"+digitoVerificador);
            gejb.insert(company);
            listaCompany = null;
            getListaCompany();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Compañia agregada con exito"));
            PrimeFaces.current().executeScript("PF('managecompanyDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dtCompany");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha registrado el dato"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtCompany");
        }

    }

    public void editarCompany() throws AuditEJBException {
        try {
            gejb.update(companyselect);
            listaCompany = null;
            getListaCompany();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Compañia editada con exito"));
            PrimeFaces.current().executeScript("PF('managecompanyDialog_u').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dtCompany");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha editado el dato"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtCompany");
        }
    }

    public void eliminarCompany() throws AuditEJBException {
        try {
            gejb.delete(companyAEliminar);
            listaCompany = null;
            getListaCompany();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Compañia eliminada con exito"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtCompany");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha podido realizar el borrado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtCompany");
        }
    }

    public void cancelar() {
        company = new Company();
        companyselect = new Company();
        companyAEliminar = null;
        digitoVerificador = 0;
    }

    public void rucChangeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null && !(event.getOldValue() == null && event.getNewValue().toString().equals(""))) {
           digitoVerificador =  traerDigitoVerificador(event.getNewValue().toString(), 11);
        }
    }
        
    public Integer traerDigitoVerificador(String numero, int basemax){
        String numeroAl = "";
        int valorAscii;
        int k = 2, i= 0;
        int total = 0;
        int resto = 0;
        
        numero = numero.toUpperCase();
        for(i=0;i<numero.length();i++){
            valorAscii = numero.charAt(i);
            
            if (!(valorAscii >= 48 && valorAscii <= 57))
                numeroAl = numeroAl + (char) valorAscii;
            else
		numeroAl = numeroAl + numero.charAt(i);
        }
        
        for(i=numeroAl.length() - 1; i > -1; i--){
            if(k > basemax) k = 2;
            
            total = total + (Character.getNumericValue(numeroAl.charAt(i)) * k);
            k++;
        }
               
	resto = total % 11;

	if (resto > 0)
            return 11 - resto;
	else
            return 0;
    }

}
