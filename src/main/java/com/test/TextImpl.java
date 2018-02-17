package com.test;

import com.dao.TestDao;
import com.dao.UserDao;
import com.service.TestService;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
@Configuration
@EnableAspectJAutoProxy
public class TextImpl {

    @Resource
    private UserService userService;

    @Resource
    private TestService testService;

    @Test
    public void textUser() throws SQLException {
//        System.out.println(userService.userLogin("text","text"));
//        System.out.println(userService.userDetailedById(2));
//        System.out.println(userService.userDeductBalance(2,100));
    }

    @Test
    public void test(){
        System.out.println(testService.testWr(9,"9"));
//        System.out.println(testService.testR(10));
    }
}
