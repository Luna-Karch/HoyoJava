package HoyoJava;

import HoyoJava.Clients.Client;
import HoyoJava.HSR.HSRProfile;

/**
 * The main App class, being used for testing ATM
 * 
 * @author Luna Karch
 */
public class App {
    public static void main(String[] args) throws Exception {
        Client client = new Client("613792348");
        HSRProfile profile = client.getHSRProfile();
        System.out.println(profile);


        //System.out.println(client.getCharacters());
    }
}