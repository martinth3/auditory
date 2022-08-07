package py.com.audit.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.audit.ErrorMenssage.Message;
import py.com.audit.auditexception.AuditEJBException;
import py.com.audit.entity.Company;

@Stateless
public class CompanyModel {

    @PersistenceContext(unitName = "AuditoriaDS")
    private EntityManager em;

    Date Fecha;

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(String f) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Fecha = sdf.parse(f);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Company> listarCompany() throws AuditEJBException {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select c.* ");
            sb.append("  from base.company c");
            Query query = em.createNativeQuery(sb.toString(), Company.class);
            return query.getResultList();
        } catch (Exception e) {
            Message.addErrorMessage("Lista", "No se puede recuperar la compañia");
            throw new AuditEJBException("Error al lista de compañia.", e);
        }
    }

    public boolean insertarCompany(Company company) throws AuditEJBException {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO base.company ");
            sb.append("(name, ruc, date_create,  phone, address, status) ");
            sb.append("VALUES(?1,?2,CURRENT_DATE,?3,?4,true)");
            Query q = em.createNativeQuery(sb.toString());
            q.setParameter(1, company.getName());
            q.setParameter(2, company.getRuc());
            q.setParameter(3, company.getPhone());
            q.setParameter(4, company.getAddress());

            int result = q.executeUpdate();
            System.out.println("result  " + result);
            return result > 0;

        } catch (Exception e) {
            Message.addErrorMessage("RUC", "Ya existe un ruc registrado");
            throw new AuditEJBException("Error al crear company.", e);
        }
    }

    public boolean EditCompany(Company companyselect) throws AuditEJBException {
        try {

            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE base.company ");
            sb.append(" SET name=?1, date_modified=CURRENT_DATE, phone=?2, address=?3 , status=?4");
            sb.append(" WHERE ruc=?5 ");
            Query q = em.createNativeQuery(sb.toString());

            q.setParameter(1, companyselect.getName());
            System.out.println("llega al model name" + companyselect.getName());
            q.setParameter(2, companyselect.getPhone());
            System.out.println("llega al model name" + companyselect.getPhone());
            q.setParameter(3, companyselect.getAddress());
            System.out.println("llega al model name" + companyselect.getAddress());
            q.setParameter(4, companyselect.getStatus());
            System.out.println("llega al model name" + companyselect.getRuc());
            q.setParameter(5, companyselect.getRuc());
            System.out.println("llega al model name" + companyselect.getRuc());
            int result = q.executeUpdate();
            System.out.println("result  " + result);
            return result > 0;

        } catch (Exception ex) {
            System.out.println("Error al editar company." + ex);
            throw new AuditEJBException("Error al editar company.", ex);

        }
    }

    public Company obtenerRuc(String ruc) {
        return em.find(Company.class, ruc);
    }

    public boolean DeleteCompany(String ruc) throws AuditEJBException {
        try {
            System.out.println("llega al model");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM base.company c");
            sb.append(" WHERE c.ruc= ?1 ");
            Query q = em.createNativeQuery(sb.toString());
            q.setParameter(1, ruc);
            System.out.println("ruc: " + ruc);
            int result = q.executeUpdate();
            return result > 0;
        } catch (Exception ex) {
            throw new AuditEJBException("Error al eliminar auditor.", ex);

        }
    }

    public boolean DeleteCompany(Company company) throws AuditEJBException {
        try {
            em.remove(em.contains(company) ? company : em.merge(company));
            em.flush();
            return true;
        } catch (Exception ex) {
            throw new AuditEJBException("Error al eliminar compañia.", ex);
        }
    }

}
