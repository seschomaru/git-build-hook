package com.rudikershaw.gitbuildhook;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.maven.it.Verifier;
import org.junit.Test;

/** Unit and integration tests for the GitBuildHookMojo. */
public class InitializeMojoTest extends AbstractMojoTest {
    /**
     * Tests that a new repo is initialised if none exists and the initialise flag is configured.
     *
     * @throws IOException if a temp project cannot be created for testing.
     */
    @Test
    public void testInitialiseNewGitRepo() throws Exception {
        moveToTempTestDirectory("test-project-initialise", "pom.xml");

        final File rootFolder = getFolder().getRoot();
        assertTrue(rootFolder.exists());
        final Verifier verifier = getVerifier(rootFolder.toString());
        verifier.executeGoal("install");
        verifier.verifyErrorFreeLog();
        verifier.assertFilePresent(".git");
        verifier.resetStreams();
    }
}

