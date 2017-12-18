
package com.yundao.tenant.app.controller.user.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.annotation.CommonField;
import com.yundao.tenant.app.service.user.card.CardScanService;
import com.yundao.tenant.app.view.user.card.CardScanInfoView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年11月24日 下午4:01:44
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/card/")
@ResponseBody
@Api("卡片扫描")
public class CardScanController {
	@Autowired
	private CardScanService cardScanService;

	@ApiOperation(value = "名片扫描信息", notes = "名片扫描信息")
	@RequestMapping(value = "scaning", method = RequestMethod.POST)
//	@CommonField(certificate = false, tenantCode = false)
	public Result<CardScanInfoView> getCardInfo(@RequestParam(value = "file") MultipartFile file) throws Exception {
		return cardScanService.getCardInfo(file);
	}
}
