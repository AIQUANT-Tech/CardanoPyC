package org.intellij.sdk.language.wallet;

import javax.net.ssl.HttpsURLConnection;

import java.net.URL;

public class BlockfrostApiValidator {

    public static boolean validate(String apiKey, String network) {
        try {
            String baseUrl = switch (network) {
                case "mainnet" -> "https://cardano-mainnet.blockfrost.io/api/v0/";
                case "preprod" -> "https://cardano-preprod.blockfrost.io/api/v0/";
                case "preview" -> "https://cardano-preview.blockfrost.io/api/v0/";
                default -> null;
            };

            if (baseUrl == null) return false;

            URL url = new URL(baseUrl + "blocks/latest");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("project_id", apiKey);
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            return responseCode == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
