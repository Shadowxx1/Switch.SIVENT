package com.jswitch.inventario.modelo;

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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@Table(name="CONF_Unidad")
public class Unidad extends BeanVO implements Serializable, Auditable {

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

    @OneToMany(cascade=CascadeType.ALL, mappedBy="unidad", orphanRemoval=true)
    @OrderBy(value="nombre")
    private List<UnidadMedida> medidas= new ArrayList<UnidadMedida>(0);

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

    public Unidad() {
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

    public List<UnidadMedida> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<UnidadMedida> medidas) {
        this.medidas = medidas;
    }
    
}
