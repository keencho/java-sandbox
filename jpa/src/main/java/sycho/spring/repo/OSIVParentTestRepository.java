package sycho.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sycho.spring.model.OSIVParentTestModel;

@Repository
public interface OSIVParentTestRepository extends JpaRepository<OSIVParentTestModel, Long> {
}
