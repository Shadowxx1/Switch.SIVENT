/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.base.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NELSON
 */
public class Clave {

    public Clave(String passAdmin, String usuarioAdmin ,String passDB, String usuarioDB) {
        crear(passAdmin, usuarioAdmin, passDB, usuarioDB);
    }

    private void crear(String passAdmin, String usuarioAdmin ,String passDB, String usuarioDB) {
        try {
            File f = new File("sip.conf");
            if (!f.exists()) {
                f.createNewFile();
            }
            try {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(f));
                Conf conf= new Conf();

                conf.setPassAdmin(passAdmin);
                conf.setPassDB(passDB);
                conf.setUsuarioAdmin(usuarioAdmin);
                conf.setUsuarioDB(usuarioDB);

                outputStream.writeObject(conf);
                outputStream.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Clave.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Clave.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Clave.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Conf leer() {
        File f = new File("sip.conf");

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(f));
            Conf conf = (Conf) inputStream.readObject();
            inputStream.close();
            return conf;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Clave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Clave.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        new Clave("nelson2011", "nelson", "1234", "postgres");
        System.out.println(Clave.leer().getPassDB());
        System.out.println(Clave.leer().getUsuarioDB());
    }
}
