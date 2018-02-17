package com.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TestDao {
    /**
     * 测试写
     * @param id
     * @param name
     * @return
     */
    public int testWr(@Param("id") int id,@Param("name") String name);

    /**
     * 测试读
     * @param id
     * @return
     */
    public List<Map<String,String>> testR(@Param("id") int id);

}
