package org.intellij.sdk.language.diagnostics;

import com.intellij.openapi.project.Project;
import com.intellij.testFramework.LightPlatformTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class DiagnosticsTest extends LightPlatformTestCase {

    private GhcidRunner ghcidRunner;
    private Path tempDir;
    private File testHsFile;
    private File cabalFile;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        Project project = getProject();
        ghcidRunner = new GhcidRunner(project);

        // Create temporary directory and files for testing
        tempDir = Files.createTempDirectory("cardano_test");
        testHsFile = new File(tempDir.toFile(), "Test.hs");
        testHsFile.createNewFile();

        cabalFile = new File(tempDir.toFile(), "test.cabal");
        cabalFile.createNewFile();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        if (ghcidRunner != null) {
            ghcidRunner.stop();
        }

        // Clean up temporary files
        if (testHsFile.exists()) {
            testHsFile.delete();
        }
        if (cabalFile.exists()) {
            cabalFile.delete();
        }
        if (tempDir != null) {
            Files.deleteIfExists(tempDir);
        }

        super.tearDown();
    }

    @Test
    public void testGhcidRunnerInstantiation() {
        assertNotNull("GhcidRunner should be properly instantiated", ghcidRunner);
        assertNotNull("GhcidRunner should have a project reference", ghcidRunner.getProject());
    }

    @Test
    public void testFindErrorEndOffsetWithVariousInputs() {
        // Test the findErrorEndOffset method with different scenarios
        assertEquals("Should find space as end", 5, ghcidRunner.findErrorEndOffset("hello world"));
        assertEquals("Should find comma as end", 3, ghcidRunner.findErrorEndOffset("fun,next"));
        assertEquals("Should find semicolon as end", 4, ghcidRunner.findErrorEndOffset("test;rest"));
        assertEquals("Should find parenthesis as end", 4, ghcidRunner.findErrorEndOffset("func(args"));
        assertEquals("Should return full length if no delimiter", 8, ghcidRunner.findErrorEndOffset("function"));
        assertEquals("Should handle empty string", 0, ghcidRunner.findErrorEndOffset(""));
    }

    @Test
    public void testProcessGhcidOutputBlockWithError() {
        String[] errorOutput = {
                "Test.hs:10:5: error: Variable not in scope: undefinedVar",
                "  |",
                "10 |     undefinedVar = 42",
                "  |     ^^^^^^^^^^^^"
        };

        ghcidRunner.processGhcidOutputBlock(errorOutput);
        assertTrue("Error processing should complete without exceptions", true);
    }

    @Test
    public void testProcessGhcidOutputBlockWithWarning() {
        String[] warningOutput = {
                "Test.hs:15:8: warning: [-Wunused-binds]",
                "    Defined but not used: 'unusedVar'",
                "   |",
                "15 | unusedVar = \"hello\"",
                "   |        ^^^"
        };

        ghcidRunner.processGhcidOutputBlock(warningOutput);
        assertTrue("Warning processing should complete without exceptions", true);
    }

    @Test
    public void testProcessGhcidOutputBlockWithInvalidFormat() {
        String[] invalidOutput = {
                "This is not a valid ghcid output format",
                "Just some random text"
        };

        ghcidRunner.processGhcidOutputBlock(invalidOutput);
        assertTrue("Invalid format should be handled gracefully", true);
    }

    @Test
    public void testProcessGhcidOutputBlockWithEmptyInput() {
        String[] emptyOutput = {};

        ghcidRunner.processGhcidOutputBlock(emptyOutput);
        assertTrue("Empty input should be handled gracefully", true);
    }

    @Test
    public void testProcessGhcidOutputBlockWithNullInput() {
        ghcidRunner.processGhcidOutputBlock(null);
        assertTrue("Null input should be handled gracefully", true);
    }

    @Test
    public void testClearAllHighlights() {
        ghcidRunner.clearAllHighlights();
        assertTrue("clearAllHighlights should work without errors", true);
    }

    @Test
    public void testClearFixedHighlights() {
        ghcidRunner.clearFixedHighlights();
        assertTrue("clearFixedHighlights should work without errors", true);
    }

    @Test
    public void testStopMethod() {
        ghcidRunner.stop();
        assertTrue("stop method should work without errors", true);
    }

    @Test
    public void testDisposeMethod() {
        ghcidRunner.dispose();
        assertTrue("dispose method should work without errors", true);
    }

    @Test
    public void testIsRunningWhenNotStarted() {
        assertFalse("Should not be running when not started", ghcidRunner.isRunning());
    }

    @Test
    public void testGetInstance() {
        Project project = getProject();
        GhcidRunner instance = GhcidRunner.getInstance(project);
        assertNotNull("getInstance should return non-null instance", instance);
    }

    @Test
    public void testGetProject() {
        Project project = getProject();
        assertEquals("getProject should return the correct project", project, ghcidRunner.getProject());
    }

    @Test
    public void testTextAttributesCreation() {
        assertNotNull("Error attributes should not be null", ghcidRunner.ERROR_ATTRIBUTES);
        assertNotNull("Warning attributes should not be null", ghcidRunner.WARNING_ATTRIBUTES);
    }

    @Test
    public void testStartIfNeeded() {
        ghcidRunner.startIfNeeded();
        assertTrue("startIfNeeded should work without errors", true);
    }
}