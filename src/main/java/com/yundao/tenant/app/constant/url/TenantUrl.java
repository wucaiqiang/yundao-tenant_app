package com.yundao.tenant.app.constant.url;

import com.yundao.core.utils.ConfigUtils;
import com.yundao.tenant.app.enums.url.UrlEnum;

/**
 * 租户后置系统url
 *
 * @author jan
 * @create 2017-06-30 PM4:11
 **/
public interface TenantUrl {

	/**
	 * HOST地址
	 */
	String BASE_URL = ConfigUtils.getValue(UrlEnum.TENANT_URL.getKey());

	// ###################################gjl begin######################

	/**
	 * 取“我的客户”数据 - 只获取自己的客户数据
	 */
	String GET_CUSTOMER_MY_PAGE = BASE_URL + "/customer/get_my_page";
	/**
	 * 获取客户页面 所有分页数据
	 */
	String GET_CUSTOMER_V2_PAGE_PUBLIC = BASE_URL + "/customer/v2/get_page_public";

	/**
	 * 获取客户页面 部门数据
	 */
	String GET_CUSTOMER_V2_PAGE_DEPARTMENT = BASE_URL + "/customer/v2/get_page_department";

	/**
	 * 获取客户页面 私有数据
	 */
	String GET_CUSTOMER_V2_PAGE_PERSONAL = BASE_URL + "/customer/v2/get_page_personal";
	/**
	 * 关注客户
	 */
	String FOCUS_CUSTOMER = BASE_URL + "/user/customer/focus";

	/**
	 * 取消关注
	 */
	String UNFOCUS_CUSTOMER = BASE_URL + "/user/customer/unfocus";
	/**
	 * 获取客户详情
	 */
	String GET_CUSTOMER_DETAIL = BASE_URL + "/customer/get_detail";

	String GET_CUSTOMER = BASE_URL + "/customer/get";
	/**
	 * 校验客户手机号码
	 */
	String CUSTOMER_VALIDATE_MOBILE = BASE_URL + "/customer/validate_mobile";
	String CUSTOMER_GET_BY_MOBILE_OR_NUMBER = BASE_URL + "/customer/get_by_mobile_or_number";
	/**
	 * 客户修改
	 */
	String CUSTOMER_UPDATE = BASE_URL + "/customer/app/update";
	String CUSTOMER_BANK_UPDATE = BASE_URL + "/customer/bank/update";
	String CUSTOMER_BANK_ADD = BASE_URL + "/customer/bank/add";
	String CUSTOMER_SALE_GET_RESERVATION = BASE_URL + "/customer/sale/gets_reservation";
	String CUSTOMER_SALE_GET_DECLARATION = BASE_URL + "/customer/sale/gets_declaration";
	/**
	 * 设置客户标签
	 */
	String SET_CUSTOMER_TAG = BASE_URL + "/tag/set_customer_tag";
	String TAG_GET_PAGE = BASE_URL + "/tag/get_page";
	/**
	 * 标签获取排名靠前的。
	 */
	String TAG_GET_TOP = BASE_URL + "/tag/get_top";
	String TAG_GET_ALL = BASE_URL + "/tag/get_all";
	/**
	 * 新增业务对象分组
	 */
	String FIELD_GROUP_ADD = BASE_URL + "/fieldgroup/add";
	/**
	 * 删除业务对象分组
	 */
	String FIELD_GROUP_DELETE = BASE_URL + "/fieldgroup/delete";
	/**
	 * 新增业务对象分组
	 */
	String FIELD_GROUP_UPDASTE = BASE_URL + "/fieldgroup/update";
	/**
	 * 新增业务对象分组
	 */
	String FIELD_GROUP_GETS = BASE_URL + "/fieldgroup/get_page";
	/**
	 * 新增业务对象分组
	 */
	String FIELD_GROUP_GET = BASE_URL + "/fieldgroup/get";
	/**
	 * 新增业务对象分组
	 */
	String FIELDGROUPREL_GET = BASE_URL + "/fieldgrouprel/get_groupid";
	/**
	 * 文件上传
	 */
	String FILE_UPLOAD_POST = BASE_URL + "/cloudfile/upload";

