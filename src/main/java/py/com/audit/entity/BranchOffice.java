
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crixx
 */
@Entity
@Table(schema = "base", name = "branch_office")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BranchOffice.findAll", query = "SELECT b FROM BranchOffice b"),
    @NamedQuery(name = "BranchOffice.findById", query = "SELECT b FROM BranchOffice b WHERE b.id = :id"),
    @NamedQuery(name = "BranchOffice.findByName", query = "SELECT b FROM BranchOffice b WHERE b.name = :name"),
    @NamedQuery(name = "BranchOffice.findByStatus", query = "SELECT b FROM BranchOffice b WHERE b.status = :status")})
public class BranchOffice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;

    @JoinColumn(name = "id_company", referencedColumnName = "id")
    private Company company;

    public BranchOffice() {
    }

    public BranchOffice(Integer id) {
        this.id = id;
    }

    public BranchOffice(Integer id, String name, boolean status, Company company) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
        if (!(object instanceof BranchOffice)) {
            return false;
        }
        BranchOffice other = (BranchOffice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.audit.entity.BranchOffice[ id=" + id + " ]";
    }

}
