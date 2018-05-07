package org.helloworld.coverage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.jacoco.agent.rt.IAgent;
import org.jacoco.agent.rt.RT;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class TestHubListener extends RunListener {

    private final IAgent agent;

    public TestHubListener() {
        this.agent = RT.getAgent();
    }

    private boolean testStarted;

    @Override
    public void testRunStarted(Description description) throws Exception {
        super.testRunStarted(description);
        System.out.println("All Tests Started");
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);
        System.out.printf("All Tests Finished");
    }

    @Override
    public void testStarted(Description description) throws Exception {
        if (testStarted) {
            throw new RuntimeException("Error");
        }
        dump("");
        testStarted = true;
    }

    @Override
    public void testFinished(Description description) throws Exception {

        final String name = getName(description);
        final byte[] executionData = agent.getExecutionData(false);
        Files.write(Paths.get(".", "target", name + ".exec"), executionData);
        dump(name);
        testStarted = false;
    }

    private static String getName(Description description) {
        return description.getClassName() + "_" + description.getMethodName();
    }

    private void dump(String sessionId) {
        agent.setSessionId(sessionId);
        try {
            agent.dump(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
