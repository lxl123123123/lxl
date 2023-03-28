package com.itheima;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest(properties = {"test.prop=testValue1"})
@SpringBootTest(properties = {"test.prop=testValue1"},args = {"--test.prop=testValue2"})
class PropertiesAndArgsTest {

    @Value("${test.prop}")
    private String msg;

    @Test
    void testProperties(){
        System.out.println(msg);
    }

}
