

package org.intellij.sdk.language.diagnostics;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.concurrency.AppExecutorUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HaskellFileListener implements FileEditorManagerListener {
    private final Map<Project, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    @Override
    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
        runGhcidIfHaskell(file, source.getProject());
    }

    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
        VirtualFile file = event.getNewFile();
        if (file != null) {
            Project project = event.getManager().getProject();
            // Cancel any existing scheduled task
            ScheduledFuture<?> existingTask = scheduledTasks.get(project);
            if (existingTask != null) {
                existingTask.cancel(false);
            }

            // Schedule new task with debounce
            ScheduledFuture<?> newTask = AppExecutorUtil.getAppScheduledExecutorService().schedule(
                    () -> runGhcidIfHaskell(file, project),
                    300, // 300ms debounce
                    TimeUnit.MILLISECONDS
            );

            scheduledTasks.put(project, newTask);
        }
    }

    private void runGhcidIfHaskell(VirtualFile file, Project project) {
        if (file.getName().endsWith(".hs")) {
            GhcidRunner runner = GhcidRunner.getInstance(project);

            // Check if cabal is available dynamically within this class
            boolean isCabalAvailable = isCabalInstalled();
            boolean isCabalProject = runner.isCabalProject();
            boolean isRunning = runner.isRunning();

            if (isCabalAvailable && isCabalProject && !isRunning) {
                runner.start();
            }
        }

        // Clean up the scheduled task after execution
        scheduledTasks.remove(project);
    }

    private boolean isCabalInstalled() {
        try {
            GeneralCommandLine commandLine = new GeneralCommandLine();
            if (SystemInfo.isWindows) {
                commandLine.setExePath("cmd");
                commandLine.addParameters("/c", "cabal --version");
            } else {
                commandLine.setExePath("/bin/bash");
                commandLine.addParameters("-c", "cabal --version");
            }

            // Create process and check exit code
            Process process = commandLine.createProcess();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }
}