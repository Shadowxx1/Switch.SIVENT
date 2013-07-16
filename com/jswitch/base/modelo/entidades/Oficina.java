package com.jswitch.base.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.Size;

/**
 *
 * @author Orlando Becerra
 */
@Entity
@Table(name="SYST_Oficina")
public class Oficina extends BeanVO implements Serializable {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     */
    @Column
    @Size(min = 2, max = 240)
    @BusinessKey
    private String nombre;
    /**
     */
    @Column
    @Size(min = 2, max = 240)
    @BusinessKey
    private String codigo;
    /**
     */
    @Column
    @BusinessKey
    private String responsable;
    /**
     */
    @Column
    @BusinessKey
    private String telefono;
    /**
     */
    @Column
    @BusinessKey
    private String direccion;
    /**
     */
    @Column
    @BusinessKey
    private String observacion;
    /**
     *
     */
    @Column
    @Email
    @BusinessKey
    private String emailOficinaSoporte;
    /**
     *
     */
    @Column
    @Email
    @BusinessKey
    private String emailSistemaSoporte;

    /**
     * Oficinas de la Empresa
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<Departamento> departamentos = new ArrayList<Departamento>(0);
    
    /**
     *
     */
    @Version
    @Column
    private Integer optLock;

    public Oficina() {
    }

    public Oficina(String nombre) {
        this.nombre = nombre;
    }

    public String getEmailSistemaSoporte() {
        return emailSistemaSoporte;
    }

    public void setEmailSistemaSoporte(String emailSistemaSoporte) {
        this.emailSistemaSoporte = emailSistemaSoporte;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public String getEmailOficinaSoporte() {
        return emailOficinaSoporte;
    }

    public void setEmailOficinaSoporte(String emailOficinaSoporte) {
        this.emailOficinaSoporte = emailOficinaSoporte;
    }
    
}
