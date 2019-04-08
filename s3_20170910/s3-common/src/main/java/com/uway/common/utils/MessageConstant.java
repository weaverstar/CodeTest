package com.uway.common.utils;

/**
 * 100：参数异常 200：业务异常 300：服务器异常
 * 
 * @author Administrator
 * 
 */
public class MessageConstant {

	/**
	 * 系统提示
	 * 
	 */
	public interface SYSTEM_EXCEPTION {

		public static final String METHOD = "jsb.system.exception_";
		/**
		 * 您还没有登录，请先登录
		 */
		public static final String ERROR_300 = "300";
		/**
		 * 您的秘钥不匹配
		 */
		public static final String ERROR_301 = "301";

		/**
		 * 接口报错
		 */
		public static final String INTERFACE_SYSTEM_ERROR = "302";

		/**
		 * 服务器处理中，请勿重复提交
		 */
		public static final String REPEAT_REQUEST = "303";

		/**
		 * 系统繁忙，请稍后再试
		 */
		public static final String MESSAGE_CALL_TOO_FREQUENT = "304";

		/**
		 * 系统繁忙，请稍后再试
		 */
		public static final String SYSTEM_BUSY = "305";
	}

	public interface REQUEST_HTTPS {

		public static final String METHOD = "jsb.request.https_";

		/**
		 * 不是https请求
		 */
		public static final String ERROR_200 = "200";
		/**
		 * 不是https请求
		 */
		public static final String ERROR_399 = "399";
	}

	// 登录接口
	public interface APP_USER_LOGIN {

		public static final String METHOD = "jsb.api.login_";

		public static final String MOBILE_NULL = "100";

		public static final String PWD_NULL = "101";

		// 用户名或密码错误
		public static final String ERROR_ACCOUNT_INVALID = "102";

		// 您的账号被限制，请联系客服
		public static final String ACCOUNT_DISABLE = "303";

		// 微信unionId不能为空
		public static final String UNION_ID_NULL = "304";

		// 昵称不能为空
		public static final String NICK_NAME_NULL = "305";

		// 设备号不能为空
		public static final String DEVICE_TOKEN_NULL = "306";

		// 您已经绑定过微信
		public static final String ALREADY_BIND_WEIXIN = "307";

		// 此微信号已经被他人绑定
		public static final String UNION_ID_USED = "308";

		// 您已经绑定过手机号
		public static final String ALREADY_BIND_MOBILE = "309";

	}

	// 注册-获取短信验证码接口
	public interface APP_USER_REGIST {

		public static final String METHOD = "jsb.api.regist_";
		// 手机号码为空
		public static final String MOBILE_NULL = "100";
		// 手机号已注册
		public static final String MOBILE_REGISTED = "101";
		// 系统繁忙，请稍后再试
		public static final String GET_MESSAGE_FAILED = "102";
		// 短信验证码为空
		public static final String MESSAGE_CODE_NULL = "103";
		// 注册密码为空
		public static final String PWD_NULL = "104";
		// 短信验证码错误
		public static final String CODE_VALUE_INVALID = "105";

	}

	// 找回密码
	public interface APP_USER_RESET_PWD {

		public static final String METHOD = "jsb.api.findPwd_";
		// 手机号码为空
		public static final String MOBILE_NULL = "100";
		// 系统繁忙，请稍后再试
		public static final String GET_MESSAGE_FAILED = "102";
		// 密码为空
		public static final String PWD_NULL = "103";
		// 短信验证码为空
		public static final String MESSAGE_CODE_NULL = "104";
		// 短信验证码错误
		public static final String CODE_VALUE_INVALID = "105";
		// 手机号没有注册
		public static final String MOBILE_NOT_REGISTED = "107";
	}

	/**
	 * 用户认证
	 * 
	 * @author Administrator
	 * 
	 */
	public interface APP_USER_AUTH {
		public static final String METHOD = "jsb.api.userAuth_";

		// 用户名称为空
		public static final String REAL_NAME_NULL = "100";

		// 身份证号不能为空
		public static final String IDCARD_NULL = "101";

		// 该身份证号已经被认证
		public static final String IDCARD_USED = "102";
	}

	/**
	 * app版本信息
	 * 
	 * @author Administrator
	 * 
	 */
	public interface APP_CHECK_VERSION {
		public static final String METHOD = "jsb.api.checkVersion_";

