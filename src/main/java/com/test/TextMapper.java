package com.test;

import com.bean.Order;
import com.bean.Tea;
import com.dao.OrderDao;
import com.dao.TeaDao;
import com.dao.TestDao;
import com.dao.UserDao;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TextMapper {

    @Resource
    private UserDao userDao;

    @Resource
    private UserService userService;

    @Resource
    private TeaDao teaDao;

    @Resource
    private OrderDao orderDao;

    @Resource
    private TestDao testDao;

    @Test
    public void textUser(){
//        System.out.println(userDao.userRegister("text","text"));
//        System.out.println(userDao.userRechargeBalance(1,100));
//        System.out.println(userDao.userDeductBalance(1,50));
//        System.out.println(userDao.userLogin("admin","sadqw"));
        String text = String.valueOf(userService.userDetailedById(2).get(0).get("user_balance"));
        double text2 = Double.parseDouble(text);
        System.out.println(text2);
        System.out.println(String.valueOf(userService.userDetailedById(2).get(0).get("user_balance")));

//        System.out.println(userDao.userRegisterCheckName("admin"));
    }
    @Test
    public void textTea(){
        Tea tea = new Tea();
        tea.setTeaId(2);
        tea.setTeaIntro("text2");
        tea.setTeaName("text2");
        tea.setTeaNum(2);
        tea.setTeaPhoto("text2");
        tea.setTeaPrice(2.0);
//        System.out.println(teaDao.addTea(tea));
//        System.out.println(teaDao.deleteTeaByID(1));
//        System.out.println(teaDao.updateTeaDetailed(tea));
//        System.out.println(teaDao.updateTeaState(2,1));
//        System.out.println(teaDao.getTeaDetailed_all());
//        System.out.println(teaDao.getTeaDetailedByID(2));
//        System.out.println(teaDao.getTeaDetailedByState());
        System.out.println(teaDao.updateTeaNum(3,10));
    }


    @Test
    public void textOrder(){
        Order order = new Order();
        order.setOrderId(2);
        order.setTeaId(3);
        order.setUserId(2);
        order.setOrderBuyNum(3);
//        orderDao.createOrder(order);
//        System.out.println(orderDao.deleteOrder(1));
//        System.out.println(orderDao.updateOrder(order));
//        System.out.println(orderDao.getOrderDealByUserId(2));
//        System.out.println(orderDao.getAllOrderDetail());
//        System.out.println(orderDao.updateOrderState(3,1));
        System.out.println(orderDao.getOrderDetailedById(3));
    }

    @Test
    public void text(){
        Order order = new Order();
        System.out.println(order.getOrderId());
    }


    @Test
    public void test(){
        System.out.println(testDao.testR(1));
    }
}
