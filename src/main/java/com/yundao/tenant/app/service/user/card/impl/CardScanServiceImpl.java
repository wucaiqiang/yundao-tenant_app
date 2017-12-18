
package com.yundao.tenant.app.service.user.card.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.ByteStreams;
import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.utils.ConfigUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.tenant.app.dto.user.card.CardDataValueDto;
import com.yundao.tenant.app.dto.user.card.CardOutputDataDto;
import com.yundao.tenant.app.dto.user.card.CardOutputDto;
import com.yundao.tenant.app.dto.user.card.CardScanInfoDto;
import com.yundao.tenant.app.service.user.card.CardScanService;
import com.yundao.tenant.app.util.CardHttpUtils;
import com.yundao.tenant.app.view.user.card.CardScanInfoView;

/**
 * Function: Reason: Date: 2017年11月24日 下午4:12:55
 * 
 * @author wucq
 * @version
 */
@Service
public class CardScanServiceImpl implements CardScanService {
	private String host = "https://dm-57.data.aliyun.com";
	private String path = "/rest/160601/ocr/ocr_business_card.json";
	private String method = "POST";
	private String appcode = ConfigUtils.getValue("appcode");

	@Override
	public Result<CardScanInfoView> getCardInfo(MultipartFile file) throws BaseException {
		try {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Authorization", "APPCODE " + appcode);
			headers.put("Content-Type", "application/json; charset=UTF-8");
			Map<String, String> querys = new HashMap<String, String>();

			// 上传文件到云
			byte[] byteArray = ByteStreams.toByteArray(file.getInputStream());
			String base64Value = Base64.encodeBase64String(byteArray);
			String bodys = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\"" + base64Value + "\"}}]}";

			HttpResponse response = CardHttpUtils.doPost(host, path, method, headers, querys, bodys);
			if (response == null) {
				return Result.newFailureResult();
			}
			String result = EntityUtils.toString(response.getEntity());

			if (StringUtils.isNotBlank(result)) {
				CardScanInfoDto dto = JsonUtils.jsonToObject(result, CardScanInfoDto.class);
				if (dto != null && dto.getOutputs() != null && !dto.getOutputs().isEmpty()) {
					CardOutputDto outputDto = dto.getOutputs().get(0);
					CardOutputDataDto data = outputDto.getOutputValue();
					if (data != null && StringUtils.isNotBlank(data.getDataValue())) {
						String value = data.getDataValue();
						CardDataValueDto dataValueDto = JsonUtils.jsonToObject(value, CardDataValueDto.class);
						if (dataValueDto.isSuccess()) {
							// 扫描成功
							CardScanInfoView view = new CardScanInfoView();
							view.setName(dataValueDto.getName());
							if (dataValueDto.getCompany() != null && !dataValueDto.getCompany().isEmpty()) {
								view.setCompany(dataValueDto.getCompany().get(0));
							}
							if (dataValueDto.getEmail() != null && !dataValueDto.getEmail().isEmpty()) {
								view.setEmail(dataValueDto.getEmail().get(0));
							}
							if (dataValueDto.getTel_cell() != null && !dataValueDto.getTel_cell().isEmpty()) {
								view.setMobile(dataValueDto.getTel_cell().get(0));
							}
							if (dataValueDto.getTitle() != null && !dataValueDto.getTitle().isEmpty()) {
								view.setJob(dataValueDto.getTitle().get(0));
							}
							if (dataValueDto.getAddr() != null && !dataValueDto.getAddr().isEmpty()) {
								view.setAddress(dataValueDto.getAddr().get(0));
							}
							return Result.newSuccessResult(view);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.newFailureResult();
	}

}
