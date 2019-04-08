package com.uway.resale.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daboo.entity.page.PageObject;
import com.uway.common.utils.JSONUtils;
import com.uway.common.utils.OpLog;
import com.uway.core.util.NumberUtil;
import com.uway.core.util.SpringSecurityUtils;
import com.uway.core.web.BaseController;
import com.uway.product.entity.Product;
import com.uway.product.entity.ProductType;
import com.uway.product.service.ProductService;
import com.uway.product.service.ProductTypeService;
import com.uway.resale.entity.RelaseMainDayNumModel;
import com.uway.resale.entity.ResaleMainModel;
import com.uway.resale.entity.ResaleModel;
import com.uway.resale.service.ResaleMainDayNumService;
import com.uway.resale.service.ResaleMainService;
import com.uway.resale.service.ResaleService;
import com.uway.system.service.SysDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *   零售
 */
@Controller
@Scope("session")
@RequestMapping(value = "/resaleMain")
@OpLog(logDesc="零售控制")
public class ResaleController extends BaseController {
    Logger log = LoggerFactory.getLogger(ResaleController.class);
    @Autowired
    private ResaleService resaleService;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private ResaleMainService resaleMainService;
    @Autowired
    private ResaleMainDayNumService resaleMainDayNumService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;

    /**
     *
     * 初始化页面
     * */
    @RequestMapping(value = "init")
    public String init(HttpServletRequest request){
        PageObject pageObject = getPageObject(request,"create_time desc");
        pageObject.getCondition().put("statusId",1);
        List<ProductType> prductTypeList = productTypeService.findModelsByCondition(pageObject);
        JSONArray productJsonArray = JSONObject.parseArray(JSONUtils.toJson(prductTypeList));
        request.setAttribute("productCombo", JSONUtils.toComboByObject(productJsonArray,"id","productType",""));
        return "resaleMain/initList";
    }

