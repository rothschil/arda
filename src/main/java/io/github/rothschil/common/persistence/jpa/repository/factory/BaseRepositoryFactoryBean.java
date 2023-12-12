package io.github.rothschil.common.persistence.jpa.repository.factory;

import io.github.rothschil.common.persistence.jpa.entity.AbstractPo;
import io.github.rothschil.common.persistence.jpa.repository.impl.BaseRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import java.io.Serializable;


@SuppressWarnings({"rawtypes","unchecked"})
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T extends AbstractPo,I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {


    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseRepositoryFactory(entityManager);
    }

    private static class BaseRepositoryFactory<T,I extends Serializable> extends JpaRepositoryFactory{

        private final EntityManager em;

        public BaseRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        @Override
        protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
            return new BaseRepositoryImpl((Class<T>) information.getDomainType(), em);
        }


        /**
         *  设置具体的实现类的class
         * @param metadata
         * @return
         */
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }

}
