package com.yundao.tenant.app.controller.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yundao.core.code.Result;
import com.yundao.tenant.app.service.product.ProductNoticeTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 业务对象字段分组
 *
 * @author gjl
 * @create 2017-07-06 PM6:07
 **/
@RestController
@RequestMapping("/product/noticetype/")
@ResponseBody
@Api("产品公告类型")
public class ProductNoticeTypeController {
	@Autowired
	private ProductNoticeTypeService productNoticeTypeService;
	
    @RequestMapping(value = "get_all", method=RequestMethod.POST)
    @ApiOperation(value="获取所有的产品公告类型")
    public Result<Map<String, Object>> getAll() throws Exception {
    	return productNoticeTypeService.getAll();
    }
}