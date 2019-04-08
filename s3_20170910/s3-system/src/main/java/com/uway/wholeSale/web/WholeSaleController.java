package com.uway.wholeSale.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daboo.entity.page.PageObject;
import com.uway.common.utils.JSONUtils;
import com.uway.common.utils.OpLog;
import com.uway.core.util.NumberUtil;
import com.uway.core.util.SpringSecurityUtils;
import com.uway.core.web.BaseController;
import com.uway.customer.entity.Customer;
import com.uway.customer.service.CustomerService;
import com.uway.product.entity.Product;
import com.uway.product.entity.ProductType;
import com.uway.product.service.ProductService;
import com.uway.product.service.ProductTypeService;
import com.uway.resale.entity.RelaseMainDayNumModel;
import com.uway.resale.service.ResaleMainDayNumService;
import com.uway.system.service.SysDictService;
import com.uway.wholeSale.entity.WholeSaleMainModel;
import com.uway.wholeSale.entity.WholeSaleModel;
import com.uway.wholeSale.service.WholeSaleMainService;
import com.uway.wholeSale.service.WholeSaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 *  批发
 */
@Controller
@Scope("session")
@RequestMapping(value = "/wholeSale")
@OpLog(logDesc="批发控制")
public class WholeSaleController  extends BaseController{
    Logger log = LoggerFactory.getLogger(WholeSaleController.class);
    @Autowired
    private WholeSaleService wholeSaleService;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private WholeSaleMainService wholeSaleMainService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ResaleMainDayNumService resaleMainDayNumService;

    /**
     *
     * 初始化页面
     * */
    @RequestMapping(value = "init")
    public String init(HttpServletRequest request){
        Map<String,String> statusMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
        request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
        Map<String,String> sexMap = sysDictService.getDetailValueMap("性别");
        request.setAttribute("sexMap", JSONUtils.toJson(sexMap));
        request.setAttribute("sexCombo", JSONUtils.toCombo(sexMap));
        PageObject pageObject = getPageObject(request,"create_time desc");
        pageObject.getCondition().put("statusId",1);
        List<Customer> customerList = customerService.findModelsByCondition(pageObject);
        JSONArray jsonArray = JSONObject.parseArray(JSONUtils.toJson(customerList));
        request.setAttribute("customerMap", JSONUtils.toComboByObject(jsonArray,"id","customerName",""));
        Map<String,String> cityMap = sysDictService.getDetailValueMap("地市");
        request.setAttribute("cityMap", JSONUtils.toCombo(cityMap));
        Map<String,String> payMap = new HashMap<String, String>();
        payMap.put("已付款","已付款");
        payMap.put("未付款","未付款");
        request.setAttribute("payMap", JSONUtils.toCombo(payMap));
        return "whole/init";
    }

    /**
     *  增加零售数据
     *
     * */
    @RequestMapping(value = "showAdd")
    public String add(HttpServletRequest request){
        PageObject pageObject = getPageObject(request,"create_time desc");
        pageObject.getCondition().put("statusId",1);
        List<ProductType> prductTypeList = productTypeService.findModelsByCondition(pageObject);
        JSONArray productJsonArray = JSONObject.parseArray(JSONUtils.toJson(prductTypeList));
        request.setAttribute("productCombo", JSONUtils.toComboByObject(productJsonArray,"id","productType",""));
        Map<String,String> unitMap = sysDictService.getDetailValueMap("单位");
        request.setAttribute("unitCombo", JSONUtils.toCombo(unitMap));
        List<Customer> customerList = customerService.findModelsByCondition(pageObject);
        JSONArray jsonArray = JSONObject.parseArray(JSONUtils.toJson(customerList));
        request.setAttribute("customerMap", JSONUtils.toComboByObject(jsonArray,"id","customerName",""));
        return "whole/add";
    }

