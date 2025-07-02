package com.genaidemo.demo;

import com.genaidemo.demo.utility.CustomPdfReportPlugin;
import com.genaidemo.demo.utility.PdfReportGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

@Slf4j
@ComponentScan
@EnableAutoConfiguration
public class TestMainJava {
    public static void main(String[] args) {

        String tagName = "@loginTest3";


        String numberOfThreads = "1";
        for (int i = 0; i < args.length; i++) {
            if (i == 0) {
                tagName = args[i];
            }
            if (i == 1) {
                numberOfThreads = args[i];
            }
        }
        log.info("TestMain Starting Test Tag:" + tagName);
        log.info("Threads count:" + numberOfThreads);
        String[] cucumberOptionsArguments = {"--threads", numberOfThreads, "classpath:features", "--glue", "com.genaidemo.demo", "--tags", tagName, "--plugin", "junit:report/junitreport.xml", "--plugin", "html:report/htmlreport.html", "--plugin", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","--plugin", "com.genaidemo.demo.utility.CustomPdfReportPlugin"};

        io.cucumber.core.cli.Main.main(cucumberOptionsArguments);


    }

}
