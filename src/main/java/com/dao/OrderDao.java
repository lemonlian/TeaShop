package com.dao;

import com.bean.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    /**
     * 创建一个订单
     * @param order
     * @return
     */
    public int createOrder(Order order);

    /**
     * 通过订单ID删除订单
     * @param orderId
     * @return
     */
    public int deleteOrder(@Param("orderId") int orderId);

    /**
     * 修改订单信息
     * @param order
     * @return
     */
    public int updateOrder(Order order);

    /**
     * 修改订单状态
     * @param orderId
     * @return
     */
    public int updateOrderState(@Param("orderId")int orderId,
                                @Param("orderState")int orderState);

    /**
     * 获取所有已处理的订单信息（管理员）
     * @return
     */
    public List<Map<String,Object>> getAllOrderDetail();

    /**
     * 通过用户ID返回该用户没有处理的订单信息（购物车）
     * @param userId
     * @return
     */
    public List<Map<String,Object>> getOrderNotDealByUserId(@Param("userId") int userId);

    /**
     * 通过用户ID返回该用户已经处理的订单信息
     * @param userId
     * @return
     */
    public List<Map<String,Object>> getOrderDealByUserId(@Param("userId") int userId);

    /**
     * 通过订单ID获取订单信息
     * @param orderId
     * @return
     */
    public Order getOrderDetailedById(@Param("orderId") int orderId);

}
