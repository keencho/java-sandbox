package sycho.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sycho.spring.model.CacheTestModel;

@Repository
public interface CacheTestRepository extends JpaRepository<CacheTestModel, Long> {
}
