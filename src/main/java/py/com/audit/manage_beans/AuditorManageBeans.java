package py.com.audit.manage_beans;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.Visibility;
import py.com.audit.model.AuditorModel;
import py.com.audit.auditexception.AuditEJBException;
import py.com.audit.entity.Auditor;
import py.com.audit.entity.Company;

/**
 *
 * @author crixx
 */
@Named(value = "auditorManageBeans")
@ViewScoped
public class AuditorManageBeans implements Serializable {

    @EJB
    private AuditorModel auditorModel;

    private Auditor auditor = new Auditor();
    private Auditor auditorselect = new Auditor();
    private Auditor auditorAeliminar;
    private List<Auditor> listaAuditor;
    private List<Company> listaCompanys;
    private List<Auditor> auditorFiltered;
    private List<FilterMeta> filterBy;
    private List<Auditor> selectedAuditor;

    private List<Auditor> auditors;
    private Company companySelect;

    public AuditorModel getAuditorModel() {
        return auditorModel;
    }

    public void setAuditorModel(AuditorModel auditorModel) {
        this.auditorModel = auditorModel;
    }

    public Auditor getAuditor() {
        return auditor;
    }

    public void setAuditor(Auditor auditor) {
        this.auditor = auditor;
    }

    public Auditor getAuditorselect() {
        return auditorselect;
    }

    public void setAuditorselect(Auditor auditorselect) {
        this.auditorselect = auditorselect;
    }

    public Auditor getAuditorAeliminar() {
        return auditorAeliminar;
    }

    public void setAuditorAeliminar(Auditor auditorAeliminar) {
        this.auditorAeliminar = auditorAeliminar;
    }

    public List<Auditor> getListaAuditor() {
        return listaAuditor;
    }

    public void setListaAuditor(List<Auditor> listaAuditor) {
        this.listaAuditor = listaAuditor;
    }

    public Company getCompanySelect() {
        return companySelect;
    }

    public void setCompanySelect(Company companySelect) {
        this.companySelect = companySelect;
    }

    public List<Company> getListaCompanys() {
        return listaCompanys;
    }

    public void setListaCompanys(List<Company> listaCompanys) {
        this.listaCompanys = listaCompanys;
    }

    public List<Auditor> getAuditorFiltered() {
        return auditorFiltered;
    }

    public void setAuditorFiltered(List<Auditor> auditorFiltered) {
        this.auditorFiltered = auditorFiltered;
    }

    public List<FilterMeta> getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(List<FilterMeta> filterBy) {
        this.filterBy = filterBy;
    }

    public List<Auditor> getSelectedAuditor() {
        return selectedAuditor;
    }

    public void setSelectedAuditor(List<Auditor> selectedAuditor) {
        this.selectedAuditor = selectedAuditor;
    }

    public List<Auditor> getAuditors() {
        return auditors;
    }

    public void setAuditors(List<Auditor> auditors) {
        this.auditors = auditors;
    }

    @PostConstruct
    public void init() {
        filterBy = new ArrayList<>();

        listaAuditor = new ArrayList<>();

        listaCompanys = new ArrayList<>();

        if (listaAuditor == null || listaAuditor.isEmpty()) {
            System.out.println("Cargando Lista de lista Auditor");
            try {
                listaAuditor = auditorModel.listarAuditor();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(AuditorManageBeans.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (listaCompanys == null || listaCompanys.isEmpty()) {
            System.out.println("Cargando Lista de lista compañia");
            try {
                listaCompanys = auditorModel.listarAllCompany();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(AuditorManageBeans.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void guardarAuditor() throws AuditEJBException {
        try {
            System.out.println("Entro en el metodo");
            auditorModel.insertarAuditor(auditor);
            listaAuditor = auditorModel.listarAuditor();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Auditor agregada con exito"));
            PrimeFaces.current().executeScript("PF('manageauditorDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dtAuditor");
        } catch (Exception e) {
            e.printStackTrace(System.err);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha registrado el dato"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtAuditor");
            return;
        }
        cancelar();
    }

    public void cancelar() {
        System.out.println("Cancela la puerquesa");
        auditor = new Auditor();
        auditor.setCompany(new Company());
        auditorselect = new Auditor();
        auditorAeliminar = null;
    }

    public void editarAuditor() throws AuditEJBException {
        try {
            auditorModel.EditAuditor(auditorselect);
            listaAuditor = auditorModel.listarAuditor();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Auditor editado con exito"));
            PrimeFaces.current().executeScript("PF('manageauditorDialog_u').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dtAuditor");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha editado el dato"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtAuditor");
        }
    }

    public void eliminarAuditor() throws AuditEJBException {

        try {
            auditorModel.DeleteAuditor(auditorAeliminar);
            listaAuditor = auditorModel.listarAuditor();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Auditor eliminada con exito"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtAuditor");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha podido realizar el borrado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtAuditor");
        }
    }

    public void mostrarDialog() {
        //agregar logica si es necesario
        PrimeFaces.current().executeScript("PF('wdialogo').show();");
    }

    public void onRowToggle(ToggleEvent event) {
        if (event.getVisibility() == Visibility.VISIBLE) {
            Auditor company = (Auditor) event.getData();
//            if (company.getName() == null) {
//                product.setOrders(orderService.getOrders((int) (Math.random() * 10)));
//            }
        }
    }
}
