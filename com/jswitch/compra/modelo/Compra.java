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
import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *  Clase Asociativa la persona y el tipo de direccion
 *  @version 1.0 22/05/2009
 *  @since JDK 1.5
 *  @see Persona
 *  @see TipoDireccion
 * @author Nelson Moncada
 */
@Entity
@Table(name="COMP_Compra")
public class Compra extends BeanVO implements Serializable, Auditable {

    /**
     *  PK autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;

    /**
     *
     */
    @ManyToOne
    private Persona vendedor;

    /**
     * Direccion de la persona
     */
    @Column
    @BusinessKey
    private String numero;

    /**
     * 
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;

    /**
     * Direccion de la persona
     */
    @Column
    @BusinessKey
    private String codigoBarras;

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

    public Compra() {
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

    
}
