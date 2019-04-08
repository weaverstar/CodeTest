package com.uway.log.web;

import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.uway.common.utils.JSONUtils;
import com.uway.core.web.BaseController;
import com.uway.system.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/log/loginLog")
public class LoginLogController extends BaseController {
	Logger log = LoggerFactory.getLogger(LoginLogController.class);
	@Autowired
	private SysLogService sysLogService;

	/**
	 * 加载log/login log页面方法
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		return "log/loginInit";
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public void list(HttpServletRequest request,HttpServletResponse response) {
		log.debug("method: list() ");

		//设置 查询的 动作 为 登录 动作
		String controllerMethod = "login";
		PageObject po = getPageObject(request,"log_time desc");

		po.addCondition("controllerMethod",controllerMethod);
		po.addCondition("userNameLike",request.getParameter("loginName"));

		
		po.addCondition("createTimeStart",request.getParameter("createTimeStart"));

		po.addCondition("createTimeEnd",request.getParameter("createTimeEnd"));

		GridDataModel gridDataModel = sysLogService.getGridDataModelByCondition(po);

		writeToPage(JSONUtils.toJson(gridDataModel), response);
	}
	

	

}
