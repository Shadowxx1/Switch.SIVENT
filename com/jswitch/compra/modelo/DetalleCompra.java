package com.jswitch.compra.modelo;

import com.jswitch.auditoria.modelo.Auditable;
import com.jswitch.auditoria.modelo.AuditoriaBasica;
import com.jswitch.persona.modelo.dominio.TipoDireccion;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *  Clase Asociativa la persona y el tipo de direccion
 *  @version 1.0 22/05/2009
 *  @since JDK 1.5
 *  @see Persona
 *  @see TipoDireccion
 * @author Orlando Becerra
 * @author Nelson Moncada
 */
@Entity
@Table(name="COMP_DetalleCompra")
public class DetalleCompra extends BeanVO implements Serializable, Auditable {

    /**
     *  PK autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Direccion de la persona
     */
    @Column
    @Size(min = 2, max = 255)
    @BusinessKey
    private String nombre;

    /**
     * Observacion de la direccion
     */
    @Column
    @BusinessKey
    private String observacion;

    /**
     */
    @Version
    @Column(name = "OPTLOCK")
    private Integer optLock;
    /**
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public DetalleCompra() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    @Override
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    @Override
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
