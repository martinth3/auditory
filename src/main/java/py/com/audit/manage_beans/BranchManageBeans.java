package py.com.audit.manage_beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import py.com.audit.model.GenericEJB;
import py.com.audit.auditory.auditexception.AuditEJBException;
import py.com.audit.entity.BranchOffice;
import py.com.audit.entity.Rol;

/**
 *
 * @author crixx
 */
@Named(value = "branchManageBeans")
@ViewScoped
public class BranchManageBeans implements Serializable {

    @EJB
    private GenericEJB gejb;

    private BranchOffice branchOffice = new BranchOffice();
    private BranchOffice branchOfficeselect = new BranchOffice();
    private BranchOffice branchOfficeAEliminar;
    private List<BranchOffice> listabranchOffice;
    private List<BranchOffice> branchOfficeFiltered;
    private List<FilterMeta> filterBy;
    private List<BranchOffice> selectedbranchOffices;
    private List<BranchOffice> branchOffices;

    public GenericEJB getGejb() {
        return gejb;
    }

    public void setGejb(GenericEJB gejb) {
        this.gejb = gejb;
    }

    public BranchOffice getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(BranchOffice branchOffice) {
        this.branchOffice = branchOffice;
    }

    public BranchOffice getBranchOfficeselect() {
        return branchOfficeselect;
    }

    public void setBranchOfficeselect(BranchOffice branchOfficeselect) {
        this.branchOfficeselect = branchOfficeselect;
    }

    public BranchOffice getBranchOfficeAEliminar() {
        return branchOfficeAEliminar;
    }

    public void setBranchOfficeAEliminar(BranchOffice branchOfficeAEliminar) {
        this.branchOfficeAEliminar = branchOfficeAEliminar;
    }

    public List<BranchOffice> getListabranchOffice() {
         if (listabranchOffice == null || listabranchOffice.isEmpty()) {
            try {
                listabranchOffice = gejb.getEM().createNamedQuery("BranchOffice.findAll", BranchOffice.class).getResultList();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return listabranchOffice;
    }

    public void setListabranchOffice(List<BranchOffice> listabranchOffice) {
        this.listabranchOffice = listabranchOffice;
    }

    public List<BranchOffice> getBranchOfficeFiltered() {
        try {
            branchOfficeFiltered = gejb.getEM().createNamedQuery("BranchOffice.findById", BranchOffice.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return branchOfficeFiltered;
    }

    public void setBranchOfficeFiltered(List<BranchOffice> branchOfficeFiltered) {
        this.branchOfficeFiltered = branchOfficeFiltered;
    }

    public List<BranchOffice> getSelectedbranchOffices() {
        return selectedbranchOffices;
    }

    public void setSelectedbranchOffices(List<BranchOffice> selectedbranchOffices) {
        this.selectedbranchOffices = selectedbranchOffices;
    }

    public List<BranchOffice> getBranchOffices() {
        return branchOffices;
    }

    public void setBranchOffices(List<BranchOffice> branchOffices) {
        this.branchOffices = branchOffices;
    }
   
    public List<FilterMeta> getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(List<FilterMeta> filterBy) {
        this.filterBy = filterBy;
    }

    @PostConstruct
    public void init() {
        filterBy = new ArrayList<>();
        listabranchOffice = new ArrayList<>();
        getListabranchOffice();
    }

    public void guardarRol() throws AuditEJBException {
        try {
             branchOffice.setStatus(true);
            gejb.insert(branchOffice);
            listabranchOffice = null;
            getListabranchOffice();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("branch agregado con exito"));
            PrimeFaces.current().executeScript("PF('managebranchDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dtBranch");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha registrado el dato"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtBranch");
        }

    }

    public void editarRol() throws AuditEJBException {
        try {
            gejb.update(selectedbranchOffices);
            listabranchOffice = null;
            getListabranchOffice();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("branch editada con exito"));
            PrimeFaces.current().executeScript("PF('managebranchDialog_u').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dtBranch");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha editado el dato"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtBranch");
        }
    }

    public void eliminarRol() throws AuditEJBException {
        try {
            gejb.delete(branchOfficeAEliminar);
            listabranchOffice = null;
            getListabranchOffice();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("branch eliminada con exito"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtBranch");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha podido realizar el borrado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtBranch");
        }
    }

    public void cancelar() {
        branchOffice = new BranchOffice();
        branchOfficeselect = new BranchOffice();
        branchOfficeAEliminar = null;
    }
} 
