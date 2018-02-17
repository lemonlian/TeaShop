package com.service.Impl;

import com.annotation.DataSource;
import com.dao.TestDao;
import com.service.TestService;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestDao testDao;
    @Override
//    @DataSource("write")
    public int testWr(int id, String name) {
        return testDao.testWr(id,name);
    }

    @Override
//    @DataSource("read")
    public List<Map<String, String>> testR(int id) {
        return testDao.testR(id);
    }

    public int aps(int i){
        System.out.println(i);
        return i;
    }

    @Test
    public void test(){
        aps(5);
    }
}
