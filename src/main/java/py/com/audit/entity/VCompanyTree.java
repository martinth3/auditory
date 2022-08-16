package py.com.audit.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crixx
 */
@Entity
@Table(schema = "base", name = "v_company_tree")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VCompanyTree.findAll", query = "SELECT v FROM VCompanyTree v"),
    @NamedQuery(name = "VCompanyTree.findById", query = "SELECT v FROM VCompanyTree v WHERE v.id = :id"),
    @NamedQuery(name = "VCompanyTree.findByTabla", query = "SELECT v FROM VCompanyTree v WHERE v.tabla = :tabla"),
    @NamedQuery(name = "VCompanyTree.findByName", query = "SELECT v FROM VCompanyTree v WHERE v.name = :name"),
    @NamedQuery(name = "VCompanyTree.findByIdTabla", query = "SELECT v FROM VCompanyTree v WHERE v.idTabla = :idTabla"),
    @NamedQuery(name = "VCompanyTree.findByCompany", query = "SELECT v FROM VCompanyTree v WHERE v.company = :company"),
    @NamedQuery(name = "VCompanyTree.findByCompanyRelNull", query = "SELECT v FROM VCompanyTree v WHERE v.company = :company AND v.relacion = null"),
    @NamedQuery(name = "VCompanyTree.findByRelacion", query = "SELECT v FROM VCompanyTree v WHERE v.relacion = :relacion"),
    @NamedQuery(name = "VCompanyTree.findByCompanyRelacionNivel", query = "SELECT v FROM VCompanyTree v WHERE v.company = :company AND v.relacion = :relacion AND v.nivel = :nivel"),
    @NamedQuery(name = "VCompanyTree.findByOrden", query = "SELECT v FROM VCompanyTree v WHERE v.orden = :orden")})
public class VCompanyTree implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "tabla")
    private String tabla;
    @Column(name = "name")
    private String name;
    @Column(name = "id_tabla")
    private Integer idTabla;
    @Column(name = "relacion")
    private Integer relacion;
    @Column(name = "orden")
    private String orden;
    @Column(name = "nivel")
    private Integer nivel;
    @Column(name = "titulo")
    private String titulo;

    @JoinColumn(name = "id_company", referencedColumnName = "id")
    private Company company;

    public VCompanyTree() {
    }

    public VCompanyTree(Company company) {
        this.company = company;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(Integer idTabla) {
        this.idTabla = idTabla;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getRelacion() {
        return relacion;
    }

    public void setRelacion(Integer relacion) {
        this.relacion = relacion;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
