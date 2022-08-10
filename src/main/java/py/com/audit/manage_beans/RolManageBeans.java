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
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import py.com.audit.model.GenericEJB;
import py.com.audit.auditory.auditexception.AuditEJBException;
import py.com.audit.entity.Rol;

/**
 *
 * @author crixx
 */
@Named(value = "rolManageBeans")
@ViewScoped
public class RolManageBeans implements Serializable {

    @EJB
    private GenericEJB gejb;

    private Rol rol = new Rol();
    private Rol rolselect = new Rol();
    private Rol rolAEliminar;
    private List<Rol> listaRol;
    private List<Rol> rolFiltered;
    private List<FilterMeta> filterBy;
    private List<Rol> selectedRoles;
    private List<Rol> roles;

    public GenericEJB getGejb() {
        return gejb;
    }

    public void setGejb(GenericEJB gejb) {
        this.gejb = gejb;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Rol getRolselect() {
        return rolselect;
    }

    public void setRolselect(Rol rolselect) {
        this.rolselect = rolselect;
    }

    public Rol getRolAEliminar() {
        return rolAEliminar;
    }

    public void setRolAEliminar(Rol rolAEliminar) {
        this.rolAEliminar = rolAEliminar;
    }

    public List<Rol> getListaRol() {
        if (listaRol == null || listaRol.isEmpty()) {
            try {
                listaRol = gejb.getEM().createNamedQuery("Rol.findAll", Rol.class).getResultList();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    public List<Rol> getRolFiltered() {
        try {
            rolFiltered = gejb.getEM().createNamedQuery("Rol.findById", Rol.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        
        return rolFiltered;
    }

    public void setRolFiltered(List<Rol> rolFiltered) {
        this.rolFiltered = rolFiltered;
    }

    public List<Rol> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<Rol> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
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
        listaRol = new ArrayList<>();
        getListaRol();
    }

    public void guardarRol() throws AuditEJBException {
        try {
            
             rol.setStatus(true);
            gejb.insert(rol);
            listaRol = null;
            getListaRol();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Rol agregado con exito"));
            PrimeFaces.current().executeScript("PF('managerolDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dtRol");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha registrado el dato"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtRol");
        }

    }

    public void editarRol() throws AuditEJBException {
        try {
            gejb.update(rolselect);
            listaRol = null;
            getListaRol();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("rol editada con exito"));
            PrimeFaces.current().executeScript("PF('managerolDialog_u').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dtRol");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha editado el dato"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtRol");
        }
    }

    public void eliminarRol() throws AuditEJBException {
        try {
            gejb.delete(rolAEliminar);
            listaRol = null;
            getListaRol();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("rol eliminada con exito"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtRol");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha podido realizar el borrado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtRol");
        }
    }

    public void cancelar() {
        rol = new Rol();
        rolselect = new Rol();
        rolAEliminar = null;
    }
}
