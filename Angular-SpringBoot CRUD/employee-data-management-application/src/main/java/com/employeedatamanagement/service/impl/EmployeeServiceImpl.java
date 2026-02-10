package com.employeedatamanagement.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeedatamanagement.dto.EmployeeRequestDto;
import com.employeedatamanagement.dto.EmployeeResponseDto;
import com.employeedatamanagement.entity.tenant.Employee;
import com.employeedatamanagement.repository.employee.EmployeeRepository;
import com.employeedatamanagement.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;

/**
 * @author Het Patel
 * @since 11/12/25
 * 
 *        Implementation of {@link EmployeeService} for managing employee
 *        records.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository repo;
	private final HttpSession session;
	/**
	 * Constructs the service with the required repository.
	 *
	 * @param repo the {@link EmployeeRepository} to access employee data
	 */
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository repo,HttpSession session) {
		this.repo = repo;
		this.session = session;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {
		dto = trimDto(dto);
		if (repo.existsByEmail(dto.getEmail())) {
			throw new IllegalArgumentException("Email already exists");
		}
		if (repo.existsByMobile(dto.getMobile())) {
			throw new IllegalArgumentException("Mobile already exists");
		}
		Employee e = mapToEntity(dto);
		e.setAge(calculateAge(dto.getDateOfBirth()));
		Employee saved = repo.save(e);
		return mapToResponse(saved);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto) {
		Employee existing = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found"));

		dto = trimDto(dto);
		if (repo.existsByEmailAndIdNot(dto.getEmail(), id)) {
			throw new IllegalArgumentException("Email already exists");
		}
		if (repo.existsByMobileAndIdNot(dto.getMobile(), id)) {
			throw new IllegalArgumentException("Mobile already exists");
		}
		// Update fields
		existing.setFirstName(dto.getFirstName());
		existing.setLastName(dto.getLastName());
		existing.setDateOfBirth(dto.getDateOfBirth());
		existing.setMobile(dto.getMobile());
		existing.setAddress1(dto.getAddress1());
		existing.setAddress2(dto.getAddress2());
		existing.setGender(dto.getGender());
		existing.setEmail(dto.getEmail());
		existing.setAge(calculateAge(dto.getDateOfBirth()));
		Employee saved = repo.save(existing);
		return mapToResponse(saved);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeResponseDto getEmployeeById(Long id) {
		Employee e = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
		return mapToResponse(e);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EmployeeResponseDto> getAllEmployees() {
		return repo.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteEmployee(Long id) {
		Employee existing = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
		repo.delete(existing);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EmployeeResponseDto> searchByName(String name) {
		return repo.findByNameContainingIgnoreCase(name).stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmailTaken(String email, Long excludingId) {
		if (excludingId != null) {
			return repo.existsByEmailAndIdNot(email, excludingId);
		}
		return repo.existsByEmail(email);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isMobileTaken(String mobile, Long excludingId) {
		if (excludingId != null) {
			return repo.existsByMobileAndIdNot(mobile, excludingId);
		}
		return repo.existsByMobile(mobile);
	}

	/**
	 * Maps {@link EmployeeRequestDto} to {@link Employee} entity.
	 *
	 * @param dto the employee request DTO
	 * @return a new Employee entity
	 */
	private Employee mapToEntity(EmployeeRequestDto dto) {
		Employee e = new Employee();
		e.setFirstName(dto.getFirstName());
		e.setLastName(dto.getLastName());
		e.setDateOfBirth(dto.getDateOfBirth());
		e.setMobile(dto.getMobile());
		e.setAddress1(dto.getAddress1());
		e.setAddress2(dto.getAddress2());
		e.setGender(dto.getGender());
		e.setEmail(dto.getEmail());
		return e;
	}

	/**
	 * Maps {@link Employee} entity to {@link EmployeeResponseDto}.
	 *
	 * @param e the employee entity
	 * @return the response DTO
	 */
	private EmployeeResponseDto mapToResponse(Employee e) {
		EmployeeResponseDto r = new EmployeeResponseDto();
		r.setId(e.getId());
		r.setFirstName(e.getFirstName());
		r.setLastName(e.getLastName());
		r.setDateOfBirth(e.getDateOfBirth());
		r.setAge(e.getAge());
		r.setMobile(e.getMobile());
		r.setEmail(e.getEmail());
		r.setGender(e.getGender());
		r.setAddress1(e.getAddress1());
		r.setAddress2(e.getAddress2());
		return r;
	}

	/**
	 * Calculates age in years from a given date of birth.
	 *
	 * @param dob the date of birth
	 * @return the calculated age
	 */
	private int calculateAge(LocalDate dob) {
		return Period.between(dob, LocalDate.now()).getYears();
	}

	/**
	 * Trims leading and trailing spaces from a string.
	 *
	 * @param value the input string
	 * @return trimmed string or null if input was null
	 */
	private String trim(String value) {
		return value == null ? null : value.trim();
	}

	/**
	 * Trims all string fields of an {@link EmployeeRequestDto}.
	 *
	 * @param dto the employee request DTO
	 * @return the trimmed DTO
	 */
	private EmployeeRequestDto trimDto(EmployeeRequestDto dto) {
		dto.setFirstName(trim(dto.getFirstName()));
		dto.setLastName(trim(dto.getLastName()));
		dto.setEmail(trim(dto.getEmail()));
		dto.setMobile(trim(dto.getMobile()));
		dto.setAddress1(trim(dto.getAddress1()));
		dto.setAddress2(trim(dto.getAddress2()));
		dto.setGender(trim(dto.getGender()));
		return dto;
	}

}