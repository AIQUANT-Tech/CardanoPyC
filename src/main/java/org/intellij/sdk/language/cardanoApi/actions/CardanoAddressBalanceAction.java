package org.intellij.sdk.language.cardanoApi.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.intellij.sdk.language.cardanoApi.CardanoScanFetcher;
import org.jetbrains.annotations.NotNull;

public class CardanoAddressBalanceAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // Get the current project
        Project project = e.getProject();
        if (project == null) {
            Messages.showErrorDialog("No active project found.", "Error");
            return;
        }

        // Create a fetcher instance
        CardanoScanFetcher fetcher = new CardanoScanFetcher(project);

        // Ensure API key
        String apiKey = fetcher.ensureApiKey();
        if (apiKey == null || apiKey.trim().isEmpty()) {
            Messages.showErrorDialog("API key is missing. Please enter a valid API key.", "API Key Missing");
            return;
        }

        // Prompt the user for an address
        String address = promptForAddress(project);
        if (address == null || address.trim().isEmpty()) {
            Messages.showErrorDialog("Please provide a valid address.", "Invalid Address");
            return;
        }

        // Fetch address details in the background
        new Task.Backgroundable(project, "Fetching address details") {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setText("Fetching balance for address...");
                try {
                    fetcher.fetchAddressDetails(address);
                } catch (Exception ex) {
                    throw new RuntimeException("Error fetching address details: " + ex.getMessage(), ex);
                }
            }

            @Override
            public void onSuccess() {
                Messages.showInfoMessage("Address details fetched successfully.", "Success");
            }

            @Override
            public void onThrowable(@NotNull Throwable error) {
                Messages.showErrorDialog("Error fetching address details: " + error.getMessage(), "Error");
            }
        }.queue();
    }


    private String promptForAddress(Project project) {
        return Messages.showInputDialog(project, "Enter the Cardano address:", "Address Input", Messages.getQuestionIcon());
    }
}