	/**
	 * 删除产品公告类型
	 */
	String PRODUCT_NOTICE_TYPE_DELETE = BASE_URL + "/productnoticetype/delete";

	/**
	 * 新增产品公告类型
	 */
	String PRODUCT_NOTICE_TYPE_ADD = BASE_URL + "/productnoticetype/add";

	/**
	 * 获取所有产品公告类型
	 */
	String PRODUCT_NOTICE_TYPE_GET_ALL = BASE_URL + "/productnoticetype/get_all";

	/**
	 * 新增产品公告
	 */
	String PRODUCT_NOTICE_ADD = BASE_URL + "/productnotice/add";

	/**
	 * 批量删除产品公告
	 */
	String PRODUCT_NOTICE_DELETE = BASE_URL + "/productnotice/delete";

	/**
	 * 编辑产品公告
	 */
	String PRODUCT_NOTICE_UPDATE = BASE_URL + "/productnotice/update";

	/**
	 * 审核公告
	 */
	String PRODUCT_NOTICE_FLOW = BASE_URL + "/productnotice/flow";

	/**
	 * 产品公告列表
	 */
	String PRODUCT_NOTICE_GETS = BASE_URL + "/productnotice/get_page";
	/**
	 * 产品公告详情
	 */
	String PRODUCT_NOTICE_GET = BASE_URL + "/productnotice/get";

	/**
	 * 产品公告审核列表
	 */
	String PRODUCT_NOTICE_EXAMINE_GETS = BASE_URL + "/productnoticeexamine/get_page";

	/**
	 * 新增日志
	 */
	String LOGIN_LOG_ADD = BASE_URL + "/login/log/add";

	// ###################################gjl end########################

	/**
	 * 获取产品字段集合
	 */
	String PRODUCT_GET_FIELD = BASE_URL + "/product/get_fields";

	/**
	 * 添加产品
	 */
	String PRODUCT_ADD = BASE_URL + "/product/add";
	String GET_PRODUCT_BASE_INFO = BASE_URL + "/product/get_base_by_id";
	/**
	 * 修改产品状态
	 */
	String UPDATE_PRODUCT_STATUS = BASE_URL + "/product/update_status";

	/**
	 * 获取产品管理列表的下拉选项值
	 */
	String PRODUCT_GET_MANAGER_LIST_SELECT_VALUE = BASE_URL + "/product/get_manager_List_select_value";

	/**
	 * 获取产品管理列表的下拉选项值
	 */
	String PRODUCT_GET_MANAGER_LIST = BASE_URL + "/product/get_manager_List";
	String PRODUCT_GET_RESERVATION_DECLARATION_TOTAL = BASE_URL + "/product/get_reservation_declaration_total";

	/**
	 * 修改产品基本信息
	 */
	String UPDATE_PRODUCT_BASE = BASE_URL + "/product/base/update";

	/**
	 * 通过产品名称，获取产的下拉列表
	 */
	String PRODUCT_GET_SELECT_OPTION_LIST = BASE_URL + "/product/get_select_option_list";

	/**
	 * 通过产品类型id，获取产的下拉列表
	 */
	String PRODUCT_GET_SELECT_OPTION_LIST_BY_TYPEID = BASE_URL + "/product/get_select_option_list_by_typeId";

	/**
	 * 修改产品基本信息
	 */
	String UPDATE_PRODUCT_SALE = BASE_URL + "/product/sale/update";

	/**
	 * 获取所有产品要素
	 */
	String OBJECT_FIELD_GETS = BASE_URL + "/object/field/gets";
	/**
	 * 获取菜单
	 */
	String GET_MENU = BASE_URL + "/resource/get_menu";
	/**
	 * 获取我的资源权限
	 */
	String GET_MY = BASE_URL + "/resource/get_my";

	/**
	 * 添加产品附件
	 */
	String ADD_PRODUCT_ATTACH = BASE_URL + "/product/attach/add";

	/**
	 * 删除产品附件
	 */
	String DELETE_PRODUCT_ATTACH = BASE_URL + "/product/attach/delete";

