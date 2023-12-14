package io.github.rothschil.mapper;

import io.github.rothschil.domain.mybatis.entity.IntfLogBatis;
import io.github.rothschil.domain.mybatis.mapper.IntfLogMapper;
import org.junit.jupiter.api.*;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@DisplayName("MybatisTest测试用例")
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestMybatis {

    @Autowired
    private IntfLogMapper intfLogMapper;


    @DisplayName("测试桩")
    @Test
    public void testIntf(){
        IntfLogBatis intfLogBatis = (IntfLogBatis) intfLogMapper.findByCaller("22");
        System.out.println(intfLogBatis.toString());
    }
}
