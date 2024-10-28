package org.automationutils.com.commonutils;

import io.cucumber.java.Scenario;
import lombok.Getter;
import lombok.Setter;


public class TestUitls {

    @Getter
    @Setter
    static volatile Scenario globalScenario;
}
