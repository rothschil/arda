package io.github.rothschil.domain.mybatis.mapper;

import io.github.rothschil.domain.mybatis.entity.IntfLogBatis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IntfLogMapper {

    @Select("select * from intf_log where caller=#{caller}")
    IntfLogBatis findByCaller(@Param("caller") String caller);
}