	/**
	 * 分页查询产品附件
	 */
	String GETS_PRODUCT_ATTACH = BASE_URL + "/product/attach/gets";
	/**
	 * 修改产品佣金
	 */
	String UPDATE_PRODUCT_COMMISSION = BASE_URL + "/product/commission/update";
	/**
	 * 修改产品收益
	 */
	String UPDATE_PRODUCT_INCOME = BASE_URL + "/product/income/update";
	/**
	 * 修改产品供应商报价
	 */
	String UPDATE_PRODUCT_SUPPLIER_QUOTATION = BASE_URL + "/product/supplier_quotation/update";

	/** role **/

	/**
	 * 添加角色
	 */
	String ADD_ROLE = BASE_URL + "/role/add";

	/**
	 * 修改角色
	 */
	String UPDATE_ROLE = BASE_URL + "/role/update";

	/**
	 * 获取角色信息
	 */
	String GET_ROLE = BASE_URL + "/role/get";

	/**
	 * 获取角色列表
	 */
	String GET_ROLES = BASE_URL + "/role/gets";
	String GET_ROLES_BY_USER_ID = BASE_URL + "/role/gets_by_user_id";

	/**
	 * 删除角色
	 */
	String DELETE_ROLE = BASE_URL + "/role/delete";

	/** role **/

	/** resource **/

	/**
	 * 获取所有资源
	 */
	String GET_REOUSRCES = BASE_URL + "/resource/gets";

	/**
	 * 获取角色资源
	 */
	String GET_RESOURCE_BY_ROLE_ID = BASE_URL + "/resource/get_by_role_id";

	/**
	 * 修改角色资源
	 */
	String UPDATE_RESOURCE_BY_ROLE_ID = BASE_URL + "/resource/update_by_role_id";

	/**
	 * 获取用户资源url列表
	 */
	String GET_RESOURCE_URL_BY_USER_ID = BASE_URL + "/resource/get_url_by_user_id";

	/** resource **/

	/** department **/
	/**
	 * 添加部门
	 */
	String ADD_DEPARTMENT = BASE_URL + "/department/add";

	/**
	 * 获取部门
	 */
	String GET_DEPARTMENT = BASE_URL + "/department/get";

	/**
	 * 获取部门列表
	 */
	String GET_DEPARTMENTS = BASE_URL + "/department/gets";

	/**
	 * 修改部门
	 */
	String UPDATE_DEPARTMENT = BASE_URL + "/department/update";

	/**
	 * 删除部门
	 */
	String DELETE_DEPARTMENT = BASE_URL + "/department/delete";
	/** department **/

	/**
	 * 添加用户
	 */
	String ADD_USER = BASE_URL + "/user/add";

	/**
	 * 检查用户工号是否存在
	 */
	String CHECK_USER_JOB_NUMBER_EXIST = BASE_URL + "/user/check_job_number_exist";

	/**
	 * 检查手机号码是否存在
	 */
	String CHECK_USER_MOBILE_EXIST = BASE_URL + "/user/check_mobile_exist";

	/**
	 * 检查用户名是否已经存在
	 */
	String CHECK_USER_USERNAME_EXIST = BASE_URL + "/user/check_username_exist";

	/**
	 * 用户离职
	 */
	String USER_DIMISSION = BASE_URL + "/user/dimission";

	/**
	 * 通过用户id查询用户详情
	 */
	String GET_USER_BY_ID = BASE_URL + "/user/get_by_id";

	/**
	 * 通过用户id查询用户详情
	 */
	String GETS_USER_BY_ROLE = BASE_URL + "/user/get_by_role";

	/**
	 * 获取用户离职列
	 */
	String GETS_USER_DIMISSION = BASE_URL + "/user/get_dimission_users";

	/**
	 * 获取非离职用户列表
	 */
	String GETS_USER_NOT_DIMISSION = BASE_URL + "/user/get_not_dimission_users";

	/**
	 * 通过用户名称查找在职用户
	 */
	String GETS_USER_BY_REALNAME = BASE_URL + "/user/get_users_by_realName";
	String USER_UPDATE_BASE_INFO = BASE_URL + "/user/app/update_base_info";

