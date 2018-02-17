package com.service;

import com.bean.Tea;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeaService {

    /**
     * 增
     */

    /**
     * 添加茶
     * @param tea
     * @return
     */
    public int addTea(Tea tea);

    /**
     * 删
     */

    /**
     * 根据ID 删除茶
     * @param teaId
     * @return
     */
    public int deleteTeaByID(int teaId);

    /**
     * 改
     */

    /**
     * 修改茶的信息
     * @param tea
     */
    public int updateTeaDetailed(Tea tea);

    /**
     * 更改茶的状态
     * @param teaId
     * @param teaState
     * @return
     */
    public int updateTeaState(int teaId,int teaState);

    /**
     * 更新茶的份数
     * @param teaId
     * @param buyNum
     * @return
     */
    public int updateTeaNum(int teaId,int buyNum);

    /**
     * 查
     */

    /**
     * 得到所有的茶的信息
     * @return
     */
    public List<Map<String,Object>> getTeaDetailed_all();

    /**
     * 得到上架的所有的茶的信息
     * @return
     */
    public List<Map<String,Object>> getTeaDetailedByState();

    /**
     * 根据茶的ID 返回茶的信息
     * @param teaId
     * @return
     */
    public List<Map<String,Object>> getTeaDetailedByID(int teaId);
}
