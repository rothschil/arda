package io.github.rothschil.mapper;

import io.github.rothschil.domain.entity.Intf;
import io.github.rothschil.domain.mapper.IntfMapper;
import org.junit.jupiter.api.*;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@DisplayName("MybatisTest测试用例")
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestMybatis {

    @Autowired
    private IntfMapper intfMapper;


    @DisplayName("测试桩")
    @Test
    public void testIntf(){
        Intf intf = (Intf) intfMapper.findByCaller("22");
        System.out.println(intf.toString());
    }
}
