package com.spring.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = BirthDayValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthDate {
	String message() default "birthdate must not be null and have fromat dd/MM/yyyy";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