    @RequestMapping(value = "detialDict")
    public void detialDict(HttpServletRequest request,HttpServletResponse response) {
        String dict = request.getParameter("type");
        Map<String,String> productMap = sysDictService.getDetailValueMap(dict);
        JSONObject json = new JSONObject();
        json.put("detialDict", JSONUtils.toCombo(productMap));
        writeToPage(json.toString(), response);
    }

    @RequestMapping(value = "list")
    public void list(HttpServletRequest request,HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String,Object>();
        String startTime = request.getParameter("createTimeStart");
        String endTime = request.getParameter("createTimeEnd");
        String name = request.getParameter("customerId");
        String city = request.getParameter("cityId");
        String town = request.getParameter("town");
        String temp2 = request.getParameter("temp2");
        map.put("createTimeStart", startTime == null?"":startTime);
        map.put("createTimeEnd", endTime == null?"":endTime);
        map.put("customerId", name == null?"":name);
        map.put("city", city == null?"":city);
        map.put("town", town == null?"":town);
        map.put("temp2", temp2 == null?"":temp2);
        int pages = Integer.parseInt(request.getParameter("page"));
        int rows = Integer.parseInt(request.getParameter("rows"));
        map.put("pages", (pages - 1)*rows);
        map.put("rows", rows);
        List<Map<String,Object>> list = wholeSaleMainService.findObject(map);
        int total = wholeSaleMainService.findObjectTotal(map);
        JSONObject json = new JSONObject();
        json.put("total",total);
        json.put("rows",list);
        writeToPage(json.toString(),response);
    }

