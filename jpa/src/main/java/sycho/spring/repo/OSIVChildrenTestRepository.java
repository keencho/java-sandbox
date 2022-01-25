package sycho.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sycho.spring.model.OSIVChildrenTestModel;

@Repository
public interface OSIVChildrenTestRepository extends JpaRepository<OSIVChildrenTestModel, Long> {
}
