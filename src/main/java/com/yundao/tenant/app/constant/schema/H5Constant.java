
package com.yundao.tenant.app.constant.schema;

/**
 * Function: Reason: Date: 2017年9月22日 上午11:04:33
 * 
 * @author wucq
 * @version
 */
public interface H5Constant {
	String PROTOCOL = "http://";
	// 理财师名片分享
	String AFP_CARD = PROTOCOL + "%s/cfp/card?cfp=%s";// 第一个参数为 动态域名地址
	// 路演分享
	String ROADSHOW_SHARE = PROTOCOL + "%s/roadshow/detail/%s";// 第一个参数为
																// 动态域名地址

	// 风险测评
	String RISK_SCHEME = PROTOCOL + "%s/risk/scheme?id=%s&isEdit=%s"; // 测评id
	// 风险测评
	String RISK_SCHEME_CUSTOMER = PROTOCOL + "%s/risk/scheme?customerId=%s&isEdit=%s"; // 测评id

	// 产品详情分享
	String PRODUCT_DETAIL_SHARE = PROTOCOL + "%s/product/detail/%s?cfp=%s";
	//产品公告
	String PRODUCT_NOTICE_SHARE = PROTOCOL + "%s/product/notice/%s";

}
