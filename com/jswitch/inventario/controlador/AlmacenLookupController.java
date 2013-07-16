/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.inventario.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.inventario.modelo.Almacen;
import com.jswitch.inventario.modelo.Unidad;
import com.jswitch.inventario.modelo.UnidadMedida;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.FilterWhereClause;
import org.openswing.swing.util.java.Consts;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Nelson Moncada
 */
public class AlmacenLookupController extends DefaultLookupController {

//    private Unidad unidadd;

    public AlmacenLookupController() {

        this.setLookupDataLocator(new AlmacenLookupDataLocator());
        this.setLookupGridController(new AlmacenLookupGridController());
        setLookupValueObjectClassName(Almacen.class.getName());

        setCodeSelectionWindow(GRID_FRAME);
        this.getLookupDataLocator().setNodeNameAttribute("nombre");
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_LAST_VALID_CODE);

        setAllColumnVisible(false);
        setVisibleColumn("id", true);
        setVisibleColumn("nombre", true);
        setVisibleColumn("observacion", true);
        setVisibleColumn("principal", true);
        setVisibleColumn("ubicacion", true);

        setAllColumnRequired(false);
        setColumnRequired("nombre", true);
//        setColumnRequired("observacion", true);
        setColumnRequired("principal", true);
        setColumnRequired("ubicacion", true);

        setGridEditButton(true);
        setColumnEditableOnEdit("id", false);
        setColumnEditableOnEdit("nombre", true);
        setColumnEditableOnEdit("observacion", true);
        setColumnEditableOnEdit("principal", true);
        setColumnEditableOnEdit("ubicacion", true);

        setGridInsertButton(true);
        setColumnEditableOnInsert("id", false);
        setColumnEditableOnInsert("nombre", true);
        setColumnEditableOnInsert("observacion", true);
        setColumnEditableOnInsert("principal", true);
        setColumnEditableOnInsert("ubicacion", true);

        setPreferredWidthColumn("id", 50);
        setPreferredWidthColumn("nombre", 200);

        setFilterableColumn("nombre", true);

        setSortableColumn("id", true);
        setSortableColumn("nombre", true);

        setSortedColumn("nombre", Consts.DESC_SORTED, 0);

        setFramePreferedSize(new Dimension(400, 300));

    }

    class AlmacenLookupDataLocator extends DefaultLookupDataLocator {

        public AlmacenLookupDataLocator() {
        }

        @Override
        public Response validateCode(String code) {
            Session s = null;
            try {
                String sql = "FROM " + Almacen.class.getName() + " C " + ""
                        + "WHERE C.auditoria.activo=? AND upper(C.nombre) like ?";
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
        public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType) {
            Session s = null;
            try {
                String sql = "FROM " + Almacen.class.getName() + " C ";
                filteredColumns.put(
                        "auditoria.activo",
                        new FilterWhereClause[]{
                            new FilterWhereClause("auditoria.activo", "=", true),
                            null
                        });
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
                return res;
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "loadData", ex);
                return new ErrorResponse(ex.getMessage());
            } finally {
                s.close();
            }
        }
//        @Override
//        public Response getTreeModel(JTree tree) {
//            Session s = null;
//            try {
//                Unidad vo = null;
//                vo = new Unidad();
//                vo.setId(new Long(-1));
//                vo.setNombre("Unidades");
//                DefaultMutableTreeNode root = new OpenSwingTreeNode(vo);
//                s = HibernateUtil.getSessionFactory().openSession();
//                List unidades = s.createQuery("FROM " + Unidad.class.getName() + " M ORDER BY M.nombre").list();
//                for (Unidad unidad : (List<Unidad>) unidades) {
//                    root.add(new OpenSwingTreeNode(unidad));
//                }
//                return new VOResponse(new DefaultTreeModel(root));
//            } catch (Exception ex) {
//                LoggerUtil.error(this.getClass(), "getTreeModel", ex);
//                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
//            } finally {
//                s.close();
//            }
//        }
    }

    class AlmacenLookupGridController extends DefaultLookupGridController {

        public AlmacenLookupGridController() {
        }
        
    }
}
