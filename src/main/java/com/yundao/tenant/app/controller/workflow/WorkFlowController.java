
package com.yundao.tenant.app.controller.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.BasePageDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.workflow.ActionDto;
import com.yundao.tenant.app.dto.workflow.TaskPageResDto;
import com.yundao.tenant.app.dto.workflow.WorkFlowAuditReqDto;
import com.yundao.tenant.app.enums.workflow.ActionEnum;
import com.yundao.tenant.app.enums.workflow.TaskCategoryEnum;
import com.yundao.tenant.app.service.workflow.WorkFlowService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.customer.Structure3009View;
import com.yundao.tenant.app.view.customer.Structure4View;
import com.yundao.tenant.app.view.customer.Structure5View;
import com.yundao.tenant.app.view.workflow.WorkflowDetailView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年9月14日 下午4:53:00
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/workflow")
@Api("工作流")
@ResponseBody
public class WorkFlowController {

	@Autowired
	private WorkFlowService workFlowService;

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ApiOperation(value = "获取某个工作流的详情")
	public Result<WorkflowDetailView> get(@RequestParam Long id, @RequestParam String type, @RequestParam String taskId)
			throws BaseException {
		return workFlowService.get(id, type, taskId, "TODO");
	}

	@RequestMapping(value = "/done/get", method = RequestMethod.POST)
	@ApiOperation(value = "获取某个工作流的详情")
	public Result<WorkflowDetailView> getDone(@RequestParam Long id, @RequestParam String type,
			@RequestParam String taskId) throws BaseException {
		return workFlowService.get(id, type, taskId, "DONE");
	}

	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	@ApiOperation(value = "审批")
	public Result<Boolean> audit(@ModelAttribute WorkFlowAuditReqDto auditReqDto) throws BaseException {
		return workFlowService.audit(auditReqDto);
	}

	/**
	 * 分页查询待审批的任务 getPagePersonal:
	 * 
	 * @author: 欧阳利
	 * @param dto
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	@ApiOperation(value = "分页查询待审批的任务")
	@RequestMapping(value = "/audit/get_page", method = RequestMethod.POST)
	public Result<ItemListDTO> getPage(@ModelAttribute BasePageDto dto) throws BaseException {

		Result<PaginationSupport<TaskPageResDto>> result = HttpUtils.get(TenantUrl.GET_WORK_FLOW_AUDIT_PAGE,
				ArgsUtils.toMap(dto), new BaseTypeReference<Result<PaginationSupport<TaskPageResDto>>>() {
				});

		if (!result.getSuccess() || result.getResult() == null) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}

		PaginationSupport<TaskPageResDto> pager = result.getResult();
		ItemListDTO itemListDTO = new ItemListDTO(pager);
		List<ViewItem> viewItems = new ArrayList<>();
		List<TaskPageResDto> taskPageResDtos = pager.getDatas();
		if (!BooleanUtils.isEmpty(taskPageResDtos)) {
			if (pager.getCurrentPage() == 1) {
				Structure3009View view3009 = new Structure3009View();
				// view3009.setText("共有"+pager.getTotalCount()+"项待审批");
				view3009.setTextStart("共有");
				view3009.setCount(pager.getTotalCount());
				view3009.setTextEnd("项待审批");
				viewItems.add(new ViewItem(3009, null, view3009));
			}
			for (TaskPageResDto resDto : taskPageResDtos) {
				String jumpUrl = Schema.TASK_DETAIL + String.format(Schema.TASK_DETAIL_PARAMS, resDto.getTaskId(),
						resDto.getObjectId(), resDto.getType());
				Structure4View view4 = new Structure4View();
				BeanUtils.copyProperties(resDto, view4);
				setAction(resDto.getActionDtos(), view4);
				view4.setId(resDto.getObjectId().toString());
				viewItems.add(new ViewItem(4, jumpUrl, view4));
			}
		}
		itemListDTO.setViewItems(viewItems);
		return Result.newSuccessResult(itemListDTO);
	}
	
	
	/**
	 * 设置操作
	 * setAction:
	 * @author: 欧阳利
	 * @param actionDtos
	 * @param view4
	 * @description:
	 */
	private void setAction(List<ActionDto> actionDtos,Structure4View view4){
		if(BooleanUtils.isEmpty(actionDtos)){
			return;
		}
		
		for(ActionDto dto : actionDtos){
			if(dto.getActionValue().equals(ActionEnum.NO_PASS.getValue())){
				view4.setHasVetoOperate(true);
			}
			if(dto.getActionValue().equals(ActionEnum.REJECT.getValue())){
				view4.setHasRejectOperate(true);
			}
			if(dto.getActionValue().equals(ActionEnum.PASS.getValue())){
				view4.setHasPassOperate(true);
			}
		}
	}
	

	@ApiOperation(value = "我参与的历史")
	@RequestMapping(value = "/done/get_page", method = RequestMethod.POST)
	public Result<ItemListDTO> getDonePage(@ModelAttribute BasePageDto dto) throws BaseException {
		dto.setOrderColumn("END_TIME_");
		dto.setSort("desc");
		Map<String, Object> methodParams = ArgsUtils.toMap(dto);
		methodParams.put("filtNodeCategorys", TaskCategoryEnum.REJECT_EDIT.getValue());
		Result<PaginationSupport<TaskPageResDto>> result = HttpUtils.get(TenantUrl.GET_WORK_FLOW_DONE_PAGE,
				methodParams, new BaseTypeReference<Result<PaginationSupport<TaskPageResDto>>>() {
				});

		if (!result.getSuccess() || result.getResult() == null) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}

		PaginationSupport<TaskPageResDto> pager = result.getResult();
		ItemListDTO itemListDTO = new ItemListDTO(pager);
		List<ViewItem> viewItems = new ArrayList<>();
		List<TaskPageResDto> taskPageResDtos = pager.getDatas();
		if (!BooleanUtils.isEmpty(taskPageResDtos)) {
			for (TaskPageResDto resDto : taskPageResDtos) {
				String jumpUrl = Schema.TASK_DONE_DETAIL + String.format(Schema.TASK_DONE_DETAIL_PARAMS,
						resDto.getTaskId(), resDto.getObjectId(), resDto.getType());
				Structure5View view5 = new Structure5View();
				view5.setId(resDto.getObjectId().toString());
				view5.setTaskId(resDto.getTaskId());
				view5.setType(resDto.getType());
				view5.setTitle(resDto.getTitle());
				view5.setContentList(resDto.getContentList());
				view5.setApplyUserRealName(resDto.getApplyUserRealName());
				view5.setApplyTime(resDto.getApplyTime());
				view5.setOperateTime(resDto.getEndTime());
				view5.setOperateResult(ActionEnum.getEnumByValue(resDto.getActionValue()).getDesc());
				viewItems.add(new ViewItem(5, jumpUrl, view5));
			}
		}
		itemListDTO.setViewItems(viewItems);
		return Result.newSuccessResult(itemListDTO);
	}
}
