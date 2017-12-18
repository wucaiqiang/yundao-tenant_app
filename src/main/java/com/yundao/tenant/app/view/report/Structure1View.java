

package com.yundao.tenant.app.view.report;

import java.io.Serializable;
import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.view.NameIdView;


/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月19日 下午8:17:02 
 * @author   wucq
 * @version   
 */
public class Structure1View implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 时间类型筛选荐
	 */
	private List<NameIdView> timeTypeList;
	/**
	 * 报单及预约
	 */
	private List<ReportView> achievementList;
	public Structure1View(){}
	public Structure1View(List<ReportView> achievementList,List<NameIdView> timeTypeList){
		this.achievementList=achievementList;
		this.timeTypeList=timeTypeList;
	}
	public List<ReportView> getAchievementList() {
	
		return achievementList;
	}
	public void setAchievementList(List<ReportView> achievementList) {
	
		this.achievementList = achievementList;
	}
	public List<NameIdView> getTimeTypeList() {
	
		return timeTypeList;
	}
	public void setTimeTypeList(List<NameIdView> timeTypeList) {
	
		this.timeTypeList = timeTypeList;
	}
	

}

