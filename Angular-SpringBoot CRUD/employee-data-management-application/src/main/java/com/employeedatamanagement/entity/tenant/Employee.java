package com.employeedatamanagement.entity.tenant;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author Het Patel
 * @since 11/12/25
 * 
 * Entity representing an employee record in the system.
 */
@Entity
@Table(name = "employee", uniqueConstraints = { @UniqueConstraint(columnNames = "mobile"),@UniqueConstraint(columnNames = "email") })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {

	/**
	 * Primary key of the employee table.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	/**
	 * User who owns and manages this employee.
	 * <p>
	 * This establishes a many-to-one relationship where multiple employees can
	 * belong to a single user.
	 * </p>
	 */
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id", nullable = false)
//	User user;

	/**
	 * Employee's first name.
	 */
	@Column(name = "first_name", length = 50, nullable = false)
	String firstName;

	/**
	 * Employee's last name.
	 */
	@Column(name = "last_name", length = 50, nullable = false)
	String lastName;

	/**
	 * Employee's date of birth.
	 */
	@Column(name = "date_of_birth", nullable = false)
	LocalDate dateOfBirth;

	/**
	 * Employee's mobile number.
	 */
	@Column(name = "mobile", length = 10, nullable = false, unique = true)
	String mobile;

	/**
	 * Employee's primary address.
	 */
	@Column(name = "address1", length = 255, nullable = false)
	String address1;
	/**
	 * Employee's secondary address.
	 */
	@Column(name = "address2", length = 255)
	String address2;

	/**
	 * Employee's age.
	 */
	@Column(name = "age", nullable = false)
	Integer age;

	/**
	 * Employee's gender.
	 */
	@Column(name = "gender", length = 10, nullable = false)
	String gender;

	/**
	 * Employee's email address.
	 */
	@Column(name = "email", length = 255, nullable = false, unique = true)
	String email;

}