	/**
	 * 修改用户信息
	 */
	String MODIFY_USER = BASE_URL + "/user/modify";
	/**
	 * 获取用户名片
	 */
	String USER_DETAIL_GET_CARD = BASE_URL + "/user_detail/get_card";
	/**
	 * 修改用户名片
	 */
	String UPDATE_CARD_INTRODUCE = BASE_URL + "/user_detail/update_card_introduce";
	/**
	 * 修改用户简介与头衔
	 */
	String UPDATE_CARD_INTRODUCE_AND_HEADER_BIT = BASE_URL + "/user_detail/update_introduce_and_header_bit";

	/**
	 * 用户登录
	 */
	String LOGIN = BASE_URL + "/app/user/login";

	/**
	 * 用户退出
	 */
	String LOGOUT = BASE_URL + "/user/logout";
	String GET_KEY_PUBLIC = BASE_URL + "/user/get_key_public";

	/**
	 * 修改用户密码
	 */
	String UPDATE_USER_PASSWORD = BASE_URL + "/user/update_password";

	/** product **/
	/**
	 * 获取产品要素分页数据
	 */
	String GET_ELEMENT_PAGE = BASE_URL + "/object/field/get_page";

	/**
	 * 获取产品要素列表
	 */
	String GET_ELEMENT_LIST = BASE_URL + "/object/field/get_list";

	/**
	 * 获取产品要素
	 */
	String GET_ELEMENT = BASE_URL + "/object/field/get";

	/**
	 * 更新产品要素
	 */
	String ADD_ELEMENT = BASE_URL + "/object/field/add";

	/**
	 * 更新产品要素
	 */
	String UPDATE_ELEMENT = BASE_URL + "/object/field/update";

	/**
	 * 删除产品要素
	 */
	String DELETE_ELEMENT = BASE_URL + "/object/field/delete";

	/** product **/

	/** data object **/

	/**
	 * 获取所有数据对象权限
	 */
	String GET_DATA_OBJECTS = BASE_URL + "/data/object/gets";

	/**
	 * 更新角色数据权限
	 */
	String UPDATE_DATA_OBJECT = BASE_URL + "/data/object/upate";

	/** data object **/

	/** dictionary **/

	/**
	 * 获取数据字典列表
	 */
	String GET_Dictionaries = BASE_URL + "/dictionary/gets";
	String GET_REGION_TEXT = BASE_URL + "/dictionary/get_region_text";

	/** dictionary **/

	/** 产品审核 **/

	/**
	 * 获取产品审核分页
	 **/
	String GET_PRODUCT_EXAMINES = BASE_URL + "/product/examine/get_page";
	/**
	 * 产品列表
	 */
	String GET_LIST = BASE_URL + "/product/get_List";
	String GET_DIRECT_MODEL_RESERVATION_LIST = BASE_URL + "/product/get_direct_model_reservation_list";
	/**
	 * 获取推荐产品列表
	 */
	String GET_RECOMMENDED_LIST = BASE_URL + "/product/app/get_recommended_list";
	/**
	 * 产品详情
	 */
	String GET_DETAIL_BY_ID = BASE_URL + "/product/app/get_detail_by_id";
	/** 产品审核 **/

	/** ======数据权限====== **/

	/**
	 * 根据编码获取数据对象字段
	 */
	String GET_DATA_OBJECT_FIELDS_BY_CODES = BASE_URL + "/data/object/field/get_by_codes";

	/**
	 * 获取读取权限
	 */
	String GET_DATA_OBJECT_PERMISSION_READ = BASE_URL + "/data/object/permission/get_read";

	/**
	 * 获取编辑权限
	 */
	String GET_DATA_OBJECT_PERMISSION_UPDATE = BASE_URL + "/data/object/permission/get_update";

	/**
	 * 获取删除权限
	 */
	String GET_DATA_OBJECT_PERMISSION_DELETE = BASE_URL + "/data/object/permission/get_delete";

	/**
	 * 校验读取权限
	 */
	String GHECK_DATA_OBJECT_PERMISSION_READ = BASE_URL + "/data/object/permission/check_read";

	/**
	 * 校验编辑权限
	 */
	String GHECK_DATA_OBJECT_PERMISSION_UPDATE = BASE_URL + "/data/object/permission/check_update";

