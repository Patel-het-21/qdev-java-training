package com.employeedatamanagement.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.employeedatamanagement.dto.EmployeeReportDto;
import com.employeedatamanagement.entity.Employee;
import com.employeedatamanagement.repository.EmployeeRepository;
import com.employeedatamanagement.service.EmployeeReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Service implementation for generating employee reports.
 */
@Service
public class EmployeeReportServiceImpl implements EmployeeReportService {

	private final EmployeeRepository repo;

	/**
	 * Constructs an {@code EmployeeReportServiceImpl} with the required repository.
	 *
	 * @param repo the {@link EmployeeRepository} used to retrieve employee data
	 */
	@Autowired
	public EmployeeReportServiceImpl(EmployeeRepository repo) {
		this.repo = repo;
	}

	/**
	 * Generates a Jasper report containing employee details.
	 *
	 * @return a {@link JasperPrint} object representing the generated report
	 * @throws IOException if the report template cannot be loaded
	 * @throws JRException if an error occurs during report generation
	 */
	@Override
	public JasperPrint generateReport() throws IOException, JRException {
		List<Employee> employees = repo.findAll();
		List<EmployeeReportDto> dtoList = employees.stream().map(this::convertToDto).toList();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dtoList);
		InputStream inputStream = new ClassPathResource("reports/Employee_List.jasper").getInputStream();
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
		return JasperFillManager.fillReport(jasperReport, null, dataSource);
	}

	/**
	 * Converts an {@link Employee} entity into an {@link EmployeeReportDto}.
	 *
	 * @param employee the employee entity to convert
	 * @return a populated {@link EmployeeReportDto}, or {@code null} if the input
	 *         is null
	 */
	private EmployeeReportDto convertToDto(Employee employee) {
		if (employee == null) {
			return null;
		}
		EmployeeReportDto dto = new EmployeeReportDto();
		dto.setId(employee.getId());
		dto.setFirstName(employee.getFirstName());
		dto.setLastName(employee.getLastName());
		dto.setGender(employee.getGender());
		dto.setMobile(employee.getMobile());
		dto.setAge(employee.getAge());
		dto.setDateOfBirth(employee.getDateOfBirth());
		dto.setEmail(employee.getEmail());
		return dto;
	}

}