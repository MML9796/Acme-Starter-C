
package acme.entities.Strategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.datatypes.Moment;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidMoment.Constraint;
import acme.realms.Fundraiser;
import acme.client.components.validation.ValidUrl;
import lombok.Getter;
import lombok.Setter;
import validation.ValidHeader;
import validation.ValidText;
import validation.ValidTicker;

@Entity
@Getter
@Setter
public class Strategy extends AbstractEntity {

	@Transient
	@Autowired
	private StrategyRepository	repository;

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@ValidTicker
	@Column(unique = true)
	private String				ticker;

	@NotNull
	@ValidHeader
	@Column
	private String				name;

	@NotNull
	@ValidText
	@Column
	private String				description;

	@NotNull
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	//@Temporal(TemporalType.TIMESTAMP)
	private Moment				startMoment;

	@NotNull
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	//@Temporal(TemporalType.TIMESTAMP)
	private Moment				endMoment;

	@ValidUrl
	@Column
	private String				moreInfo;

	@NotNull
	@Valid
	@Column
	private Boolean				draftMode;


	@NotNull
	@Valid
	@Transient
	public double getMonthsActive() {
		long diffMillis = this.endMoment.getTime() - this.startMoment.getTime();
		double dias = diffMillis / (1000.0 * 60 * 60 * 24);
		double meses = dias / 30.436875;
		return Math.round(meses * 10.0) / 10.0;
	}

	@NotNull
	//@ValidScore
	@Transient
	public Double expectedPercentage() {
		Double total = this.repository.sumPercentageByStrategyId(this.getId());
		if (total == null)
			return 0.0;
		return total;
	}


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Fundraiser fundraiser;

}
