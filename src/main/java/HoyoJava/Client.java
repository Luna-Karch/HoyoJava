package HoyoJava;

import HoyoJava.API.MihomoAPI;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents a Client, a user who will attempt to connect to the api.
 * The goal of this class is to make Interacting with the API a little easier
 * and to manage how a user is allowed to interact with the API
 * 
 * @author Luna Karch
 */
public class Client {
    private final MihomoAPI API;
    private final String UID;

    public Client(MihomoAPI API, String UID) {
        this.API = API;
        this.UID = UID;
    }

    public String getUID() { return this.UID; }

    /**
     * Attempts to connect to the API, retrieves data from the API as a JsonNode object.
     *
     * If the connection or data retrieval process fails due to any exception, this method
     * will catch the exception and return null. 
     *
     * @return JsonNode containing the data retrieved from the API if successful, or null
     * if the connection or data retrieval fails.
     */
    public JsonNode connect() {
        try {
            JsonNode src = API.getDefault(this.UID); 
            return src;
        } catch (Exception e) {
            System.out.println("Failed to connect to MihomoAPI...");
            return null;
        }
    }
}