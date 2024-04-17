package HoyoJava;

import HoyoJava.API.Language;
import HoyoJava.API.MihomoAPI;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * The main App class, being used for testing ATM
 * 
 * @author Luna Karch
 */
public class App {
    public static void main(String[] args) throws Exception {
        MihomoAPI api = new MihomoAPI(Language.en);

        Client client = new Client(api, "613792348");
        JsonNode src = client.connect();

        if (src != null) {
            System.out.println(src.get("player"));
        }
    }
}