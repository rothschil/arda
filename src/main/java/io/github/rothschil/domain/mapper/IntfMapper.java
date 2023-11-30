package io.github.rothschil.domain.mapper;

import io.github.rothschil.domain.entity.IntfEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IntfMapper {

    @Select("select * from intf_log where caller=#{caller}")
    IntfEntity findByCaller(@Param("caller") String caller);
}
