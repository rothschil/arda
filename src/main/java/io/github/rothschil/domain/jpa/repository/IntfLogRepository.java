package io.github.rothschil.domain.jpa.repository;


import io.github.rothschil.common.persistence.jpa.repository.BaseRepository;
import io.github.rothschil.domain.jpa.entity.IntfLog;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2021/10/15 - 16:41
 * @since 1.0.0
 */
public interface IntfLogRepository extends BaseRepository<IntfLog, Long> {

    IntfLog getIntfLogByCaller(String caller);

    List<IntfLog> findIntfLogByCallerIsLike(@Param("caller") String caller);
}
