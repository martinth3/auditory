/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.audit.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Williams
 */
@Stateless
public class GenericEJB {

    @PersistenceContext(unitName = "AuditoriaDS")
    private EntityManager em;

    public EntityManager getEM() {
        return em;
    }

    public Boolean insert(Object entity) {
        try {
            em.persist(entity);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
             System.out.println("es este error 3");
            return false;
        }
    }

    public Boolean update(Object entity) {
        try {
            em.merge(entity);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return false;
        }
    }

    public Boolean delete(Object entity) {
        try {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return false;
        }
    }
}
