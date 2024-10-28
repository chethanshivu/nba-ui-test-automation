package org.automationutils.com.commonutils;

import io.cucumber.java.Scenario;


public class ScenarioContext {

    private static ThreadLocal<Scenario> currentScenario = new ThreadLocal<>();

    /**
     *
     * Set the current cucumber scenario
     * @param scenario
     */
    public static void setCurrentScenario(Scenario scenario) {
        currentScenario.set(scenario);
    }

    /**
     *
     * get the cucumber Scenario
     * @return
     */
    public static Scenario getCurrentScenario() {
        return currentScenario.get();
    }

    /**
     *
     * logging into cucumber reports
     * @param message
     */
    public static void logInfo(String message) {
        getCurrentScenario().log(message);
    }

    /**
     *
     * Attach file to cucumber report
     * @param filename
     * @param content
     */
    public static void attachFile(String filename, byte[] content) {
        getCurrentScenario().attach(content, "application/octet-stream", filename);
    }
}
