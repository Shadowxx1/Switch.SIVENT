package com.jswitch.base.modelo.entidades;

import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.persona.modelo.transac.CuentaBancariaPersona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.Size;

/**
 *
 * @author Nelson Moncada
 */
@Entity
public class Empresa extends BeanVO implements Serializable {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Coleccion de cuentas bancarias de la empresa
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<CuentaBancariaPersona> cuentasBancarias = new ArrayList<CuentaBancariaPersona>(0);

    /**
     * Oficinas de la Empresa
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @BusinessKey(exclude = Method.ALL)
    private List<Oficina> oficinas = new ArrayList<Oficina>(0);

    /**
     *
     */
    @Column
    @BusinessKey
    private String rif;
    /**
     */
    @Column
    @Size(min = 2, max = 240)
    @BusinessKey
    private String nombre;
    /**
     */
    @Column
    @BusinessKey
    private String telefonos;

    /**
     */
    @Column
    @BusinessKey
    private String direccion;
    /**
     * Email de la persona
     */
    @Column
    @Email
    @BusinessKey
    private String email;
    /**
     *
     */
    @Column
    @BusinessKey
    private String web;
    /**
     *
     */
    @Column
    @Email
    @BusinessKey
    private String emailEmpresaSoporte;
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

    public Empresa() {
    }

    public Empresa(String rif2, String nombre, String emailEmpresaSoporte, String emailSistemaSoporte, String rutaDocDigitales, String rutaReportes) {
        this.rif = rif2;
        this.nombre = nombre;
        this.emailEmpresaSoporte = emailEmpresaSoporte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailEmpresaSoporte() {
        return emailEmpresaSoporte;
    }

    public void setEmailEmpresaSoporte(String emailEmpresaSoporte) {
        this.emailEmpresaSoporte = emailEmpresaSoporte;
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

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public List<CuentaBancariaPersona> getCuentasBancarias() {
        return cuentasBancarias;
    }

    public void setCuentasBancarias(List<CuentaBancariaPersona> cuentasBancarias) {
        this.cuentasBancarias = cuentasBancarias;
    }

    public List<Oficina> getOficinas() {
        return oficinas;
    }

    public void setOficinas(List<Oficina> oficinas) {
        this.oficinas = oficinas;
    }
}
