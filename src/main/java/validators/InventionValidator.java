
package acme.validators;

import java.util.List;

import javax.validation.ConstraintValidatorContext;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.entities.Part;
import acme.entities.invention.Invention;
import acme.internals.helpers.HibernateHelper;
import acme.validation.ValidInvention;

@Validator
public class InventionValidator extends AbstractValidator<ValidInvention, Invention> {

	@Override
	public void initialise(final ValidInvention annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Invention invention, final ConstraintValidatorContext context) {
		assert context != null;

		if (invention == null)
			return true;

		boolean result = true;

		if (!invention.getEndMoment().after(invention.getStartMoment())) {
			HibernateHelper.replaceParameter(context, "placeholder", "End moment must be after start moment");
			result = false;
		}

		List<Part> parts = invention.getRepository().findPartsByInventionId(invention.getId());
		if (parts.isEmpty()) {
			HibernateHelper.replaceParameter(context, "placeholder", "An invention must have at least one part");
			result = false;
		}

		return result;
	}
}
