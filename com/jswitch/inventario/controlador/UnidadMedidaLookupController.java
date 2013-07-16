/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.inventario.controlador;

import com.jswitch.auditoria.modelo.Auditable;
import com.jswitch.auditoria.modelo.AuditoriaBasica;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.inventario.modelo.Unidad;
import com.jswitch.inventario.modelo.UnidadMedida;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.send.java.FilterWhereClause;
import org.openswing.swing.tree.java.OpenSwingTreeNode;
import org.openswing.swing.util.java.Consts;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author NELSON
 */
public class UnidadMedidaLookupController extends DefaultLookupController {

    private Unidad unidadd;

    public UnidadMedidaLookupController() {

        this.setLookupDataLocator(new UnidadMedidaLookupDataLocator());
        this.setLookupGridController(new UnidadMedidaLookupGridController());
        setLookupValueObjectClassName(UnidadMedida.class.getName());

        setCodeSelectionWindow(TREE_GRID_FRAME);
        this.getLookupDataLocator().setNodeNameAttribute("nombre");
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_LAST_VALID_CODE);

        setAllColumnVisible(false);
        setVisibleColumn("id", true);
        setVisibleColumn("nombre", true);
        setVisibleColumn("nombreCorto", true);
        setVisibleColumn("simbolo", true);
        setVisibleColumn("observacion", true);

        setAllColumnRequired(false);
        setColumnRequired("nombre", true);
        setColumnRequired("nombreCorto", true);

        setGridEditButton(true);
        setColumnEditableOnEdit("id", false);
        setColumnEditableOnEdit("nombre", true);
        setColumnEditableOnEdit("nombreCorto", true);
        setColumnEditableOnEdit("simbolo", true);
        setColumnEditableOnEdit("observacion", true);

        setGridInsertButton(true);
        setColumnEditableOnInsert("id", false);
        setColumnEditableOnInsert("nombre", true);
        setColumnEditableOnInsert("nombreCorto", true);
        setColumnEditableOnInsert("simbolo", true);
        setColumnEditableOnInsert("observacion", true);

        setPreferredWidthColumn("id", 50);
        setPreferredWidthColumn("nombre", 200);

        setFilterableColumn("nombre", true);

        setSortableColumn("id", true);
        setSortableColumn("nombre", true);

        setSortedColumn("nombre", Consts.DESC_SORTED, 0);

        setFramePreferedSize(new Dimension(800, 600));

    }

    class UnidadMedidaLookupDataLocator extends DefaultLookupDataLocator {

        public UnidadMedidaLookupDataLocator() {
        }

        @Override
        public Response validateCode(String code) {
            Session s = null;
            try {
                String sql = "FROM " + UnidadMedida.class.getName() + " C " + ""
                        + "WHERE C.auditoria.activo=? AND upper(C.nombreCorto) like ?";
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                List list = s.createQuery(sql).
                        setBoolean(0, true).
                        setString(1, code.toUpperCase()+"%").list();
                t.commit();
                return new VOListResponse(list, false, list.size());
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "Unidad de Medida no Encontrada", ex);
                return new ErrorResponse(ex.getMessage());
            } finally {
                s.close();
            }
        }

        @Override
        public Response loadData(int action,
                int startIndex,
                Map filteredColumns,
                ArrayList currentSortedColumns,
                ArrayList currentSortedVersusColumns,
                Class valueObjectType) {
            try {
                Map map = getLookupFrameParams();
                if (map.get(Consts.TREE_FILTER) != null) {
                    Unidad unidad = (Unidad) map.get(Consts.TREE_FILTER);
                    if (unidad.getId() != -1) {
                        filteredColumns.put(
                                "unidad.id",
                                new FilterWhereClause[]{
                                    new FilterWhereClause("unidad.id", "=", unidad.getId()),
                                    null
                                });
                        Session s = null;
                        try {
                            String sql = "FROM " + UnidadMedida.class.getName() + " C ";
                            SessionFactory sf = HibernateUtil.getSessionFactory();
                            s = sf.openSession();
                            Response res = HibernateUtils.getAllFromQuery(
                                    filteredColumns,
                                    currentSortedColumns,
                                    currentSortedVersusColumns,
                                    valueObjectType,
                                    sql,
                                    new Object[0],
                                    new Type[0],
                                    "C",
                                    sf,
                                    s);
//                            filteredColumns = new HashMap(0);
                            unidadd = unidad;
                            return res;
                        } catch (Exception ex) {
                            LoggerUtil.error(this.getClass(), "loadData", ex);
                            return new ErrorResponse(ex.getMessage());
                        } finally {
                            s.close();
                        }
                    } else {
                        return new VOListResponse(new ArrayList(0), false, 0);
                    }
                }
                return new VOListResponse(new ArrayList(0), false, 0);
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "loadData", ex);
                return new ErrorResponse(ex.getMessage());
            }
        }

        @Override
        public Response getTreeModel(JTree tree) {
            Session s = null;
            try {
                Unidad vo = null;
                vo = new Unidad();
                vo.setId(new Long(-1));
                vo.setNombre("Unidades");
                DefaultMutableTreeNode root = new OpenSwingTreeNode(vo);
                s = HibernateUtil.getSessionFactory().openSession();
                List unidades = s.createQuery("FROM " + Unidad.class.getName() + " M ORDER BY M.nombre").list();
                for (Unidad unidad : (List<Unidad>) unidades) {
                    root.add(new OpenSwingTreeNode(unidad));
                }
                return new VOResponse(new DefaultTreeModel(root));
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "getTreeModel", ex);
                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
            } finally {
                s.close();
            }
        }
    }

    class UnidadMedidaLookupGridController extends DefaultLookupGridController {

        public UnidadMedidaLookupGridController() {
        }

        @Override
        public Response deleteRecords(ArrayList persistentObjects) throws Exception {
            Session s = null;
            try {
                boolean allOk = true;
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                for (Object o : persistentObjects) {
                    if (o instanceof Auditable) {
                        AuditoriaBasica a = ((Auditable) o).getAuditoria();
                        if (a != null && a.getBorrable() != null && !a.getBorrable()) {
                            return new ErrorResponse("El registro no es borrable!!!");
                        } else {
                            s.delete(o);
                        }
                    } else {
                        s.delete(o);
                    }
                }
                try {
                    t.commit();
                } catch (Exception ex) {
                    LoggerUtil.error(this.getClass(), "deleteRecords", ex);
                    t.rollback();
                    allOk = false;
                }
                if (allOk) {
                    return new VOResponse(true);
                } else {
                    return new ErrorResponse("delete.constraint.violation");
                }
            } finally {
                s.close();
            }
        }

        @Override
        public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                for (Object o : newValueObjects) {
                    if (o instanceof Auditable) {
                        ((Auditable) o).setAuditoria(ab);
                    }

                    if (unidadd != null) {
                        Unidad m = (Unidad) s.get(Unidad.class, unidadd.getId());
                        Hibernate.initialize(m.getMedidas());
                        m.getMedidas().add((UnidadMedida) o);
                        ((UnidadMedida) o).setUnidad(m);
                        s.update(m);
                    } else {
                        s.save(o);
                    }
                }
                t.commit();
                return new VOListResponse(newValueObjects, false, newValueObjects.size());
            } catch (Exception ex) {
                return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
            } finally {
                s.close();
            }
        }
    }
}
