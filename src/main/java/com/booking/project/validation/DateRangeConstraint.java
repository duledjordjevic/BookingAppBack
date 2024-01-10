package com.booking.project.validation;

import java.lang.annotation.Documented;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Documented
@Constraint(validatedBy = DateRangeConstraintValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRangeConstraint {

    String message() default "startDate must be before endDate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
