package com.service.Impl;

import com.bean.Order;
import com.dao.OrderDao;
import com.service.OrderService;
import com.utils.EnumUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderDao orderDao;

    /**
     * 创建一个订单
     * @param order
     * @return
     */
    @Override
    public int createOrder(Order order) {
        int reslut = 0;
        try {
            reslut = orderDao.createOrder(order);
        }catch (Exception e){
            return EnumUtil.SQL_FAILURE;
        }
        return reslut;
    }

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    @Override
    public int deleteOrder(int orderId) {
        int reslut = 0;
        try {
            reslut = orderDao.deleteOrder(orderId);
        }catch (Exception e){
            return EnumUtil.SQL_FAILURE;
        }
        return reslut;
    }

    /**
     * 更新订单
     * @param order
     * @return
     */
    @Override
    public int updateOrder(Order order) {
        int reslut = 0;
        try {
            reslut = orderDao.updateOrder(order);
        }catch (Exception e){
            return EnumUtil.SQL_FAILURE;
        }
        return reslut;
    }

    /**
     * 修改订单状态
     * @param orderId
     * @param orderState
     * @return
     */
    @Override
    public int updateOrderState(int orderId, int orderState) {
        int reslut = 0;
        try {
            reslut = orderDao.updateOrderState(orderId,orderState);
        }catch (Exception e){
            return EnumUtil.SQL_FAILURE;
        }
        return reslut;
    }

    /**
     * 得到所有已处理的订单信息(管理员)
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllOrderDetail() {
        List reslut = new ArrayList();
        try {
            reslut = orderDao.getAllOrderDetail();
        }catch (Exception e){
            return null;
        }
        return reslut;
    }

    /**
     * 得到用户未处理的订单信息
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> getOrderNotDealByUserId(int userId) {
        List reslut = new ArrayList();
        try {
            reslut = orderDao.getOrderNotDealByUserId(userId);
        }catch (Exception e){
            return null;
        }
        return reslut;
    }

    /**
     * 得到已处理的订单信息
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> getOrderDealByUserId(int userId) {
        List reslut;
        try {
            reslut = orderDao.getOrderDealByUserId(userId);
        }catch (Exception e){
            return null;
        }
        return reslut;
    }

    /**
     * 通过订单ID获取订单信息
     * @param orderId
     * @return
     */
    @Override
    public Order getOrderDetailedById(int orderId) {
        Order reslut;
        try {
            reslut = orderDao.getOrderDetailedById(orderId);
        }catch (Exception e){
            return null;
        }
        return reslut;
    }
}
