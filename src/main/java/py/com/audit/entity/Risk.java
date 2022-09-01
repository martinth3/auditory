/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.audit.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc-documenta
 */
@Entity
@Table(schema = "planificacion", name = "risk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Risk.findAll", query = "SELECT r FROM Risk r"),
    @NamedQuery(name = "Risk.findById", query = "SELECT r FROM Risk r WHERE r.id = :id"),
    @NamedQuery(name = "Risk.findByCode", query = "SELECT r FROM Risk r WHERE r.code = :code"),
    @NamedQuery(name = "Risk.findByDescription", query = "SELECT r FROM Risk r WHERE r.description = :description"),
    @NamedQuery(name = "Risk.findByUserCreate", query = "SELECT r FROM Risk r WHERE r.userCreate = :userCreate"),
    @NamedQuery(name = "Risk.findByCompanyRisk", query = "SELECT r FROM Risk r WHERE r.company = :company")})
public class Risk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "user_create", referencedColumnName = "id_user")
    private User userCreate;
    @JoinColumn(name = "company_risk", referencedColumnName = "id")
    private Company company;

    public Risk() {
    }

    public Risk(Integer id, String code, String description, User userCreate, Company company) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.userCreate = userCreate;
        this.company = company;
    }

    public Risk(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(User userCreate) {
        this.userCreate = userCreate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        if (!(object instanceof Risk)) {
            return false;
        }
        Risk other = (Risk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.audit.entity.Risk[ id=" + id + " ]";
    }

}
