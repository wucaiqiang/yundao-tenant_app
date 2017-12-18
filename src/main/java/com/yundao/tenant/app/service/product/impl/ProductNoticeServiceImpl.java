
package com.yundao.tenant.app.service.product.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.enums.NumberEnum;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.service.AbstractService;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.ConfigUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.schema.H5Constant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.FileDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ShareInfo;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.permission.PermissionDetailResDto;
import com.yundao.tenant.app.dto.permission.PermissionListResDto;
import com.yundao.tenant.app.dto.product.BaseProductNoticeAttach;
import com.yundao.tenant.app.dto.product.ProductNoticeAddReqDto;
import com.yundao.tenant.app.dto.product.ProductNoticeDto;
import com.yundao.tenant.app.dto.product.ProductNoticeQueryReqDto;
import com.yundao.tenant.app.dto.product.ProductNoticeReqDto;
import com.yundao.tenant.app.enums.access.DataObjectPermissionEnum;
import com.yundao.tenant.app.enums.dataobject.DataObjectEnum;
import com.yundao.tenant.app.enums.product.NoticeStatusEnum;
import com.yundao.tenant.app.model.product.ProductNoticeModel;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.service.product.ProductNoticeService;
import com.yundao.tenant.app.service.share.ShareService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.DateFormatUtils;
import com.yundao.tenant.app.util.FileSupport;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.LambdaFilter;
import com.yundao.tenant.app.util.UserUtils;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.product.AttachmentView;
import com.yundao.tenant.app.view.product.OperateView;
import com.yundao.tenant.app.view.product.ProductNoticeView;
import com.yundao.tenant.app.view.product.Structure1014View;

/**
 * Function: Reason: Date: 2017年8月8日 上午10:23:35
 * 
 * @author wucq
 * @version
 */
@Service
public class ProductNoticeServiceImpl extends AbstractService implements ProductNoticeService {
	private Log logger = LogFactory.getLog(ProductNoticeServiceImpl.class);
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ShareService shareService;

	@Override
	public Result<Integer> add(ProductNoticeAddReqDto dto) throws BaseException {
		ProductNoticeReqDto paramDto = new ProductNoticeReqDto();
		paramDto.setContent(dto.getNoticeDes());
		paramDto.setIsTimingSend(dto.getPublishType());
		paramDto.setNoticeTypeId(dto.getNoticeType());
		paramDto.setProductId(dto.getProductId());
		if (dto.getPublishDate() != null) {
			paramDto.setSendTime(new Date(dto.getPublishDate()));
		}
		paramDto.setTitle(dto.getNoticeTitle());
		if (StringUtils.isNotBlank(dto.getAttachList())) {
			List<FileDto> filesList = JsonUtils.jsonToObject(dto.getAttachList(),
					new BaseTypeReference<List<FileDto>>() {
					});
			if (filesList != null && !filesList.isEmpty()) {
				for (FileDto fileDto : filesList) {
					String url = fileDto.getFileUrl();
					int index_ = url.lastIndexOf(".");
					if (index_ != -1) {
						String format = url.substring(index_ + 1);
						fileDto.setFileType(format);
					}
				}
				paramDto.setFiles(JsonUtils.objectToJson(filesList));
			}
		}
		Map<String, Object> params = ArgsUtils.toMap(paramDto);
		return HttpUtils.post(TenantUrl.PRODUCT_NOTICE_ADD, params, new BaseTypeReference<Result<Integer>>() {
		});

	}

