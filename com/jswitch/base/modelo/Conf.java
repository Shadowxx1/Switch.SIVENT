/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jswitch.base.modelo;

import java.io.Serializable;

/**
 *
 * @author NELSON
 */
public class Conf implements Serializable{

    public Conf() {
    }

    private String passDB;
    private String usuarioDB;
    private String passAdmin;
    private String usuarioAdmin;
    private String nameBD;

    public String getPassAdmin() {
        return passAdmin;
    }

    public void setPassAdmin(String passAdmin) {
        this.passAdmin = passAdmin;
    }

    public String getPassDB() {
        return passDB;
    }

    public void setPassDB(String passDB) {
        this.passDB = passDB;
    }

    public String getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public void setUsuarioAdmin(String usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }

    public String getUsuarioDB() {
        return usuarioDB;
    }

    public void setUsuarioDB(String usuarioDB) {
        this.usuarioDB = usuarioDB;
    }

    public String getNameBD() {
        return nameBD;
    }

    public void setNameBD(String nameBD) {
        this.nameBD = nameBD;
    }

}
