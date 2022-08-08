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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "Risk.findByCompanyRisk", query = "SELECT r FROM Risk r WHERE r.companyRisk = :companyRisk")})
public class Risk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_create")
    private int userCreate;
    @Column(name = "company_risk")
    private Integer companyRisk;

    public Risk() {
    }

    public Risk(Integer id) {
        this.id = id;
    }

    public Risk(Integer id, String description, int userCreate) {
        this.id = id;
        this.description = description;
        this.userCreate = userCreate;
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

    public int getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(int userCreate) {
        this.userCreate = userCreate;
    }

    public Integer getCompanyRisk() {
        return companyRisk;
    }

    public void setCompanyRisk(Integer companyRisk) {
        this.companyRisk = companyRisk;
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
