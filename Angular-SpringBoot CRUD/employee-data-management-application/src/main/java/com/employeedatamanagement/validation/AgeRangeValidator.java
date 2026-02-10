package com.employeedatamanagement.validation;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator implementation for the {@link AgeRange} annotation.
 */
public class AgeRangeValidator implements ConstraintValidator<AgeRange, LocalDate> {

	private int min;
	private int max;

	/**
	 * Initializes the validator with the values from the {@link AgeRange}
	 * annotation.
	 *
	 * @param constraintAnnotation the annotation instance
	 */
	@Override
	public void initialize(AgeRange constraintAnnotation) {
		this.min = constraintAnnotation.min();
		this.max = constraintAnnotation.max();
	}

	/**
	 * Validates that the given date of birth results in an age within the specified
	 * range.
	 *
	 * @param dob     the date of birth to validate
	 * @param context the constraint validation context
	 * @return {@code true} if age is within range, {@code false} otherwise
	 */
	@Override
	public boolean isValid(LocalDate dob, ConstraintValidatorContext context) {
		if (dob == null) {
			return false;
		}

		LocalDate today = LocalDate.now();
		if (dob.isAfter(today)) {
			return false;
		}

		int age = Period.between(dob, today).getYears();
		return age >= min && age <= max;
	}

}