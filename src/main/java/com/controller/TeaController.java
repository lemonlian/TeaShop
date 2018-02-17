package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.Tea;
import com.bean.User;
import com.service.TeaService;
import com.utils.CommonUtil;
import com.utils.EnumUtil;
import com.utils.FileUploadTools;
import com.utils.IPTimeStamp;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tea")
public class TeaController {

    //图片保存到本地路径
    private static final String PHOTO_SAVE_URL = "D:/web大作业/text/src/main/webapp/images/";
    //图片保存到数据库路径
    private static final String PHOTO_SAVE_IN_MYSQL_URL = "http://localhost:8080/images/"/*+new IPTimeStamp().getTimeStamp()*/;

    @Resource
    private TeaService teaService;

    /**
     * 添加茶
     * @param file
     * @param request
     * @return
     * @throws IOException
     * eg.{"code":1,"data":null,"msg":"添加成功"}
     */
    @RequestMapping("/addTea")
    @ResponseBody
    public JSONObject addTea(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        /**
         * 数据验证
         */
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        if (user.getUserState() != 1)
            return CommonUtil.constructResponse(EnumUtil.FAILURE,"没有管理员权限",null);
        if (file.isEmpty())
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"上传图片出错",null);
        // 文件保存路径
        String filePath = PHOTO_SAVE_URL + file.getOriginalFilename();
        // 转存文件
        file.transferTo(new File(filePath));
        //表中路径
        String teaPhoto = PHOTO_SAVE_IN_MYSQL_URL+file.getOriginalFilename();
        if (request.getParameter("teaName") == null || "".equals(request.getParameter("teaName")))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品名字不能为空",null);
        String teaName = request.getParameter("teaName");
        double teaPrice = 0;
        if (request.getParameter("teaPrice") != null && !("".equals(request.getParameter("teaPrice")))){
            try {
                teaPrice = Double.valueOf(request.getParameter("teaPrice"));
            }catch (Exception e){
                return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品价格不能有字符",null);
            }
        }
        String teaInro = "暂无简介";
        if (request.getParameter("teaInro") != null && !("".equals(request.getParameter("teaInro"))))
            teaInro = request.getParameter("teaInro");
        int teaNum = 0;
        if (request.getParameter("teaNum") != null && !("".equals(request.getParameter("teaNum")))){
            try {
                teaNum = Integer.valueOf(request.getParameter("teaNum"));
            }catch (Exception e){
                return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品数量不能有字符",null);
            }
        }
        Tea tea = new Tea(teaName,teaPrice,teaPhoto,teaInro,teaNum);

