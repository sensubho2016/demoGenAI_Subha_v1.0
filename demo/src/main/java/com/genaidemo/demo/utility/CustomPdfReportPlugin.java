package com.genaidemo.demo.utility;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomPdfReportPlugin implements EventListener {

    private final List<String> testResults = new ArrayList<>();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, this::onTestRunStarted);
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
        publisher.registerHandlerFor(TestRunFinished.class, this::onTestRunFinished);
    }

    private void onTestRunStarted(TestRunStarted event) {
        log.info("Test run started.");
    }

    private void onTestCaseStarted(TestCaseStarted event) {
        log.info("Test case started: " + event.getTestCase().getName());
    }

    private void onTestCaseFinished(TestCaseFinished event) {
        String result = "Test case finished: " + event.getTestCase().getName() + " - " + event.getResult().getStatus();
        testResults.add(result);
        log.info(result);
    }

    private void onTestRunFinished(TestRunFinished event) {
        log.info("Test run finished. Generating PDF report...");
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("Test Results:\n");
        for (String result : testResults) {
            reportContent.append(result).append("\n");
        }
        PdfReportGenerator.generatePdfReport("report/test-report.pdf", reportContent.toString());
    }
}
