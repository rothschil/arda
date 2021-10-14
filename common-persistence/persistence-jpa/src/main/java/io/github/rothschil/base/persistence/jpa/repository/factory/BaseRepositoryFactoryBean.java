package io.github.rothschil.base.persistence.jpa.repository.factory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import io.github.rothschil.base.persistence.jpa.entity.BasePo;
import io.github.rothschil.base.persistence.jpa.repository.impl.BaseRepositoryImpl;

import javax.persistence.EntityManager;
import java.io.Serializable;


/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2021/10/12 - 15:48
 * @since 1.0.0
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T extends BasePo,I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {


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
         * @param metadata 原数据
         * @return Class
         */
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }

}
