/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jswitch.base.modelo.entidades;

import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author NELSON
 */
@Entity
public class Departamento {

    public Departamento() {
    }


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
    private String emailDepartamentoSoporte;

    /**
     *
     */
    @Column
    @Email
    @BusinessKey
    private String emailSistemaSoporte;

    /**
     *
     */
    @Version
    @Column
    private Integer optLock;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmailDepartamentoSoporte() {
        return emailDepartamentoSoporte;
    }

    public void setEmailDepartamentoSoporte(String emailDepartamentoSoporte) {
        this.emailDepartamentoSoporte = emailDepartamentoSoporte;
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

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
