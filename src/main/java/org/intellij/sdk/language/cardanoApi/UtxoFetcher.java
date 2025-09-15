package org.intellij.sdk.language.cardanoApi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.awt.Dimension;
import java.io.IOException;

/**
 * Utility class to fetch and display UTXO details from Blockfrost.
 */
public class UtxoFetcher {

    private static final String BASE_URL = "https://cardano-%s.blockfrost.io/api/v0";
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    /**
     * Fetch and show latest UTXO for the given script address.
     */
    public void fetchAndShowUtxo(Project project, String apiKey, String network, String scriptAddress) {
        String url = String.format(BASE_URL, network) + "/addresses/" + scriptAddress + "/utxos";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("project_id", apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                showError(project, "Failed to fetch UTXOs: " + response.message());
                return;
            }

            String body = response.body().string();
            JsonArray utxos = gson.fromJson(body, JsonArray.class);

            if (utxos.size() == 0) {
                showInfo(project, "No UTXOs found at address: " + scriptAddress);
                return;
            }

            JsonObject latestUtxo = utxos.get(utxos.size() - 1).getAsJsonObject();

            StringBuilder sb = new StringBuilder();
            sb.append("📦 Latest UTXO:\n")
                    .append("Tx Hash: ").append(latestUtxo.get("tx_hash").getAsString()).append("\n")
                    .append("Tx Index: ").append(latestUtxo.get("output_index").getAsInt()).append("\n");

            // Amounts
            JsonArray amounts = latestUtxo.get("amount").getAsJsonArray();
            sb.append("Amount(s):\n");
            for (int i = 0; i < amounts.size(); i++) {
                JsonObject amt = amounts.get(i).getAsJsonObject();
                sb.append(" - ").append(amt.get("quantity").getAsString())
                        .append(" ").append(amt.get("unit").getAsString()).append("\n");
            }

            // Datum hash or inline datum
            if (latestUtxo.has("data_hash") && !latestUtxo.get("data_hash").isJsonNull()) {
                String dataHash = latestUtxo.get("data_hash").getAsString();
                sb.append("Datum Hash: ").append(dataHash).append("\n");

                // Fetch datum
                fetchDatum(project, apiKey, network, dataHash, sb);
            } else if (latestUtxo.has("inline_datum") && !latestUtxo.get("inline_datum").isJsonNull()) {
                sb.append("Inline Datum: ").append(latestUtxo.get("inline_datum").getAsString()).append("\n");
            }

            // Show in a popup
            showPopup(project, "Script UTXO Info", sb.toString());

        } catch (IOException e) {
            showError(project, "Error fetching UTXOs: " + e.getMessage());
        }
    }

    private void fetchDatum(Project project, String apiKey, String network, String dataHash, StringBuilder sb) {
        String url = String.format(BASE_URL, network) + "/scripts/datum/" + dataHash;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("project_id", apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String body = response.body().string();
                sb.append("Datum Value: ").append(body).append("\n");
            } else {
                sb.append("❌ Failed to fetch datum: ").append(response.message()).append("\n");
            }
        } catch (IOException e) {
            sb.append("❌ Error fetching datum: ").append(e.getMessage()).append("\n");
        }
    }

    private void showError(Project project, String message) {
        Notifications.Bus.notify(new Notification("CardanoPyC", "UTXO Error", message, NotificationType.ERROR), project);
    }

    private void showInfo(Project project, String message) {
        Notifications.Bus.notify(new Notification("CardanoPyC", "UTXO Info", message, NotificationType.INFORMATION), project);
    }

    private void showPopup(Project project, String title, String message) {
        SwingUtilities.invokeLater(() -> {
            JTextArea textArea = new JTextArea(message);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 400));

            JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