		// 版本为空
		public static final String APPVERSION_NULL = "100";
		// app信息不存在
		public static final String APPINFO_NULL = "200";
	}

	/**
	 * 获取用户认证
	 * 
	 * @author Administrator
	 * 
	 */
	public interface APP_GET_USER_AUTH {
		public static final String METHOD = "jsb.api.getUserAuth_";

		// 用户为空
		// public static final String USER_NULL="100";
	}

	/**
	 * 用户收藏
	 * 
	 * @author Administrator
	 * 
	 */
	public interface APP_USER_COLLECT {
		public static final String METHOD = "jsb.api.userCollect_";

		// 收藏文件不能为空
		public static final String FILEID = "100";
	}

	/**
	 * 获取用户收藏信息
	 * 
	 * @author Administrator
	 * 
	 */
	public interface APP_GET_USER_COLLECT {
		public static final String METHOD = "jsb.api.getUserCollect_";

		// 收藏文件不能为空
		// public static final String FILEMDID ="100";
	}

	/**
	 * 保存用户下载信息
	 * 
	 * @author Administrator
	 * 
	 */
	public interface APP_USER_DOWN_FILE {
		public static final String METHOD = "jsb.api.userDownFile_";

		// 下载文件不能为空
		public static final String FILEID = "100";
	}

	/**
	 * 资料打赏
	 * 
	 * @author Administrator
	 * 
	 */
	public interface APP_FILE_REWARD {
		public static final String METHOD = "jsb.api.fileReward_";
		// 文件不能为空
		public static final String FILEID = "900";

		// 打赏金额不对
		public static final String REWARDAMOUNT = "901";
		// 打赏类型不对
		public static final String REWARDTYPE = "902";

		// 用户现金账户不存在
		public static final String USERACCOUNT = "903";
		// 用户现金账户不存在
		public static final String ACCOUNT_NOT_ENOUGH = "904";
		// 资料不存在
		public static final String FILE_NOT_EXIST = "905";
		// 免费打赏已达到上限
		public static final String FREE_UPPER_LIMIT = "906";
		// 文件用户已免费打赏
		public static final String FREE_REWARD_NUM = "907";
		// 文件用户已免费打赏
		public static final String SAME_USER = "908";
		// 文件用户已免费打赏
		public static final String HAS_FREE_REWARD = "909";
		// 用户账户类型
		public static final String ACCOUNTTYPE = "910";
	}

	public interface APP_FILE_FileErrorCorrect {
		public static final String METHOD = "jsb.api.fileErrorCorrect_";

		/**
		 * 资料ID参数有误
		 */
		public static final String FILEID_NOT_NULL = "101";
		/**
		 * 纠错者ID参数有误
		 */
		public static final String CORRECTUSERID_NOT_NULL = "102";
		/**
		 * 纠错描述内容不能为空
		 */
		public static final String ERROR_DESC_NOT_NULL = "103";
		/**
		 * 根据资料ID查询资料对象为空
		 */
		public static final String FILE_OBJECT_NULL = "104";
		/**
		 * 根据userID查询用户对象为空
		 */
		public static final String USER_OBJECT_NULL = "105";
	}

	public interface APP_FILE_SHARE {
		public static final String METHOD = "jsb.api.fileShare_";
		/**
		 * 资料ID参数有误
		 */
		public static final String FILEID_NOT_NULL = "101";

		/**
		 * 根据资料ID查询资料对象为空
		 */
		public static final String FILE_OBJECT_NULL = "104";
		/**
		 * 根据userID查询用户对象为空
		 */
		public static final String USER_OBJECT_NULL = "105";
	}
	
	public interface APP_FAULT_CODE {
		public static final String METHOD = "jsb.api.faultCode_";
		/**
		 * 故障码参数有误
		 */
		public static final String FAULT_CODE_NOT_NULL = "101";
		/**
		 * 故障码ID不能为空
		 */
		public static final String FAULT_CODE_ID_NOT_NULL = "102";
		/**
		 * 根据故障码ID查询用户对象为空
		 */
		public static final String FAULT_CODE_OBJECT_NULL = "103";
	}

	/**
	 * 获取用户下载信息
	 */
	public interface APP_GET_USER_DOWN_FILES {
		public static final String METHOD = "jsb.api.getUserDownFiles_";
	}

