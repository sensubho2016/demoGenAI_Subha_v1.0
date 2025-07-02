package com.genaidemo.demo.utility;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.File;
import java.io.IOException;

@Slf4j
public class PdfReportGenerator {

    @Getter
    private static final SummaryGeneratingListener summaryListener = new SummaryGeneratingListener();

    public static void generatePdfReport(String outputPath, String reportContent) {
        try {
            // Create a PDF writer instance
            PdfWriter writer = new PdfWriter(outputPath);

            // Create a PDF document
            PdfDocument pdfDocument = new PdfDocument(writer);

            // Create a document layout
            Document document = new Document(pdfDocument);

            // Add content to the PDF
            document.add(new Paragraph("Test Report"));
            document.add(new Paragraph(reportContent));

            // Close the document
            document.close();

            System.out.println("PDF report generated at: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateReportAfterTests() {
        TestExecutionSummary summary = summaryListener.getSummary();
        if (summary == null) {
            log.info("TestExecutionSummary is null. No report will be generated.");
            return;
        }

        StringBuilder reportContent = new StringBuilder();
        reportContent.append("Total Tests: ").append(summary.getTestsFoundCount()).append("\n");
        reportContent.append("Tests Succeeded: ").append(summary.getTestsSucceededCount()).append("\n");
        reportContent.append("Tests Failed: ").append(summary.getTestsFailedCount()).append("\n");
        reportContent.append("Tests Aborted: ").append(summary.getTestsAbortedCount()).append("\n");

        String outputPath = "report/test-report.pdf";
        new File("report").mkdirs();
        System.out.println("Generating PDF report...");
        System.out.println("Total Tests: " + summary.getTestsFoundCount());
        System.out.println("Tests Succeeded: " + summary.getTestsSucceededCount());
        System.out.println("Tests Failed: " + summary.getTestsFailedCount());
        System.out.println("Tests Aborted: " + summary.getTestsAbortedCount());
        generatePdfReport(outputPath, reportContent.toString());
    }

    /* public static void main(String[] args) {
        // Example usage
        String outputPath = "report/test-report.pdf";
        String reportContent = "This is a sample test report content.";

        // Ensure the output directory exists and handle errors
        File reportDir = new File("report");
        if (!reportDir.exists() && !reportDir.mkdirs()) {
            System.err.println("Failed to create report directory.");
            return;
        }

        generatePdfReport(outputPath, reportContent);
    }*/
}
