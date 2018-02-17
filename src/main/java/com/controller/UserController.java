package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.User;
import com.service.UserService;
import com.utils.CommonUtil;
import com.utils.EnumUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    /**
     * 用户注册
     * @param userName
     * @param userPassWord
     * @return  json ：code: 1 注册成功，0注册失败  msg: 返回信息  date: 返回数据
     * eg.{"code":0,"data":null,"msg":"用户名已存在"}
     */
    @RequestMapping(value = "/userRegister")
    @ResponseBody
    public JSONObject userRegister(String userName, String userPassWord){
        if (userName == null || "".equals(userName))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"用户名为空",null);
        if (userPassWord == null || "".equals(userPassWord))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"密码为空",null);
        int reslut = 0;
        try{
            reslut = userService.userRegister(userName,userPassWord);
        }catch (Exception e){
            return CommonUtil.constructResponse(EnumUtil.SQL_FAILURE,"数据库异常",null);
        }
        if (reslut == 1)
            return CommonUtil.constructResponse(EnumUtil.OK,"注册成功",null);
        if (reslut == EnumUtil.SAME_NAME)
            return CommonUtil.constructResponse(EnumUtil.SAME_NAME,"同户名已存在",null);
        return CommonUtil.constructResponse(EnumUtil.FAILURE,"注册失败",null);
    }

    /**
     * 用户登录
     * @param userName
     * @param userPassWord
     * @return json：code: 2 管理员登录成功，1 普通用户登录成功，0登录失败  msg: 返回信息  date: 返回数据
     * eg.{"code":1,"data":{"userBalance":0,"userId":3,"userName":"text1","userPassWord":"text1","userState":0},"msg":"用户登录成功"}
     */
    @RequestMapping("/userLogin")
    @ResponseBody
    public JSONObject userLogin(HttpServletRequest request, HttpServletResponse response, String userName, String userPassWord){
        if (userName == null || "".equals(userName))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"用户名为空",null);
        if (userPassWord == null || "".equals(userPassWord))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"密码为空",null);
        User user = null;
        try {
            user  = userService.userLogin(userName,userPassWord);
            if (user == null)
                return CommonUtil.constructResponse(EnumUtil.NOT_DATA,"无该用户或密码错误",null);
            request.getSession().setAttribute("user",user);
            /*
            if (user.getUserState() == 1 || user.getUserState() ==0)
                request.getRequestDispatcher("index.html").forward(request,response);
                */
        }catch (Exception e){
            return CommonUtil.constructResponse(EnumUtil.SQL_FAILURE,"数据库异常",null);
        }
        return CommonUtil.constructResponse(EnumUtil.OK,"登录成功",user);
    }

    /**
     * 根据用户ID 返回用户详情
     * @param userId
     * @return
     * eg.{"code":1,"data":[{"user_balance":200,"user_id":3,"user_name":"text1"}],"msg":"获取用户信息成功"}
     */
    @RequestMapping("/userDetailedById")
    @ResponseBody
    public JSONObject userDetailedById(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        List list = userService.userDetailedById(user.getUserId());
        if (list != null) {
            return CommonUtil.constructResponse(EnumUtil.OK,"获取用户信息成功",list);
        }
        return CommonUtil.constructResponse(EnumUtil.FAILURE,"获取用户信息失败",null);
    }

    /**
     * 根据用户ID 充值余额
     * @param userId
     * @param userBalance
     * @return
     */
    @RequestMapping("/userRechargeBalance")
    @ResponseBody
    public JSONObject userRechargeBalance(HttpSession session, String userBalance){
        double money = 0;
        try {
            money = Double.valueOf(userBalance);
        }catch (Exception e){
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"传入的参数出错",null);
        }
        User user = (User) session.getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        int reslut = 0;
        try{
            reslut = userService.userRechargeBalance(user.getUserId(),money);
        }catch (Exception e){
            return CommonUtil.constructResponse(EnumUtil.SQL_FAILURE,"数据库异常",null);
        }
        if (reslut == 1)
            return CommonUtil.constructResponse(EnumUtil.OK,"充值成功",null);
        return CommonUtil.constructResponse(EnumUtil.FAILURE,"充值失败",null);

    }
}
