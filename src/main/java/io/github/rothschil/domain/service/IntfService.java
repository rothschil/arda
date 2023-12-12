package io.github.rothschil.domain.service;

import io.github.rothschil.domain.entity.IntfEntity;
import io.github.rothschil.domain.mapper.IntfMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntfService {

    @Autowired
    private IntfMapper intfMapper;

    public IntfEntity findByCaller( String caller){
        return intfMapper.findByCaller(caller);
    }
}
