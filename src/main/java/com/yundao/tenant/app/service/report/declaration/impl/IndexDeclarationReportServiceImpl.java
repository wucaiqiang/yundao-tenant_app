

package com.yundao.tenant.app.service.report.declaration.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.ConfigUtils;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.ItemListNoPageDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.report.declaration.DeclarationTopUserDto;
import com.yundao.tenant.app.dto.report.declaration.IndexDto;
import com.yundao.tenant.app.dto.report.declaration.IndexTopDto;
import com.yundao.tenant.app.dto.report.declaration.ReportButtonDto;
import com.yundao.tenant.app.dto.report.declaration.ReportDto;
import com.yundao.tenant.app.dto.report.report.Value;
import com.yundao.tenant.app.dto.report.report.YConfigeData;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.service.report.declaration.IndexDeclarationReportService;
import com.yundao.tenant.app.service.resource.ResourceService;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.NumberUtil;
import com.yundao.tenant.app.view.report.declaration.Structure7001View;
import com.yundao.tenant.app.view.report.declaration.Structure7002View;
import com.yundao.tenant.app.view.report.declaration.Structure7003View;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月1日 上午10:59:07 
 * @author   欧阳利
 * @version   
 */

@Service
public class IndexDeclarationReportServiceImpl implements IndexDeclarationReportService {

	@Autowired
	PermissionService permissionService;
	@Autowired
	ResourceService resourceService;
	/**
	 * 报表首页信息
	 * getIndexDto:
	 * @author: 欧阳利
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	 public Result<ItemListNoPageDTO> getIndexDto()throws BaseException{
		 // 权限资源code
		 List<String> resourceCodes =  resourceService.getsByUserId().getResult();
		 // 获取数据
		 Map<String, Object> methodParams = new HashMap<>();
		 Result<IndexDto> result = HttpUtils.get(TenantUrl.REPORT_GET_INDEX, methodParams,
				new BaseTypeReference<Result<IndexDto>>() {
				});
		 IndexDto indexDto = result.getResult();
		 List<ViewItem> viewItems = new ArrayList<ViewItem>();
		 // 公司半年业绩走势
		 setIndexReport(viewItems,indexDto.getReportDtos(),resourceCodes);
		 // 分隔行
		 viewItems.add(new ViewItem(2001, null, null));
		 // 员工top5排名
		 setIndexUserTop(viewItems,indexDto.getIndexTopDtos(),resourceCodes);
		 // 报表按钮
		 setReportButton(viewItems,resourceCodes);
		 
		 ItemListNoPageDTO dto = new ItemListNoPageDTO(viewItems);
		 return Result.newSuccessResult(dto);
	 }
	 
	 /**
	  * 设置首页公司业绩报表
	  * setIndexReport:
	  * @author: 欧阳利
	  * @param viewItems
	  * @throws BaseException
	  * @description:
	  */
	 private void setIndexReport(List<ViewItem> viewItems,List<ReportDto> reportDtos,List<String> resourceCodes)throws BaseException{
		 if(!resourceCodes.contains("report.declaration.company")){
			 return;
		 }
		 if(BooleanUtils.isEmpty(reportDtos)){
			 //viewItems.add(new ViewItem(2002, null, new Structure2002View("暂无数据")));
			 //return;
			 reportDtos = new ArrayList<ReportDto>();
		 }
		 
		 // 如果所有金额都为0 显示没有数据
		 boolean isAllZero = true;;
		 for(ReportDto dto : reportDtos){
			 if(dto.getSumDealAmount() > 0){
				 isAllZero = false;
			 }
		 }
		 
		 Structure7001View structure7001View = new Structure7001View();
		 structure7001View.setTitle("公司业绩走势");
		 structure7001View.setUnit("万");
		 structure7001View.setIsIndex(true);
         if(isAllZero){
        	 structure7001View.setValues(new ArrayList<Value>());
		 }else{
			 structure7001View.setValues(ReportDto.getValues(reportDtos, "", "万"));
		 }
		 structure7001View.setxTitles(ReportDto.getXTitle(reportDtos, ""));
		 YConfigeData yConfigeData = ReportDto.getYConfigeData(reportDtos);
		 yConfigeData.setyMin(0.0);
		 structure7001View.setyConfigeData(yConfigeData);
		 
		 String jumpUrl = null;
		 if(resourceCodes.contains("report.declaration")){
			 jumpUrl=Schema.REPORT_PAGE;
		 }
		 if(resourceCodes.contains("report.declaration.company")){
			 viewItems.add(new ViewItem(7001, jumpUrl, structure7001View));
		 }
		 
	 }
	 