	/**
	 * 获取汽车部件和热搜品牌
	 * 
	 * @author Administrator
	 * 
	 */
	public interface APP_GET_PART_DICT {
		public static final String METHOD = "jsb.api.getDictByKey_";
		/**
		 * 参数key不能为空
		 */
		public static final String KEY_NULL = "100";
	}

	/**
	 * 
	 * 获取用户信息
	 * 
	 */
	public interface GET_USER_INFO {
		public static final String METHOD = "jsb.api.getUserInfo_";
		/** 参数userId不能为空 */
		public static final String USER_ID_NULL = "100";
		/**  用户信息不存在 */
		public static final String USER_NOT_EXIST = "101";
		/** 查询的操作类型operType不能为空 */
		public static final String QUERY_OPER_TYPE_NOT_NULL = "102";
		
		
		
	}

	/**
	 * 
	 * 查询资料
	 * 
	 */
	public interface QUERY_FILE {

		public static final String METHOD = "jsb.api.queryFileData_";

		/**
		 * 查询条件不能为空
		 */
		public static final String QUERY_CONDITION_NULL = "100";

	}

	/**
	 * 
	 * 修改用户信息
	 * 
	 */
	public interface UPDATE_USER_INFO {

		public static final String METHOD = "jsb.api.updateUserInfo_";

		/**
		 * 修改的用户信息不能为空
		 */
		public static final String USER_INFO_NULL = "100";
		/**
		 * 原始密码不能为空
		 */
		public static final String OLD_PWD_NULL = "101";
		/**
		 * 新密码不能为空
		 */
		public static final String PWD_NULL = "102";
		/**
		 * 原始密码不正确
		 */
		public static final String OLD_PWD_ERROR = "103";

		/**
		 * 关注品牌不能为空
		 */
		public static final String FOLLOW_BRAND_NULL = "104";
	}

	/**
	 * 用户绑定支付宝账号错误信息
	 * 
	 * @author dingyang
	 */
	public interface BIND_ALIPAY {
		public static final String METHOD = "jsb.api.bindAlipay_";
		/**
		 * 用户输入的支付宝账号不能为空
		 */
		public static final String ALIPAY_NO_NULL = "100";
		/**
		 * 用户输入的支付宝真实姓名不能为空
		 */
		public static final String ALIPAY_NAME_NULL = "101";
		/**
		 * 用户未设置支付密码，请先设置支付密码
		 */
		public static final String PAY_PWD_NOT_SET = "102";
		/**
		 * 输入的支付密码不正确
		 */
		public static final String PAY_PWD_ERROR = "103";
		/**
		 * 支付密码不能为空
		 */
		public static final String PAY_PWD_NULL = "104";
		/**
		 * 支付宝账号已经存在
		 */
		public static final String ALIPAY_NO_EXIST = "105";
		/**
		 * 根据userID查询用户信息对象为空
		 */
		public static final String USER_INFO_OBJECT_NULL = "111";
		/**
		 * 更新支付密码操作结果失败
		 */
		public static final String UPDATE_PAYPASSWD_ERROR = "201";
		/**
		 * 更新支付宝账号操作结果失败
		 */
		public static final String UPDATE_ALIPAYNO_ERROR = "202";
	}

	/**
	 * 用户支付密码接口错误信息
	 * 
	 * @author dingyang
	 */
	public interface USER_PAY_PASSWD {
		public static final String METHOD = "jsb.api.userPayPasswd_";

		/**
		 * 用户ID不能为空
		 */
		public static final String USER_ID_NULL = "100";
		/**
		 * 支付密码不能为空
		 */
		public static final String PAY_PWD_NULL = "101";
		/**
		 * 用户未设置支付密码，请先设置支付密码
		 */
		public static final String PAY_PWD_NOT_SET = "102";
		/**
		 * 输入的支付密码不正确
		 */
		public static final String PAY_PWD_ERROR = "103";
		/**
		 * 身份证号不能为空
		 */
		public static final String ID_CARD_NULL = "104";
		/**
		 * 身份证号不匹配
		 */
		public static final String ID_CARD_ERROR = "105";
		/**
		 * 手机号码不能为空
		 */
		public static final String MOBILE_NULL = "106";

		/**
		 * 手机号必须为注册时填写的手机号
		 */
		public static final String MOBILE_IN_VALID = "107";

