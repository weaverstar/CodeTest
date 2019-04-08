package com.uway.customer.web;

import com.alibaba.fastjson.JSONObject;
import com.daboo.entity.page.PageObject;
import com.uway.common.utils.JSONUtils;
import com.uway.common.utils.OpLog;
import com.uway.core.web.BaseController;
import com.uway.customer.entity.Customer;
import com.uway.customer.service.CustomerService;
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
@RequestMapping(value = "/customer")
@OpLog(logDesc="零售控制")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

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
        Map<String,String> sexMap = sysDictService.getDetailValueMap("性别");
        request.setAttribute("sexMap", JSONUtils.toJson(sexMap));
        Map<String,String> cityMap = sysDictService.getDetailValueMap("地市");
        request.setAttribute("cityMap", JSONUtils.toCombo(cityMap));
        return "customer/init";
    }

    /**
     *
     *  增加客户
     * */
    @RequestMapping(value = "showAdd")
    public String showAdd(HttpServletRequest request){
        Map<String,String> productMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusMap", JSONUtils.toCombo(productMap));
        Map<String,String> cityMap = sysDictService.getDetailValueMap("地市");
        request.setAttribute("cityMap", JSONUtils.toCombo(cityMap));
        Map<String,String> sexMap = sysDictService.getDetailValueMap("性别");
        request.setAttribute("sexMap", JSONUtils.toCombo(sexMap));
        return "customer/add";
    }

    @RequestMapping(value = "list")
    public void list(HttpServletRequest request,HttpServletResponse response) {
        PageObject po = getPageObject(request,"create_time desc");
        String startTime = request.getParameter("createTimeStart");
        String endTime = request.getParameter("createTimeEnd");
        String name = request.getParameter("customerName");
        String city = request.getParameter("cityId");
        String town = request.getParameter("townId");
        String statusId = request.getParameter("statusId");
        po.getCondition().put("createTimeStart", startTime == null?"":startTime);
        po.getCondition().put("createTimeEnd", endTime == null?"":endTime);
        po.getCondition().put("name", name == null?"":name);
        po.getCondition().put("cityId", city == null?"":city);
        po.getCondition().put("townId", town == null?"":town);
        po.getCondition().put("statusId", statusId == null?"":statusId);
        writeToPage(JSONUtils.toJson(customerService.getGridDataModelByCondition(po)), response);
    }

    /**
     *  增加零售数据
     *
     * */
    @RequestMapping(value ="add")
    public String add(HttpServletRequest request, Customer customer){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format( new Date());
        customer.setCreateTime(time);
        customerService.persist(customer);
        return "success";
    }

    /**
     *  修改数据
     *
     * */
    @RequestMapping(value = "showEdit")
    public String edit(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.findById(id);
        request.setAttribute("customer", customer);
        Map<String,String> productMap = sysDictService.getDetailValueMap("状态");
        request.setAttribute("statusMap", JSONUtils.toCombo(productMap));
        Map<String,String> cityMap = sysDictService.getDetailValueMap("地市");
        request.setAttribute("cityMap", JSONUtils.toCombo(cityMap));
        Map<String,String> sexMap = sysDictService.getDetailValueMap("性别");
        request.setAttribute("sexMap", JSONUtils.toCombo(sexMap));
        String cityId = customer.getCityId();
        Map<String,String> townMap = sysDictService.getDetailValueMap(cityId);
        request.setAttribute("townMap", JSONUtils.toCombo(townMap));
        return "customer/edit";
    }

    @RequestMapping(value = "update")
    public String update(HttpServletRequest request,Customer customer){
        customerService.updateById(customer);
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
        List<String> customerIds = Arrays.asList(id);
        customerService.deleteByIds(customerIds);
        JSONObject json = new JSONObject();
        json.put("success", "true");
        writeToPage(json.toString(), response);
    }

}
