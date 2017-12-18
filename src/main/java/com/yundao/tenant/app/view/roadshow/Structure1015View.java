
package com.yundao.tenant.app.view.roadshow;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.ShareInfo;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure1015View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 路演名称
	 */
	private String roadshowName;
	/**
	 * 路演封面
	 */
	private String image;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 时间长度；e.g. 12：32
	 */
	private String duration;

	public Structure1015View() {
	}

	public Structure1015View(String type,String roadshowName, String image,String duration) {
		this.type=type;
		this.roadshowName = roadshowName;
		this.image = image;
		this.duration=duration;
	}

	public String getRoadshowName() {

		return roadshowName;
	}

	public void setRoadshowName(String roadshowName) {

		this.roadshowName = roadshowName;
	}

	public String getImage() {

		return image;
	}

	public void setImage(String image) {

		this.image = image;
	}

	public String getType() {
	
		return type;
	}

	public void setType(String type) {
	
		this.type = type;
	}

	public String getDuration() {
	
		return duration;
	}

	public void setDuration(String duration) {
	
		this.duration = duration;
	}

}
