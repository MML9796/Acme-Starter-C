
package acme.realms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.client.components.basis.AbstractRole;
import lombok.Getter;
import lombok.Setter;
import validation.ValidHeader;
import validation.ValidText;

@Entity
@Getter
@Setter
public class Fundraiser extends AbstractRole {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@ValidHeader
	@Column
	private String				bank;

	@NotNull
	@ValidText
	@Column
	private String				statement;

	@NotNull
	@Valid
	@Column
	private Boolean				agent;

}
