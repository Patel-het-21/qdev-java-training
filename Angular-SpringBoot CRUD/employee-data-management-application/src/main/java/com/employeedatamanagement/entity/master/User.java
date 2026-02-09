package com.employeedatamanagement.entity.master;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author Het Patel
 * @since 27/01/26
 * 
 * Entity representing a system user.
 * <p>
 * This entity is stored in the master database and represents
 * a registered company/user who can authenticate into the system.
 * </p>
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

	/**
	 * Primary key of the users table.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	/**
	 * Unique company name used for authentication.
	 * <p>
	 * This value is also used to associate the user with a tenant.
	 * </p>
	 */
	@NotBlank
	@Size(max = 255)
	@Column(name = "companyname", unique = true, nullable = false)
	String companyName;

	/**
	 * Encrypted password of the user.
	 * <p>
	 * This value should be stored in encrypted/hashed form.
	 * </p>
	 */
	@NotBlank
	@Size(min = 8)
	@Column(nullable = false)
	String password;

	/**
	 * Unique email address of the user.
	 * <p>
	 * Used for login and communication purposes.
	 * </p>
	 */
	@NotBlank
	@Email
	@Column(unique = true, nullable = false)
	String email;

//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//	List<Employee> employees;

}