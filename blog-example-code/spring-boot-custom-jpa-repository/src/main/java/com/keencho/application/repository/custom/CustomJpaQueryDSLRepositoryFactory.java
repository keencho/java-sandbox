package com.keencho.application.repository.custom;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.RepositoryFragment;

public class CustomJpaQueryDSLRepositoryFactory<T extends Repository<E, ID>, E, ID> extends JpaRepositoryFactoryBean<T, E, ID> {
    public CustomJpaQueryDSLRepositoryFactory(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new QueryDSLRepositoryFactory(entityManager);
    }

    private static class QueryDSLRepositoryFactory extends JpaRepositoryFactory {
        private final EntityManager entityManager;

        public QueryDSLRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

        @Override
        protected RepositoryComposition.RepositoryFragments getRepositoryFragments(RepositoryMetadata metadata) {
            var fragments = super.getRepositoryFragments(metadata);

            // repository interface가 CustomJpaQueryDSLRepository 를 상속하고 있다면
            if (CustomJpaQueryDSLRepository.class.isAssignableFrom(metadata.getRepositoryInterface())) {
                var impl = super.instantiateClass(
                        DefaultCustomJpaSearchQuery.class,
                        this.getEntityInformation(metadata.getDomainType()), this.entityManager
                );

                // CustomJpaQueryDSLRepository 인터페이스를 상속한 DefaultCustomJpaSearchQuery 클래스를 생성하여 사용 가능하게 한다.
                fragments = fragments.append(RepositoryFragment.implemented(impl));
            }

            return fragments;
        }
    }
}
