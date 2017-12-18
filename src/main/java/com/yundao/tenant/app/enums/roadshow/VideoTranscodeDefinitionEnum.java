
package com.yundao.tenant.app.enums.roadshow;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 视频类型枚举
 *
 * @author jan
 * @create 2017-10-27 20:21
 **/
public enum VideoTranscodeDefinitionEnum {

	/**
	 * 标清
	 */
	STANDARD(20, "标清"),
	/**
	 * 高清
	 */
	HIGH(30, "高清");

	private Integer value;
	private String name;
	public static Map<Integer, VideoTranscodeDefinitionEnum> enumMap = new HashMap<Integer, VideoTranscodeDefinitionEnum>();
	static {
		EnumSet<VideoTranscodeDefinitionEnum> set = EnumSet.allOf(VideoTranscodeDefinitionEnum.class);
		for (VideoTranscodeDefinitionEnum each : set) {
			enumMap.put(each.getValue(), each);
		}
	}

	private VideoTranscodeDefinitionEnum(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public Integer getValue() {

		return value;
	}

	public void setValue(Integer value) {

		this.value = value;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	/**
	 * 获取枚举
	 * 
	 * @param value
	 * @return
	 */
	public static VideoTranscodeDefinitionEnum getEnum(Integer value) {
		VideoTranscodeDefinitionEnum definitionEnum = enumMap.get(value);
		return definitionEnum;
	}

}
