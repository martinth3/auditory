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
import py.com.audit.entity.User;
import py.com.audit.entity.UserRol;

/**
 *
 * @author crixx
 */
@Named(value = "userrolManageBeans")
@ViewScoped
public class UserRolManageBeans implements Serializable {

    @EJB
    private GenericEJB gejb;

    private UserRol userrol = new UserRol();
    private UserRol userrolselect = new UserRol();
    private UserRol userrolAEliminar;
    private List<UserRol> listaUserRol;
    private List<UserRol> userrolFiltered;
    private List<FilterMeta> filterBy;
    private List<UserRol> selectedUserRoles;
    private List<UserRol> userrole;
    private User userSelect;
    private List<User> listaUsers;
     private Rol rolSelect;
    private List<Rol> listaroles;

    public GenericEJB getGejb() {
        return gejb;
    }

    public void setGejb(GenericEJB gejb) {
        this.gejb = gejb;
    }
    public User getUserSelect() {
        return userSelect;
    }

    public Rol getRolSelect() {
        return rolSelect;
    }

    public void setRolSelect(Rol rolSelect) {
        this.rolSelect = rolSelect;
    }

    public List<Rol> getListaroles() {
          if (listaroles == null || listaroles.isEmpty()) {
            try {
                listaroles = gejb.getEM().createNamedQuery("Rol.findAll", Rol.class).getResultList();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        
        
        return listaroles;
    }

    public void setListaroles(List<Rol> listaroles) {
        this.listaroles = listaroles;
    }

    public List<User> getListaUsers() {
        
           if (listaUsers == null || listaUsers.isEmpty()) {
            try {
                listaUsers = gejb.getEM().createNamedQuery("User.findAll", User.class).getResultList();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        
        
        return listaUsers;
    }

    public void setListaUsers(List<User> listaUsers) {
        this.listaUsers = listaUsers;
    }

    public void setUserSelect(User userSelect) {
        this.userSelect = userSelect;
    }
    

    public UserRol getUserrol() {
        return userrol;
    }

    public void setUserrol(UserRol userrol) {
        this.userrol = userrol;
    }

    public UserRol getUserrolselect() {
        return userrolselect;
    }

    public void setUserrolselect(UserRol userrolselect) {
        this.userrolselect = userrolselect;
    }

    public UserRol getUserrolAEliminar() {
        return userrolAEliminar;
    }

    public void setUserrolAEliminar(UserRol userrolAEliminar) {
        this.userrolAEliminar = userrolAEliminar;
    }

    public List<UserRol> getListaUserRol() {
         if (listaUserRol == null || listaUserRol.isEmpty()) {
            try {
                listaUserRol = gejb.getEM().createNamedQuery("UserRol.findAll", UserRol.class).getResultList();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return listaUserRol;
    }

    public void setListaUserRol(List<UserRol> listaUserRol) {
        this.listaUserRol = listaUserRol;
    }

    public List<UserRol> getUserrolFiltered() {
           try {
            userrolFiltered = gejb.getEM().createNamedQuery("UserRol.findById", UserRol.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
 
        return userrolFiltered;
    }

    public void setUserrolFiltered(List<UserRol> userrolFiltered) {
        this.userrolFiltered = userrolFiltered;
    }

    public List<UserRol> getSelectedUserRoles() {
        return selectedUserRoles;
    }

    public void setSelectedUserRoles(List<UserRol> selectedUserRoles) {
        this.selectedUserRoles = selectedUserRoles;
    }

    public List<UserRol> getUserrole() {
        return userrole;
    }

    public void setUserrole(List<UserRol> userrole) {
        this.userrole = userrole;
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
        listaUserRol = new ArrayList<>();
        getListaUserRol();
        
    }

    public void guardarUserRol() throws AuditEJBException {
        System.out.println("Entro en metodo guardar");
        try {
            //userrol.setStatus(true);
            //userrol.setUser(user);
            userrolselect.setStatus(true);
            gejb.insert(userrolselect);
            listaUserRol = null;
            getListaUserRol();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Rol agregado con exito"));
            PrimeFaces.current().executeScript("PF('managerolDialog_r').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dtRol");

        } catch (Exception e) {
            System.out.println("Entro en catch");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha registrado el dato"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtRol");
            e.printStackTrace();
        }

    }

    public void editarUserRol() throws AuditEJBException {
        try {
             gejb.update(userrolselect);
            listaUserRol = null;
            getListaUserRol();
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
            gejb.delete(userrolAEliminar);
            listaUserRol = null;
            getListaUserRol();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("rol eliminada con exito"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtRol");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha podido realizar el borrado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtRol");
        }
    }

    public void cancelar() {
        userrol = new UserRol();
        userrolselect = new UserRol();
        userrolAEliminar = null;
    }
}
