package com.spring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntNumberValid implements ConstraintValidator<IntNumber, Integer> {
	private final String PATTERN = "[1-9]+";

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return String.valueOf(value).matches(PATTERN);
	}

}
