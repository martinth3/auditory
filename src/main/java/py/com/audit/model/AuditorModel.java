<<<<<<< HEAD
package py.com.audit.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import  py.com.audit.ErrorMenssage.Message;
import py.com.audit.auditexception.AuditEJBException;
import py.com.audit.entity.Auditor;
import py.com.audit.entity.Company;

/**
 *
 * @author crixx
 */
@Stateless
public class AuditorModel {

    @PersistenceContext(unitName = "AuditoriaDS")
    private EntityManager em;

    public EntityManager getEM() {
        return em;
    }

    public List<Auditor> listarAuditor() throws AuditEJBException {
        try {
            List<Auditor> lista = em.createNamedQuery("Auditor.findAll", Auditor.class).getResultList();
            return lista;
        } catch (Exception e) {
            Message.addErrorMessage("Lista", "No se puede recuperar lista auditor");
            throw new AuditEJBException("Error al listar lista de auditor.", e);
        }
    }

    public List<Company> listarAllCompany() throws AuditEJBException {
        try {
            List<Company> lista = em.createNamedQuery("Company.findAll", Company.class).getResultList();
            return lista;
        } catch (Exception e) {
            Message.addErrorMessage("Lista", "No se puede recuperar lista Compañia");
            throw new AuditEJBException("Error al listar lista de Compañia.", e);
        }
    }

    public boolean insertarAuditor(Auditor auditor) throws AuditEJBException {
        try {
            em.persist(auditor);
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("error" + e);
            Message.addErrorMessage("Documento", "Ya existe un auditor creado con los mismos datos");
            throw new AuditEJBException("Error al crear auditor.", e);
        }
    }

    public boolean DeleteAuditor(Auditor auditor) throws AuditEJBException {
        try {
            em.remove(em.contains(auditor) ? auditor : em.merge(auditor));
            em.flush();
            return true;
        } catch (Exception ex) {
            System.out.println("error" + ex);
            throw new AuditEJBException("Error al eliminar auditor.", ex);
        }
    }

    public boolean EditAuditor(Auditor auditor) throws AuditEJBException {
        try {
            em.merge(auditor);
            em.flush();
            return true;
        } catch (Exception ex) {
            System.out.println("Error al editar auditor." + ex);
            throw new AuditEJBException("Error al editar auditor.", ex);
        }
    }
}
=======
package py.com.audit.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.audit.auditory.ErrorMenssage.Message;
import py.com.audit.auditory.auditexception.AuditEJBException;
import py.com.audit.entity.Auditor;
import py.com.audit.entity.Company;

/**
 *
 * @author crixx
 */
@Stateless
public class AuditorModel {

    @PersistenceContext(unitName = "AuditoriaDS")
    private EntityManager em;

    public EntityManager getEM() {
        return em;
    }

    public List<Auditor> listarAuditor() throws AuditEJBException {
        try {
            List<Auditor> lista = em.createNamedQuery("Auditor.findAll", Auditor.class).getResultList();
            return lista;
        } catch (Exception e) {
            Message.addErrorMessage("Lista", "No se puede recuperar lista auditor");
            throw new AuditEJBException("Error al listar lista de auditor.", e);
        }
    }

    public List<Company> listarAllCompany() throws AuditEJBException {
        try {
            List<Company> lista = em.createNamedQuery("Company.findAll", Company.class).getResultList();
            return lista;
        } catch (Exception e) {
            Message.addErrorMessage("Lista", "No se puede recuperar lista Compañia");
            throw new AuditEJBException("Error al listar lista de Compañia.", e);
        }
    }

    public boolean insertarAuditor(Auditor auditor) throws AuditEJBException {
        try {
            em.persist(auditor);
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("error" + e);
            Message.addErrorMessage("Documento", "Ya existe un auditor creado con los mismos datos");
            throw new AuditEJBException("Error al crear auditor.", e);
        }
    }

    public boolean DeleteAuditor(Auditor auditor) throws AuditEJBException {
        try {
            em.remove(em.contains(auditor) ? auditor : em.merge(auditor));
            em.flush();
            return true;
        } catch (Exception ex) {
            System.out.println("error" + ex);
            throw new AuditEJBException("Error al eliminar auditor.", ex);
        }
    }

    public boolean EditAuditor(Auditor auditor) throws AuditEJBException {
        try {
            em.merge(auditor);
            em.flush();
            return true;
        } catch (Exception ex) {
            System.out.println("Error al editar auditor." + ex);
            throw new AuditEJBException("Error al editar auditor.", ex);
        }
    }
}
>>>>>>> 32d47b74d9d48a5e74dc4b3f24762222cca1cb51
