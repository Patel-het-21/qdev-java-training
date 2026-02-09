package com.employeedatamanagement.repository.master;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeedatamanagement.entity.master.User;

/**
 * Repository interface for performing CRUD operations on {@link User} entities.
 * <p>
 * This repository interacts with the master database and manages system users
 * and company authentication details.
 * </p>
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds a user by the associated company name.
	 *
	 * @param companyName the unique company name of the user
	 * @return an {@link Optional} containing the user if found, otherwise empty
	 */
	Optional<User> findByCompanyName(String companyName);

	/**
	 * Finds a user by the associated email address.
	 *
	 * @param email the unique email address of the user
	 * @return an {@link Optional} containing the user if found, otherwise empty
	 */
	Optional<User> findByEmail(String email);

	/**
	 * Checks whether a user exists with the given company name.
	 *
	 * @param companyName the company name to check
	 * @return {@code true} if a user exists with the given company name, otherwise {@code false}
	 */
	boolean existsByCompanyName(String companyName);

	/**
	 * Checks whether a user exists with the given email address.
	 *
	 * @param email the email address to check
	 * @return {@code true} if a user exists with the given email address, otherwise {@code false}
	 */
	boolean existsByEmail(String email);

}