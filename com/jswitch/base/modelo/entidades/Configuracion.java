/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.base.modelo.entidades;

import com.jswitch.auditoria.modelo.Auditable;
import com.jswitch.auditoria.modelo.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author NELSON
 */
@Entity
public class Configuracion extends BeanVO implements Auditable {

    public Configuracion() {
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
     *
     */
    @Column
    @Size(min = 1)
    @BusinessKey
    private String rutaDocDigitales;
    /**
     *
     */
    @Column
    @Size(min = 1)
    @BusinessKey
    private String rutaReportes;

    /**
     *
     */
    @Column
    @Size(min = 1)
    @Email
    @BusinessKey
    private String emailSoporte;

    /*
     *
     */
    @Column
    @BusinessKey
    private Boolean smsBienvenida;

    /*
     *
     */
    @Column
    @BusinessKey
    private Boolean gridProfileManager;

    /**
     *
     */
    @Column
    @BusinessKey
    private String smsClave;
    /**
     *
     */
    @Column
    @BusinessKey
    private String smsUsuario;
    /**
     *
     */
    @Column
    @BusinessKey
    private String URLSms;
    /**
     */
    @Column
    @BusinessKey
    private String superintendencia;
    /**
     */
    @Column
    @BusinessKey
    private String servidorSMTP;
    /**
     */
    @Column
    @BusinessKey
    private String usuarioSMTP;
    /**
     */
    @Column
    @BusinessKey
    private String passSMTP;
    /**
     */
    @Column
    @BusinessKey
    private String servidorPOP3;
    /**
     */
    @Column
    @BusinessKey
    private String usuarioPOP3;

    /**
     */
    @Column
    @BusinessKey
    private String passPOP3;

    /*
     *
     */
    @Column
    @BusinessKey
    private Boolean smsVariosProductores;

    /*
     *
     */
    @Column
    @BusinessKey
    private Boolean textoMayuscula;
    /**
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public String getRutaDocDigitales() {
        return rutaDocDigitales;
    }

    public void setRutaDocDigitales(String rutaDocDigitales) {
        this.rutaDocDigitales = rutaDocDigitales;
    }

    public String getRutaReportes() {
        return rutaReportes;
    }

    public void setRutaReportes(String rutaReportes) {
        this.rutaReportes = rutaReportes;
    }

    public Boolean getSmsBienvenida() {
        return smsBienvenida;
    }

    public void setSmsBienvenida(Boolean smsBienvenida) {
        this.smsBienvenida = smsBienvenida;
    }

    public String getSmsClave() {
        return smsClave;
    }

    public void setSmsClave(String smsClave) {
        this.smsClave = smsClave;
    }

    public String getSmsUsuario() {
        return smsUsuario;
    }

    public void setSmsUsuario(String smsUsuario) {
        this.smsUsuario = smsUsuario;
    }

    public Boolean getSmsVariosProductores() {
        return smsVariosProductores;
    }

    public void setSmsVariosProductores(Boolean smsVariosProductores) {
        this.smsVariosProductores = smsVariosProductores;
    }

    public String getSuperintendencia() {
        return superintendencia;
    }

    public void setSuperintendencia(String superintendencia) {
        this.superintendencia = superintendencia;
    }

    public Boolean getTextoMayuscula() {
        return textoMayuscula;
    }

    public void setTextoMayuscula(Boolean textoMayuscula) {
        this.textoMayuscula = textoMayuscula;
    }

    public Long getId() {
        return id;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public String getURLSms() {
        return URLSms;
    }

    public void setURLSms(String URLSms) {
        this.URLSms = URLSms;
    }

    public String getServidorSMTP() {
        return servidorSMTP;
    }

    public void setServidorSMTP(String servidorSMTP) {
        this.servidorSMTP = servidorSMTP;
    }

    public String getServidorPOP3() {
        return servidorPOP3;
    }

    public void setServidorPOP3(String servidorPOP3) {
        this.servidorPOP3 = servidorPOP3;
    }

    public String getEmailSoporte() {
        return emailSoporte;
    }

    public void setEmailSoporte(String emailSoporte) {
        this.emailSoporte = emailSoporte;
    }

    public String getPassPOP3() {
        return passPOP3;
    }

    public void setPassPOP3(String passPOP3) {
        this.passPOP3 = passPOP3;
    }

    public String getPassSMTP() {
        return passSMTP;
    }

    public void setPassSMTP(String passSMTP) {
        this.passSMTP = passSMTP;
    }

    public String getUsuarioPOP3() {
        return usuarioPOP3;
    }

    public void setUsuarioPOP3(String usuarioPOP3) {
        this.usuarioPOP3 = usuarioPOP3;
    }

    public String getUsuarioSMTP() {
        return usuarioSMTP;
    }

    public void setUsuarioSMTP(String usuarioSMTP) {
        this.usuarioSMTP = usuarioSMTP;
    }

    public Boolean getGridProfileManager() {
        return gridProfileManager;
    }

    public void setGridProfileManager(Boolean gridProfileManager) {
        this.gridProfileManager = gridProfileManager;
    }

}
