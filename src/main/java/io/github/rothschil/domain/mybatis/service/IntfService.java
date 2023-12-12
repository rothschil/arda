package io.github.rothschil.domain.mybatis.service;

import io.github.rothschil.domain.mybatis.entity.IntfEntity;
import io.github.rothschil.domain.mybatis.mapper.IntfMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntfService {

    @Autowired
    private IntfMapper intfMapper;

    public IntfEntity findByCaller(String caller){
        return intfMapper.findByCaller(caller);
    }
}
