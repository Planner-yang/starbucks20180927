package com.starbucks.api.service.test;

import com.starbucks.api.service.TbUserService;
import com.starbucks.domain.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void login () {
        TbUser tbUser = tbUserService.get("admin@163.com");
        List<TbUser> list = new ArrayList<>();
        list.add(tbUser);
        System.out.println(list);
    }
}
