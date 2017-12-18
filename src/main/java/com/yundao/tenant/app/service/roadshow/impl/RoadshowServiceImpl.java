
package com.yundao.tenant.app.service.roadshow.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.service.AbstractService;
import com.yundao.core.utils.ConfigUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.schema.H5Constant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ShareInfo;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.roadshow.BaseRoadshowColumn;
import com.yundao.tenant.app.dto.roadshow.BaseVideoTranscode;
import com.yundao.tenant.app.dto.roadshow.RoadshowDetailResDto;
import com.yundao.tenant.app.dto.roadshow.RoadshowListReqDto;
import com.yundao.tenant.app.dto.roadshow.RoadshowReqDto;
import com.yundao.tenant.app.dto.roadshow.RoadshowResDto;
import com.yundao.tenant.app.dto.roadshow.VideoDetailResDto;
import com.yundao.tenant.app.enums.platform.PlatformEnum;
import com.yundao.tenant.app.enums.roadshow.VideoTranscodeDefinitionEnum;
import com.yundao.tenant.app.enums.user.EnabledEnum;
import com.yundao.tenant.app.service.roadshow.RoadshowService;
import com.yundao.tenant.app.service.share.ShareService;
import com.yundao.tenant.app.service.user.detail.UserDetailService;
import com.yundao.tenant.app.util.AESUtils;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.CalendarUtils;
import com.yundao.tenant.app.util.DateFormatUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.UserUtils;
import com.yundao.tenant.app.util.VideoDurationUtils;
import com.yundao.tenant.app.view.DividingLineView;
import com.yundao.tenant.app.view.NameIdView;
import com.yundao.tenant.app.view.NameValueView;
import com.yundao.tenant.app.view.TitleView;
import com.yundao.tenant.app.view.product.Structure2002View;
import com.yundao.tenant.app.view.roadshow.RoadshowDetailView;
import com.yundao.tenant.app.view.roadshow.Structure6001View;
import com.yundao.tenant.app.view.roadshow.Structure6003View;
import com.yundao.tenant.app.view.roadshow.Structure6004View;
import com.yundao.tenant.app.view.roadshow.Structure6005View;
import com.yundao.tenant.app.view.roadshow.Structure6006View;
import com.yundao.tenant.app.view.roadshow.UrlDefinitionDto;
import com.yundao.tenant.app.view.roadshow.VideoAudioDto;
import com.yundao.tenant.app.view.roadshow.VideoAudioPDFDto;
import com.yundao.tenant.app.view.roadshow.VideoContentDto;
import com.yundao.tenant.app.view.roadshow.VideoInfoView;
import com.yundao.tenant.app.view.roadshow.VideoPDFDto;

/**
 * Function: Reason: Date: 2017年11月9日 下午6:03:51
 * 
 * @author wucq
 * @version
 */
@Service
public class RoadshowServiceImpl extends AbstractService implements RoadshowService {
	private Log logger = LogFactory.getLog(RoadshowServiceImpl.class);
	@Autowired
	private ShareService shareService;
	@Autowired
	private UserDetailService userDetailService;

