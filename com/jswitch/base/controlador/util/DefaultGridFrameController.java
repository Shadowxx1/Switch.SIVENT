package com.jswitch.base.controlador.util;

import com.jswitch.auditoria.modelo.Auditable;
import com.jswitch.auditoria.modelo.AuditoriaBasica;
import com.jswitch.base.controlador.General;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.server.HibernateUtils;
import com.jswitch.base.vista.util.DefaultGridFrame;
import org.hibernate.transform.AliasedTupleSRT;

/**
 *
 * @author Orlando Becerra
 * @author Adrian Gonzalez
 */
public class DefaultGridFrameController extends GridController implements GridDataLocator {

    protected String claseModeloFullPath;
    protected String detailFramePath;
    protected DefaultGridFrame gridFrame;

    public DefaultGridFrameController() {
    }

    public DefaultGridFrameController(String gridFramePath,
            String detailFramePath, String claseModeloFullPath, String titulo) {
        this.detailFramePath = detailFramePath;
        this.claseModeloFullPath = claseModeloFullPath;
        if (titulo == null) {
            titulo = claseModeloFullPath;
        }
        try {
            Class<DefaultGridFrame> t = (Class<com.jswitch.base.vista.util.DefaultGridFrame>) Class.forName(gridFramePath);
            gridFrame = t.newInstance();
            gridFrame.inicializar(this, this, claseModeloFullPath, titulo, true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns,
            ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {

            String tableName = "C";
            String select = gridFrame.getGridControl().getVOListTableModel().
                    createSelect("C", AliasedTupleSRT.SEPARATOR);
            String sql = select + " FROM " + claseModeloFullPath + " " + tableName + " ";
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getBlockFromQuery(
                    new AliasedTupleSRT(Class.forName(claseModeloFullPath)),
                    action,
                    startIndex,
                    General.licencia.getBlockSize(),
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    sql,
                    new Object[0],
                    new Type[0],
                    tableName,
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

    @Override
    public void loadDataCompleted(boolean error) {
        //if (gridFrame.getGridControl().getVOListTableModel().getRowCount() > 0) {
        gridFrame.setOwnerVO((BeanVO) gridFrame.getGridControl().getVOListTableModel().getObjectForRow(0));
        //}
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
                s.save(o);
                gridFrame.setOwnerVO((BeanVO) o);
            }
            t.commit();

            return new VOListResponse(newValueObjects, false, newValueObjects.size());
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
        } finally {
            s.close();
        }
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            for (Object o : persistentObjects) {
                if (o instanceof Auditable) {
                    AuditoriaBasica a = ((Auditable) o).getAuditoria();
                    if (a != null && a.getEditable2() != null && !a.getEditable2()) {
                        return new ErrorResponse("El registro no permite modificación!!!");
                    } else {
                        AuditoriaBasica ab = ((Auditable) o).getAuditoria();
                        ab.setFechaUpdate(new Date());
                        ab.setUsuarioUpdate(General.usuario.getUserName());
                    }
                }
                s.update(o);
            }
            t.commit();
            return new VOListResponse(persistentObjects, false, persistentObjects.size());
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "updateRecords", ex));
        } finally {
            s.close();
        }
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
            System.out.println("sesion cerrada*******------------------");
            s.close();
        }
    }

    @Override
    public void afterDeleteGrid() {
        gridFrame.reloadGridsData();
        gridFrame.setOwnerVO((BeanVO) gridFrame.getGridControl().getVOListTableModel().getObjectForRow(0));
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (detailFramePath != null) {
            new DefaultDetailFrameController(detailFramePath, gridFrame.getGridControl(), (BeanVO) persistentObject, false);
        }
    }

    @Override
    public void selectedCell(int rowNumber, int columnIndex, String attributedName, ValueObject persistentObject) {
        //super.selectedCell(rowNumber, columnIndex, attributedName, persistentObject);
        gridFrame.setOwnerVO((BeanVO) persistentObject);
    }
}
