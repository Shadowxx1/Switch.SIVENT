package com.jswitch.sms.controlador;

import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import java.util.ArrayList;
import java.util.Map;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import com.jswitch.sms.modelo.SMSEntradaActualizar;


/**
 *
 * @author Nelson Moncada
 * @author adrian.and1
 */
public class SMSEntradaGridFrameController extends DefaultGridFrameController {

    public SMSEntradaGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
      new SMSEntradaActualizar().Actualizar();
        return super.loadData(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, valueObjectType, otherGridParams);
    }


    //TODO crear el visor de mensaje como si fuera para mi
    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (detailFramePath != null) {
            new SMSEntradaDetailController(detailFramePath, gridFrame.getGridControl(), (BeanVO) persistentObject, false);
        }
    }


}
