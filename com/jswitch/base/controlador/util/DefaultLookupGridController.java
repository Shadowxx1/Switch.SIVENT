package com.jswitch.base.controlador.util;

import com.jswitch.auditoria.modelo.Auditable;
import com.jswitch.auditoria.modelo.AuditoriaBasica;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openswing.swing.lookup.client.LookupGridController;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;

/**
 *
 * @author Nelson Moncada
 */
public class DefaultLookupGridController extends LookupGridController {

    public DefaultLookupGridController() {
        
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
//                gridFrame.setOwnerVO((BeanVO) o);
            }
            t.commit();

            return new VOListResponse(newValueObjects, false, newValueObjects.size());
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "Error Insertando Registro", ex));
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
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "Error Actualizando Registro", ex));
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
                return new ErrorResponse("Hay otros registros asosiados al registro actual y no puede ser eliminado");
            }
        } finally {
            s.close();
        }
    }

}
