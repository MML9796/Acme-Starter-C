
package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.ValidScore;
import acme.entities.Strategy.Strategy;
import lombok.Getter;
import lombok.Setter;
import validation.ValidHeader;
import validation.ValidText;

@Entity
@Getter
@Setter
public class Tactic extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@ValidHeader
	@Column
	private String				name;

	@NotNull
	@ValidText
	@Column
	private String				notes;

	@NotNull
	@ValidScore
	@Column
	private Double				expectedPercentage;

	@NotNull
	@Valid
	@Column
	private TacticKind			kind;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Strategy			strategy;
}