	 /**
	  * 查询报单排行
	  * setIndexUserTop:
	  * @author: 欧阳利
	  * @param viewItems
	  * @param indexDto
	  * @description:
	  */
	 private void setIndexUserTop(List<ViewItem> viewItems, List<IndexTopDto> indexTopDtos,List<String> resourceCodes){
		 if(!resourceCodes.contains("report.declaration.user.top")){
			 return;
		 }
		 if(BooleanUtils.isEmpty(indexTopDtos)){
			 //viewItems.add(new ViewItem(2002, null, new Structure2002View("暂无数据")));
			 Structure7002View structure7002View = new Structure7002View();
			 structure7002View.setTitle("员工业绩龙虎榜");
			 viewItems.add(new ViewItem(7002, null, structure7002View));
			 return; 
		 }
		 Structure7002View structure7002View = new Structure7002View();
		 structure7002View.setTitle("员工业绩龙虎榜");
		 List<DeclarationTopUserDto> values = new ArrayList<DeclarationTopUserDto>();
		 structure7002View.setValues(values);
		 Double maxRecord = 0d;
		 for(IndexTopDto indexTopDto : indexTopDtos){
			 if(maxRecord < indexTopDto.getSumDealAmount()){
				 maxRecord =  indexTopDto.getSumDealAmount();
			 }
		 }
		 for(IndexTopDto indexTopDto : indexTopDtos){
			 DeclarationTopUserDto dto = new DeclarationTopUserDto();
			 values.add(dto);
			 dto.setName(indexTopDto.getUsermame());
			 dto.setRecord(indexTopDto.getSumDealAmount());
			 dto.setRecordText(NumberUtil.getAmountStr(indexTopDto.getSumDealAmount())+"万");
			 dto.setMaxRecord(maxRecord);
		 }
		 if(resourceCodes.contains("report.declaration.user.top")){
			 viewItems.add(new ViewItem(7002, null, structure7002View));
		 }
		 
	 }
	 
	 
	 /**
	  * 设置报单按钮
	  * setReportButton:
	  * @author: 欧阳利
	  * @param viewItems
	  * @param IndexDto
	  * @description:
	  */
	 private void setReportButton(List<ViewItem> viewItems,List<String> resourceCodes) throws BaseException{
		 if(!resourceCodes.contains("report.declaration.customer") && !resourceCodes.contains("report.declaration") ){
			 //viewItems.add(new ViewItem(2002, null, new Structure2002View("暂无权限查看")));
			 return;
		 }
		
		 //viewItems.add(new ViewItem(1005, null, new Structure1005View("统计类目",true)));
		 Structure7003View structure7003View = new Structure7003View();
		 structure7003View.setTitle("统计类目");
		 List<ReportButtonDto> items = new ArrayList<ReportButtonDto>();
		 if(resourceCodes.contains("report.declaration")){
			 items.add(new ReportButtonDto("业绩报表",ConfigUtils.getValue("app.url.report.declaration"),new ActionDTO(Schema.REPORT_PAGE)));
		 }
		 if(resourceCodes.contains("report.customer")){
			 items.add(new ReportButtonDto("客户新增",ConfigUtils.getValue("app.url.report.customer.add_customer"),new ActionDTO(Schema.REPORT_CUSTOMER_ADD_CUSTOMER)));
		 }
		 if(resourceCodes.contains("report.declaration.customer")){
			 items.add(new ReportButtonDto("客户成交",ConfigUtils.getValue("app.url.report.declaration.customer"),new ActionDTO(Schema.REPORT_CUSTOMER)));
		 }
		 structure7003View.setItems(items);
		 viewItems.add(new ViewItem(7003, null, structure7003View));
		 
	 }
	
}

