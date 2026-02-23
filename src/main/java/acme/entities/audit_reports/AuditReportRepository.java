
package acme.entities.audit_reports;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface AuditReportRepository extends AbstractRepository {

	@Query("select sum(as.hours) from AuditSection as where as.auditReport.id = :id")
	int computeHours(int id);

	@Query("select ar from AuditReport ar where ar.ticker = :ticker")
	AuditReport findAuditReportByTicker(String ticker);

	@Query("select size(as) from AuditSection as where as.auditReport.id = :id")
	int findAuditSectionsSizeById(int id);

}