	/**
	 * 校验删除权限
	 */
	String CHECK_DATA_OBJECT_PERMISSION_DELETE = BASE_URL + "/data/object/permission/check_delete";
	/**
	 * 校验多条修改权限
	 */
	String CHECK_DATA_OBJECT_PERMISSION_UPDATE_LIST = BASE_URL + "/data/object/permission/check_update_list";

	/**
	 * 校验多条删除权限
	 */
	String CHECK_DATA_OBJECT_PERMISSION_DELETE_LIST = BASE_URL + "/data/object/permission/check_delete_list";

	/**
	 * 校验多条读取权限
	 */
	String CHECK_DATA_OBJECT_PERMISSION_RED_LIST = BASE_URL + "/data/object/permission/check_red_list";
	/**
	 * 校验多个数据对象权限
	 */
	String CHECK_DATA_OBJECT_PERMISSION_MULTI_CODE = BASE_URL + "/data/object/permission/check_multi_code";

	/** ======数据权限====== **/
	/** ======客户回访====== **/

	/**
	 * 获取客户详情页面的回访数据 - 只获取自己的数据
	 */
	String GET_USER_VISIT_PAGE_FOR_CUSTOMER_DETAIL_PERSONAL = BASE_URL
			+ "/user/visit/get_page_for_customer_detail_personal";

	/**
	 * 获取客户详情页面的回访数据 - 获取部门的数据
	 */
	String GET_USER_VISIT_PAGE_FOR_CUSTOMER_DETAIL_DEPARTMENT = BASE_URL
			+ "/user/visit/get_page_for_customer_detail_department";

	/**
	 * 获取客户详情页面的回访数据 - 获取所有数据
	 */
	String GET_USER_VISIT_PAGE_FOR_CUSTOMER_DETAIL_PUBLIC = BASE_URL
			+ "/user/visit/get_page_for_customer_detail_public";
	String GET_USER_VISIT_GET = BASE_URL + "/user/visit/get";
	/** ======客户回访====== **/
	/**
	 * 分配客户给理财师 V2
	 */
	String CUSTOMER_DISTRIBUTION_ALLOT_TO_FP_V2 = BASE_URL + "/customer/v2/distribution/allot_to_fp";
	/**
	 * 分配客户给客服 V2
	 */
	String CUSTOMER_DISTRIBUTION_ALLOT_TO_CS_V2 = BASE_URL + "/customer/v2/distribution/allot_to_cs";
	/**
	 * 回收客户 V2
	 */
	String CUSTOMER_DISTRIBUTION_RECYCLE_V2 = BASE_URL + "/customer/v2/distribution/recycle";
	/**
	 * 获取客户动态
	 */
	String GET_CUSTOMER_FEED = BASE_URL + "/msg/feed/get_customer_page";
	String MSG_MESSAGE_GET_UNREAD_COUNT = BASE_URL + "/msg/message/get_unread_count_by_type";
	/**
	 * 获取待办数
	 */
	String MSG_MESSAGE_GET_TODO_COUNT = BASE_URL + "/msg/message/get_todo_count";
	/**
	 * 添加客户跟进记录
	 */
	String FOLLOW_UP_ADD = BASE_URL + "/customer/follow/up/add";
	/**
	 * 客户跟进列表 - 获取所有客户数据
	 */
	String CUSTOMER_FOLLOW_GETS_PUBLIC = BASE_URL + "/customer/follow/up/get_page_public";
	/**
	 * 新增客户
	 */
	String CUSTOMER_ADD = BASE_URL + "/customer/add";
	String CUSTOMER_ADD_FROM_BOOK = BASE_URL + "/customer/add_from_book";
	String CUSTOMER_ADD_FROM_SCANING = BASE_URL + "/customer/app/scaning/add";
	/**
	 * 申请回退客户
	 */
	String BACK_CUSTOMER_APPLY = BASE_URL + "/customer/back/apply";

	/**
	 * 新增预约
	 */
	String RESERVATION_ADD = BASE_URL + "/reservation/add";

	/**
	 * 取消预约
	 */
	String RESERVATION_CANCEL = BASE_URL + "/reservation/cancel";
	/**
	 * 重新预约
	 */
	String RESERVATION_AGAIN_COMMIT = BASE_URL + "/reservation/again_commit";

