<<<<<<< HEAD
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crixx
 */
@Entity
@Table(schema = "base", name = "auditor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auditor.findAll", query = "SELECT a FROM Auditor a"),
    @NamedQuery(name = "Auditor.findById", query = "SELECT a FROM Auditor a WHERE a.id = :id"),
    @NamedQuery(name = "Auditor.findByName", query = "SELECT a FROM Auditor a WHERE a.name = :name"),
    @NamedQuery(name = "Auditor.findBySurname", query = "SELECT a FROM Auditor a WHERE a.surname = :surname"),
    @NamedQuery(name = "Auditor.findByPhone", query = "SELECT a FROM Auditor a WHERE a.phone = :phone"),
    @NamedQuery(name = "Auditor.findByMail", query = "SELECT a FROM Auditor a WHERE a.mail = :mail"),
    @NamedQuery(name = "Auditor.findByAddress", query = "SELECT a FROM Auditor a WHERE a.address = :address"),
    @NamedQuery(name = "Auditor.findByNroDoc", query = "SELECT a FROM Auditor a WHERE a.nroDoc = :nroDoc")})
public class Auditor implements Serializable {

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
    @Size(min = 1, max = 255)
    @Column(name = "surname")
    private String surname;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;
    @Column(name = "nro_doc")
    private Integer nroDoc;
    @JoinColumn(name = "company", referencedColumnName = "id")
    private Company company;

    public Auditor() {
    }

    public Auditor(Integer id) {
        this.id = id;
    }

    public Auditor(Integer id, String name, String surname, String phone, String mail, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(Integer nroDoc) {
        this.nroDoc = nroDoc;
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
        if (!(object instanceof Auditor)) {
            return false;
        }
        Auditor other = (Auditor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.audit.auditory.process.entity.Auditor[ id=" + id + " ]";
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crixx
 */
@Entity
@Table(schema = "base", name = "auditor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auditor.findAll", query = "SELECT a FROM Auditor a"),
    @NamedQuery(name = "Auditor.findById", query = "SELECT a FROM Auditor a WHERE a.id = :id"),
    @NamedQuery(name = "Auditor.findByName", query = "SELECT a FROM Auditor a WHERE a.name = :name"),
    @NamedQuery(name = "Auditor.findBySurname", query = "SELECT a FROM Auditor a WHERE a.surname = :surname"),
    @NamedQuery(name = "Auditor.findByPhone", query = "SELECT a FROM Auditor a WHERE a.phone = :phone"),
    @NamedQuery(name = "Auditor.findByMail", query = "SELECT a FROM Auditor a WHERE a.mail = :mail"),
    @NamedQuery(name = "Auditor.findByAddress", query = "SELECT a FROM Auditor a WHERE a.address = :address"),
    @NamedQuery(name = "Auditor.findByNroDoc", query = "SELECT a FROM Auditor a WHERE a.nroDoc = :nroDoc")})
public class Auditor implements Serializable {

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
    @Size(min = 1, max = 255)
    @Column(name = "surname")
    private String surname;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;
    @Column(name = "nro_doc")
    private Integer nroDoc;
    @JoinColumn(name = "company", referencedColumnName = "id")
    private Company company;

    public Auditor() {
    }

    public Auditor(Integer id) {
        this.id = id;
    }

    public Auditor(Integer id, String name, String surname, String phone, String mail, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(Integer nroDoc) {
        this.nroDoc = nroDoc;
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
        if (!(object instanceof Auditor)) {
            return false;
        }
        Auditor other = (Auditor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.audit.auditory.process.entity.Auditor[ id=" + id + " ]";
    }

}
>>>>>>> 32d47b74d9d48a5e74dc4b3f24762222cca1cb51
