/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.audit.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crixx
 */
@Entity
@Table(schema = "planificacion", name = "matrix")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matrix.findAll", query = "SELECT m FROM Matrix m"),
    @NamedQuery(name = "Matrix.findById", query = "SELECT m FROM Matrix m WHERE m.id = :id"),
    @NamedQuery(name = "Matrix.findByCode", query = "SELECT m FROM Matrix m WHERE m.code = :code"),
    @NamedQuery(name = "Matrix.findByDateCreated", query = "SELECT m FROM Matrix m WHERE m.dateCreated = :dateCreated"),
    @NamedQuery(name = "Matrix.findByDateAudit", query = "SELECT m FROM Matrix m WHERE m.dateAudit = :dateAudit"),
    @NamedQuery(name = "Matrix.findByDateFinished", query = "SELECT m FROM Matrix m WHERE m.dateFinished = :dateFinished"),
    @NamedQuery(name = "Matrix.findByStatus", query = "SELECT m FROM Matrix m WHERE m.status = :status"),
    @NamedQuery(name = "Matrix.findByDateFinalFinished", query = "SELECT m FROM Matrix m WHERE m.dateFinalFinished = :dateFinalFinished")})
public class Matrix implements Serializable {

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
    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_audit")
    @Temporal(TemporalType.DATE)
    private Date dateAudit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_finished")
    @Temporal(TemporalType.DATE)
    private Date dateFinished;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "status")
    private String status;
    @Column(name = "date_final_finished")
    @Temporal(TemporalType.DATE)
    private Date dateFinalFinished;
    @JoinColumn(name = "id_user_created",
    referencedColumnName = "id_user")
    private User idUser; 

    public Matrix(Integer id, String code, Date dateCreated, Date dateAudit, Date dateFinished, String status, Date dateFinalFinished, User idUser) {
        this.id = id;
        this.code = code;
        this.dateCreated = dateCreated;
        this.dateAudit = dateAudit;
        this.dateFinished = dateFinished;
        this.status = status;
        this.dateFinalFinished = dateFinalFinished;
        this.idUser = idUser;
    }
    

    public Matrix() {
    }

    public Matrix(Integer id) {
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateAudit() {
        return dateAudit;
    }

    public void setDateAudit(Date dateAudit) {
        this.dateAudit = dateAudit;
    }

    public Date getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }
   
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateFinalFinished() {
        return dateFinalFinished;
    }

    public void setDateFinalFinished(Date dateFinalFinished) {
        this.dateFinalFinished = dateFinalFinished;
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
        if (!(object instanceof Matrix)) {
            return false;
        }
        Matrix other = (Matrix) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.audit.entity.Matrix[ id=" + id + " ]";
    }

}
