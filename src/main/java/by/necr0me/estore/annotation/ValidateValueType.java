package by.necr0me.estore.annotation;

import by.necr0me.estore.validator.TypeValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = TypeValidator.class)
public @interface ValidateValueType {
    String message() default "Type of value is not valid";

    Class[] groups() default {};

    Class[] payload() default {};
}

