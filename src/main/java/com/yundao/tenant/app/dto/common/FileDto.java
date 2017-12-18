package com.yundao.tenant.app.dto.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gjl on 2017/7/20.
 */
public class FileDto {
    @ApiModelProperty(value = "附件url")
    private String fileUrl;

    @ApiModelProperty(value = "附件名称")
    private String fileName;
    
    @ApiModelProperty(value = "附件类型")
    private String fileType;
    public FileDto(){}
    public FileDto(String fileUrl,String fileName,String fileType){
    	this.fileName=fileName;
    	this.fileUrl=fileUrl;
    	this.fileType=fileType;
    }
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
	public String getFileType() {
	
		return fileType;
	}
	public void setFileType(String fileType) {
	
		this.fileType = fileType;
	}
    
}
