package com.employeedatamanagement.service;

import java.io.IOException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Service interface for generating employee reports.
 */
public interface EmployeeReportService {

	/**
	 * Generates an employee report.
	 *
	 * @return the generated {@link JasperPrint} report
	 * @throws IOException if the report template cannot be loaded
	 * @throws JRException if an error occurs during report generation
	 */
	JasperPrint generateReport() throws IOException, JRException;

}