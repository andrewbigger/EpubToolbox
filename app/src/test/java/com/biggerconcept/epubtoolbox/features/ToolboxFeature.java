package com.biggerconcept.epubtoolbox.features;

import static java.util.concurrent.TimeUnit.SECONDS;
import org.junit.Test;
import support.ToolboxUITestHarness;

public class ToolboxFeature extends ToolboxUITestHarness {
    @Test
    public void showToolboxForm() throws Exception {
        System.out.println("Show toolbox form");
        sleep(2, SECONDS);
    }
}
