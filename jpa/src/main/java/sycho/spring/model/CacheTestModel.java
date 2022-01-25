package sycho.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cache_test_model")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CacheTestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String keyword;
}
