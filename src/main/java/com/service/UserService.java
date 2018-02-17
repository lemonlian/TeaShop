package com.service;

import com.alibaba.fastjson.JSONObject;
import com.bean.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 用户注册
     * @param userName
     * @param userPassWord
     * @return  json ：code: 1 注册成功，0注册失败  msg: 返回信息  date: 返回数据
     */
    public int userRegister(String userName, String userPassWord) throws Exception;


    /**
     * 用户登录
     * @param userName
     * @param userPassWord
     * @return json：code: 2 管理员登录成功，1 普通用户登录成功，0登录失败  msg: 返回信息  date: 返回数据
     */
    public User userLogin(String userName, String userPassWord);

    /**
     * 根据用户ID 返回用户详情
     * @param userId
     * @return
     */
    public List<Map<String,String>> userDetailedById(int userId);

    /**
     * 根据用户ID 充值余额
     * @param userId
     * @param userBalance
     * @return
     */
    public int userRechargeBalance(int userId, double userBalance) throws Exception;

    /**
     * 根据用户ID 扣除余额
     * @param userId
     * @param userBalance
     * @return
     */
    public int userDeductBalance(int userId, double userBalance) throws Exception;
}