		/**
		 * 获取短信验证码失败
		 */
		public static final String GET_MESSAGE_FAILED = "108";
		/**
		 * 短信验证码不能为空
		 */
		public static final String MESSAGE_CODE_NULL = "109";
		/**
		 * 短信验证码错误
		 */
		public static final String CODE_VALUE_INVALID = "110";
		/**
		 * 根据userID查询用户扩展信息对象为空
		 */
		public static final String USER_AUTH_OBJECT_NULL = "111";
		/**
		 * 根据userID查询用户扩展信息的身份证号为空
		 */
		public static final String USER_AUTH_IDCARD_NULL = "112";
		/**
		 * 根据用户ID查询用户对象为空
		 */
		public static final String USER_OBJECT_NULL = "113";
		/**
		 * 更新操作结果失败
		 */
		public static final String UPDATE_ERROR = "200";
	}

	/**
	 * 我的上传
	 */
	public interface MY_UPLOAD_FILE_DATA {
		public static final String METHOD = "jsb.api.myFileData_";
	}

	/**
	 * 他的上传
	 */
	public interface OTHERS_UPLOAD_FILE_DATA {
		public static final String METHOD = "jsb.api.othersUploadFile_";

		/**
		 * 用户ID不能为空且应为数字
		 */
		public static final String USER_ID_ERROR = "1000";
	}

	public interface UPLOAD_FILE_DATA {
		public static final String METHOD = "jsb.api.uploadFileData_";
		// 文档标题为空
		public static final String TITLE_NULL = "100";

		// 标签不能为空
		public static final String LABELS_NULL = "101";

		// 资料类型
		public static final String FILETYPE_NULL = "102";
	}

	/**
	 * 
	 * 阅读资料
	 * 
	 */
	public interface VIEW_FILE_DATA {

		public static final String METHOD = "jsb.api.viewFileData_";

		/**
		 * 文件Id不能为空
		 */
		public static final String FILE_ID_NULL = "100";

		/**
		 * 用户未登录,精选资料不能查阅
		 */
		public static final String FILE_USER_LOGIN = "101";

		/**
		 * 积分余额不足,精选资料不能查阅
		 */
		public static final String FILE_INTEGER_NOT_ENOUGH = "102";

	}

	/**
	 * 
	 * 用户消息相关的提示信息
	 * 
	 */
	public interface USER_MESSAGE {

		public static final String METHOD = "jsb.api.userMessage_";

		/**
		 * 消息Id不能为空
		 */
		public static final String MESSAGE_ID_NULL = "100";

		/**
		 * 消息类型错误
		 */
		public static final String MESSAGE_TYPE_ERROR = "101";

	}

	/**
	 * 
	 * 用户邀请相关的提示信息
	 * 
	 */
	public interface USER_INVITATION {

		public static final String METHOD = "jsb.api.userInvitation_";

		/**
		 * 邀请码不能为空
		 */
		public static final String INVITATION_CODE_NULL = "900";

		/**
		 * 邀请码不存在
		 */
		public static final String INVITATION_CODE_NOT_EXIST = "901";

	}

	/**
	 * 
	 * 技师创业申请
	 * 
	 */
	public interface TECH_APPLY {

		public static final String METHOD = "jsb.api.techApply_";

		/**
		 * 手机号码不能为空
		 */
		public static final String MOBILE_NULL = "100";
		/**
		 * 真实姓名不能为空
		 */
		public static final String REAL_NAME_NULL = "101";
		/**
		 * 所在省份
		 */
		public static final String PROVINCE_NULL = "102";
		/**
		 * 所在城市
		 */
		public static final String CITY_NULL = "103";
		/**
		 * 创业省份
		 */
		public static final String BUSINESS_PROVINCE_NULL = "104";
		/**
		 * 创业城市
		 */
		public static final String BUSINESS_CITY_NULL = "105";
		/**
		 * 开始创业时间不能为空
		 */
		public static final String BUSINESS_TIME_NULL = "106";
		/**
		 * 工作经验不能为空
		 */
		public static final String WORK_EXPERIENCE_NULL = "107";
		/**
		 * 擅长修理品牌不能为空
		 */
		public static final String SKILL_BRAND_NULL = "108";
		/**
		 * 岗位职称不能为空
		 */
		public static final String JOB_POSITION_NULL = "109";
		/**
		 * 您已经报过名，不能重复提交
		 */
		public static final String REPEAT = "110";

	}

