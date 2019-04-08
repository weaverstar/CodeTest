package com.uway.repertory.web;

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
import com.uway.repertory.entity.Entrepot;
import com.uway.repertory.service.EntrepotService;
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
 *  仓库
 */
@Controller
@Scope("session")
@RequestMapping(value = "/entrepot")
@OpLog(logDesc="零售控制")
public class EntrepotController extends BaseController {

    @Autowired
    private EntrepotService entrepotService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductService productService;


    /**
     *
     * 初始化页面
     * */
    @RequestMapping(value = "init")
    public String init(HttpServletRequest request){
        Map conditionMap = new HashMap<String,Object>();
        conditionMap.put("statusId",1);
        List<ProductType> productTypeList = productTypeService.findModelsByCondition(conditionMap);
        JSONArray jsnArray = JSONObject.parseArray(JSONUtils.toJson(productTypeList));
        request.setAttribute("productTypeMap", JSONUtils.toComboByObject(jsnArray,"id","productType",""));
        return "repertory/entrepotInit";
    }

    /**
     *  新增
     * */
    @RequestMapping(value = "showAdd")
    public String showAdd(HttpServletRequest request){
        Map conditionMap = new HashMap<String,Object>();
        conditionMap.put("statusId",1);
        List<ProductType> productTypeList = productTypeService.findModelsByCondition(conditionMap);
        JSONArray jsnArray = JSONObject.parseArray(JSONUtils.toJson(productTypeList));
        request.setAttribute("productTypeMap", JSONUtils.toComboByObject(jsnArray,"id","productType",""));
        return "repertory/entrepotAdd";
    }


    /**
     * 分页
     * **/
    @RequestMapping(value = "list")
    @ResponseBody
    public void list(HttpServletRequest request,HttpServletResponse response) {
        int pages = Integer.parseInt(request.getParameter("page"));
        int rows = Integer.parseInt(request.getParameter("rows"));
        String productType = request.getParameter("productType");
        String product = request.getParameter("product");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pages", (pages - 1)*rows);
        map.put("rows", rows);
        map.put("productType", productType);
        map.put("productId", product);
        List<Entrepot> productList = entrepotService.searchByPaging(map);
        Map<String,Object> totalMap = new HashMap<String,Object>();
        int total = entrepotService.total(totalMap);
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
    public String add(HttpServletRequest request, Entrepot entrepot){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format( new Date());
        entrepot.setTemp1(time);
        entrepotService.persist(entrepot);
        return "success";
    }

    /**
     *  修改数据
     *
     * */
    @RequestMapping(value = "showEdit")
    public String edit(HttpServletRequest request){
        /**获取仓库信息**/
        int id = Integer.parseInt(request.getParameter("id"));
        Entrepot entrepot = entrepotService.findById(id);
        request.setAttribute("entrepot", entrepot);
        /**获取产品名称**/
        int productId = entrepot.getProductId();
        Product product = productService.findById(productId);
        String productName = "";
        if(product != null){
            productName = product.getProductName();
        }
        request.setAttribute("productName", productName);
        /**获取产品类型名称**/
        int productTypeId = entrepot.getProductType();
        ProductType productType = productTypeService.findById(productTypeId);
        String productTypeName = "";
        if(productType != null){
            productTypeName = productType.getProductType();
        }
        request.setAttribute("productTypeName", productTypeName);
        /**获取产品类型下拉框**/
        PageObject pageObject = getPageObject(request,"create_time asc");
        pageObject.getCondition().put("status_id","1");
        List<ProductType> productTypeList = productTypeService.findModelsByCondition(pageObject);
        JSONArray jsonArray = JSONObject.parseArray(JSONUtils.toJson(productTypeList));
        request.setAttribute("productTypeMap", JSONUtils.toComboByObject(jsonArray,"id","productType",productTypeId+""));
        /**获取对应产品类型的产品下拉框**/
        pageObject.getCondition().clear();
        pageObject.getCondition().put("status_id","1");
        pageObject.getCondition().put("typeId",productTypeId);
        List<Product> productList = productService.findModelsByCondition(pageObject);
        JSONArray jsonArray2 = JSONObject.parseArray(JSONUtils.toJson(productList));
        request.setAttribute("productMap", JSONUtils.toComboByObject(jsonArray2,"id","productName",productId+""));
        return "repertory/entrepotEdit";
    }

    @RequestMapping(value = "update")
    public String update(HttpServletRequest request, Entrepot entrepot){
        entrepotService.updateById(entrepot);
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
        entrepotService.deleteByIds(productIds);
        JSONObject json = new JSONObject();
        json.put("success", "true");
        writeToPage(json.toString(), response);
    }
}
