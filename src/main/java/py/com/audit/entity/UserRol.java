<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.audit.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crixx
 */
@Entity
@Table(name = "user_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserRol.findAll", query = "SELECT u FROM UserRol u"),
    @NamedQuery(name = "UserRol.findByIdUser", query = "SELECT u FROM UserRol u WHERE u.userRolPK.idUser = :idUser"),
    @NamedQuery(name = "UserRol.findByIdRol", query = "SELECT u FROM UserRol u WHERE u.userRolPK.idRol = :idRol"),
    @NamedQuery(name = "UserRol.findByIdCompany", query = "SELECT u FROM UserRol u WHERE u.userRolPK.idCompany = :idCompany"),
    @NamedQuery(name = "UserRol.findByIdAuditor", query = "SELECT u FROM UserRol u WHERE u.userRolPK.idAuditor = :idAuditor"),
    @NamedQuery(name = "UserRol.findByStatus", query = "SELECT u FROM UserRol u WHERE u.status = :status")})
public class UserRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserRolPK userRolPK;
    @Basic(optional = false)
    @NotNull
    private boolean status;
    @JoinColumn(name = "id_auditor", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Auditor auditor;
    @JoinColumn(name = "id_company", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumn(name = "id_rol", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserRol() {
    }

    public UserRol(UserRolPK userRolPK) {
        this.userRolPK = userRolPK;
    }

    public UserRol(UserRolPK userRolPK, boolean status) {
        this.userRolPK = userRolPK;
        this.status = status;
    }

    public UserRol(int idUser, int idRol, int idCompany, int idAuditor) {
        this.userRolPK = new UserRolPK(idUser, idRol, idCompany, idAuditor);
    }

    public UserRolPK getUserRolPK() {
        return userRolPK;
    }

    public void setUserRolPK(UserRolPK userRolPK) {
        this.userRolPK = userRolPK;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Auditor getAuditor() {
        return auditor;
    }

    public void setAuditor(Auditor auditor) {
        this.auditor = auditor;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userRolPK != null ? userRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRol)) {
            return false;
        }
        UserRol other = (UserRol) object;
        if ((this.userRolPK == null && other.userRolPK != null) || (this.userRolPK != null && !this.userRolPK.equals(other.userRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.process.entity.UserRol[ userRolPK=" + userRolPK + " ]";
    }
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.audit.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crixx
 */
@Entity
@Table(schema = "base",name = "user_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserRol.findAll", query = "SELECT u FROM UserRol u"),
    @NamedQuery(name = "UserRol.findByStatus", query = "SELECT u FROM UserRol u WHERE u.status = :status"),
    @NamedQuery(name = "UserRol.findById", query = "SELECT u FROM UserRol u WHERE u.id = :id")})
public class UserRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    private Rol rol;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;
    

    public UserRol() {
    }

    public UserRol(Integer id) {
        this.id = id;
    }

    public UserRol(Integer id, boolean status, Rol rol, User user) {
        this.id = id;
        this.status = status;
        this.rol = rol;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRol)) {
            return false;
        }
        UserRol other = (UserRol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.audit.entity.UserRol[ id=" + id + " ]";
    }
    
}
>>>>>>> 32d47b74d9d48a5e74dc4b3f24762222cca1cb51
