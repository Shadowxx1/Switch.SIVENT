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
import javax.validation.constraints.Size;

/**
 *
 * @author NELSON
 */
public class Permisologia {
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
    @Size(min = 4, max = 100)
    @BusinessKey
    private String nombre;
}
