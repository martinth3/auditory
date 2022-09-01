package py.com.audit.manage_beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import py.com.audit.entity.Area;
import py.com.audit.entity.BranchOffice;
import py.com.audit.entity.Company;
import py.com.audit.entity.DetailArea;
import py.com.audit.model.GenericEJB;
import py.com.audit.entity.VCompanyTree;

/**
 *
 * @author crixx
 */
@Named
@ViewScoped
public class TreeCompanyManageBeans implements Serializable {

    @EJB
    private GenericEJB gejb;

    private TreeNode<VCompanyTree> treeNode;
    private List<VCompanyTree> vCompanyTreeList;
    private String txtDesc;
    private VCompanyTree selected;

    public VCompanyTree getSelected() {
        return selected;
    }

    public void setSelected(VCompanyTree selected) {
        this.selected = selected;
    }

    public String getTxtDesc() {
        return txtDesc;
    }

    public void setTxtDesc(String txtDesc) {
        this.txtDesc = txtDesc;
    }

    @PostConstruct
    public void init() { 
        treeNode = cargarArbol();
    }

    public List<VCompanyTree> getvCompanyTreeList() {
        if (vCompanyTreeList == null) {
            try {
                gejb.getEM().getEntityManagerFactory().getCache().evictAll();
                //<editor-fold defaultstate="collapsed" desc="Obtenemos el Objeto COMPANY de forma provisoria">
                Company auxComp = null;
                try {
                    auxComp = gejb.getEM().createNamedQuery("Company.findById", Company.class)
                            .setParameter("id", 6)
                            .getSingleResult();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
                //</editor-fold>

                vCompanyTreeList = gejb.getEM().createNamedQuery("VCompanyTree.findByCompanyRelNull", VCompanyTree.class)
                        .setParameter("company", auxComp)
                        //                        .setParameter("idCompany", 6)
                        .getResultList();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return vCompanyTreeList;
    }

    public void setvCompanyTreeList(List<VCompanyTree> vCompanyTreeList) {
        this.vCompanyTreeList = vCompanyTreeList;
    }

    public TreeNode<VCompanyTree> getTreeNode() {
        return treeNode;
    }

    public TreeNode<VCompanyTree> cargarArbol() {
        getvCompanyTreeList();
        TreeNode<VCompanyTree> padre = new DefaultTreeNode<>(new VCompanyTree(), null);
        for (VCompanyTree reg : vCompanyTreeList) {
            TreeNode node = new DefaultTreeNode(reg, padre);
            cargarArbolHijo(node, reg.getNivel() + 1);
        }
        return padre;
    }

    public void cargarArbolHijo(TreeNode hijoPadre, Integer nivel) {
        List<VCompanyTree> vCompanyTreeChildList;
        try {
            vCompanyTreeChildList = gejb.getEM().createNamedQuery("VCompanyTree.findByCompanyRelacionNivel")
                    .setParameter("company", ((VCompanyTree) hijoPadre.getData()).getCompany())
                    .setParameter("relacion", ((VCompanyTree) hijoPadre.getData()).getIdTabla())
                    .setParameter("nivel", nivel)
                    .getResultList();
            for (VCompanyTree reg : vCompanyTreeChildList) {
                TreeNode nodeChild = new DefaultTreeNode(reg, hijoPadre);
                cargarArbolHijo(nodeChild, nivel + 1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void onRowEditcompany(RowEditEvent<TreeNode> event) {
        FacesMessage msg = new FacesMessage("Document Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<TreeNode> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void insertar() {
        if (selected.getTabla().equals("company")) {
            BranchOffice branchOffice = new BranchOffice();
            branchOffice.setCompany(selected.getCompany());
            branchOffice.setName(txtDesc);
            branchOffice.setStatus(Boolean.valueOf(true));
            if (gejb.insert(branchOffice)) {
                cancelar();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro agregado con exito"));
                PrimeFaces.current().executeScript("PF('managetreeDialog').hide()");
                PrimeFaces.current().ajax().update("form:msgs", "form:dtTree");
                return;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "registro no agregado"));
                PrimeFaces.current().ajax().update("form:msgs", "form:dtTree");
            }
        }
        if (selected.getTabla().equals("branch_office")) {
            BranchOffice branchOffice = null;
            try {
                branchOffice = gejb.getEM().createNamedQuery("BranchOffice.findById", BranchOffice.class)
                        .setParameter("id", selected.getIdTabla())
                        .getSingleResult();
            } catch (Exception e) {
                System.out.println("es este error 1");
                e.printStackTrace(System.err);
            }
            Area area = new Area();
            area.setBranchOffice(branchOffice);
            area.setDescription(txtDesc);
            if (gejb.insert(area)) {
                cancelar();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro agregado con exito"));
                PrimeFaces.current().executeScript("PF('managetreeDialog').hide()");
                PrimeFaces.current().ajax().update("form:msgs", "form:dtTree");
                return;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "registro no agregado"));
                PrimeFaces.current().ajax().update("form:msgs", "form:dtTree");
            }
        }
         if (selected.getTabla().equals("area")) {
            Area area = null;
            try {
                area = gejb.getEM().createNamedQuery("Area.findById", Area.class)
                        .setParameter("id", selected.getIdTabla())
                        .getSingleResult();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
            DetailArea detailArea = new DetailArea();
            detailArea.setArea(area);
            detailArea.setDescription(txtDesc);
            if (gejb.insert(detailArea)) {
                cancelar();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro agregado con exito"));
                PrimeFaces.current().executeScript("PF('managetreeDialog').hide()");
                PrimeFaces.current().ajax().update("form:msgs", "form:dtTree");
                return;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "registro no agregado"));
                PrimeFaces.current().ajax().update("form:msgs", "form:dtTree");
            }
        }
        
    }

    public void modificar() {
        
         if (selected.getTabla().equals("branch_office")) {
            BranchOffice branchOffice = null;
                 try {
                branchOffice = gejb.getEM().createNamedQuery("BranchOffice.findById", BranchOffice.class)
                        .setParameter("id", selected.getIdTabla())
                        .getSingleResult();
            } catch (Exception e) {
                System.out.println("es este error 1");
                e.printStackTrace(System.err);
            }
            branchOffice.setName(txtDesc);
            if (gejb.update(branchOffice)) {
                cancelar();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro agregado con exito"));
                PrimeFaces.current().executeScript("PF('managetreeDialog').hide()");
                PrimeFaces.current().ajax().update("form:msgs", "form:dtTree");
                return;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "registro no agregado"));
                PrimeFaces.current().ajax().update("form:msgs", "form:dtTree");
            }
        }
    }

    public void borrar() {

    }

    public void cancelar() {
        selected = null;
        txtDesc = null;
        vCompanyTreeList = null;
        treeNode = cargarArbol();
    }
}
