package com.kuang;

import com.kuang.pojo.Dog;
import com.kuang.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot02ConfigApplicationTests {
    @Autowired
    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
