
package acme.entities.invention;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.components.datatypes.Money;
import acme.client.repositories.AbstractRepository;

@Repository
public interface InventionRepository extends AbstractRepository {

	@Query("SELECT new acme.client.components.datatypes.Money(SUM(p.cost.amount), p.cost.currency) FROM Part p WHERE p.invention.id = :inventionId")
	Money getSumCostsPartsByInvention(int inventionId);

}
