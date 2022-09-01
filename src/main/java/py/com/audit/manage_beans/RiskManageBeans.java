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
import py.com.audit.auditexception.AuditEJBException;
import py.com.audit.entity.Company;
import py.com.audit.entity.Risk;
import py.com.audit.entity.User;

/**
 *
 * @author crixx
 */
@Named(value = "riskManageBeans")
@ViewScoped
public class RiskManageBeans implements Serializable {

    @EJB
    private GenericEJB gejb;

    private Risk risk = new Risk();
    private Risk riskselect = new Risk();
    private Risk riskAEliminar;
    private List<Risk> listarisk ;
    private List<Risk> riskFiltered;
    private List<FilterMeta> filterBy;
    private List<Risk> selectedrisks;
    private List<Risk> risks;

    public GenericEJB getGejb() {
        return gejb;
    }

    public void setGejb(GenericEJB gejb) {
        this.gejb = gejb;
    }

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    public Risk getRiskselect() {
        return riskselect;
    }

    public void setRiskselect(Risk riskselect) {
        this.riskselect = riskselect;
    }

    public Risk getRiskAEliminar() {
        return riskAEliminar;
    }

    public void setRiskAEliminar(Risk riskAEliminar) {
        this.riskAEliminar = riskAEliminar;
    }

    public List<Risk> getListarisk() {
        if (listarisk == null || listarisk.isEmpty()) {
            try {

                listarisk =  gejb.getEM().createNamedQuery("Risk.findByUserCreate", Risk.class)
                        .setParameter("userCreate",43).getResultList();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return listarisk;
    }

    public List<Risk> getRiskFiltered() {
        try {
            riskFiltered = gejb.getEM().createNamedQuery("Risk.findById", Risk.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return riskFiltered;
    }

    public void setRiskFiltered(List<Risk> riskFiltered) {
        this.riskFiltered = riskFiltered;
    }

    public List<Risk> getSelectedrisks() {
        return selectedrisks;
    }

    public void setSelectedrisks(List<Risk> selectedrisks) {
        this.selectedrisks = selectedrisks;
    }

    public List<Risk> getRisks() {
        return risks;
    }

    public void setRisks(List<Risk> risks) {
        this.risks = risks;
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
        listarisk = new ArrayList<>();
        getListarisk();
    }

    public void guardarRisk() throws AuditEJBException {
        try {
            
            User user = gejb.getEM().find(User.class, 43);
            Company company = gejb.getEM().find(Company.class, 7);
            
            risk.setUserCreate(user);
            risk.setCompany(company);
            gejb.insert(risk);
            listarisk = null;
            getListarisk();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Riesgo agregado con exito"));
            PrimeFaces.current().executeScript("PF('manageriskDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dtRisk");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha registrado el dato"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtRisk");
        }

    }

    public void editarRisk() throws AuditEJBException {
        try {
            gejb.update(riskselect);
            listarisk = null;
            getListarisk();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Riego modificada con exito"));
            PrimeFaces.current().executeScript("PF('manageriskDialog_u').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dtRisk");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha modificado el dato"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtRisk");
        }
    }

    public void eliminarRisk() throws AuditEJBException {
        try {
            gejb.delete(riskAEliminar);
            listarisk = null;
            getListarisk();
            cancelar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("riego eliminado con exito"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtRisk");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No se ha podido realizar el borrado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dtRisk");
        }
    }

    public void cancelar() {
        risk = new Risk();
        riskselect = new Risk();
        riskAEliminar = null;
    }
}
