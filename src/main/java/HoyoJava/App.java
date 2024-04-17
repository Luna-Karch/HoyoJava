package HoyoJava;

import HoyoJava.API.Language;
import HoyoJava.API.MihomoAPI;
import com.fasterxml.jackson.databind.JsonNode;

public class App {
    public static void main(String[] args) throws Exception {
        // UID -> 613792348
        MihomoAPI api = new MihomoAPI(Language.en);
        JsonNode src = api.getDefault("613792348");
        System.out.println(src.get("player"));
    }
}