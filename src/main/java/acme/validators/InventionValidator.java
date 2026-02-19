
package acme.validators;

import java.util.List;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.entities.invention.Invention;
import acme.entities.invention.InventionRepository;
import acme.entities.part.Part;
import acme.validation.ValidInvention;

@Validator
public class InventionValidator extends AbstractValidator<ValidInvention, Invention> {

	@Autowired
	private InventionRepository repository;


	@Override
	public void initialise(final ValidInvention annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Invention invention, final ConstraintValidatorContext context) {
		assert context != null;

		boolean resultado;

		if (invention == null)
			resultado = true;
		else {

			if (!invention.getDraftMode()) {

				boolean fechaCorrecta;
				fechaCorrecta = invention.getEndMoment().after(invention.getStartMoment());

				super.state(context, fechaCorrecta, "endMoment", "startMoment/endMoment must be a valid time interval in future");

				boolean hasParts = true;

				if (invention.getId() != 0) {
					List<Part> parts = this.repository.findPartsByInventionId(invention.getId());
					hasParts = !parts.isEmpty();
				}

				super.state(context, hasParts, "*", "Inventions cannot be published unless they have at least one part");
			}

			resultado = !super.hasErrors(context);
		}

		return resultado;
	}
}
