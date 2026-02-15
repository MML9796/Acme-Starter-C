
package acme.entities.invention;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.components.datatypes.Money;
import acme.client.repositories.AbstractRepository;
import acme.entities.Part;

@Repository
public interface InventionRepository extends AbstractRepository {

	@Query("SELECT new acme.client.components.datatypes.Money(SUM(p.cost.amount), p.cost.currency) FROM Part p WHERE p.invention.id = :inventionId")
	Money getSumCostsPartsByInvention(int inventionId);

	@Query("SELECT p FROM Part p WHERE p.invention.id = :inventionId")
	List<Part> findPartsByInventionId(int inventionId);

}
