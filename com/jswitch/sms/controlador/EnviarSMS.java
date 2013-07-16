package com.jswitch.sms.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.modelo.Dominios.EstatusSMS;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.persona.modelo.transac.TelefonoPersona;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.classic.Session;
import com.jswitch.sms.modelo.SMS;
import com.jswitch.sms.vista.SMSDetailFrame;

/**
 *
 * @author Nelson Moncada
 */
public class EnviarSMS {

    public static Set<Persona> getPersonas(String numero) {
        if (numero.startsWith("0") && numero.length() == 11) {
            numero = numero.replaceFirst("0", "");
        }
        List<Persona> l = null;
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            l = s.createQuery("SELECT DISTINCT C FROM " + Persona.class.getName() + " C "
                    + "RIGHT JOIN C.telefonos T WHERE T.numeroCompleto=?").setString(0, numero).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            l = new ArrayList<Persona>(0);
        } finally {
            s.close();
        }
        return new HashSet<Persona>(l);
    }

    public static void crearSMS(String nombre, String nombreCorto, Set<TelefonoPersona> telefonos, Persona p) {
        SMS sms = new SMS();
        sms.setNombre(nombre);
        String numero = "";
        for (TelefonoPersona telefonoPersona : telefonos) {
            if (telefonoPersona.getNotificar()) {
                numero += "0" + telefonoPersona.getNumeroCompleto() + ",";
            }
        }
        if (numero.length() > 0) {
            numero = numero.substring(0, numero.length() - 1);
        } else {
            numero = "S/N";
        }
        sms.setNumero(numero);
        new SMSDetailController(SMSDetailFrame.class.getName(), null, sms, false, p);
    }

    public static void crearSMS(String nombre, String prod, Set<TelefonoPersona> telefonos) {
        SMS sms = new SMS();
        sms.setNombre(nombre);
        String numero = "";
        for (TelefonoPersona telefonoPersona : telefonos) {
            if (telefonoPersona.getNotificar()) {
                numero += "0" + telefonoPersona.getNumeroCompleto() + ",";
            }
        }
        if (numero.length() > 0) {
            numero = numero.substring(0, numero.length() - 1);
        } else {
            numero = "S/N";
        }
        sms.setNumero(numero);
        new SMSDetailController(SMSDetailFrame.class.getName(), null, sms, false);
    }

    public static EstatusSMS enviarSMS(SMS sms) throws UnsupportedEncodingException {
        if (sms.getTexto().length() > 160) {
            return EstatusSMS.MUY_LARGO;
        }
        if ((sms.getNumero().length() < 11)) {
            return EstatusSMS.SIN_NUMERO;
        }
//        String host = "http://www.interconectados.net/api2/?";
        String host = General.configuracion.getURLSms();
        String usuario = General.configuracion.getSmsUsuario();
        String clave = General.configuracion.getSmsClave();
        String telf = sms.getNumero();
        String text = java.net.URLEncoder.encode(sms.getTexto(), "ISO-8859-1");


        URL url;
        try {
            url = new URL(host + "phonenumber=" + telf + "&Text=" + text + "&user=" + usuario
                    + "&password=" + clave);
            BufferedReader b = new BufferedReader(new InputStreamReader(url.openStream()));
            String respuesta = b.readLine();
            b.close();
            if (respuesta.startsWith("200")) {
                saveSMS(sms);
                return EstatusSMS.ENVIADO;
            }
            if (respuesta.equals("401")) {
                return EstatusSMS.ATENTICACION_INVALIDA;
            }
            if (respuesta.equals("402")) {
                return EstatusSMS.CREDITOS_INSUFICIENTES;
            }
            if (respuesta.equals("403")) {
                return EstatusSMS.CUENTA_INVALIDA;
            }
            if (respuesta.equals("501")) {
                return EstatusSMS.TIPOCUENTA_INVALIDA;
            }
            if (respuesta.equals("502")) {
                return EstatusSMS.PETICION_SOBRECARGADA;
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.err.println(ex.getMessage());
            System.out.println("sin internet");
            return EstatusSMS.NO_ENVIADO2;
        }
        return EstatusSMS.NO_ENVIADO;
    }

    public static boolean enviarSMSs(Set<SMS> smss) {
        if (hayInternet()) {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                for (SMS sms : smss) {
                    EstatusSMS estatus =
                            enviarSMS(sms);
                    sms.setEstatus(estatus);
                    s.update(sms);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                s.close();
            }
        } else {
            return false;
        }
    }

    public static boolean hayInternet() {
        try {
            URL url = new URL("http://www.interconectados.net");
            BufferedReader b = new BufferedReader(new InputStreamReader(url.openStream()));
            String respuesta = b.readLine();
            System.out.println(respuesta);
            b.close();
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    public static boolean saveSMS(SMS sms) {
        try {
            String telf = java.net.URLEncoder.encode(sms.getNumero(), "ISO-8859-1");
            String texto = java.net.URLEncoder.encode(sms.getTexto(), "ISO-8859-1");
            String usuario = java.net.URLEncoder.encode(General.empresa.getNombre(), "ISO-8859-1");
            String para = java.net.URLEncoder.encode(sms.getNombre(), "ISO-8859-1");
            URL url = new URL("http://desolinfor.comze.com//smsbox/index.php?telf=" + telf + "&texto=" + texto + "&usuario=" + usuario + "&para=" + para);
            BufferedReader b = new BufferedReader(new InputStreamReader(url.openStream()));
            String respuesta = b.readLine();
            System.out.println(respuesta);
            b.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean hayInternetHost2() {
        try {
            URL url = new URL("http://desolinfor.comze.com//smsbox/index.php?");
            BufferedReader b = new BufferedReader(new InputStreamReader(url.openStream()));
            String respuesta = b.readLine();
            System.out.println(respuesta);
            b.close();
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
}
