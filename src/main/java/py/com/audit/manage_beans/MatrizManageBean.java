package py.com.audit.manage_beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.treetable.TreeTable;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import py.com.audit.entity.Area;
import py.com.audit.entity.BranchOffice;
import py.com.audit.entity.Company;
import py.com.audit.entity.DetailArea;
import py.com.audit.entity.DetailMatrix;
import py.com.audit.entity.Risk;
import py.com.audit.entity.SubDetailMatriz;
import py.com.audit.entity.VCompanyTree;
import py.com.audit.model.GenericEJB;

/**
 *
 * @author crixx
 */
@Named
@ViewScoped
public class MatrizManageBean implements Serializable {

    @EJB
    private GenericEJB gejb;
    private TreeNode<VCompanyTree> treeNode;
    private List<VCompanyTree> vCompanyTreeList;
    private List<DetailMatrix> detailMatrixList;
    private SubDetailMatriz subDetailMatriz;
    private List<Risk> riskList;
    private DetailMatrix detailMatrixSelected;

    @PostConstruct
    public void init() {
        treeNode = cargarArbol();
    }

    //<editor-fold defaultstate="collapsed" desc="Gets & Sets">
    public TreeNode<VCompanyTree> getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(TreeNode<VCompanyTree> treeNode) {
        this.treeNode = treeNode;
    }

    public List<DetailMatrix> getDetailMatrixList() {
        if (detailMatrixList == null) {
            detailMatrixList = new ArrayList();
        }
        return detailMatrixList;
    }

    public void setDetailMatrixList(List<DetailMatrix> detailMatrixList) {
        this.detailMatrixList = detailMatrixList;
    }

    public SubDetailMatriz getSubDetailMatriz() {
        if (subDetailMatriz == null) {
            subDetailMatriz = new SubDetailMatriz();
            subDetailMatriz.setRisk(new Risk());
        }
        return subDetailMatriz;
    }

    public List<Risk> getRiskList() {
        if (riskList == null) {
            riskList = new ArrayList();
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
                riskList = gejb.getEM().createNamedQuery("Risk.findByCompanyRisk", Risk.class)
                        .setParameter("company", auxComp)
                        .getResultList();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return riskList;
    }

    public void setRiskList(List<Risk> riskList) {
        this.riskList = riskList;
    }

    public void setSubDetailMatriz(SubDetailMatriz subDetailMatriz) {
        this.subDetailMatriz = subDetailMatriz;
    }

    public DetailMatrix getDetailMatrixSelected() {
        if (detailMatrixSelected == null) {
            detailMatrixSelected = new DetailMatrix();
            detailMatrixSelected.setArea(new Area());
            detailMatrixSelected.setBranchOffice(new BranchOffice());
            detailMatrixSelected.setCompany(new Company());
            detailMatrixSelected.setDetailArea(new DetailArea());
        }
        return detailMatrixSelected;
    }

    public void setDetailMatrixSelected(DetailMatrix detailMatrixSelected) {
        System.out.println("ENTRA EN EL SET --> " + detailMatrixSelected);
        this.detailMatrixSelected = detailMatrixSelected;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Arbol Lista de Valores">

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
                        .getResultList();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return vCompanyTreeList;
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

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="onRowSelect">
    public void onRowSelectArea(NodeSelectEvent event) {
        //Seleccionamos un objeto
        TreeTable arbol = (TreeTable) event.getSource();
        DefaultTreeNode node = (DefaultTreeNode) arbol.getSelection();
        VCompanyTree companyTree = (VCompanyTree) node.getData();
        FacesMessage msg = new FacesMessage("Area seleccionado", String.valueOf(companyTree.getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);

        DetailMatrix detailMatrix = new DetailMatrix();
        //cuando es company
        if (companyTree.getTabla().equals("company")) {
            detailMatrix.setCompany(companyTree.getCompany());
        }
        //cuando es branch
        if (companyTree.getTabla().equals("branch_office")) {
            try {
                BranchOffice branchOffice = new BranchOffice();
                branchOffice = gejb.getEM().createNamedQuery("BranchOffice.findById", BranchOffice.class)
                        .setParameter("id", companyTree.getIdTabla())
                        .getSingleResult();
                detailMatrix.setCompany(companyTree.getCompany());
                detailMatrix.setBranchOffice(branchOffice);

                /*
                Le pasamos el objeto por la consulta realizada los tres diferentes areas
                 */
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        //cuando es area
        if (companyTree.getTabla().equals("area")) {
            try {
                Area area = new Area();
                area = gejb.getEM().createNamedQuery("Area.findById", Area.class)
                        .setParameter("id", companyTree.getIdTabla())
                        .getSingleResult();
                detailMatrix.setCompany(companyTree.getCompany());
                detailMatrix.setBranchOffice(area.getBranchOffice());
                detailMatrix.setArea(area);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        if (companyTree.getTabla().equals("detail_area")) {
            try {
                DetailArea detailArea = new DetailArea();
                detailArea = gejb.getEM().createNamedQuery("DetailArea.findById", DetailArea.class)
                        .setParameter("id", companyTree.getIdTabla())
                        .getSingleResult();
                detailMatrix.setCompany(companyTree.getCompany());
                detailMatrix.setBranchOffice(detailArea.getBranchOffice());
                detailMatrix.setArea(detailArea.getArea());
                detailMatrix.setDetailArea(detailArea);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        detailMatrixList.add(detailMatrix);
        settearIndices();
    }
    
    public void settearIndices() {
        if (detailMatrixList != null || !detailMatrixList.isEmpty()) {
            for (int i = 0; i < detailMatrixList.size(); i++) {
                detailMatrixList.get(i).setIndice(i);
            }
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Agregar SubDetalles">
    public void probando() {
        System.out.println("ENTRO EN LA PUERQUESA");
        try {
            if (subDetailMatriz != null) {
                // Validamos si esta nula la lista de detalles para inicializarla y podes agregarle detalles
                if (detailMatrixSelected.getSubDetailMatrizList() == null) {
                    detailMatrixSelected.setSubDetailMatrizList(new ArrayList<>());
                }
                detailMatrixSelected.getSubDetailMatrizList().add(subDetailMatriz);
            }
//            for (int i = 0; i < detailMatrixList.size(); i++) {
//                detailMatrixList.set(i, detailMatrixSelected);
//            }
//            limpiarSubDet();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void limpiarSubDet() {
        subDetailMatriz = null;
        detailMatrixSelected = null;
    }
//</editor-fold>
}