	/**
	 * 
	 * 资料评论相关的错误提示
	 * 
	 */
	public interface FILE_COMMENT {

		public static final String METHOD = "jsb.api.fileComment_";

		/**
		 * 资料Id不能为空
		 */
		public static final String FILE_ID_NULL = "100";
		/**
		 * 评论内容不能为空
		 */
		public static final String COMMENT_CONTENT_NULL = "101";

	}

	/**
	 * 
	 * 问答相关的错误提示
	 * 
	 */
	public interface QUESTION_REPLY {

		public static final String METHOD = "jsb.api.question_";

		/**
		 * 提问内容不能为空
		 */
		public static final String QUESTION_CONTENT_NULL = "800";

		/**
		 * 品牌不能为空
		 */
		public static final String BRAND_NULL = "801";

		/**
		 * 问题类型错误
		 */
		public static final String QUESTION_TYPE_ERROR = "802";

		/**
		 * 悬赏分值不能为空则应该为数字
		 */
		public static final String REWARD_ERROR = "803";

		/**
		 * 积分余额不足，不能悬赏
		 */
		public static final String BALANCE_NOT_ENOUGH = "804";

		/**
		 * 追问内容不能为空
		 */
		public static final String ADDITIONAL_QUESTION_NULL = "805";

		/**
		 * 问题ID不能为空且应为数字
		 */
		public static final String QUESTION_ID_ERROR = "806";

		/**
		 * 聊天会话ID不能为空
		 */
		public static final String CONVERSATION_ID_NULL = "807";

		/**
		 * 回答内容不能为空
		 */
		public static final String REPLY_NULL = "808";

		/**
		 * 回答时间不能为空
		 */
		public static final String REPLY_TIME_NULL = "809";

		/**
		 * 回答时间格式错误
		 */
		public static final String REPLY_TIME_ERROR = "810";

		/**
		 * 回答ID不能为空且应为数字
		 */
		public static final String REPLY_ID_ERROR = "811";

		/**
		 * 点赞或踩的动作类型错误
		 */
		public static final String ACTION_TYPE_ERROR = "812";

		/**
		 * 不能重复点赞或点踩
		 */
		public static final String ACTION_REPEAT = "813";

		/**
		 * 该问题已关闭
		 */
		public static final String QUESTION_CLOSED = "814";

		/**
		 * 该回答已经被采纳过，不能重复操作
		 */
		public static final String ANSWER_ACCEPTED = "815";

		/**
		 * 问题ID错误，对应的问题不存在
		 */
		public static final String QUESTION_NOT_EXIST = "816";

		/**
		 * 回答ID错误，对应的回答不存在
		 */
		public static final String REPLY_NOT_EXIST = "817";

		/**
		 * 只有通过认证的用户才能回答问题
		 */
		public static final String REPLYER_NOT_AUTHED = "818";

		/**
		 * 不能回答自己提的问题
		 */
		public static final String CAN_NOT_REPLY_SELF = "819";

		/**
		 * 不能对自己的回答点赞或点踩
		 */
		public static final String CAN_NOT_FEEDBACK_SELF = "820";

		/**
		 * 只有提问者才能采纳回答
		 */
		public static final String CAN_NOT_ACCPET_REPLY_FOR_OTHER = "821";

		/**
		 * 只有提问者才能追问或补充提问
		 */
		public static final String CAN_NOT_ADD_QUESTION_FOR_OTHER = "822";

		/**
		 * 提问人才能给问题追加悬赏
		 */
		public static final String CAN_NOT_ADD_REWARD_FOR_OTHER = "823";

		/**
		 * 增加悬赏分值应大于零
		 */
		public static final String ADD_REWARD_ERROR = "824";

		/**
		 * 问题状态错误，应为数字1或2
		 */
		public static final String QUESTION_STATE_ERROR = "825";

		/**
		 * 用户ID应为数字
		 */
		public static final String USER_ID_ERROR = "826";

		/**
		 * 聊天会话不存在
		 */
		public static final String CONVERSATION_NOT_EXIST = "830";

		/**
		 * 每天提问仅限10次
		 */
		public static final String OVER_MAX_QUESTION_TIMES = "831";

		/**
		 * 您已被禁言，不能提问和回答
		 */
		public static final String QUESTION_NOT_ALLOW = "832";
	}

