package py.com.audit.auditory.process.Audit.manage_beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.Visibility;
import py.com.audit.auditory.process.Audit.model.CompanyModel;
import py.com.audit.auditory.process.auditexception.AuditEJBException;
import py.com.audit.auditory.process.entity.Company;

/**
 *
 * @author crixx
 */
@Named(value = "companyManageBeans")
@ViewScoped
public class CompanyManageBeans implements Serializable {

    @EJB
    private CompanyModel companyModel;
    
    private Company company = new Company();
    private Company companyselect = new Company();
    private Company companyAEliminar;
    private List<Company> listaCompany;
    private List<Company> companyFiltered;
    private List<FilterMeta> filterBy;
    private List<Company> selectedCompanys;
    private List<Company> companys;
    private String ruc;

    public List<Company> getCompanys() {
        return companys;
    }

    public void setCompanys(List<Company> companys) {
        this.companys = companys;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public List<Company> getSelectedCompanys() {
        return selectedCompanys;
    }

    public void setSelectedCompanys(List<Company> selectedCompanys) {
        System.out.println("selectedCompanys --> " + selectedCompanys);
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
        companyFiltered = companyModel.listarCompany();
        return companyFiltered;
    }

    public void setCompanyFiltered(List<Company> companyFiltered) {
        this.companyFiltered = companyFiltered;
    }

    public CompanyModel getCompanyModel() {
        if (companyModel == null) {
            companyModel = new CompanyModel();
        }
        return companyModel;
    }

    public void setCompanyModel(CompanyModel companyModel) {
        this.companyModel = companyModel;
    }

    public List<Company> getListaCompany() {
        return listaCompany;
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

        if (listaCompany == null || listaCompany.isEmpty()) {
            System.out.println("Cargando Lista de lista Company");

            try {
                listaCompany = companyModel.listarCompany();

            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(CompanyManageBeans.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void guardarCompany() throws AuditEJBException {
        try {
            companyModel.insertarCompany(company);
            listaCompany = companyModel.listarCompany();
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
            companyModel.EditCompany(companyselect);
            listaCompany = companyModel.listarCompany();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Compañia editada con exito"));
            PrimeFaces.current().executeScript("PF('managecompanyDialog_u').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dtCompany");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha editado el dato"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtCompany");
        }
    }

    public void eliminarCompany(String ruc) throws AuditEJBException {
        System.out.println("llama aca?");
        System.out.println("ruc" + ruc);
        try {
            companyModel.DeleteCompany(ruc);
            listaCompany = companyModel.listarCompany();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Compañia eliminada con exito"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtCompany");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha podido realizar el borrado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtCompany");
        }
    }
    
    public void eliminarCompany() throws AuditEJBException {
        try {
            companyModel.DeleteCompany(companyAEliminar);
            listaCompany = companyModel.listarCompany();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Compañia eliminada con exito"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtCompany");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha podido realizar el borrado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtCompany");
        }
    }

   

    public void cancelar() {
        company = new Company();
    }

    public void mostrarDialog() {
        //agregar logica si es necesario

        PrimeFaces.current().executeScript("PF('wdialogo').show();");
    }

    public void onRowToggle(ToggleEvent event) {
        if (event.getVisibility() == Visibility.VISIBLE) {
            Company company = (Company) event.getData();
//            if (company.getName() == null) {
//                product.setOrders(orderService.getOrders((int) (Math.random() * 10)));
//            }
        }
    }

}