    /**
     *  增加零售数据
     *
     * */
    @RequestMapping(value ="add")
    public String add(HttpServletRequest request, WholeSaleMainModel wholeSaleMainModel){
        long userId = SpringSecurityUtils.getCurrentUser().getUserId();
        wholeSaleMainModel.setOperator(userId+"");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format( new Date());
        wholeSaleMainModel.setOperatorTime(time);

        Map map = request.getParameterMap();
        Set set = map.keySet();
        List<String> list = new ArrayList<String>();
        for (Iterator iter = set.iterator(); iter.hasNext();) {
            String key = (String)iter.next();
            if(key.contains("productType_")){
                String[] keys = key.split("_");
                if(keys.length == 3){
                    list.add(keys[1]);
                }
            }
        }
        List<WholeSaleModel> wholeSaleModelList = new ArrayList<WholeSaleModel>();
        for(String index : list){
            String produtType = request.getParameter("productType_"+index+"_1");
            String product = request.getParameter("product_"+index+"_2");
            double price = NumberUtil.parseDouble(request.getParameter("price_"+index+"_3"),0);
            int produtNum = NumberUtil.parseInt(request.getParameter("productNum_"+index+"_4"),0);
            String unit = request.getParameter("unit_"+index+"_5");
            WholeSaleModel wholeSaleModel = new WholeSaleModel();
            wholeSaleModel.setUnit(unit);
            wholeSaleModel.setProductNum(produtNum);
            wholeSaleModel.setPrice(price);
            wholeSaleModel.setProduct(product);
            wholeSaleModel.setProductType(produtType);
            wholeSaleModel.setOperName(Integer.parseInt(userId+""));
            wholeSaleModel.setStartTime(time);
            wholeSaleModelList.add(wholeSaleModel);
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String time2 = sdf2.format( new Date());
        PageObject po = getPageObject(request,"time_index desc");
        po.getCondition().put("timeIndex", time2);
        List<RelaseMainDayNumModel> modelList = resaleMainDayNumService.findModelsByCondition(po);
        wholeSaleMainService.saveModel(wholeSaleMainModel,wholeSaleModelList,modelList);
        return "success";
    }

    /**
     *  修改数据
     *
     * */
    @RequestMapping(value = "showEdit")
    public String edit(HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("mainId"));
        /**批发信息**/
        WholeSaleMainModel wholeSaleMainModel = wholeSaleMainService.findById(id);
        request.setAttribute("wholeSaleMainModel", wholeSaleMainModel);
        /**用户姓名**/
        Customer customer = customerService.findById(Integer.parseInt(wholeSaleMainModel.getCustomerId()));
        request.setAttribute("customer", customer);
        /**用户信息**/
        PageObject po = getPageObject(request,"create_time desc");
        po.getCondition().put("statusId",1);
        List<Customer> customerList = customerService.findModelsByCondition(po);
        JSONArray jsonArray = JSONObject.parseArray(JSONUtils.toJson(customerList));
        request.setAttribute("customerMap", JSONUtils.toComboByObject(jsonArray,"id","customerName",wholeSaleMainModel.getCustomerId()));
        /**产品明细**/
        PageObject po2 = getPageObject(request,"start_time desc");
        po2.getCondition().put("mainId", id);
        //List<Map<String,Object>> wholeSaleList = wholeSaleService.findModelsByCondition(po2);
        List<Map<String,Object>> wholeSaleList = wholeSaleService.findAllWholeSale(id+"");
        request.setAttribute("wholeSaleList", wholeSaleList);
        request.setAttribute("trNum", wholeSaleList.size());
        request.setAttribute("mainId", id);
        Map<String,String> unitMap = sysDictService.getDetailValueMap("单位");
        request.setAttribute("unitCombo", JSONUtils.toCombo(unitMap));
        /**产品类型**/
        po2.getCondition().clear();
        po2.getCondition().put("statusId",1);
        List<ProductType> prductTypeList = productTypeService.findModelsByCondition(po2);
        JSONArray productJsonArray = JSONObject.parseArray(JSONUtils.toJson(prductTypeList));
        request.setAttribute("productCombo", JSONUtils.toComboByObject(productJsonArray,"id","productType",""));
        /**所有产品**/
        Map<String,List<Object>> allMap = new HashMap<String,List<Object>>();
        List<Product> productList = productService.findModelsByCondition(po2);
        if(productList != null){
            for(int i=0;i<productList.size();i++){
                Product product = productList.get(i);
                String productType = product.getTypeId() + "";
                String productName = product.getProductName();
                String productId = product.getId() + "";
                JSONObject json = new JSONObject();
                json.put("text",productName);
                json.put("value",productId);
                if(allMap.containsKey(productType)){
                    List<Object> objectList = allMap.get(productType);
                    objectList.add(json);
                }else{
                    List<Object> objectList = new ArrayList<Object>();
                    objectList.add(json);
                    allMap.put(productType,objectList);
                }
            }
        }
        request.setAttribute("allCombo", JSONUtils.toJson(allMap));
        String payValue = wholeSaleMainModel.getTemp2();
        Map<String,String> payMap = new HashMap<String, String>();
        payMap.put("已付款","已付款");
        payMap.put("未付款","未付款");
        request.setAttribute("payMap", JSONUtils.toCombo(payMap,payValue));
        return "whole/edit";
    }

