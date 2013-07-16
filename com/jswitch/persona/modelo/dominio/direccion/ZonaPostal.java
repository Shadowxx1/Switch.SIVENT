package com.jswitch.persona.modelo.dominio.direccion;

import com.jswitch.auditoria.modelo.Auditable;
import com.jswitch.auditoria.modelo.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

/**
 * Clase Dominio para los tipos de actividad economica que podria tener una
 * persona
 *
 * @version 1.0 22/05/2009
 * @since JDK 1.5
 * @author Orlando Becerra
 * @author Nelson Moncada
 */
@Entity
@Table(name = "PERS_DIR_ZonaPostal")
public class ZonaPostal extends BeanVO implements Serializable, Auditable {

    /**
     * PK autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Estado
     */
    @ManyToOne()
    @BusinessKey
    private Estado estado;
    /**
     */
    @Column
    @Size(min = 4, max = 120)
    @BusinessKey
    private String nombre;
    /**
     */
    @Column
    @Size(min = 4, max = 5)
    @BusinessKey
    private String zona;
    /**
     */
    @Version
    @Column
    private Integer optLock;
    /**
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public ZonaPostal() {
    }

    public ZonaPostal(String nombre, AuditoriaBasica auditoria) {
        this.nombre = nombre;
        this.auditoria = auditoria;
    }

    public ZonaPostal(Estado estado, String nombre, String zona, AuditoriaBasica auditoria) {
        this.estado = estado;
        this.nombre = nombre;
        this.zona = zona;
        this.auditoria = auditoria;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
