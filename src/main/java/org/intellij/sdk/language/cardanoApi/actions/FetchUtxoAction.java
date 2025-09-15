package org.intellij.sdk.language.cardanoApi.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.intellij.sdk.language.cardanoApi.UtxoFetcher;
import org.intellij.sdk.language.wallet.WalletApiKeyState;

import javax.swing.*;

public class FetchUtxoAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;

        // Get stored API key & network from WalletApiKeyState
        WalletApiKeyState state = WalletApiKeyState.getInstance();
        state.setProject(project); // ensure project is tracked
        String apiKey = state.getApiKey();
        String network = state.getNetwork();

        if (apiKey == null || apiKey.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No API Key found. Please set your API Key in the Wallet settings.",
                    "Missing API Key",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (network == null || network.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No Network selected. Please set the Cardano network in the Wallet settings.",
                    "Missing Network",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String scriptAddress = JOptionPane.showInputDialog("Enter Cardano script address (addr1...)");
        if (scriptAddress == null || scriptAddress.isEmpty()) {
            return;
        }

        UtxoFetcher fetcher = new UtxoFetcher();
        fetcher.fetchAndShowUtxo(project, apiKey, network, scriptAddress);
    }
}
