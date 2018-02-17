package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bean.Order;
import com.bean.User;
import com.service.OrderService;
import com.service.TeaService;
import com.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.utils.CommonUtil;
import com.utils.EnumUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @Resource
    private TeaService teaService;

    /**
     * 创建一个订单
     * @param
     * @return
     * eg.{"code":1,"data":null,"msg":"创建成功"}
     */
    @RequestMapping("/creatOrder")
    @ResponseBody
    public JSONObject creatOrder(HttpServletRequest request){
        Order order = new Order();
        //数据验证
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        order.setUserId(user.getUserId());
        String teaId = request.getParameter("teaId");
        if (teaId == null || "".equals(teaId))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品参数不能为空",null);
        else {
            try {
                order.setTeaId(Integer.valueOf(teaId));
            }
            catch (Exception e){
                return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品参数不能有字符",null);
            }
        }
        String teaBuyNum = request.getParameter("teaBuyNum");
        if (teaBuyNum == null || "".equals(teaBuyNum))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"购买数量参数不能为空",null);
        else {
            try {
                order.setOrderBuyNum(Integer.valueOf(teaBuyNum));
            }
            catch (Exception e){
                return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"数量参数不能有字符",null);
            }
        }
        int reslut = orderService.createOrder(order);
        if (reslut == 1)
            return CommonUtil.constructResponse(EnumUtil.OK,"加入购物车成功",null);
        else
            return CommonUtil.constructResponse(reslut,"操作失败",null);
    }

    /**
     * 删除订单
     * @param request
     * @return
     * eg.{"code":1,"data":null,"msg":"删除成功"}
     */
    @RequestMapping("/deleteOrder")
    @ResponseBody
    public JSONObject deleteOrder(HttpServletRequest request){
        //数据验证
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        String orderId = request.getParameter("orderId");
        if (orderId == null || "".equals(orderId))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"订单号不能为空",null);
        int id = 0;
        try {
            id = Integer.valueOf(orderId);
        }catch (Exception e){
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"订单号不能有字符",null);
        }
        int reslut = orderService.deleteOrder(id);
        if (reslut == 1)
            return CommonUtil.constructResponse(EnumUtil.OK,"删除成功",null);
        else
            return CommonUtil.constructResponse(reslut,"删除失败",null);
    }

    /**
     * 更新订单
     * @param request
     * @return
     * eg.{"code":1,"data":null,"msg":"修改成功"}
     */
    @RequestMapping("/updateOrder")
    @ResponseBody
    public JSONObject updateOrder(HttpServletRequest request){
        Order order = new Order();
        //数据验证
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        String orderId = request.getParameter("orderId");
        if (orderId == null || "".equals(orderId))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"订单号不能为空",null);
        else {
            try {
                order.setOrderId(Integer.valueOf(orderId));
            }
            catch (Exception e){
                return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"订单号不能有字符",null);
            }
        }
        String teaBuyNum = request.getParameter("teaBuyNum");
        if (teaBuyNum == null || "".equals(teaBuyNum))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"购买数量参数不能为空",null);
        else {
            try {
                order.setOrderBuyNum(Integer.valueOf(teaBuyNum));
            }
            catch (Exception e){
                return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"数量参数不能有字符",null);
            }
        }
        int reslut = orderService.updateOrder(order);
        if (reslut == 1)
            return CommonUtil.constructResponse(EnumUtil.OK,"修改成功",null);
        else
            return CommonUtil.constructResponse(reslut,"修改失败",null);
    }

    /**
     * 获取所有已处理的订单信息（管理员）
     * @return
     * eg.{"code":1,"data":[{"o_tea_id":3,"o_user_id":2,"order_id":3,"order_price":500,"order_state":1,"tea_buy_num":5}],"msg":"获取信息成功"}
     */
    @RequestMapping("/getAllOrderDetail")
    @ResponseBody
    public JSONObject getAllOrderDetail(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        if (user.getUserState() != 1) {
            return CommonUtil.constructResponse(EnumUtil.FAILURE,"没有管理员权限",null);
        }
        List list = orderService.getAllOrderDetail();
        if (list == null)
            return CommonUtil.constructResponse(EnumUtil.SQL_FAILURE,"数据库异常",null);
        if (list.size() <= 0)
            return CommonUtil.constructResponse(EnumUtil.NOT_DATA,"数据为空",null);
        return CommonUtil.constructResponse(EnumUtil.OK,"获取信息成功",list);
    }

    /**
     * 通过用户ID返回该用户没有处理的订单信息（购物车）
     * @param
     * @return
     * eg.{"code":1,"data":[{"o_tea_id":3,"o_user_id":2,"order_id":4,"order_price":200,"order_state":0,"tea_buy_num":2}],"msg":"获取信息成功"}
     */
    @RequestMapping("/getOrderNotDealByUserId")
    @ResponseBody
    public JSONObject getOrderNotDealByUserId(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        List list = orderService.getOrderNotDealByUserId(user.getUserId());
        if (list == null)
            return CommonUtil.constructResponse(EnumUtil.SQL_FAILURE,"数据库异常",null);
        if (list.size() <= 0)
            return CommonUtil.constructResponse(EnumUtil.NOT_DATA,"数据为空",null);
        return CommonUtil.constructResponse(EnumUtil.OK,"获取信息成功",list);
    }

    /**
     * 通过用户ID返回该用户已经处理的订单信息
     * @param
     * @return
     * eg.{"code":1,"data":[{"o_tea_id":3,"o_user_id":2,"order_id":3,"order_price":500,"order_state":1,"tea_buy_num":5}],"msg":"获取信息成功"}
     */
    @RequestMapping("/getOrderDealByUserId")
    @ResponseBody
    public JSONObject getOrderDealByUserId(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        List list = orderService.getOrderDealByUserId(user.getUserId());
        if (list == null)
            return CommonUtil.constructResponse(EnumUtil.SQL_FAILURE,"数据库异常",null);
        if (list.size() <= 0)
            return CommonUtil.constructResponse(EnumUtil.NOT_DATA,"数据为空",null);
        return CommonUtil.constructResponse(EnumUtil.OK,"获取信息成功",list);
    }

    /**
     * 支付订单
     * @param request
     * @return
     */
    @RequestMapping("/payForOrder")
    @ResponseBody
    public JSONObject payForOrder(HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        double userBalance ;
        int teaNum;
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = orderService.getOrderDetailedById(orderId);
        String temp = String.valueOf(userService.userDetailedById(2).get(0).get("user_balance"));
        userBalance = Double.parseDouble(temp);
        double price = order.getOrderPrice();
        if (price > userBalance)
            return CommonUtil.constructResponse(EnumUtil.FAILURE,"余额不足",null);
        teaNum = Integer.parseInt(teaService.getTeaDetailedByID(order.getTeaId()).get(0).get("tea_num").toString());
        if (teaNum < order.getOrderBuyNum()) {
            return CommonUtil.constructResponse(EnumUtil.FAILURE,"茶剩余份数不足",null);
        }
        userService.userDeductBalance(order.getUserId(),order.getOrderPrice());
        int reslut = teaService.updateTeaNum(order.getTeaId(),order.getOrderBuyNum());
        orderService.updateOrderState(orderId,1);
        return CommonUtil.constructResponse(EnumUtil.OK,"支付成功",null);
    }
}
