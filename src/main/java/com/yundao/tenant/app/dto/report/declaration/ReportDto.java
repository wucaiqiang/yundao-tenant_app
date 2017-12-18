

package com.yundao.tenant.app.dto.report.declaration;

import java.util.ArrayList;
import java.util.List;

import com.yundao.core.utils.BooleanUtils;
import com.yundao.tenant.app.dto.report.customer.DepartmentUserAddCustomerReportDto;
import com.yundao.tenant.app.dto.report.declaration.detail.DepartmentUserReportDto;
import com.yundao.tenant.app.dto.report.report.Value;
import com.yundao.tenant.app.dto.report.report.XTitle;
import com.yundao.tenant.app.dto.report.report.YConfigeData;
import com.yundao.tenant.app.enums.report.ReportCustomGroupEnum;
import com.yundao.tenant.app.enums.report.ReportFilterTypeEnum;
import com.yundao.tenant.app.util.NumberUtil;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年10月31日 下午4:38:28 
 * @author   欧阳利
 * @version   
 */
public class ReportDto {
	
    private String date;
    
    private Double sumDealAmount;

	public String getDate() {
	
		return date;
	}

	public void setDate(String date) {
	
		this.date = date;
	}

	public Double getSumDealAmount() {
	
		return sumDealAmount;
	}

	public void setSumDealAmount(Double sumDealAmount) {
	
		this.sumDealAmount = sumDealAmount;
	}


	public static List<Value> getValues(List<ReportDto> reportDtos,String xUnit,String yUnit){
		if(BooleanUtils.isEmpty(reportDtos)){
			return null;
		}
		List<Value> values = new ArrayList<Value>();
		for(int i=0;i<reportDtos.size();i++){
			ReportDto reportDto = reportDtos.get(i);
			Value value = new Value();
			value.setxTitle(reportDto.getDate()+xUnit);
			value.setyTitle(NumberUtil.getAmountStr(reportDto.getSumDealAmount())+yUnit);
			value.setPointX(Float.valueOf(i+1));
			value.setPointY(reportDto.getSumDealAmount());
			values.add(value);
		}
		return values;
	}
	
	
	public static List<XTitle> getXTitle(List<ReportDto> reportDtos,String xUnit){
		if(BooleanUtils.isEmpty(reportDtos)){
			return null;
		}
		List<XTitle> xTitles = new ArrayList<XTitle>();
		for(int i=0;i<reportDtos.size();i++){
			ReportDto reportDto = reportDtos.get(i);
			XTitle xTitle = new XTitle();
			xTitle.setxTitle(reportDto.getDate()+xUnit);
			xTitle.setPointX(Float.valueOf(i+1));
			xTitles.add(xTitle);
		}
		return xTitles;
	}
	
