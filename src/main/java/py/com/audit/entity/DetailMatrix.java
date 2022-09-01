/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.audit.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crixx
 */
@Entity
@Table(schema = "planificacion", name = "detail_matrix")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailMatrix.findAll", query = "SELECT d FROM DetailMatrix d"),
    @NamedQuery(name = "DetailMatrix.findById", query = "SELECT d FROM DetailMatrix d WHERE d.id = :id"),
    @NamedQuery(name = "DetailMatrix.findByIdArea", query = "SELECT d FROM DetailMatrix d WHERE d.area = :area"),
    @NamedQuery(name = "DetailMatrix.findByIdCompany", query = "SELECT d FROM DetailMatrix d WHERE d.company = :company"),
    @NamedQuery(name = "DetailMatrix.findByIdBranchOffice", query = "SELECT d FROM DetailMatrix d WHERE d.branchOffice = :branchOffice"),
    @NamedQuery(name = "DetailMatrix.findByIdDetailArea", query = "SELECT d FROM DetailMatrix d WHERE d.detailArea = :detailArea"),
    @NamedQuery(name = "DetailMatrix.findByIdMatriz", query = "SELECT d FROM DetailMatrix d WHERE d.matrix = :matrix")})
public class DetailMatrix implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_matriz", referencedColumnName = "id")
    private Matrix matrix;
    @JoinColumn(name = "id_area", referencedColumnName = "id")
    private Area area;
    @JoinColumn(name = "id_branch_office", referencedColumnName = "id")
    private BranchOffice branchOffice;
    @JoinColumn(name = "id_detail_area", referencedColumnName = "id")
    private DetailArea detailArea;
    @JoinColumn(name = "id_company", referencedColumnName = "id")
    @ManyToOne
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detailMatrix")
    private List<SubDetailMatriz> subDetailMatrizList;
    
    @Transient
    private int indice;

    public DetailMatrix() {
    }

    public DetailMatrix(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public BranchOffice getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(BranchOffice branchOffice) {
        this.branchOffice = branchOffice;
    }

    public DetailArea getDetailArea() {
        return detailArea;
    }

    public void setDetailArea(DetailArea detailArea) {
        this.detailArea = detailArea;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<SubDetailMatriz> getSubDetailMatrizList() {
        return subDetailMatrizList;
    }

    public void setSubDetailMatrizList(List<SubDetailMatriz> subDetailMatrizList) {
        this.subDetailMatrizList = subDetailMatrizList;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
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
        if (!(object instanceof DetailMatrix)) {
            return false;
        }
        DetailMatrix other = (DetailMatrix) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.audit.entity.DetailMatrix[ id=" + id + " ]";
    }

}
