package HoyoJava.Clients;

import com.fasterxml.jackson.databind.JsonNode;

import HoyoJava.HSR.HSRProfile;

/**
 * Represents a Client, a user who will attempt to connect to the api.
 * The goal of this class is to make Interacting with the API a little easier
 * and to manage how a user is allowed to interact with the API
 * 
 * @author Luna Karch
 */
public class Client {
    private final String UID;
    private static final String ASSET_URL = "https://raw.githubusercontent.com/Mar-7th/StarRailRes/master";
    private JsonNode SRC;

    /**
     * @param Original String the original source, ex: "icon/relic/306.png"
     * @return The actual asset url string
     */
    public static String getActualURL(String Original) {
        return ASSET_URL + "/" + Original;
    }

    public Client(String UID) {
        this.UID = UID;

        try { 
            this.SRC = ClientManager.API.getDefault(this.UID);
            ClientManager.add(this);
        } catch (Exception e) {
            this.SRC = null;
            System.out.println("Failed to connect to MihomoAPI...");
        } // Automatically attempt to retrieve the data upon object creation
    }

    public String getUID() { return this.UID; }

    /**
     * Attempts to reconnect to the API
     */
    public void attemptReconnect() {
        try {
            this.SRC = ClientManager.API.getDefault(this.UID);
            
            if (!ClientManager.contains(this)) {
                ClientManager.add(this);
            }

        } catch (Exception e) {
            this.SRC = null;
            System.out.println("Failed to connect to MihomoAPI...");
        }
    }

    public JsonNode getPlayer() {
        return this.SRC.get("player");
    }

    public JsonNode getCharacters() {
        return this.SRC.get("characters");
    }

    public HSRProfile getHSRProfile() {
        if (SRC != null) {
            return new HSRProfile(SRC);
        }

        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Client)) {
            return false;
        }

        Client other = (Client)object;
        return other.getUID().equals(this.getUID());
    }
}