	public static List<Value> getValuesDepartmentUser(Integer filterType,String statType,List<DepartmentUserReportDto> reportDtos,String xUnit,String yUnit){
		if(BooleanUtils.isEmpty(reportDtos)){
			return null;
		}
		List<Value> values = new ArrayList<Value>();
		for(int i=0;i<reportDtos.size();i++){
			DepartmentUserReportDto reportDto = reportDtos.get(i);
			Value value = new Value();
			if(reportDto.getOrderIndex() != null ){
				value.setxTitle(reportDto.getStartDate()+"~"+reportDto.getEndDate());
				value.setyTitle("第"+reportDto.getOrderIndex()+"周:" +NumberUtil.getAmountStr(reportDto.getSumDealAmount())+yUnit);
				value.setPointX(Float.valueOf(reportDto.getOrderIndex()));
				value.setPointY(reportDto.getSumDealAmount());
			}else{
				value.setxTitle(reportDto.getDate()+xUnit);
				value.setyTitle(NumberUtil.getAmountStr(reportDto.getSumDealAmount())+yUnit);
				value.setPointX(Float.valueOf(i+1));
				value.setPointY(reportDto.getSumDealAmount());
			}
			if(ReportFilterTypeEnum.MONTH.getValue().equals(filterType) 
					|| ReportFilterTypeEnum.UPPER_MONTH.getValue().equals(filterType)){
				value.setxTitle(String.valueOf(i+1));
				value.setyTitle(reportDto.getDate().substring(5)+":"+ value.getyTitle());
			}
			if(ReportFilterTypeEnum.QUARTER.getValue().equals(filterType) 
					|| ReportFilterTypeEnum.UPPER_QUARTER.getValue().equals(filterType)
					|| ReportFilterTypeEnum.YEAR.getValue().equals(filterType)
					|| ReportFilterTypeEnum.UPPER_YEAR.getValue().equals(filterType)){
				value.setyTitle(reportDto.getDate().substring(5)+"月:"+ value.getyTitle());
			}
			if(ReportFilterTypeEnum.CUSTOM.getValue().equals(filterType) && ReportCustomGroupEnum.DAY.getValue().equals(statType)){
				value.setxTitle(value.getxTitle().substring(5));
				value.setyTitle(value.getxTitle()+"日:"+value.getyTitle());
			}
			if(ReportFilterTypeEnum.CUSTOM.getValue().equals(filterType) && ReportCustomGroupEnum.MONTH.getValue().equals(statType)){
				value.setyTitle(reportDto.getDate().substring(5)+"月:"+ value.getyTitle());
			}

			values.add(value);
		}
		return values;
	}
	
	
	public static List<XTitle> getXTitleDepartmentUser(Integer filterType,String statType,List<DepartmentUserReportDto> reportDtos,String xUnit){
		if(BooleanUtils.isEmpty(reportDtos)){
			return null;
		}
		List<XTitle> xTitles = new ArrayList<XTitle>();
		for(int i=0;i<reportDtos.size();i++){
			DepartmentUserReportDto reportDto = reportDtos.get(i);
			XTitle xTitle = new XTitle();
			if(reportDto.getOrderIndex() == null){
				xTitle.setxTitle(reportDto.getDate());
				xTitle.setPointX(Float.valueOf(i+1));
			}else{
				xTitle.setxTitle("第"+reportDto.getOrderIndex()+"周");
				xTitle.setPointX(Float.valueOf(reportDto.getOrderIndex()));
			}
			if(ReportFilterTypeEnum.MONTH.getValue().equals(filterType) 
					|| ReportFilterTypeEnum.UPPER_MONTH.getValue().equals(filterType)){
				xTitle.setxTitle(String.valueOf(i+1));
			}
			if(ReportFilterTypeEnum.CUSTOM.getValue().equals(filterType) && ReportCustomGroupEnum.DAY.getValue().equals(statType)){
				xTitle.setxTitle(xTitle.getxTitle().substring(5));
			}
			xTitles.add(xTitle);
		}
		return xTitles;
	}
	
	public static YConfigeData getYConfigeDataDepartmentUser(List<DepartmentUserReportDto> reportDtos){
		if(BooleanUtils.isEmpty(reportDtos)){
			return null;
		}
		Double min = null;
		Double max = null;
		for(DepartmentUserReportDto dto:reportDtos){
			if(min == null){
				min = dto.getSumDealAmount();
			}
			if(max == null){
				max = dto.getSumDealAmount();
			}
			if(min > dto.getSumDealAmount()){
				min = dto.getSumDealAmount();
			}
			if(max < dto.getSumDealAmount()){
				max = dto.getSumDealAmount();
			}
		}
		
		
		YConfigeData yConfigeData = new YConfigeData();
		yConfigeData.setyMax(Math.rint(max/100)*100);
		yConfigeData.setyMin(min);
		return yConfigeData;
	}
	
