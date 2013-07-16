/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jswitch.inventario.controlador;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import org.openswing.swing.client.GridControl;

/**
 *
 * @author NELSON
 */
public class ProductoDetailFrameController extends DefaultDetailFrameController {

    public ProductoDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

}
