package io.github.rothschil.domain.mybatis.service;

import io.github.rothschil.domain.mybatis.entity.IntfLogBatis;
import io.github.rothschil.domain.mybatis.mapper.IntfLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntfBatisService {

    @Autowired
    private IntfLogMapper intfLogMapper;

    public IntfLogBatis findByCaller(String caller){
        return intfLogMapper.findByCaller(caller);
    }
}
