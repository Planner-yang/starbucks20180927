package com.starbucks.admin.service.test;

import com.starbucks.admin.service.TbUserService;
import com.starbucks.commons.utils.IdUniqueUtils;
import com.starbucks.domain.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void AddTest() {
        TbUser tbUser = new TbUser();
        tbUser.setEmail("admin@163.com");
        System.out.println(IdUniqueUtils.generateId());
        tbUser.setId(IdUniqueUtils.generateId()+'L');
        tbUser.setPassword(DigestUtils.md5DigestAsHex(("123456").getBytes()));
        tbUser.setUsername("admin");
        tbUserService.add(tbUser);
    }
}
