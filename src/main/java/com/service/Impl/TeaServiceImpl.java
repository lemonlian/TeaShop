package com.service.Impl;

import com.bean.Tea;
import com.dao.TeaDao;
import com.service.TeaService;
import com.utils.EnumUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeaServiceImpl implements TeaService{

    @Resource
    private TeaDao teaDao;
    /**
     * 添加茶
     * @param tea
     * @return
     */
    @Override
    public int addTea(Tea tea) {
        int reslut = 0;
        try {
            reslut = teaDao.addTea(tea);
        }catch (Exception e){
            return EnumUtil.SQL_FAILURE;
        }
        return reslut;
    }

    /**
     * 删除茶
     * @param teaId
     * @return
     */
    @Override
    public int deleteTeaByID(int teaId) {
        int reslut = 0;
        try {
            reslut = teaDao.deleteTeaByID(teaId);
        }catch (Exception e){
            return EnumUtil.SQL_FAILURE;
        }
        return reslut;
    }

    /**
     * 更新茶的信息
     * @param tea
     * @return
     */
    @Override
    public int updateTeaDetailed(Tea tea) {
        int reslut = 0;
        try {
            reslut = teaDao.updateTeaDetailed(tea);
        }catch (Exception e){
            return EnumUtil.SQL_FAILURE;
        }
        return reslut;
    }

    /**
     * 更新茶的状态
     * @param teaId
     * @param teaState
     * @return
     */
    @Override
    public int updateTeaState(int teaId, int teaState) {
        int reslut = 0;
        try {
            reslut = teaDao.updateTeaState(teaId,teaState);
        }catch (Exception e){
            return EnumUtil.SQL_FAILURE;
        }
        return reslut;
    }

    /**
     * 更新茶的份数
     * @param teaId
     * @param buyNum
     * @return
     */
    public int updateTeaNum(int teaId,int buyNum){
        int reslut = 0;
        try {
            reslut = teaDao.updateTeaNum(teaId,buyNum);
        }catch (Exception e){
            return EnumUtil.SQL_FAILURE;
        }
        return reslut;
    }

    /**
     * 得到所有产品参数
     * @return
     */
    @Override
    public List<Map<String, Object>> getTeaDetailed_all() {
        List list;
        try {
            list = teaDao.getTeaDetailed_all();
        }catch (Exception e){
            return null;
        }
        return list;
    }

    /**
     * 得到上架的茶的信息
     * @return
     */
    @Override
    public List<Map<String, Object>> getTeaDetailedByState() {
        List list;
        try {
            list = teaDao.getTeaDetailedByState();
        }catch (Exception e){
            return null;
        }
        return list;
    }

    /**
     * 通过ID 得到茶的信息
     * @param teaId
     * @return
     */
    @Override
    public List<Map<String, Object>> getTeaDetailedByID(int teaId) {
        List list;
        try {
            list = teaDao.getTeaDetailedByID(teaId);
        }catch (Exception e){
            return null;
        }
        return list;
    }
}