	/**
	 * 查询单个预约详情
	 */
	String RESERVATION_SINGLE_DETAIL = BASE_URL + "/reservation/app/get_detail";
	/**
	 * 查询我的预约列表
	 */
	String RESERVATION_GET_MY_PAGE = BASE_URL + "/reservation/gets/my";
	String RESERVATION_GETS_BY_PRODUCT_ID = BASE_URL + "/reservation/gets_by_product_id";
	/**
	 * 编辑预约资料
	 */
	String RESERVATION_UPATE = BASE_URL + "/reservation/update/app";
	String RESERVATION_GET = BASE_URL + "/reservation/get";

	/**
	 * 报单列表
	 */
	String DECLARATION_GET_PAGE = BASE_URL + "/declaration/get_page";
	String DECLARATION_SEARCH = BASE_URL + "/declaration/app/search";

	/**
	 * 报单详情
	 */
	String DECLARATION_GET = BASE_URL + "/declaration/get";
	String DECLARATION_APPLY_REFUND = BASE_URL + "/declaration/apply_refund";
	String DECLARATION_REFUND_CANCEL = BASE_URL + "/refund/cancel";
	String DECLARATION_REFUND_CANCEL_BY_DECLARATION_ID = BASE_URL + "/refund/cancel_by_declaration_id";
	String DECLARATION_RESUBMIT_REFUND = BASE_URL + "/refund/resubmit_by_declaration_id";

	/**
	 * 修改银行卡信息
	 */
	String DECLARATION_UPDATE_BANK = BASE_URL + "/declaration/update_bank";
	/**
	 * 报单修改
	 */
	String DECLARATION_UPDATE = BASE_URL + "/declaration/app/update";

	/**
	 * 获取成单用户信息
	 */
	String DECLARATION_GET_USER = BASE_URL + "/declaration/get_declaration_user";

	/**
	 * 取消
	 */
	String DECLARATION_CANCEL = BASE_URL + "/declaration/cancel";

	/**
	 * 报单新增
	 */
	String DECLARATION_ADD = BASE_URL + "/declaration/add";
	String DECLARATION_PATCH_ADD = BASE_URL + "/declaration/patch";
	String DECLARATION_ADD_IN_RESERVATION = BASE_URL + "/reservation/submit_declaration";
	/**
	 * 报单提交
	 */
	String DECLARATION_SUBMIT = BASE_URL + "/declaration/submit";
	/**
	 * 报单重新提交
	 */
	String DECLARATION_RESUBMIT = BASE_URL + "/declaration/app/resubmit";
	/**
	 * 消息列表
	 */
	String MESSAGE_GET_MY = BASE_URL + "/msg/message/get_my_page";
	/**
	 * 全部已读
	 */
	String MESSAGE_READ_ALL = BASE_URL + "/msg/message/readAll";
	/**
	 * 批量设置已读
	 */
	String MESSAGE_READ = BASE_URL + "/msg/message/read";
	/**
	 * 获取我的待办任务数
	 */
	String TASK_GET_TODO_COUNT = BASE_URL + "/task/get_todo_count";
	/**
	 * 获取客户详情页面分页数据
	 */
	String GET_LEADS_FOR_CUSTOMER_DETAIL = BASE_URL + "/leads/customer/detail/get_page";
	/**
	 * 获取客户详情页面分页数据-个人
	 */
	String GET_LEADS_FOR_CUSTOMER_DETAIL_PERSONAL = BASE_URL + "/leads/customer/detail/get_page_personal";

	/**
	 * 获取客户详情页面分页数据-部门
	 */
	String GET_LEADS_FOR_CUSTOMER_DETAIL_DEPARTMENT = BASE_URL + "/leads/customer/detail/get_page_department";

	/**
	 * 获取客户详情页面分页数据-所有
	 */
	String GET_LEADS_FOR_CUSTOMER_DETAIL_PUBLIC = BASE_URL + "/leads/customer/detail/get_page_public";

