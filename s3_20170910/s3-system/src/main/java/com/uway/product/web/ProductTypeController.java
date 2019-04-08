package com.uway.product.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daboo.entity.page.PageObject;
import com.uway.common.utils.JSONUtils;
import com.uway.common.utils.OpLog;
import com.uway.core.web.BaseController;
import com.uway.product.entity.ProductType;
import com.uway.product.service.ProductTypeService;
import com.uway.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
@Controller
@Scope("session")
@RequestMapping(value = "/productType")
@OpLog(logDesc="零售控制")
public class ProductTypeController extends BaseController{

    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private SysDictService sysDictService;
    /**
     *
     * 初始化页面
     * */
    @RequestMapping(value = "init")
    public String init(HttpServletRequest request){
        Map<String,String> productMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusMap", JSONUtils.toJson(productMap));
        Map<String,String> statusMap = new HashMap<String,String>();
        statusMap.put("0","无效");
        statusMap.put("1","有效");
        request.setAttribute("status", JSONUtils.toCombo(statusMap));
        Map conditionMap = new HashMap<String,Object>();
        conditionMap.put("statusId",1);
        List<ProductType> productTypeList = productTypeService.findModelsByCondition(conditionMap);
        JSONArray jsnArray = JSONObject.parseArray(JSONUtils.toJson(productTypeList));
        request.setAttribute("productTypeMap", JSONUtils.toComboByObject(jsnArray,"id","productType",""));
        return "productType/init";
    }

    /**
     *
     *  增加客户
     * */
    @RequestMapping(value = "showAdd")
    public String showAdd(HttpServletRequest request){
        Map<String,String> productMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusMap", JSONUtils.toCombo(productMap));
        return "productType/add";
    }

    @RequestMapping(value = "list")
    public void list(HttpServletRequest request,HttpServletResponse response) {
        PageObject po = getPageObject(request,"create_time desc");
        String startTime = request.getParameter("createTimeStart");
        String endTime = request.getParameter("createTimeEnd");
        String productType = request.getParameter("productType");
        String typeId = request.getParameter("typeId");
        String statusId = request.getParameter("statusId");
        po.getCondition().put("createTimeStart", startTime == null?"":startTime);
        po.getCondition().put("createTimeEnd", endTime == null?"":endTime);
        po.getCondition().put("productType", productType == null?"":productType);
        po.getCondition().put("statusId", statusId == null?"":statusId);
        po.getCondition().put("typeId", typeId == null?"":typeId);
        writeToPage(JSONUtils.toJson(productTypeService.getGridDataModelByCondition(po)), response);
    }

    /**
     *  增加零售数据
     *
     * */
    @RequestMapping(value ="add")
    public String add(HttpServletRequest request, ProductType productType){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format( new Date());
        productType.setCreateTime(time);
        productTypeService.persist(productType);
        return "success";
    }

    /**
     *  修改数据
     *
     * */
    @RequestMapping(value = "showEdit")
    public String edit(HttpServletRequest request){
        Map<String,String> productMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusMap", JSONUtils.toCombo(productMap));
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id", id);
        ProductType productType = productTypeService.findById(id);
        request.setAttribute("productType", productType);
        return "productType/edit";
    }

    @RequestMapping(value = "update")
    public String update(HttpServletRequest request,ProductType productType){
        productTypeService.updateById(productType);
        return "success";
    }

    /**
     *  删除数据
     *
     * */
    @RequestMapping(value = "delete")
    public void delete(HttpServletRequest request,HttpServletResponse response){
        String ids = request.getParameter("ids");
        String[] id = ids.split(",");
        List<String> productTypeId = Arrays.asList(id);
        productTypeService.deleteByIds(productTypeId);
        JSONObject json = new JSONObject();
        json.put("success", "true");
        writeToPage(json.toString(), response);
    }

}
