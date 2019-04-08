package com.uway.product.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daboo.entity.page.PageObject;
import com.uway.common.utils.JSONUtils;
import com.uway.common.utils.OpLog;
import com.uway.core.web.BaseController;
import com.uway.product.entity.Product;
import com.uway.product.entity.ProductType;
import com.uway.product.service.ProductService;
import com.uway.product.service.ProductTypeService;
import com.uway.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
@Controller
@Scope("session")
@RequestMapping(value = "/product")
@OpLog(logDesc="零售控制")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private ProductTypeService productTypeService;

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
        return "product/init";
    }

    /**
     *
     *  增加客户
     * */
    @RequestMapping(value = "showAdd")
    public String showAdd(HttpServletRequest request){
        Map<String,String> productMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusMap", JSONUtils.toCombo(productMap));
        Map conditionMap = new HashMap<String,Object>();
        conditionMap.put("statusId",1);
        List<ProductType> productTypeList = productTypeService.findModelsByCondition(conditionMap);
        JSONArray jsnArray = JSONObject.parseArray(JSONUtils.toJson(productTypeList));
        request.setAttribute("productTypeMap", JSONUtils.toComboByObject(jsnArray,"id","productType",""));
        return "product/add";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public void list(HttpServletRequest request,HttpServletResponse response) {
        int pages = Integer.parseInt(request.getParameter("page"));
        int rows = Integer.parseInt(request.getParameter("rows"));
        Map<String,Object> map = new HashMap<String,Object>();
        String startTime = request.getParameter("createTimeStart");
        String endTime = request.getParameter("createTimeEnd");
        String productType = request.getParameter("productType");
        String product = request.getParameter("product");
        String statusId = request.getParameter("statusId");
        map.put("createTimeStart", startTime == null?"":startTime);
        map.put("createTimeEnd", endTime == null?"":endTime);
        map.put("productType", productType == null?"":productType);
        map.put("product", product == null?"":product);
        map.put("statusId", statusId == null?"":statusId);
        map.put("pages", (pages - 1)*rows);
        map.put("rows", rows);
        List<Product> productList = productService.searchByPaging(map);
        Map<String,Object> totalMap = new HashMap<String,Object>();
        int total = productService.total(totalMap);
        JSONObject json = new JSONObject();
        json.put("total",total);
        json.put("rows",productList);
        writeToPage(json.toString(),response);
    }

    /**
     *  增加零售数据
     *
     * */
    @RequestMapping(value ="add")
    public String add(HttpServletRequest request, Product product){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format( new Date());
        product.setCreateTime(time);
        productService.persist(product);
        return "success";
    }

    /**
     *  修改数据
     *
     * */
    @RequestMapping(value = "showEdit")
    public String edit(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        String productType = product.getTypeId()+"";
        request.setAttribute("product", product);
        Map<String,String> productMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusMap", JSONUtils.toCombo(productMap));
        PageObject pageObject = getPageObject(request,"create_time asc");
        pageObject.getCondition().put("status_id","1");
        List<ProductType> productTypeList = productTypeService.findModelsByCondition(pageObject);
        JSONArray jsonArray = JSONObject.parseArray(JSONUtils.toJson(productTypeList));
        request.setAttribute("productTypeMap", JSONUtils.toComboByObject(jsonArray,"id","productType",productType+""));
        return "product/edit";
    }

    @RequestMapping(value = "update")
    public String update(HttpServletRequest request,Product product){
        productService.updateById(product);
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
        List<String> productIds = Arrays.asList(id);
        productService.deleteByIds(productIds);
        JSONObject json = new JSONObject();
        json.put("success", "true");
        writeToPage(json.toString(), response);
    }

}
