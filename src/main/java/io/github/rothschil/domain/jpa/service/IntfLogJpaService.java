package io.github.rothschil.domain.jpa.service;

import io.github.rothschil.common.persistence.jpa.service.BaseService;
import io.github.rothschil.domain.jpa.entity.IntfLogJpa;
import io.github.rothschil.domain.jpa.repository.IntfLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2021/10/15 - 16:41
 * @since 1.0.0
 */
@Transactional
@Service
public class IntfLogJpaService extends BaseService<IntfLogJpa, Long> {


    private IntfLogRepository intfLogRepository;

    @Autowired
    @Qualifier("intfLogRepository")
    @Override
    public void setJpaRepository(JpaRepository<IntfLogJpa, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
        this.intfLogRepository = (IntfLogRepository) jpaRepository;
    }

    @Autowired
    public void setIntfLogRepository(IntfLogRepository intfLogRepository) {
        this.intfLogRepository = intfLogRepository;
    }


    @Cacheable(cacheNames = "intf:cache#60s#10m#20", key = "#caller", condition = "#caller != null")
    public IntfLogJpa getIntfLogByCaller(String caller) {
        return intfLogRepository.getIntfLogByCaller(caller);
    }

    public List<?> listBySql(String sql) {
        return intfLogRepository.listBySql(sql);
    }

}