	public static YConfigeData getYConfigeData(List<ReportDto> reportDtos){
		if(BooleanUtils.isEmpty(reportDtos)){
			return null;
		}
		Double min = null;
		Double max = null;
		for(ReportDto dto:reportDtos){
			if(min == null){
				min = dto.getSumDealAmount();
			}
			if(max == null){
				max = dto.getSumDealAmount();
			}
			if(min > dto.getSumDealAmount()){
				min = dto.getSumDealAmount();
			}
			if(max < dto.getSumDealAmount()){
				max = dto.getSumDealAmount();
			}
		}
		
		
		YConfigeData yConfigeData = new YConfigeData();
		yConfigeData.setyMax(Math.ceil(max/100)*100);
		yConfigeData.setyMin(min);
		return yConfigeData;
	}
	
	
	public static List<Value> getValuesCustomerDepartmentUser(Integer filterType,String statType,List<DepartmentUserAddCustomerReportDto> reportDtos,String xUnit,String yUnit){
		if(BooleanUtils.isEmpty(reportDtos)){
			return null;
		}
		List<Value> values = new ArrayList<Value>();
		for(int i=0;i<reportDtos.size();i++){
			DepartmentUserAddCustomerReportDto reportDto = reportDtos.get(i);
			Value value = new Value();
			if(reportDto.getOrderIndex() != null ){
				value.setxTitle(reportDto.getStartDate()+"~"+reportDto.getEndDate());
				value.setyTitle("第"+reportDto.getOrderIndex()+"周:" +reportDto.getSumCount()+yUnit);
				value.setPointX(Float.valueOf(reportDto.getOrderIndex()));
				value.setPointY(Double.valueOf(reportDto.getSumCount()));
			}else{
				value.setxTitle(reportDto.getDate()+xUnit);
				value.setyTitle(reportDto.getSumCount()+yUnit);
				value.setPointX(Float.valueOf(i+1));
				value.setPointY(Double.valueOf(reportDto.getSumCount()));
			}
			if(ReportFilterTypeEnum.MONTH.getValue().equals(filterType) 
					|| ReportFilterTypeEnum.UPPER_MONTH.getValue().equals(filterType)){
				value.setxTitle(String.valueOf(i+1));
				value.setyTitle(reportDto.getDate().substring(5)+":"+ value.getyTitle());
			}
			if(ReportFilterTypeEnum.QUARTER.getValue().equals(filterType) 
					|| ReportFilterTypeEnum.UPPER_QUARTER.getValue().equals(filterType)
					|| ReportFilterTypeEnum.YEAR.getValue().equals(filterType)
					|| ReportFilterTypeEnum.UPPER_YEAR.getValue().equals(filterType)){
				value.setyTitle(reportDto.getDate().substring(5)+"月:"+ value.getyTitle());
			}
			if(ReportFilterTypeEnum.CUSTOM.getValue().equals(filterType) && ReportCustomGroupEnum.DAY.getValue().equals(statType)){
				value.setxTitle(value.getxTitle().substring(5));
				value.setyTitle(value.getxTitle()+"日:"+value.getyTitle());
			}
			if(ReportFilterTypeEnum.CUSTOM.getValue().equals(filterType) && ReportCustomGroupEnum.MONTH.getValue().equals(statType)){
				value.setyTitle(reportDto.getDate().substring(5)+"月:"+ value.getyTitle());
			}

			values.add(value);
		}
		return values;
	}
	public static List<XTitle> getXTitleCustomerDepartmentUser(Integer filterType,String statType,List<DepartmentUserAddCustomerReportDto> reportDtos,String xUnit){
		if(BooleanUtils.isEmpty(reportDtos)){
			return null;
		}
		List<XTitle> xTitles = new ArrayList<XTitle>();
		for(int i=0;i<reportDtos.size();i++){
			DepartmentUserAddCustomerReportDto reportDto = reportDtos.get(i);
			XTitle xTitle = new XTitle();
			if(reportDto.getOrderIndex() == null){
				xTitle.setxTitle(reportDto.getDate());
				xTitle.setPointX(Float.valueOf(i+1));
			}else{
				xTitle.setxTitle("第"+reportDto.getOrderIndex()+"周");
				xTitle.setPointX(Float.valueOf(reportDto.getOrderIndex()));
			}
			if(ReportFilterTypeEnum.MONTH.getValue().equals(filterType) 
					|| ReportFilterTypeEnum.UPPER_MONTH.getValue().equals(filterType)){
				xTitle.setxTitle(String.valueOf(i+1));
			}
			if(ReportFilterTypeEnum.CUSTOM.getValue().equals(filterType) && ReportCustomGroupEnum.DAY.getValue().equals(statType)){
				xTitle.setxTitle(xTitle.getxTitle().substring(5));
			}
			xTitles.add(xTitle);
		}
		return xTitles;
	}
	
	
	public static YConfigeData getYConfigeDataCustomerDepartmentUser(List<DepartmentUserAddCustomerReportDto> reportDtos){
		if(BooleanUtils.isEmpty(reportDtos)){
			return null;
		}
		Integer min = null;
		Integer max = null;
		for(DepartmentUserAddCustomerReportDto dto:reportDtos){
			if(min == null){
				min = dto.getSumCount();
			}
			if(max == null){
				max = dto.getSumCount();
			}
			if(min > dto.getSumCount()){
				min = dto.getSumCount();
			}
			if(max < dto.getSumCount()){
				max = dto.getSumCount();
			}
		}
		
		
		YConfigeData yConfigeData = new YConfigeData();
		yConfigeData.setyMax(Math.rint(max/100)*100);
		yConfigeData.setyMin(Double.valueOf(min));
		return yConfigeData;
	}
	
}

