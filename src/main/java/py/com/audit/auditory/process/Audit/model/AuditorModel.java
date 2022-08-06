package py.com.audit.auditory.process.Audit.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.audit.auditory.process.ErrorMenssage.Message;
import py.com.audit.auditory.process.auditexception.AuditEJBException;
import py.com.audit.auditory.process.entity.Auditor;
import py.com.audit.auditory.process.entity.Company;

/**
 *
 * @author crixx
 */
@Stateless
public class AuditorModel {

    @PersistenceContext(unitName = "AuditoriaDS")
    private EntityManager em;

    public List<Auditor> listarAuditor() throws AuditEJBException {
        try {
//            StringBuilder sb = new StringBuilder();
//            sb.append("select a.* ");
//            sb.append("  from base.auditor a");
//            Query query = em.createNativeQuery(sb.toString(), Auditor.class);
//            return query.getResultList();
            List<Auditor> lista = em.createNamedQuery("Auditor.findAll", Auditor.class).getResultList();
            return lista;
        } catch (Exception e) {
            Message.addErrorMessage("Lista", "No se puede recuperar lista auditor");
            throw new AuditEJBException("Error al listar lista de auditor.", e);
        }
    }

       public List<Company> listarAllCompany() throws AuditEJBException {
        try {
//            StringBuilder sb = new StringBuilder();
//            sb.append("select a.* ");
//            sb.append("  from base.auditor a");
//            Query query = em.createNativeQuery(sb.toString(), Auditor.class);
//            return query.getResultList();
            List<Company> lista = em.createNamedQuery("Company.findAll", Company.class).getResultList();
            return lista;
        } catch (Exception e) {
            Message.addErrorMessage("Lista", "No se puede recuperar lista Compañia");
            throw new AuditEJBException("Error al listar lista de Compañia.", e);
        }
    }
    
    
    
    public boolean insertarAuditor(Auditor auditor) throws AuditEJBException {
        try {
//            StringBuilder sb = new StringBuilder();
//            sb.append("INSERT INTO base.auditor ");
//            sb.append("(name, surname, phone, mail, address, company, nro_doc) ");
//            sb.append("VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7)");
//            Query q = em.createNativeQuery(sb.toString());
//            q.setParameter(1, auditor.getName());
//            q.setParameter(2, auditor.getSurname());
//            q.setParameter(3, auditor.getPhone());
//            q.setParameter(4, auditor.getMail());
//            q.setParameter(5, auditor.getAddress());
//            q.setParameter(6, auditor.getCompany().getId());
//            q.setParameter(7, auditor.getNroDoc());
//
//            int result = q.executeUpdate();

//            return result > 0;
            em.persist(auditor);
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("error"+ e);
            Message.addErrorMessage("Documento", "Ya existe un auditor creado con los mismos datos");
            throw new AuditEJBException("Error al crear auditor.", e);
        }
    }

//    public boolean DeleteAuditor(Auditor auditor) throws AuditEJBException {
//        try {
////            System.out.println("llega al model");
////            StringBuilder sb = new StringBuilder();
////            sb.append("DELETE FROM base.auditor a");
////            sb.append(" WHERE a.nro_doc= ?1 ");
////            Query q = em.createNativeQuery(sb.toString());
////            q.setParameter(1, nro_doc);
////            System.out.println("nro_doc: " + nro_doc);
////            int result = q.executeUpdate();
////            return result > 0;
//
//            em.remove(em.contains(auditorAEliminar) ? auditorAEliminar : em.merge(auditorAEliminar));
//            em.flush();
//            return true;
//        } catch (Exception ex) {
//            throw new AuditEJBException("Error al eliminar el auditor.", ex);
//        }
//    }
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
//            StringBuilder sb = new StringBuilder();
//            sb.append("UPDATE base.auditor ");
//            sb.append(" SET name=?1 , surname=?2, phone=?3, mail=?4, address=?5, company=?6");
//            sb.append(" WHERE nro_doc =?7");
//            Query q = em.createNativeQuery(sb.toString());
//
//            q.setParameter(1, auditorselect.getName());
//            System.out.println("llega al model name" + auditorselect.getName());
//
//            q.setParameter(2, auditorselect.getSurname());
//            System.out.println("llega al model name" + auditorselect.getSurname());
//
//            q.setParameter(3, auditorselect.getPhone());
//            System.out.println("llega al model name" + auditorselect.getPhone());
//
//            q.setParameter(4, auditorselect.getMail());
//            System.out.println("llega al model name" + auditorselect.getMail());
//
//            q.setParameter(5, auditorselect.getAddress());
//            System.out.println("llega al model name" + auditorselect.getAddress());
//
//            q.setParameter(6, auditorselect.getCompany().getId());
//            System.out.println("llega al model name" + auditorselect.getCompany().getId());
//
//            q.setParameter(7, auditorselect.getNroDoc());
//            System.out.println("llega al model name" + auditorselect.getNroDoc());
//
//            int result = q.executeUpdate();
//            System.out.println("result  " + result);
//            return result > 0;
            em.merge(auditor);
            em.flush();
             return true;
        } catch (Exception ex) {
            System.out.println("Error al editar auditor." + ex);
            throw new AuditEJBException("Error al editar auditor.", ex);
        }
    }
}