	/**
	 * 用户签到错误信息
	 * 
	 * @author dy
	 */
	public interface USER_SIGNIN {

		public static final String METHOD = "jsb.api.userSignIn_";

		/**
		 * 用户当日已签到
		 */
		public static final String USER_ALREADY_SIGNIN = "101";
		/**
		 * 查询签到记录对象返回null
		 */
		public static final String TASKINFO_OBJECT_NULL = "102";

	}

	/**
	 * 
	 * 意见反馈
	 * 
	 */
	public interface SUGGESTION {

		public static final String METHOD = "jsb.api.suggestion_";

		/**
		 * 用户ID应为数字
		 */
		public static final String USER_ID_ERROR = "1000";

		/**
		 * 反馈内容不能为空
		 */
		public static final String CONTENT_NULL = "1001";

	}

	/**
	 * 
	 * APP评分
	 * 
	 */
	public interface APP_SCORE {

		public static final String METHOD = "jsb.api.appscore_";

		/**
		 * 用户ID应为数字
		 */
		public static final String USER_ID_ERROR = "1000";

		/**
		 * 评分不能为空且应为固定的数字0-5
		 */
		public static final String SCORE_ERROR = "1001";

	}

	/**
	 * 
	 * 推送列表的错误提示
	 * 
	 */
	public interface PUSH_LIST {

		public static final String METHOD = "jsb.api.getPushList_";

		/**
		 * 品牌不能为空
		 */
		public static final String BRAND_NAME_NULL = "100";
	}

	/**
	 * 绑定银行卡
	 * 
	 * @author Administrator
	 * 
	 */
	public interface BIND_CARD {

		public static final String METHOD = "jsb.api.userBindCard_";

		/**
		 * 银行卡号为空
		 */
		public static final String CARD_NO_NULL = "100";

		/**
		 * 身份证为空
		 */
		public static final String ID_CARD_NULL = "101";

		/**
		 * 真实姓名为空
		 */
		public static final String REAL_NAME_NULL = "102";
		/**
		 * 开户银行为空
		 */
		public static final String BANK_NULL = "103";
	}

	public interface ACCOUNT_DETAIL {

		public static final String METHOD = "jsb.api.accountDetail_";

		/**
		 * 账户类型为空
		 */
		public static final String ACCOUNT_TYPE_NULL = "100";
	}

	/**
	 * 解除绑定银行卡
	 * 
	 * @author Administrator
	 * 
	 */
	public interface UNBIND_CARD {

		public static final String METHOD = "jsb.api.unBindCard_";

		/**
		 * 绑定编号为空
		 */
		public static final String BIND_ID_NULL = "100";
	}

	/**
	 * 提现
	 * 
	 * @author Administrator
	 * 
	 */
	public interface WITHDRAW {

		public static final String METHOD = "jsb.api.withdraw_";
		/**
		 * 绑定编号不能为空且应为整数
		 */
		public static final String BIND_ID_ERROR = "100";
		/**
		 * 提现金额不能为空且应为数字
		 */
		public static final String AMOUNT_ERROR = "101";
		/**
		 * 余额不足
		 */
		public static final String BALANCE_NOT_ENOUGH = "102";
		/**
		 * 输入的支付密码不正确
		 */
		public static final String PAY_PWD_ERROR = "103";
		/**
		 * 用户输入的支付宝真实姓名不能为空
		 */
		public static final String ALIPAY_NAME_NULL = "104";
		/**
		 * 用户输入的支付宝账号不能为空
		 */
		public static final String ALIPAY_NO_NULL = "105";
		/**
		 * 根据userID查询用户信息对象为空
		 */
		public static final String USER_INFO_OBJECT_NULL = "106";
		/**
		 * 支付密码不能为空
		 */
		public static final String PAY_PWD_NULL = "107";
		/**
		 * 提现金额必须为10的整数倍
		 */
		public static final String NOT_10_MULTIPLE = "108";
		/**
		 * 请勿重复申请提现
		 */
		public static final String REPEAT_APPLY_WITHDRAW = "109";
		/**
		 * 更新支付密码操作结果失败
		 */
		public static final String UPDATE_PAYPASSWD_ERROR = "201";
		/**
		 * 更新支付宝账号操作结果失败
		 */
		public static final String UPDATE_ALIPAYNO_ERROR = "202";

	}

