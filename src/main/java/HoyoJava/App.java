package HoyoJava;

import HoyoJava.Clients.Client;

/**
 * The main App class, being used for testing ATM
 * 
 * @author Luna Karch
 */
public class App {
    public static void main(String[] args) throws Exception {
        Client client = new Client("613792348");
        System.out.println(client.getHSRProfile());
        System.out.println(client.getHSRCharacters());
    }
}