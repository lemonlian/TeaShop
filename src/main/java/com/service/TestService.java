package com.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TestService {
    public int testWr(int id,String name);

    public List<Map<String,String>> testR(int id);

    public int aps(int i);
}