	/**
	 * 充值
	 * 
	 * @author DY
	 * 
	 */
	public interface RECHARGE {
		public static final String METHOD = "jsb.api.recharge_";

		/**
		 * 充值金额不能为空且应为数字
		 */
		public static final String AMOUNT_ERROR = "101";
		/**
		 * 充值方式不能为空
		 */
		public static final String PAY_WAY_NOT_NULL = "102";

	}

	public interface COURSE_VIDEO {

		public static final String METHOD = "jsb.api.course_";
		// 标题不能为空
		public static final String TITLE_NULL = "1100";

		// 封面图片不能为空
		public static final String PAGE_URL_NULL = "1101";

		// 视频路径不能为空
		public static final String URL_NULL = "1102";

		// 视频格式不能为空
		public static final String VIDEO_FORMAT_NULL = "1103";

		// 课件收费信息不能为空且应为数字
		public static final String FEE_ERROR = "1104";

		// 课件ID不能为空且应为数字
		public static final String COURSE_ID_ERROR = "1105";

		// 评论内容不能为空
		public static final String COMMENT_CONTENT_NULL = "1106";

		// 只能删除自己上传的课件
		public static final String DELETE_SELF_ONLY = "1107";

		// 页面标识不能为空且应为数字
		public static final String PAGE_TYPE_ERROR = "1108";

		// 设备号不能为空
		public static final String DEVICE_ID_NULL = "1109";

		// 账户余额不足，请充值
		public static final String BALANCE_NOT_ENOUGH = "1110";

	}

	/**
	 * 
	 * 用户吐槽
	 * 
	 */
	public interface COMPLAINT {

		public static final String METHOD = "jsb.api.complaint_";

		/**
		 * 吐槽类型不能为空
		 */
		public static final String COMPLAINT_TYPE_NULL = "1200";

		/**
		 * 反馈内容不能为空
		 */
		public static final String CONTENT_NULL = "1201";

	}

	/** 新的学习课程 */
	public interface KT_COURSE{
		public static final String METHOD = "jsb.api.kt.course_";
		/** 用户ID不能为空且应为数字 */
		public static final String USER_ID_ERROR = "1000";
		/** 学习路径ID不能为空且应为数字  */
		public static final String PATH_ID_NOT_LEGAL = "1001";
		/** 课程ID不能为空且应为数字 */
		public static final String COURSE_ID_NOT_LEGAL = "1002";
		/** 查询课程信息为空 */
		public static final String QUERY_COURSE_NOT_EXIST = "1003";
		/** 讲师ID不能为空且应为数字 */
		public static final String LECTURER_ID_NOT_LEGAL = "1004";
		/** 章节ID不能为空且应为数字  */
		public static final String CHAPTER_ID_NOT_LEGAL = "1005";
		/** 积分余额不足 */
		public static final String JF_BALANCE_NOT_ENOUGH = "1006";
		/** 牛币余额不足 */
		public static final String NIUBI_BALANCE_NOT_ENOUGH = "1007";
		/** 支付方式不能为空且应为数字 */
		public static final String PAY_WAY_NOT_LEGAL = "1008";
		/** 查询章节视频对象为空 */
		public static final String QUERY_CHAPTER_IS_NULL = "1009";
		/** 提问内容不能为空且不超过100个字 */
		public static final String ASK_CONTENT_NOT_LEGAL = "1010";
		/** 查询类型不能为空 */
		public static final String OPER_TYPE_NOT_NULL = "1011";
		/** 用户角色有误，非讲师角色才可以提问 */
		public static final String USER_ROLE_IS_TEACHER = "1012";
		/** 他的用户ID不能为空且应为数字 */
		public static final String HIS_USER_ID_NOT_LEGAL = "1013";
		/** 他的用户状态信息错误 */
		public static final String HIS_USER_STATE_NOT_LEGAL = "1014";
		/** 免费的课程无需付费 */
		public static final String KTCOURSE_IS_FREE = "1015";
		/** 课程已付费 */
		public static final String KTCOURSE_IS_PAID = "1016";
		/** 自己上传的课程无需付费 */
		public static final String KTCOURSE_IS_OWN = "1017";
		/** 课程信息状态有误 */
		public static final String COURSE_STATUS_NOT_LEGAL = "1018";
	}
	
	
}
