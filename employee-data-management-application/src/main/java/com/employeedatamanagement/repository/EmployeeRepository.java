package com.employeedatamanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employeedatamanagement.entity.Employee;

/**
 * @author Het Patel
 * @since 11/12/25
 * 
 * Repository interface for {@link Employee} entity.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	/**
	 * Retrieves an employee by email address.
	 *
	 * @param email the email address
	 * @return an {@link Optional} containing the employee if found
	 */
	Optional<Employee> findByEmail(String email);

	/**
	 * Retrieves an employee by mobile number.
	 *
	 * @param mobile the mobile number
	 * @return an {@link Optional} containing the employee if found
	 */
	Optional<Employee> findByMobile(String mobile);

	/**
	 * Searches employees by first name, last name, or full name.
	 * <p>
	 * The search is case-insensitive and supports partial matches.
	 * </p>
	 *
	 * @param name the name or partial name to search
	 * @return a list of matching employees
	 */
	@Query("""
				SELECT e
				FROM Employee e
				WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%'))
				   OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%'))
				   OR LOWER(CONCAT(e.firstName, ' ', e.lastName)) LIKE LOWER(CONCAT('%', :name, '%'))
				   OR LOWER(CONCAT(e.lastName, ' ', e.firstName)) LIKE LOWER(CONCAT('%', :name, '%'))
			""")
	List<Employee> findByNameContainingIgnoreCase(@Param("name") String name);

	/**
	 * Checks if an employee exists with the given email address.
	 *
	 * @param email the email address
	 * @return true if an employee exists, false otherwise
	 */
	boolean existsByEmail(String email);

	/**
	 * Checks if an employee exists with the given mobile number.
	 *
	 * @param mobile the mobile number
	 * @return true if an employee exists, false otherwise
	 */
	boolean existsByMobile(String mobile);

	/**
	 * Checks if an email address is already used by another employee, excluding the
	 * specified employee ID.
	 * <p>
	 * Used during update operations to avoid false duplicates.
	 * </p>
	 *
	 * @param email the email address
	 * @param id    the employee ID to exclude
	 * @return true if the email is taken by another employee
	 */
	@Query("""
				SELECT CASE
					WHEN COUNT(e) > 0 THEN true
					ELSE false
				END
				FROM Employee e
				WHERE e.email = :email
				  AND e.id <> :id
			""")
	boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);

	/**
	 * Checks if a mobile number is already used by another employee, excluding the
	 * specified employee ID.
	 *
	 * @param mobile the mobile number
	 * @param id     the employee ID to exclude
	 * @return true if the mobile number is taken by another employee
	 */
	@Query("""
				SELECT CASE
					WHEN COUNT(e) > 0 THEN true
					ELSE false
				END
				FROM Employee e
				WHERE e.mobile = :mobile
				  AND e.id <> :id
			""")
	boolean existsByMobileAndIdNot(@Param("mobile") String mobile, @Param("id") Long id);

}