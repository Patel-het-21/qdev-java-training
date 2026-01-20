package com.employeedatamanagement.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeedatamanagement.service.EmployeeReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * REST controller for generating employee reports.
 */
@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeReportController {

	private final EmployeeReportService reportService;
	/**
	 * Constructs an {@code EmployeeReportController} with the required service.
	 *
	 * @param reportService the service responsible for generating employee reports
	 */
	@Autowired
	public EmployeeReportController(EmployeeReportService reportService) {
		this.reportService = reportService;
	}

	/**
	 * Generates and downloads the employee report in PDF format.
	 *
	 * @return a {@link ResponseEntity} containing the generated PDF file
	 * @throws IOException if the report template cannot be accessed
	 * @throws JRException if an error occurs during report generation or export
	 */
	@GetMapping("/report/pdf")
	public ResponseEntity<byte[]> generateEmployeePdf() throws IOException, JRException {
		JasperPrint jasperPrint = reportService.generateReport();
		byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
		return ResponseEntity.ok()
				.header("Content-Disposition", "attachment; filename=employee.pdf")
				.contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
	}

}