	@Override
	public Result<RoadshowDetailView> get(String id) throws BaseException {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		Result<RoadshowDetailResDto> result = HttpUtils.get(TenantUrl.ROADSHOW_GET, params,
				new BaseTypeReference<Result<RoadshowDetailResDto>>() {
				});
		RoadshowDetailView view = new RoadshowDetailView();
		if (result.getResult() != null) {
			RoadshowDetailResDto dto = result.getResult();
			VideoDetailResDto videoDetailResDto = dto.getVideo();

			view.setRoadshowId(String.valueOf(dto.getId()));

			// 目前只支持视频格式
			String contentType = "VIDEO";
			view.setContentType(contentType);
			// 根据格式设置显示内容
			switch (contentType) {
			case "VIDEO": {// 视频
				VideoContentDto content = new VideoContentDto();
				if (videoDetailResDto != null) {
					List<BaseVideoTranscode> transcodes = videoDetailResDto.getBaseVideoTranscodes();
					if (transcodes != null && !transcodes.isEmpty()) {
						List<UrlDefinitionDto> videoList = new ArrayList<>();
						for (BaseVideoTranscode transcode : transcodes) {
							VideoTranscodeDefinitionEnum definitionEnum = VideoTranscodeDefinitionEnum
									.getEnum(transcode.getDefinition());
							if (definitionEnum == null) {
								continue;
							}

							try {
								videoList.add(new UrlDefinitionDto(AESUtils.encrypt(transcode.getUrl()),
										definitionEnum.getName()));
							} catch (Exception e) {
								logger.error("视频url进行AES加密出现异常，异常信息：", e);
							}
						}
						content.setVideoList(videoList);
					}
				}
				view.setContent(content);
				break;
			}
			case "ONLY_PDF": {// 只有PDF
				VideoPDFDto videoPDFDto = new VideoPDFDto();
				view.setContent(videoPDFDto);
				break;
			}
			case "ONLY_AUDIO": {// 只有音频
				VideoAudioDto videoAudioDto = new VideoAudioDto();
				view.setContent(videoAudioDto);
				break;
			}
			case "PDF_AUDIO": {// PDF+音频
				VideoAudioPDFDto videoAudioPDFDto = new VideoAudioPDFDto();
				view.setContent(videoAudioPDFDto);
				break;
			}

			}
			if (videoDetailResDto != null) {
				List<BaseVideoTranscode> transcodes = videoDetailResDto.getBaseVideoTranscodes();
				String standardUrl = "";// 标清url
				String standardMd5 = "";// 标清md5
				String highUrl = "";// 高清url
				String highMd5 = "";// 高清md5
				if (transcodes != null && !transcodes.isEmpty()) {
					for (BaseVideoTranscode transcode : transcodes) {
						if (transcode.getDefinition() == VideoTranscodeDefinitionEnum.STANDARD.getValue()) {
							standardUrl = transcode.getUrl();
							standardMd5 = transcode.getMd5();
						} else if (transcode.getDefinition() == VideoTranscodeDefinitionEnum.HIGH.getValue()) {
							highUrl = transcode.getUrl();
							highMd5 = transcode.getMd5();
						}
					}
				}
				VideoInfoView videoInfoView = new VideoInfoView();
				try {
					if (StringUtils.isNotBlank(highUrl)) {
						videoInfoView.setVideoUrl(AESUtils.encrypt(highUrl));
						videoInfoView.setMd5(highMd5);
					} else if (StringUtils.isNotBlank(standardUrl)) {
						videoInfoView.setVideoUrl(AESUtils.encrypt(standardUrl));
						videoInfoView.setMd5(standardMd5);
					}
				} catch (Exception e) {
					logger.error("视频url进行AES加密出现异常，异常信息：", e);
				}

				videoInfoView.setCoverImageUrl(dto.getCoverUrl());
				videoInfoView.setTitle(dto.getTitle());
				videoInfoView.setTotalSize(videoDetailResDto.getSize());
				view.setDownloadVideo(videoInfoView);
			}

			ShareInfo shareInfo = new ShareInfo();
			shareInfo.setTitle(dto.getTitle());
			shareInfo.setContent(UserUtils.getCurrentUser().getHeaderRealName() + "邀请您观看" + dto.getTitle());
			shareInfo.setImgUrl(ConfigUtils.getValue("roadshow.share"));
			String url = String.format(H5Constant.ROADSHOW_SHARE, shareService.getCCShareDomainUrl(), dto.getId());
			shareInfo.setUrl(url);
			view.setShareInfo(shareInfo);

			List<ViewItem> viewItems = new ArrayList<>();
			Structure6006View view6006 = new Structure6006View();
			view6006.setTitle(dto.getTitle());
			view6006.setPublishDate(dto.getReleaseTime());
			viewItems.add(new ViewItem(6006, view6006));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			viewItems.add(new ViewItem(6002, new TitleView("基本信息")));
			List<NameValueView> baseInfo = new ArrayList<>();
			baseInfo.add(new NameValueView("主讲人", dto.getSpeaker()));
			baseInfo.add(new NameValueView("主办方", dto.getSponsor()));
			baseInfo.add(new NameValueView("简介", dto.getIntroduction()));
			viewItems.add(new ViewItem(6003, new Structure6003View(baseInfo)));
			viewItems.add(new ViewItem(2001, new DividingLineView()));

			viewItems.add(new ViewItem(6002, new TitleView("查看产品详情")));
			if (dto.getProductId() != null) {

				String jumpUrl = Schema.PRODUCT_DETAIL
						+ String.format(Schema.PRODUCT_DETAIL_PARAMS, dto.getProductId(), dto.getProductName());
				viewItems.add(new ViewItem(6004, jumpUrl, new Structure6004View(dto.getProductName())));
				viewItems.add(new ViewItem(2001, new DividingLineView()));

				viewItems.add(new ViewItem(6002, new TitleView("简版介绍")));
				viewItems.add(new ViewItem(6005, new Structure6005View(dto.getWxContent())));
			} else {
				viewItems.add(new ViewItem(2002, new Structure2002View("暂无相关产品")));
			}

			view.setViewItems(viewItems);

		}
		return Result.newSuccessResult(view);
	}