        /**
         * Service
         */
        int reslut = teaService.addTea(tea);
        if (reslut == 1)
            return CommonUtil.constructResponse(EnumUtil.OK,"添加成功",null);
        else
            return CommonUtil.constructResponse(reslut,"操作失败",null);
    }

    /**
     * 删除茶
     * @param session
     * @param teaId
     * @return
     * eg.{"code":1,"data":null,"msg":"删除成功"}
     */
    @RequestMapping("/deleteTeaByID")
    @ResponseBody
    public JSONObject deleteTeaByID(HttpSession session, String teaId){
        User user = (User) session.getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        if (user.getUserState() != 1)
            return CommonUtil.constructResponse(EnumUtil.FAILURE,"没有管理员权限",null);
        if(teaId == null || "".equals(teaId))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"参数错误",null);
        int id;
        try {
            id = Integer.valueOf(teaId);
        }catch (Exception e){
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"参数错误",null);
        }
        int reslut = teaService.deleteTeaByID(id);
        if (reslut == 1)
            return CommonUtil.constructResponse(EnumUtil.OK,"删除成功",null);
        else
            return CommonUtil.constructResponse(reslut,"操作失败",null);
    }

    /**
     * 更新茶
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/updateTeaDetailed")
    @ResponseBody
    public JSONObject updateTeaDetailed(HttpServletRequest request,MultipartFile file) throws IOException {
        /**
         * 数据验证
         */
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        if (user.getUserState() != 1)
            return CommonUtil.constructResponse(EnumUtil.FAILURE,"没有管理员权限",null);
        if (file.isEmpty())
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"上传图片出错",null);
        // 文件保存路径
        String filePath = PHOTO_SAVE_URL + file.getOriginalFilename();
        // 转存文件
        file.transferTo(new File(filePath));
        //表中路径
        String teaPhoto = PHOTO_SAVE_IN_MYSQL_URL+file.getOriginalFilename();
        int teaId ;
        if (request.getParameter("teaId") == null || "".equals(request.getParameter("teaId")))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品ID不能为空",null);
        teaId = Integer.valueOf(request.getParameter("teaId"));
        if (request.getParameter("teaName") == null || "".equals(request.getParameter("teaName")))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品名字不能为空",null);
        String teaName = request.getParameter("teaName");
        double teaPrice = 0;
        if (request.getParameter("teaPrice") != null && !("".equals(request.getParameter("teaPrice")))){
            try {
                teaPrice = Double.valueOf(request.getParameter("teaPrice"));
            }catch (Exception e){
                return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品价格不能有字符",null);
            }
        }
        String teaInro = "暂无简介";
        if (request.getParameter("teaInro") != null && !("".equals(request.getParameter("teaInro"))))
            teaInro = request.getParameter("teaInro");
        int teaNum = 0;
        if (request.getParameter("teaNum") != null && !("".equals(request.getParameter("teaNum")))){
            try {
                teaNum = Integer.valueOf(request.getParameter("teaNum"));
            }catch (Exception e){
                return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品数量不能有字符",null);
            }
        }
        Tea tea = new Tea(teaName,teaPrice,teaPhoto,teaInro,teaNum);
        tea.setTeaId(teaId);
        /**
         * Service
         */
        System.out.println(tea  );
        int reslut = teaService.updateTeaDetailed(tea);
        if (reslut == 1)
            return CommonUtil.constructResponse(EnumUtil.OK,"修改成功",null);
        else
            return CommonUtil.constructResponse(reslut,"操作失败",null);

    }

    /**
     * 更新茶的状态
     * @param session
     * @param teaId
     * @param teaState
     * @return
     */
    @RequestMapping("/updateTeaState")
    @ResponseBody
    public JSONObject updateTeaState(HttpSession session, String teaId, String teaState){
        /**
         * 数据验证
         */
        User user = (User) session.getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        if (user.getUserState() != 1)
            return CommonUtil.constructResponse(EnumUtil.FAILURE,"没有管理员权限",null);
        if (teaId == null || "".equals(teaId))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品ID参数错误",null);
        if (teaState == null || "".equals(teaState))
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品状态参数错误",null);
        int id;
        try {
            id = Integer.valueOf(teaId);
        }catch (Exception e){
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品ID不能有字符",null);
        }
        int state;
        try {
            state = Integer.valueOf(teaState);
        }catch (Exception e){
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品状态码不能有字符",null);
        }
        if (state == 1)
            state = 2;
        else
            state = 1;
        int reslut = teaService.updateTeaState(id,state);
        if (reslut == 1)
            return CommonUtil.constructResponse(EnumUtil.OK,"操作成功",teaService.getTeaDetailedByID(Integer.parseInt(teaId)));
        else
            return CommonUtil.constructResponse(reslut,"操作失败",null);
    }

    /**
     * 得到所有产品参数
     * @return
     * eg.{"code":1,"data":[{"tea_id":3,"tea_intro":"这是tea1","tea_name":"tea1","tea_num":100,"tea_photo":"http://localhost:8080/images/photo1.jpg","tea_price":100,"tea_state":2},{"tea_id":4,"tea_intro":"这是tea2","tea_name":"tea2","tea_num":0,"tea_photo":"http://localhost:8080/images/photo1.jpg","tea_price":0,"tea_state":2},{"tea_id":5,"tea_intro":"暂无简介","tea_name":"tea3","tea_num":0,"tea_photo":"http://localhost:8080/images/photo1.jpg","tea_price":0,"tea_state":2},{"tea_id":6,"tea_intro":"暂无简介","tea_name":"tea3","tea_num":0,"tea_photo":"http://localhost:8080/images/2017-12-23 13.21.15.469photo2.jpg","tea_price":0,"tea_state":1}],"msg":"操作成功"}
     */
    @RequestMapping("/getTeaDetailed_all")
    @ResponseBody
    public JSONObject getTeaDetailed_all(HttpSession session){
        /**
         * 数据验证
         */
        User user = (User) session.getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);

        /**
         * service
         */
        List list = teaService.getTeaDetailed_all();
        if (list == null)
            return CommonUtil.constructResponse(EnumUtil.FAILURE,"操作失败",null);
        return CommonUtil.constructResponse(EnumUtil.OK,"操作成功",list);
    }

    /**
     * 得到所有上架产品参数
     * @return
     * eg.{"code":1,"data":[{"tea_id":6,"tea_intro":"暂无简介","tea_name":"tea3","tea_num":0,"tea_photo":"http://localhost:8080/images/2017-12-23 13.21.15.469photo2.jpg","tea_price":0,"tea_state":1}],"msg":"操作成功"}
     */
    @RequestMapping("/getTeaDetailedByState")
    @ResponseBody
    public JSONObject getTeaDetailedByState(HttpSession session){
        /**
         * 数据验证
         */
        User user = (User) session.getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        /**
         * service
         */
        List list = teaService.getTeaDetailedByState();
        if (list == null)
            return CommonUtil.constructResponse(EnumUtil.FAILURE,"操作失败",null);
        return CommonUtil.constructResponse(EnumUtil.OK,"操作成功",list);
    }

    /**
     * 通过产品ID 获取茶的信息
     * @return
     * eg.{"code":1,"data":[{"tea_id":6,"tea_intro":"暂无简介","tea_name":"tea3","tea_num":0,"tea_photo":"http://localhost:8080/images/2017-12-23 13.21.15.469photo2.jpg","tea_price":0,"tea_state":1}],"msg":"操作成功"}
     */
    @RequestMapping("/getTeaDetailedByID")
    @ResponseBody
    public JSONObject getTeaDetailedByID(HttpSession session,String teaId){
        /**
         * 数据验证
         */
        User user = (User) session.getAttribute("user");
        if (user == null)
            return CommonUtil.constructResponse(EnumUtil.NOT_LOGIN,"用户未登录",null);
        int id;
        try {
            id = Integer.valueOf(teaId);
        }catch (Exception e){
            return CommonUtil.constructResponse(EnumUtil.PARAM_ERROR,"产品ID不能有字符",null);
        }
        /**
         * service
         */
        List list = teaService.getTeaDetailedByID(id);
        if (list == null)
            return CommonUtil.constructResponse(EnumUtil.FAILURE,"操作失败",null);
        return CommonUtil.constructResponse(EnumUtil.OK,"操作成功",list);
    }

}
