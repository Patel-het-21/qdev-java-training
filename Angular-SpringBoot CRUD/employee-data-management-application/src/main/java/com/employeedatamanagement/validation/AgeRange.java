package com.employeedatamanagement.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Custom validation annotation to validate that a person's age falls within a
 * specified range.
 */
@Documented
@Constraint(validatedBy = AgeRangeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeRange {

	/**
	 * The default validation message.
	 *
	 * @return the message string
	 */
	String message() default "Age must be between {min} and {max}";
	/**
	 * Allows the specification of validation groups.
	 *
	 * @return array of groups
	 */
	Class<?>[] groups() default {};
	/**
	 * Can be used by clients of the Bean Validation API to assign custom payload
	 * objects to a constraint.
	 *
	 * @return array of payload classes
	 */
	Class<? extends Payload>[] payload() default {};
	/**
	 * Minimum allowed age.
	 *
	 * @return the minimum age
	 */
	int min() default 18;
	/**
	 * Maximum allowed age.
	 *
	 * @return the maximum age
	 */
	int max() default 110;

}