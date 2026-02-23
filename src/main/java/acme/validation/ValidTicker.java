
package acme.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import acme.validators.ValidTickerValidator;

@Target({
	ElementType.FIELD, ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidTickerValidator.class)

public @interface ValidTicker {

	String message() default "{acme.validation.ticker.invalid.message}";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
