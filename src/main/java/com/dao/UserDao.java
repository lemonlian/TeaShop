package com.dao;

import com.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * 用户注册
     * @param userName
     * @param userPassWord
     * @return  int ：1 注册成功，0注册失败
     */
    public int userRegister(@Param("userName") String userName,
                            @Param("userPassWord") String userPassWord);

    /**
     * 注册时检查是否重名
     * @param userName
     * @return
     */
    public String userRegisterCheckName(@Param("userName") String userName);

    /**
     * 用户登录
     * @param userName
     * @param userPassWord
     * @return int：1 登录成功，0注册失败
     */
    public User userLogin(@Param("userName") String userName,
                          @Param("userPassWord") String userPassWord);

    /**
     * 根据用户ID 返回用户详情
     * @param userId
     * @return
     */
    public List<Map<String, Object>> userDetailedById(@Param("userId") int userId);

    /**
     * 根据用户ID 充值余额
     * @param userId
     * @param userBalance
     * @return
     */
    public int userRechargeBalance(@Param("userId") int userId,
                                   @Param("userBalance") double userBalance);

    /**
     * 根据用户ID 扣除余额
     * @param userId
     * @param userBalance
     * @return
     */
    public int userDeductBalance(@Param("userId") int userId,
                                         @Param("userBalance") double userBalance);


}
