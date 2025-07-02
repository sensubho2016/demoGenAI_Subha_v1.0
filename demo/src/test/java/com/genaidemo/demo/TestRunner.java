package com.genaidemo.demo;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.DataProvider;
import com.genaidemo.demo.utility.PdfReportGenerator;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

@CucumberOptions(
		features = "classpath:features",
		glue = {"com.genaidemo.demo", "com.genaidemo.demo.hooks"},
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "com.genaidemo.demo.utility.CustomPdfReportPlugin"},
		tags = "@loginTest3",
		monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	public static void main(String[] args) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectPackage("com.genaidemo.demo"))
                .build();

        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(PdfReportGenerator.getSummaryListener());
        launcher.execute(request);

        // Generate the PDF report after tests
        PdfReportGenerator.generateReportAfterTests();
    }
}
