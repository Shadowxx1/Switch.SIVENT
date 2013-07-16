/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jswitch.base.modelo.entidades;

import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author NELSON
 */
public class Permiso {
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
    @BusinessKey
    private Boolean consultar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean crear;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean editar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean borrar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean verEditar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean verGuardar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean verCrear;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean verBorrar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean verExportar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean verImportar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean verRecargar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean verCopiar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean verFiltrar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean verBarraNavegacion;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean enableEditar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean enableGuardar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean enableCrear;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean enableBorrar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean enableExportar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean enableImportar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean enableRecargar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean enableCopiar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean enableFiltrar;

    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean enableBarraNavegacion;
    
}