	/**
	 * 获取销售线索分页数据-个人
	 */
	String GET_LEADS_PAGE_PERSONAL = BASE_URL + "/leads/get_page_personal";

	/**
	 * 获取销售线索分页数据-部门
	 */
	String GET_LEADS_PAGE_DEPARTMENT = BASE_URL + "/leads/get_page_department";

	/**
	 * 获取销售线索分页数据-所有
	 */
	String GET_LEADS_PAGE_PUBLIC = BASE_URL + "/leads/get_page_public";
	/**
	 * 处理销售线索
	 */
	String PROCESS_LEADS = BASE_URL + "/leads/process";
	/**
	 * 分配给理财师
	 */
	String ALLOT_LEADS_TO_FP = BASE_URL + "/leads/allot_to_fp";

	/**
	 * 尝试分配线索
	 */
	String ALLOT_LEADS = BASE_URL + "/leads/allot";
	/**
	 * 获取客户的未分配线索
	 */
	String GET_LEADS_UN_ALLOT = BASE_URL + "/leads/get_unallot";
	/**
	 * 获取风险测评
	 */
	String QUESTION_GET_RIST_EVALUATION = BASE_URL + "/question/get/customer/risk_evaluation_result";

	/**
	 * 获取风险测评page
	 */
	String QUESTION_GET_RIST_EVALUATION_PAGE = BASE_URL + "/question/get/customer/risk_evaluation_page";

	/**
	 * 预约总额
	 */
	String REPORT_GET_RESERVATION_TOTAL = BASE_URL + "/report/get_reservation_total";
	/**
	 * 报单总额
	 */
	String REPORT_GET_DECLARATION_TOTAL = BASE_URL + "/report/get_declaration_total";
	/**
	 * 理财师预约排名
	 */
	String REPORT_GET_RESERVATION_RANK = BASE_URL + "/report/get_reservation_rank";
	/**
	 * 理财师报单排名
	 */
	String REPORT_GET_DECLARATION_RANK = BASE_URL + "/report/get_declaration_rank";
	/**
	 * 领取公海客户
	 */
	String RECEIVE_CUSTOMER_OPENSEA = BASE_URL + "/customer/opensea/app/receive_customer";

	/**
	 * 获取公海分页数据数据 - 只获取自己的客户
	 */
	String GET_CUSTOMER_OPENSEA_PERSONAL = BASE_URL + "/customer/opensea/get_page_personal";

	/**
	 * 获取公海分页数据数据 - 获取部门客户数据
	 */
	String GET_CUSTOMER_OPENSEA_DEPARTMENT = BASE_URL + "/customer/opensea/get_page_department";

	/**
	 * 获取公海分页数据数据 - 获取所有客户数据
	 */
	String GET_CUSTOMER_OPENSEA_PUBLIC = BASE_URL + "/customer/opensea/get_page_public";

	/**
	 * 获取待办任务
	 */
	String GET_WORK_FLOW_AUDIT_PAGE = BASE_URL + "/task/audit/app/get_page";
	/**
	 * 获取我参与的任务列表
	 */
	String GET_WORK_FLOW_DONE_PAGE = BASE_URL + "/task/done/get_page";

	/**
	 * 获取工作流记录
	 */
	String GET_TASK_HISTORY = BASE_URL + "/task/get_task_history";

	/**
	 * 获取单个流程实例
	 */
	String GET_ACTION_AND_APPLY_INFO = BASE_URL + "/task/get_action_and_apply_info";

	/**
	 * 产品审核 审核操作
	 */
	String AUDIT_PRODUCT_EXAMINE = BASE_URL + "/product/examine/app_audit";

	/**
	 * 公告审核
	 */
	String NOTICE_EXAMINE = BASE_URL + "/productnotice/flow";

	/**
	 * 预约审核操作
	 */
	String RESERVATION_AUDIT_DO = BASE_URL + "/reservation/audit/do";

	/**
	 * 退款审批
	 */
	String REFUND_AUDIT_DO = BASE_URL + "/refund/audit/do";

	String REFUND_GET_TASK = BASE_URL + "/refund/get_task";
	String REFUND_GET_BY_DECLARATION_ID= BASE_URL + "/refund/get_by_declaration_id";
	