	@Override
	public Result<ItemListDTO> getPage(RoadshowListReqDto dto) throws BaseException {
		if (dto.getColumnId() == null) {
			throw new BaseException(CodeConstant.CODE_1300040);
		}

		RoadshowReqDto reqDto = new RoadshowReqDto();
		reqDto.setColumnIds(String.valueOf(dto.getColumnId()));
		reqDto.setCurrentPage(dto.getPage());
		reqDto.setPageSize(dto.getPageSize());
		reqDto.setReleaseTime(CalendarUtils.getDayEnd(new Date()));// 只查询已发布的路演信息
		reqDto.setStatuss(String.valueOf(EnabledEnum.ENABLED.getValue()));
		Result<PaginationSupport<RoadshowResDto>> result = HttpUtils.get(TenantUrl.ROADSHOW_GET_PAGE,
				ArgsUtils.toMap(reqDto), new BaseTypeReference<Result<PaginationSupport<RoadshowResDto>>>() {
				});
		ItemListDTO itemListDTO = new ItemListDTO();

		if (result.getResult() != null && result.getResult().getDatas() != null
				&& !result.getResult().getDatas().isEmpty()) {
			List<ViewItem> viewItems = new ArrayList<>();

			PaginationSupport<RoadshowResDto> pager = result.getResult();
			itemListDTO.setPaginaton(pager);
			List<RoadshowResDto> resDtos = pager.getDatas();
			for (RoadshowResDto resDto : resDtos) {
				VideoDetailResDto video = resDto.getVideo();
				Structure6001View view6001 = new Structure6001View();
				view6001.setRoadshowId(String.valueOf(resDto.getId()));
				view6001.setImageUrl(resDto.getCoverUrl());
				view6001.setTitle(resDto.getTitle());
				view6001.setPublishDate(DateFormatUtils.format(resDto.getReleaseTime(), "yyyy-MM-dd"));

				if (resDto.getProductId() != null) {
					view6001.setProductAction(new ActionDTO(Schema.PRODUCT_DETAIL + String
							.format(Schema.PRODUCT_DETAIL_PARAMS, resDto.getProductId(), resDto.getProductName())));

				}

				ShareInfo shareInfo = new ShareInfo();
				shareInfo.setTitle(resDto.getTitle());
				shareInfo.setContent(UserUtils.getCurrentUser().getHeaderRealName() + "邀请您观看" + resDto.getTitle());
				shareInfo.setImgUrl(ConfigUtils.getValue("roadshow.share"));

				String url = String.format(H5Constant.ROADSHOW_SHARE, shareService.getCCShareDomainUrl(),
						resDto.getId());
				shareInfo.setUrl(url);
				view6001.setShareInfo(shareInfo);
				if (video != null) {
					List<BaseVideoTranscode> transcodes = video.getBaseVideoTranscodes();

					String standardUrl = "";// 标清url
					String highUrl = "";// 高清url
					String standardMd5 = "";
					String highMd5 = "";
					if (transcodes != null && !transcodes.isEmpty()) {
						for (BaseVideoTranscode transcode : transcodes) {
							if (transcode.getDefinition() == VideoTranscodeDefinitionEnum.STANDARD.getValue()) {
								standardUrl = transcode.getUrl();
								standardMd5 = transcode.getMd5();
							} else if (transcode.getDefinition() == VideoTranscodeDefinitionEnum.HIGH.getValue()) {
								highUrl = transcode.getUrl();
								highMd5 = transcode.getMd5();
							}
						}
					}

					view6001.setDuration(
							VideoDurationUtils.changeToText(video.getDuration() == null ? 0 : video.getDuration()));

					VideoInfoView videoInfoView = new VideoInfoView();
					try {
						if (StringUtils.isNotBlank(highUrl)) {
							videoInfoView.setVideoUrl(AESUtils.encrypt(highUrl));
							videoInfoView.setMd5(highMd5);
						} else if (StringUtils.isNotBlank(standardUrl)) {
							videoInfoView.setVideoUrl(AESUtils.encrypt(standardUrl));
							videoInfoView.setMd5(standardMd5);
						}
					} catch (Exception e) {
						logger.error("视频url进行AES加密出现异常，异常信息：", e);
					}

					videoInfoView.setCoverImageUrl(resDto.getCoverUrl());
					videoInfoView.setTitle(resDto.getTitle());
					videoInfoView.setTotalSize(video.getSize());
					view6001.setDownloadVideo(videoInfoView);
				}
				String jumpUrl = Schema.ROADSHOW_DETAIL + String.format(Schema.ROADSHOW_DETAIL_PARAMS, resDto.getId());
				viewItems.add(new ViewItem(6001, jumpUrl, view6001));
			}
			itemListDTO.setViewItems(viewItems);
		}
		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<Map<String, Object>> getColumn() throws BaseException {
		Map<String, Object> params = new HashMap<>();
		params.put("code", PlatformEnum.APP_TO_B.getCode());
		Result<List<BaseRoadshowColumn>> result = HttpUtils.get(TenantUrl.ROADSHOW_COLUMN_GETS, params,
				new BaseTypeReference<Result<List<BaseRoadshowColumn>>>() {
				});
		if (result.getResult() == null || result.getResult().isEmpty()) {
			return Result.newSuccessResult(null);
		}
		List<BaseRoadshowColumn> columns = result.getResult();
		List<NameIdView> columnList = new ArrayList<>();
		for (BaseRoadshowColumn column : columns) {
			columnList.add(new NameIdView(column.getName(), String.valueOf(column.getId())));
		}
		Map<String, Object> reMap = new HashMap<>();
		reMap.put("columnList", columnList);
		return Result.newSuccessResult(reMap);
	}

}
