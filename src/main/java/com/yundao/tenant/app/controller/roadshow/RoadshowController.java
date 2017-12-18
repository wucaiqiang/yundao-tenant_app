
package com.yundao.tenant.app.controller.roadshow;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.roadshow.RoadshowListReqDto;
import com.yundao.tenant.app.service.roadshow.RoadshowService;
import com.yundao.tenant.app.view.roadshow.RoadshowDetailView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年11月9日 下午6:00:03
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/roadshow/")
@Api("路演管理")
@ResponseBody
public class RoadshowController {
	@Autowired
	private RoadshowService roadshowService;

	@RequestMapping(value = "get_column", method = RequestMethod.POST)
	@ApiOperation(value = "获取路演栏目表详细信息")
	public Result<Map<String, Object>> getColumn() throws Exception {
		return roadshowService.getColumn();
	}

	@RequestMapping(value = "get_page", method = RequestMethod.POST)
	@ApiOperation(value = "获取路演栏目表详细信息")
	public Result<ItemListDTO> getPage(@ModelAttribute RoadshowListReqDto reqDto) throws Exception {
		return roadshowService.getPage(reqDto);
	}

	@RequestMapping(value = "get", method = RequestMethod.POST)
	@ApiOperation(value = "获取路演详细信息")
	public Result<RoadshowDetailView> get(@RequestParam String roadshowId) throws Exception {
		return roadshowService.get(roadshowId);
	}
}
