
package acme.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import acme.validators.InventionValidator;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = InventionValidator.class)
public @interface ValidInvention {

	String message() default "{placeholder}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