    @RequestMapping(value = "update")
    public String update(HttpServletRequest request){
        long userId = SpringSecurityUtils.getCurrentUser().getUserId();
        String userName = SpringSecurityUtils.getCurrentUser().getUserName();
        long mainId = Long.parseLong(request.getParameter("mainId"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format( new Date());
        Map map = request.getParameterMap();
        Set set = map.keySet();
        List<String> list = new ArrayList<String>();
        for (Iterator iter = set.iterator(); iter.hasNext();) {
            String key = (String)iter.next();
            if(key.contains("productType_")){
                String[] keys = key.split("_");
                if(keys.length == 3){
                    list.add(keys[1]);
                }
            }
        }
        List<WholeSaleModel> wholeSaleModelList = new ArrayList<WholeSaleModel>();
        for(String index : list){
            String produtType = request.getParameter("productType_"+index+"_1");
            String product = request.getParameter("product_"+index+"_2");
            double price = NumberUtil.parseDouble(request.getParameter("price_"+index+"_3"),0);
            int produtNum = NumberUtil.parseInt(request.getParameter("productNum_"+index+"_4"),0);
            String unit = request.getParameter("unit_"+index+"_5");
            WholeSaleModel wholeSaleModel = new WholeSaleModel();
            wholeSaleModel.setUnit(unit);
            wholeSaleModel.setProductNum(produtNum);
            wholeSaleModel.setPrice(price);
            wholeSaleModel.setProduct(product);
            wholeSaleModel.setProductType(produtType);
            wholeSaleModel.setOperName(Integer.parseInt(userId+""));
            wholeSaleModel.setStartTime(time);
            wholeSaleModel.setMainId(Integer.parseInt(mainId+""));
            wholeSaleModelList.add(wholeSaleModel);
        }
        WholeSaleMainModel mainModel = wholeSaleMainService.findById(mainId);
        String customerId = request.getParameter("customerId");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String town = request.getParameter("town");
        String temp2 = request.getParameter("temp2");
        String note = request.getParameter("note");
        mainModel.setTemp2(temp2);
        mainModel.setCustomerId(customerId);
        mainModel.setCity(city);
        mainModel.setPhone(phone);
        mainModel.setTown(town);
        mainModel.setNote(note);
        mainModel.setTemp2(temp2);
        wholeSaleMainService.updateModel(mainModel,wholeSaleModelList);
        return "success";
    }

    /**
     *  删除数据
     *
     * */
    @RequestMapping(value = "delete")
    public void delete(HttpServletRequest request,HttpServletResponse response){
        String ids = request.getParameter("ids");
        wholeSaleMainService.deleteRefresh(ids);
        JSONObject json = new JSONObject();
        json.put("success", "true");
        writeToPage(json.toString(), response);
    }

    @RequestMapping(value = "getCustomer")
    @ResponseBody
    public void getCustomer(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.findById(id);
        writeToPage(JSONUtils.toJson(customer), response);
    }

    @RequestMapping(value = "getProduct")
    @ResponseBody
    public void getProduct(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        PageObject pageObject = getPageObject(request,"create_time desc");
        pageObject.getCondition().put("statusId",1);
        pageObject.getCondition().put("typeId",id);
        List<Product> productList = productService.findModelsByCondition(pageObject);
        JSONArray jsonArray = JSONObject.parseArray(JSONUtils.toJson(productList));
        writeToPage(JSONUtils.toComboByObject(jsonArray,"id","productName","").toString(), response);
    }

    @RequestMapping(value = "getPrice")
    @ResponseBody
    public void getPrice(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        writeToPage(JSONUtils.toJson(product), response);
    }


    @RequestMapping(value = "pay")
    public String pay(HttpServletRequest request){
        long id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id", id);
        return "whole/pay";
    }

    @RequestMapping(value = "payStatus")
    public String payStatus(HttpServletRequest request){
        long id = Integer.parseInt(request.getParameter("id"));
        WholeSaleMainModel wholeSaleMainModel = wholeSaleMainService.findById(id);
        wholeSaleMainModel.setTemp2("已付款");
        wholeSaleMainService.updateById(wholeSaleMainModel);
        return "success";
    }

    @RequestMapping(value = "print")
    public String print(HttpServletRequest request){
        long id = Integer.parseInt(request.getParameter("id"));
        WholeSaleMainModel wholeSaleMain = wholeSaleMainService.findById(id);
        request.setAttribute("wholeSaleMain", wholeSaleMain);
        List<Map<String,Object>> list = wholeSaleService.findAllWholeSale(id+"");
        request.setAttribute("wholeSaleList", list);
        int size = 0;
        if(list != null){
            size = list.size();
        }
        request.setAttribute("size", size);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format( new Date());
        String[] times = time.split("-");
        request.setAttribute("year", times[0]);
        request.setAttribute("month", times[1]);
        request.setAttribute("day", times[2]);
        return "whole/print";
    }

}
