package com.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.bean.User;
import com.dao.UserDao;
import com.service.UserService;
import com.utils.CommonUtil;
import com.utils.EnumUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.utils.EnumUtil;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    /**
     * 用户注册
     * @param userName
     * @param userPassWord
     * @return  json ：code: 1 注册成功，0注册失败  msg: 返回信息  date: 返回数据
     */
    @Override
    public int userRegister(String userName, String userPassWord) throws Exception {
        //检查是否存在重名
        String sameName = null;
        try {
            sameName = userDao.userRegisterCheckName(userName);
        }catch (Exception e){
            throw e;
        }
        if (sameName != null)
            return EnumUtil.SAME_NAME;
        else {
            int reslut = userDao.userRegister(userName,userPassWord);
            if (reslut == 1)
                return EnumUtil.OK;
        }
        return EnumUtil.SYSTEM_ERROR;
    }

    /**
     * 用户登录
     * @param userName
     * @param userPassWord
     * @return json：code: 2 管理员登录成功，1 普通用户登录成功，0登录失败  msg: 返回信息  date: 返回数据
     */
    @Override
    public User userLogin(String userName, String userPassWord) {
       User user = null;
       user = userDao.userLogin(userName, userPassWord);
       return user;
    }

    /**
     * 根据用户ID 返回用户详情
     * @param userId
     * @return
     * {"code":1,"data":[{"user_balance":0,"user_id":2,"user_name":"text","user_password":"text","user_state":0}],"msg":"成功获取信息"}
     */
    @Override
    public List<Map<String,String>> userDetailedById(int userId) {
        List list = new ArrayList();
        try {
            list = userDao.userDetailedById(userId);
        }catch (Exception e){
            return null;
        }
        return  list;
    }

    /**
     * 根据用户ID 充值余额
     * @param userId
     * @param userBalance
     * @return
     */
    @Override
    public int userRechargeBalance(int userId, double userBalance) throws Exception {
        int reslut = 0;
        try {
            reslut = userDao.userRechargeBalance(userId,userBalance);
        }catch (Exception e){
            throw e;
        }
        return reslut;
    }

    /**
     * 根据用户ID 扣除余额
     * @param userId
     * @param userBalance
     * @return
     */
    @Transactional
    @Override
    public int userDeductBalance(int userId, double userBalance) throws Exception {
        int reslut = 0;
        try {
            reslut = userDao.userDeductBalance(userId,userBalance);
        }catch (Exception e){
            throw e;
        }
        return reslut;
    }
}
