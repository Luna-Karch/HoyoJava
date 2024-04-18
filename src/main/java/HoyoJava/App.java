package HoyoJava;

import java.util.Map;
import java.util.Iterator;
import HoyoJava.Clients.Client;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * The main App class, being used for testing ATM
 * 
 * @author Luna Karch
 */
public class App {
    public static void main(String[] args) throws Exception {
        Client client = new Client("613792348");

        JsonNode characters = client.getCharacters();

        for (final JsonNode characterNode: characters) {
            Iterator<Map.Entry<String, JsonNode>> fields = characterNode.fields();

            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                System.out.println(field.getKey() + ": " + field.getValue());
            }
            break;
        }
    }
}