	/**
	 * 结佣审批
	 */
	String KNOT_COMMISSION_AUDIT_DO = BASE_URL + "/knot/commission/audit";

	/**
	 * 获取佣金审批的任务佣金信息
	 */
	String KNOT_COMMISSION_GET_TASK_DTO = BASE_URL + "/knot/commission/get_task";

	/**
	 * 报单审核
	 */
	String DECLARATION_AUDIT_DO = BASE_URL + "/declaration/audit/do";
	/**
	 * 客户风险特征类型列表
	 */
	String CC_RISK_RATING_GETS = BASE_URL + "/cc/risk/rating/gets";

	String REPORT_GET_INDEX = BASE_URL + "/report/declaration/app/get_index";

	String REPORT_GET_DEPAETMENT_DECLARATION = BASE_URL + "/report/declaration/app/get_department";

	String GET_DEPARTMENT_BY_ID = BASE_URL + "/department/get_base_department_by_id";
	String GET_DEPARTMENT_AND_USER = BASE_URL + "/department//get_department_and_users";

	String GET_CURRENT_DEPARTMENT_BY_ID = BASE_URL + "/department/get_current_user_department";

	String REPORT_GET_DEPAETMENT_OR_USER_DETAIL = BASE_URL + "/report/declaration/app/get_department_or_user_detail";

	String REPORT_GET_USER_DECLARATION_RANK_PAGE = BASE_URL + "/report/declaration/app/get_user_rank_page";

	String REPORT_GET_CUSTOMER_DECLARATION_RANK_PAGE = BASE_URL + "/report/declaration/app/get_customer_rank_page";

	/**
	 * 用户添加客户业绩
	 */
	String REPORT_GET_DEPAETMENT_ADD_CUSTOMER = BASE_URL + "/report/customer/app/get_department";
	String REPORT_GET_USER_RANK_PAGE_ADD_CUSTOMER = BASE_URL + "/report/customer/app/get_user_rank_page";
	String REPORT_GET_DEPARTMENT_OR_USER_DETAIL_ADD_CUSTOMER = BASE_URL
			+ "/report/customer/app/get_department_or_user_detail";

	String REPORT_GET_CUSTOMER_DECLARATION_LIST_PAGE = BASE_URL
			+ "/report/declaration/app/get_customer_declaration_page";

	String VIDEO_GET_UPLOAD_SIGNATURE = BASE_URL + "/video/get_upload_signature";

	String ROADSHOW_COLUMN_GETS = BASE_URL + "/roadshow/column/get_by_code";
	String ROADSHOW_GET_PAGE = BASE_URL + "/roadshow/get_page";
	String ROADSHOW_GET = BASE_URL + "/roadshow/get";

	String SMSASSISTANTCONTENT_GET_PAGE = BASE_URL + "/sms/assistant/content/get_page";
	String SMSASSISTANTCONTENT_GET_STAY_ALERT = BASE_URL + "/sms/assistant/content/get_stay_alert";
	String SMSASSISTANTCONTENT_GET = BASE_URL + "/sms/assistant/content/get";
	String SMSASSISTANTCONTENT_DELETE = BASE_URL + "/sms/assistant/content/delete";
	String SMSASSISTANTCONTENT_ADD = BASE_URL + "/sms/assistant/content/add";
	String SMSASSISTANTCONTENT_UPDATE = BASE_URL + "/sms/assistant/content/update";
	String SMSASSISTANTCONTENT_UPDATE_EXE_TIME = BASE_URL + "/sms/assistant/content/update_exe_time";

	String ASSET_PRODUCT_UPDATE = BASE_URL + "/asset/product/update";

	String ASSET_PRODUCT_GET_PRODUCTIDS = BASE_URL + "/asset/product/get_productIds";

	String ASSET_ADD = BASE_URL + "/asset/add";

	String ASSET_UPDATE = BASE_URL + "/asset/update";

	String ASSET_DELETE = BASE_URL + "/asset/delete";

	String ASSET_GET_PAGE = BASE_URL + "/asset/get_page";

	/**
	 * 获取风险评测题目
	 */
	String GETS_RISK_QUESTION = BASE_URL + "/question/gets_risk_question";
}
