
import com.jswitch.base.controlador.Principal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NELSON
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            BufferedReader b = null;
            b = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("hostname").getInputStream()));
            String hostname = b.readLine();
            System.getProperties().put("COMPUTERNAME", hostname);
            System.getProperties().put("valido", "si");
            if (args == null || args.length == 0) {
                new Principal();
                Principal.splah.dispose();
            }
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