    @RequestMapping(value = "showAdd")
    public String showAdd(HttpServletRequest request){
        String userName = SpringSecurityUtils.getCurrentUserName();
        long userId = SpringSecurityUtils.getCurrentUser().getUserId();
        PageObject pageObject = getPageObject(request,"create_time desc");
        pageObject.getCondition().put("statusId",1);
        List<ProductType> prductTypeList = productTypeService.findModelsByCondition(pageObject);
        JSONArray productJsonArray = JSONObject.parseArray(JSONUtils.toJson(prductTypeList));
        request.setAttribute("productCombo", JSONUtils.toComboByObject(productJsonArray,"id","productType",""));
        Map<String,String> unitMap = sysDictService.getDetailValueMap("单位");
        request.setAttribute("unitCombo", JSONUtils.toCombo(unitMap));
        request.setAttribute("userName",userName);
        request.setAttribute("userId",userId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format( new Date());
        PageObject po = getPageObject(request,"time_index desc");
        po.getCondition().put("timeIndex", time);
        List<RelaseMainDayNumModel> modelList = resaleMainDayNumService.findModelsByCondition(po);
        int num = 0;
        if(modelList != null){
            if(modelList.size() > 0){
                RelaseMainDayNumModel relaseMainDayNumModel = modelList.get(0);
                num = relaseMainDayNumModel.getNum() + 1;
            }else{
                num = 1;
            }
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        long flowId = Long.parseLong(sdf2.format(new Date()) + "0000") + num;
        request.setAttribute("flowId", flowId);
        return "resaleMain/addList";
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
        PageObject po = getPageObject(request,"operat_time desc");
        String startTime = request.getParameter("createTimeStart");
        String endTime = request.getParameter("createTimeEnd");
        po.getCondition().put("createTimeStart", startTime == null?"":startTime);
        po.getCondition().put("createTimeEnd", endTime == null?"":endTime);
        writeToPage(JSONUtils.toJson(resaleMainService.getGridDataModelByCondition(po)), response);
    }

    @RequestMapping(value = "update")
    public String update(HttpServletRequest request){
        long userId = SpringSecurityUtils.getCurrentUser().getUserId();
        String mainId = request.getParameter("mainId");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format( new Date());
       /* ResaleMainModel resaleMainModel = resaleMainService.findById(Integer.parseInt(mainId));
        resaleMainService.updateById(resaleMainModel);
        resaleService.deleteByMainId(mainId);*/
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
        List<ResaleModel> resaleModelList = new ArrayList<ResaleModel>();
        for(String index : list){
            String produtType = request.getParameter("productType_"+index+"_1");
            String product = request.getParameter("product_"+index+"_2");
            double price = NumberUtil.parseDouble(request.getParameter("price_"+index+"_3"),0);
            int produtNum = NumberUtil.parseInt(request.getParameter("productNum_"+index+"_4"),0);
            String unit = request.getParameter("unit_"+index+"_5");
            ResaleModel resaleModel = new ResaleModel();
            resaleModel.setUnit(unit);
            resaleModel.setProductNum(produtNum);
            resaleModel.setPrice(price);
            resaleModel.setProduct(product);
            resaleModel.setProductType(produtType);
            resaleModel.setOperName(Integer.parseInt(userId+""));
            resaleModel.setStartTime(time);
            resaleModel.setMainId(Integer.parseInt(mainId+""));
            resaleModelList.add(resaleModel);
        }
        resaleMainService.updateModel(mainId,resaleModelList);
        return "success";
    }

    /**
     *  增加零售数据
     *
     * */
    @RequestMapping(value ="add")
    public String add(HttpServletRequest request){
        long userId = SpringSecurityUtils.getCurrentUser().getUserId();
        String userName = SpringSecurityUtils.getCurrentUser().getUserName();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format( new Date());
        /**resaleMainModel实体**/
        ResaleMainModel resaleMainModel = new ResaleMainModel();
        String flowId = request.getParameter("flowId");
        resaleMainModel.setFlowId(flowId);
        resaleMainModel.setOperator(userName);
        resaleMainModel.setOperatTime(time);
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
        List<ResaleModel> resaleModelList = new ArrayList<ResaleModel>();
        for(String index : list){
            String produtType = request.getParameter("productType_"+index+"_1");
            String product = request.getParameter("product_"+index+"_2");
            double price = NumberUtil.parseDouble(request.getParameter("price_"+index+"_3"),0);
            int produtNum = NumberUtil.parseInt(request.getParameter("productNum_"+index+"_4"),0);
            String unit = request.getParameter("unit_"+index+"_5");
            ResaleModel resaleModel = new ResaleModel();
            resaleModel.setUnit(unit);
            resaleModel.setProductNum(produtNum);
            resaleModel.setPrice(price);
            resaleModel.setProduct(product);
            resaleModel.setProductType(produtType);
            resaleModel.setOperName(Integer.parseInt(userId+""));
            resaleModel.setStartTime(time);
            resaleModelList.add(resaleModel);
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String time2 = sdf2.format( new Date());
        PageObject po = getPageObject(request,"time_index desc");
        po.getCondition().put("timeIndex", time2);
        List<RelaseMainDayNumModel> modelList = resaleMainDayNumService.findModelsByCondition(po);
        resaleMainService.saveModel(resaleMainModel,resaleModelList,modelList);
        return "success";
    }

    /**
     *  修改数据
     *
     * */
    @RequestMapping(value = "showEdit")
    public String edit(HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("mainId"));
        PageObject po = getPageObject(request,"start_time desc");
        po.getCondition().put("mainId", id);
        request.setAttribute("mainId", id);
        List<Map<String,Object>> resaleModelList = resaleService.findAllWholeSale(id+"");
        request.setAttribute("resaleModelList", resaleModelList);
        request.setAttribute("trNum", resaleModelList.size());
        Map<String,String> unitMap = sysDictService.getDetailValueMap("单位");
        request.setAttribute("unitCombo", JSONUtils.toCombo(unitMap));
        PageObject pageObject = getPageObject(request,"create_time desc");
        pageObject.getCondition().put("statusId",1);
        List<ProductType> prductTypeList = productTypeService.findModelsByCondition(pageObject);
        JSONArray productJsonArray = JSONObject.parseArray(JSONUtils.toJson(prductTypeList));
        request.setAttribute("productCombo", JSONUtils.toComboByObject(productJsonArray,"id","productType",""));
        /**所有产品**/
        Map<String,List<Object>> allMap = new HashMap<String,List<Object>>();
        List<Product> productList = productService.findModelsByCondition(pageObject);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format( new Date());
        PageObject po2 = getPageObject(request,"time_index desc");
        po.getCondition().put("timeIndex", time);
        List<RelaseMainDayNumModel> modelList = resaleMainDayNumService.findModelsByCondition(po2);
        RelaseMainDayNumModel relaseMainDayNumModel = modelList.get(0);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        long flowId = Long.parseLong(sdf2.format(new Date()) + "0000") + relaseMainDayNumModel.getNum();
        request.setAttribute("flowId", flowId);
        return "resaleMain/editList";
    }

    /**
     *  删除数据
     *
     * */
    @RequestMapping(value = "delete")
    public void delete(HttpServletRequest request,HttpServletResponse response){
        String ids = request.getParameter("ids");
        /*String[] id = ids.split(",");
        List<String> mainIds = Arrays.asList(id);
        resaleMainService.deleteByIds(mainIds);*/
        resaleMainService.deleteRefresh(ids);
        JSONObject json = new JSONObject();
        json.put("success", "true");
        writeToPage(json.toString(), response);
    }


    /**
     * 打印预览
     * */
    @RequestMapping(value = "print")
    public String print(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        ResaleMainModel resaleMainModel = resaleMainService.findById(id);
        request.setAttribute("resaleMain", resaleMainModel);

        List<Map<String,Object>> list = resaleService.findAllWholeSale(id+"");
        request.setAttribute("resaleList", list);
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

        return "resaleMain/print";
    }
}
