/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jswitch.sms.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.util.java.Consts;
import com.jswitch.sms.modelo.SMSEntrada;

/**
 *
 * @author adrian
 */
class SMSEntradaDetailController extends DefaultDetailFrameController{

    public SMSEntradaDetailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio) {

        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;
        try {
            Class<DefaultDetailFrame> t = (Class<DefaultDetailFrame>) Class.forName(detailFramePath);
            vista = t.newInstance();
            vista.inicializar(this, true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
        if (beanVO == null) {
            vista.getMainPanel().setMode(Consts.INSERT);
        } else {
            if (((SMSEntrada) beanVO).getId() == null) {
                vista.getMainPanel().setMode(Consts.INSERT);
                vista.getMainPanel().getVOModel().setValue("texto", ((SMSEntrada) beanVO).getTexto());
                vista.getMainPanel().pull("texto");
                vista.getMainPanel().getVOModel().setValue("telefono", ((SMSEntrada) beanVO).getTelefono());
                vista.getMainPanel().pull("telefono");
            } else {
                vista.getMainPanel().reload();
                vista.getMainPanel().setMode(Consts.READONLY);
            }
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return new VOResponse(beanVO);
    }

}
