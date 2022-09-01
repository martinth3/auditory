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
 * @author crixx
 */
@Entity
@Table(schema = "planificacion", name = "sub_detail_matriz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubDetailMatriz.findAll", query = "SELECT s FROM SubDetailMatriz s"),
    @NamedQuery(name = "SubDetailMatriz.findById", query = "SELECT s FROM SubDetailMatriz s WHERE s.id = :id"),
    @NamedQuery(name = "SubDetailMatriz.findByCause", query = "SELECT s FROM SubDetailMatriz s WHERE s.cause = :cause"),
    @NamedQuery(name = "SubDetailMatriz.findByEffect", query = "SELECT s FROM SubDetailMatriz s WHERE s.effect = :effect"),
    @NamedQuery(name = "SubDetailMatriz.findByProbability", query = "SELECT s FROM SubDetailMatriz s WHERE s.probability = :probability"),
    @NamedQuery(name = "SubDetailMatriz.findByImpact", query = "SELECT s FROM SubDetailMatriz s WHERE s.impact = :impact"),
    @NamedQuery(name = "SubDetailMatriz.findByResult", query = "SELECT s FROM SubDetailMatriz s WHERE s.result = :result"),
    @NamedQuery(name = "SubDetailMatriz.findByIdDetailMatriz", query = "SELECT s FROM SubDetailMatriz s WHERE s.detailMatrix = :detailMatrix"),
    @NamedQuery(name = "SubDetailMatriz.findByIdRisk", query = "SELECT s FROM SubDetailMatriz s WHERE s.risk = :risk")})
public class SubDetailMatriz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cause")
    private String cause;
    @Column(name = "effect")
    private String effect;
    @Column(name = "probability")
    private int probability;
    @Column(name = "impact")
    private int impact;
    @Column(name = "result")
    private int result;
    
    @JoinColumn(name = "id_risk", referencedColumnName = "id")
    private Risk risk;
    @JoinColumn(name = "id_detail_matriz", referencedColumnName = "id")
    private DetailMatrix detailMatrix;

    public SubDetailMatriz() {
    }

    public SubDetailMatriz(Integer id) {
        this.id = id;
    }

    public SubDetailMatriz(Integer id, String cause, String effect, int probability, int impact, int result, Risk risk, DetailMatrix detailMatrix) {
        this.id = id;
        this.cause = cause;
        this.effect = effect;
        this.probability = probability;
        this.impact = impact;
        this.result = result;
        this.risk = risk;
        this.detailMatrix = detailMatrix;
    }

    

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public int getImpact() {
        return impact;
    }

    public void setImpact(int impact) {
        this.impact = impact;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    public DetailMatrix getDetailMatrix() {
        return detailMatrix;
    }

    public void setDetailMatrix(DetailMatrix detailMatrix) {
        this.detailMatrix = detailMatrix;
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
        if (!(object instanceof SubDetailMatriz)) {
            return false;
        }
        SubDetailMatriz other = (SubDetailMatriz) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.audit.entity.SubDetailMatriz[ id=" + id + " ]";
    }

}