	@Override
	public Result<Integer> doCancelFlow(Long id, String reason) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("id", id);
		// 获取公告详情信息
		Result<ProductNoticeDto> result = HttpUtils.get(TenantUrl.PRODUCT_NOTICE_GET, methodParams,
				new BaseTypeReference<Result<ProductNoticeDto>>() {
				});
		if (!result.getSuccess() || result.getResult() == null) {
			throw new BaseException(CodeConstant.CODE_1220016);
		}
		Result<Boolean> checkResult = permissionService.checkUpdate(DataObjectEnum.NOTICE.getCode(),
				result.getResult().getCreateUserId());
		if (!checkResult.getSuccess() || !checkResult.getResult()) {
			throw new BaseException(CodeConstant.CODE_1220016);
		}

		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("status", NoticeStatusEnum.STATUS_5.getStatus());
		params.put("reason", reason);
		return HttpUtils.post(TenantUrl.PRODUCT_NOTICE_FLOW, params, new BaseTypeReference<Result<Integer>>() {
		});

	}

	@Override
	public Result<Integer> doApplyOrResubmit(Long id) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("id", id);
		// 获取公告详情信息
		Result<ProductNoticeDto> result = HttpUtils.get(TenantUrl.PRODUCT_NOTICE_GET, methodParams,
				new BaseTypeReference<Result<ProductNoticeDto>>() {
				});
		if (!result.getSuccess() || result.getResult() == null) {
			throw new BaseException(CodeConstant.CODE_1300036);
		}
		Result<Boolean> checkResult = permissionService.checkUpdate(DataObjectEnum.NOTICE.getCode(),
				result.getResult().getCreateUserId());
		if (!checkResult.getSuccess() || !checkResult.getResult()) {
			throw new BaseException(CodeConstant.CODE_1300036);
		}

		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("status", NoticeStatusEnum.STATUS_2.getStatus());

		return HttpUtils.post(TenantUrl.PRODUCT_NOTICE_FLOW, params, new BaseTypeReference<Result<Integer>>() {
		});

	}

	@Override
	public Result<SearchView> searchByKeyword(String keyword) throws BaseException {

		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("title", keyword);
		methodParams.put("currentPage", 1);
		methodParams.put("pageSize", Integer.MAX_VALUE);
		// 获取用户在各租户中详情信息
		Result<PaginationSupport<ProductNoticeModel>> result = HttpUtils.get(TenantUrl.PRODUCT_NOTICE_GETS,
				methodParams, new BaseTypeReference<Result<PaginationSupport<ProductNoticeModel>>>() {
				});
		if (result == null || result.getResult() == null) {
			return Result.newFailureResult();
		}
		List<String> names = new ArrayList<>();
		PaginationSupport<ProductNoticeModel> paSupport = result.getResult();
		if (paSupport != null) {
			List<ProductNoticeModel> dtos = paSupport.getDatas();
			if (dtos != null && !dtos.isEmpty()) {
				for (ProductNoticeModel dto : dtos) {
					names.add(dto.getTitle());
				}
			}
		}
		return Result.newSuccessResult(new SearchView(names));

	}

	@Override
	public Result<ItemListDTO> getPage(ProductNoticeQueryReqDto pageReqDto) throws BaseException {

		Result<PaginationSupport<ProductNoticeModel>> result = HttpUtils.get(TenantUrl.PRODUCT_NOTICE_GETS,
				ArgsUtils.toMap(pageReqDto), new BaseTypeReference<Result<PaginationSupport<ProductNoticeModel>>>() {
				});
		ItemListDTO itemListDTO = new ItemListDTO();

		if (result.getSuccess() && result.getResult() != null) {
			PaginationSupport<ProductNoticeModel> pager = result.getResult();
			itemListDTO.setPaginaton(pager);

			List<ProductNoticeModel> list = pager.getDatas();
			if (!BooleanUtils.isEmpty(list)) {
				List<ViewItem> viewItems = new ArrayList<>();
				List<Long> ownerIdList = new ArrayList<>();
				for (ProductNoticeModel noticeModel : list) {
					ownerIdList.add(noticeModel.getCreateUserId());
				}
				PermissionListResDto permissionListResDto = permissionService
						.checkUpdateListSingleObj(DataObjectEnum.NOTICE.getCode(), ownerIdList);
				List<PermissionDetailResDto> permissions = permissionListResDto.getPermissions();// 获取各数据所对应的数据修改权限

				Integer permission = permissionService.getUpdate(DataObjectEnum.NOTICE.getCode()).getResult();// 获取用户数据修改权限

				for (ProductNoticeModel noticeModel : list) {

					Structure1014View view1014 = new Structure1014View();
					view1014.setNoticeName(noticeModel.getTitle());
					view1014.setProductName(noticeModel.getProductName());
					view1014.setNoticeId(String.valueOf(noticeModel.getId()));
					view1014.setNoticeTypeText(noticeModel.getNoticeTypeName());
					view1014.setCreator(noticeModel.getCreateUserRealName());
					view1014.setCreateTime(noticeModel.getCreateDate());

					if (noticeModel.getBaseProductNoticeAttach() == null
							|| noticeModel.getBaseProductNoticeAttach().size() == 0) {
						view1014.setAttachNum("");
					} else {
						view1014.setAttachNum((String.valueOf(noticeModel.getBaseProductNoticeAttach().size())) + "附件");
					}

					StringBuilder stateContent = new StringBuilder();
					if (noticeModel.getIsSend() == NumberEnum.ONE.getValue()) {
						stateContent.append("已发布");
					} else if (noticeModel.getIsSend() == NumberEnum.ZERO.getValue()) {
						stateContent.append("未发布");
						if (noticeModel.getStatus() != null
								&& noticeModel.getStatus() != NoticeStatusEnum.STATUS_1.getStatus()) {
							stateContent.append(
									" " + NoticeStatusEnum.getNoticeStatusEnum(noticeModel.getStatus()).getName());
						}
					}
					view1014.setStateContent(stateContent.toString());
					StringBuilder content = new StringBuilder();

					List<OperateView> operateList = new ArrayList<>();

					if (noticeModel.getStatus() == null
							|| noticeModel.getStatus().equals(NoticeStatusEnum.STATUS_1.getStatus())) {
						OperateView operateView = new OperateView();
						operateView.setType(1);
						operateView.setText("申请发布");
						if (permissions != null && !permissions.isEmpty()) {
							PermissionDetailResDto detail = LambdaFilter.firstOrDefault(permissions,
									m -> noticeModel.getCreateUserId().equals(m.getOwnerId()));
							if (detail != null && detail.getIsPass()) {
								operateView.setEnable(true);
							} else {
								operateView.setEnable(false);// 默认没有权限，是否有权限受产品公告数据权限控制
								if (DataObjectPermissionEnum.NONE.getValue() == permission) {
									operateView.setTip("您无操作公告的权限");
								} else if (DataObjectPermissionEnum.PERSONAL.getValue() == permission) {
									operateView.setTip("您的权限仅限于操作您创建的公告");
								} else if (DataObjectPermissionEnum.DEPARTMENT.getValue() == permission) {
									operateView.setTip("您的权限仅限于操作您部门员工创建的公告");
								}
							}
						} else {
							operateView.setEnable(false);// 默认没有权限，是否有权限受产品公告数据权限控制
							operateView.setTip("您无操作公告的权限");
						}
						operateList.add(operateView);
					} else if (NoticeStatusEnum.STATUS_3.getStatus().equals(noticeModel.getStatus())) {
						if (noticeModel.getIsSend() == NumberEnum.ONE.getValue()) {
							content.append("发布日期：");
							if (noticeModel.getSendTime() != null) {
								content.append(DateFormatUtils.format(noticeModel.getSendTime(), "yyyy-MM-dd"));
							}
						} else if (noticeModel.getIsSend() == NumberEnum.ZERO.getValue()) {
							content.append("预计发布时间：");
							if (noticeModel.getSendTime() != null) {
								content.append(DateFormatUtils.format(noticeModel.getSendTime(), "yyyy-MM-dd HH:mm"));
							}
						}
					} else if (noticeModel.getStatus().equals(NoticeStatusEnum.STATUS_2.getStatus())) {
						OperateView cancel = new OperateView();
						cancel.setType(2);
						cancel.setText("取消申请");
						if (noticeModel.getApplyUserId() != null
								&& noticeModel.getApplyUserId() == UserUtils.getUserId()) {
							cancel.setEnable(true);// 只有流程发起人才能取消流程
						} else {
							cancel.setEnable(false);
							cancel.setTip("审批进行中，仅限申请发起人可执行此操作");
						}
						operateList.add(cancel);
					} else if (noticeModel.getStatus().equals(NoticeStatusEnum.STATUS_4.getStatus())) {
						OperateView resubmit = new OperateView();
						resubmit.setType(3);
						resubmit.setText("重新申请");
						OperateView cancel = new OperateView();
						cancel.setType(2);
						cancel.setText("取消申请");
						if (noticeModel.getApplyUserId() != null
								&& noticeModel.getApplyUserId() == UserUtils.getUserId()) {
							resubmit.setEnable(true);// 只有流程发起人才能取消或重新提交流程
							cancel.setEnable(true);
						} else {
							resubmit.setEnable(false);
							resubmit.setTip("审批进行中，仅限申请发起人可执行此操作");
							cancel.setEnable(false);
							cancel.setTip("审批进行中，仅限申请发起人可执行此操作");
						}
						operateList.add(resubmit);
						operateList.add(cancel);

					}
					if (noticeModel.getStatus().equals(NoticeStatusEnum.STATUS_4.getStatus())
							|| noticeModel.getStatus().equals(NoticeStatusEnum.STATUS_5.getStatus())) {
						view1014.setReason(noticeModel.getReason());// 取消或驳回的原因
					}
					view1014.setContent(content.toString());
					view1014.setOperateList(operateList);

					String jumpUrl = Schema.PRODUCT_NOTICE_DETAIL
							+ String.format(Schema.PRODUCT_NOTICE_DETAIL_PARAMS, (noticeModel.getId() + ""), "");
					viewItems.add(new ViewItem(1014, jumpUrl, view1014));
				}

				itemListDTO.setViewItems(viewItems);
			}
		}

		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<ProductNoticeView> get(Long id) throws Exception {

		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("id", id);
		// 获取公告详情信息
		Result<ProductNoticeDto> result = HttpUtils.get(TenantUrl.PRODUCT_NOTICE_GET, methodParams,
				new BaseTypeReference<Result<ProductNoticeDto>>() {
				});
		ProductNoticeView view = new ProductNoticeView();

		if (result != null && result.getResult() != null) {
			ProductNoticeDto dto = result.getResult();
			if (dto != null) {
				// 设置分享信息
				ShareInfo shareInfo = new ShareInfo();
				shareInfo.setTitle(dto.getTitle());
				shareInfo.setContent(dto.getContent());
				shareInfo.setImgUrl(ConfigUtils.getValue("product.notice.share.url"));
				String url = String.format(H5Constant.PRODUCT_NOTICE_SHARE, shareService.getCCShareDomainUrl(),
						dto.getId());
				shareInfo.setUrl(url);
				view.setShareInfo(shareInfo);

				view.setProductDetailAction(new ActionDTO(Schema.PRODUCT_DETAIL
						+ String.format(Schema.PRODUCT_DETAIL_PARAMS, dto.getProductId(), dto.getProductName())));
				view.setCreateDate(DateFormatUtils.format(dto.getCreateDate(), "yyyy-MM-dd"));
				view.setContent(dto.getContent());
				view.setNoticeType(dto.getNoticeTypeName());
				view.setTitle("[" + dto.getNoticeTypeName() + "]" + dto.getTitle());
				List<AttachmentView> attachView = new ArrayList<>();
				List<BaseProductNoticeAttach> attachs = dto.getBaseProductNoticeAttach();
				if (attachs != null) {
					for (BaseProductNoticeAttach attach : attachs) {
						String fileFormat = FileSupport.getFileFormat(attach.getUrl());
						if (StringUtils.isBlank(fileFormat)) {
							logger.info("产品附件，ID为：%s，文件格式为：%s,手机端不支持。。。", attach.getId(), fileFormat);
							continue;
						}
						attachView.add(new AttachmentView(attach.getUrl(), fileFormat, attach.getSourceName()));
					}
					view.setAttachList(attachView);
				}
			}
		}
		return Result.newSuccessResult(view);
	}

}
