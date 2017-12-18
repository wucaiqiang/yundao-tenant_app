package com.yundao.tenant.app.constant.schema;

public interface Schema {
	/**
	 * 动作协议
	 */
	String PROTOCOL = "yundaojinfub://";
	/**
	 * 产品模块
	 */
	String PRODUCT_MODULE = "www.yundaoproduct.com";
	/**
	 * 客户模块
	 */
	String CUSTOMER_MODULE = "www.yundaocustomer.com";

	/**
	 * 预约报单模块
	 */
	String ORDER_MODEL = "www.yundaoorder.com";
	/**
	 * 审批
	 */
	String TASK_MODEL = "www.yundaomine.com";

	/**
	 * 报表
	 */
	String REPORT = "www.yundaoreport.com";
	/**
	 * 路演
	 */
	String ROADSHOW = "www.yundaoroadshow.com";

	String COMMON = "www.yundaocommon.com";

	String H5_PAGE = PROTOCOL + COMMON + "/h5_page";
	String H5_PAGE_PARAMS = "?url=%s&title=%s";

	/**
	 * 业绩报表
	 */
	String REPORT_PAGE = PROTOCOL + REPORT + "/report_page";

	/**
	 * 业绩报表-部门业绩 departmentId
	 */
	String REPORT_PAGE_DEPARTMENT = PROTOCOL + REPORT + "/report_page/report_department";
	String REPORT_PAGE_DEPARTMENT_PARAMS = "?departmentId=%s";

	/**
	 * 业绩报表-员工业绩 departmentId
	 */
	String REPORT_PAGE_EMPLOYEE = PROTOCOL + REPORT + "/report_page/report_employee";
	String REPORT_PAGE_EMPLOYEE_PARAMS = "?departmentId=%s";

	/**
	 * 业绩报表-业绩详情(员工业绩详情与部门业绩详情，相同界面，部门ID与员工Id，二者必须有一) departmentId userId
	 */
	String REPORT_DETAIL = PROTOCOL + REPORT + "/report_detail";
	String REPORT_DETAIL_PARAMS = "?departmentId=%s&userId=%s&title=%s";

	/**
	 * 业绩报表-客户成交
	 */
	String REPORT_CUSTOMER = PROTOCOL + REPORT + "/report_customer";
	
	/**
	 * 业绩报表-新增客户详情(员工业绩详情与部门业绩详情，相同界面，部门ID与员工Id，二者必须有一) departmentId userId
	 */
	String REPORT_CUSTOMER_ADD_CUSTOMER_DETAIL = PROTOCOL + REPORT + "/report_add_customer_detail";
	String REPORT_CUSTOMER_ADD_CUSTOMER_DETAIL_PARAMS = "?departmentId=%s&userId=%s&title=%s";
	
	/**
	 * 业绩报表-客户新增
	 */
	String REPORT_CUSTOMER_ADD_CUSTOMER = PROTOCOL + REPORT + "/report_add_customer_page";

	/**
	 * 业绩报表-客户成交详情(customerId)
	 */
	String REPORT_CUSTOMER_DETAIL = PROTOCOL + REPORT + "/report_customer_detail";
	String REPORT_CUSTOMER_DETAIL_PARAMS = "?customerId=%s";

	/**
	 * 产品列表
	 */
	String PRODUCT_LIST = PROTOCOL + PRODUCT_MODULE + "/product_list";
	/**
	 * 产品详情
	 */
	String PRODUCT_DETAIL = PROTOCOL + PRODUCT_MODULE + "/product_detail";
	String PRODUCT_DETAIL_PARAMS = "?productId=%s&productName=%s";
	/**
	 * 产品公告
	 */
	String PRODUCT_NOTICE_DETAIL = PROTOCOL + PRODUCT_MODULE + "/product_notice_detail";
	String PRODUCT_NOTICE_DETAIL_PARAMS = "?noticeId=%s&from=%s";

	/**
	 * 客户列表
	 */
	String CUSTOMER_LIST = PROTOCOL + CUSTOMER_MODULE + "/customer_list";
	/**
	 * 客户详情
	 */
	String CUSTOMER_DETAIL = PROTOCOL + CUSTOMER_MODULE + "/customer_detail";
	String CUSTOMER_DETAIL_PARAMS = "?customerId=%s";

	/**
	 * 客户回访
	 */
	String CUSTOMER_VISIT_DETAIL = PROTOCOL + CUSTOMER_MODULE + "/customer_detail/callback";
	String CUSTOMER_VISIT_DETAIL_PARAMS = "?customerId=%s";

	/**
	 * 短信编辑界面
	 */
	String SMS_ASSISTANT_CONTENT_EDIT = PROTOCOL + CUSTOMER_MODULE + "/sms_edit";
	String SMS_ASSISTANT_CONTENT_EDIT_PARAMS = "?smsId=%s";

	/**
	 * 已发送的短信详情
	 */
	String SMS_ASSISTANT_CONTENT_DETAIL = PROTOCOL + CUSTOMER_MODULE + "/sms_detail";
	String SMS_ASSISTANT_CONTENT_DETAIL_PARAMS = "?smsId=%s";

	/**
	 * 预约列表
	 */
	String RESERVATION_LIST = PROTOCOL + ORDER_MODEL + "/appoint_list";
	/**
	 * 预约详情
	 */
	String RESERVATION_DETAIL = PROTOCOL + ORDER_MODEL + "/appoint_detail";
	String RESERVATION_DETAIL_PARAMS = "?appointId=%s";

	/**
	 * 报单列表
	 */
	String DECLARATION_LIST = PROTOCOL + ORDER_MODEL + "/order_list";
	/**
	 * 报单详情
	 */
	String DECLARATION_DETAIL = PROTOCOL + ORDER_MODEL + "/order_detail";
	String DECLARATION_DETAIL_PARAMS = "?orderId=%s";

	/**
	 * 审批列表
	 */
	String TASK_LIST = PROTOCOL + TASK_MODEL + "/task_list";
	/**
	 * 办审批详情
	 */
	String TASK_DETAIL = PROTOCOL + TASK_MODEL + "/task_detail";
	String TASK_DETAIL_PARAMS = "?taskId=%s&id=%s&type=%s";
	/**
	 * 我参与审批详情
	 */
	String TASK_DONE_DETAIL = PROTOCOL + TASK_MODEL + "/task_detail_done";
	String TASK_DONE_DETAIL_PARAMS = "?taskId=%s&id=%s&type=%s";

	/**
	 * 理财师帮客户做题的智能测评结果页
	 */
	String EVALUATE_MANAGER_RESULT = PROTOCOL + TASK_MODEL + "/evaluate_manager_result";
	String EVALUATE_MANAGER_RESULT_PARAMS = "?id=%s&shareInfo=%s&productIds=%s&url=%s";

	/**
	 * 路演列表
	 */
	String ROADSHOW_LIST = PROTOCOL + ROADSHOW + "/roadshow_list";
	/**
	 * 路演详情
	 */
	String ROADSHOW_DETAIL = PROTOCOL + ROADSHOW + "/roadshow_detail";
	String ROADSHOW_DETAIL_PARAMS = "?roadshowId=%s";
}
