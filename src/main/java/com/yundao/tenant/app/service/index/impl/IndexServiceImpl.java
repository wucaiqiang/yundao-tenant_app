
package com.yundao.tenant.app.service.index.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.enums.NumberEnum;
import com.yundao.core.exception.BaseException;
import com.yundao.core.service.AbstractService;
import com.yundao.core.utils.ConfigUtils;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.BeginEndDateDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.msg.MsgMessagePageResDto;
import com.yundao.tenant.app.dto.msg.MsgMessageQueryReqDto;
import com.yundao.tenant.app.dto.report.AfpReportDto;
import com.yundao.tenant.app.enums.msg.message.MsgMessageFirstTypeEnum;
import com.yundao.tenant.app.enums.report.ReportTimeTypeEnum;
import com.yundao.tenant.app.service.index.IndexService;
import com.yundao.tenant.app.service.msg.MsgMessageService;
import com.yundao.tenant.app.service.product.ProductService;
import com.yundao.tenant.app.service.report.ReportService;
import com.yundao.tenant.app.util.CalendarUtils;
import com.yundao.tenant.app.util.DateFormatUtils;
import com.yundao.tenant.app.view.DividingLineView;
import com.yundao.tenant.app.view.NameIdView;
import com.yundao.tenant.app.view.NameTextView;
import com.yundao.tenant.app.view.TitleDateView;
import com.yundao.tenant.app.view.TitleImageView;
import com.yundao.tenant.app.view.index.NoticMessageView;
import com.yundao.tenant.app.view.index.TaskMessageView;
import com.yundao.tenant.app.view.msg.MsgMessageView;
import com.yundao.tenant.app.view.product.Structure2002View;
import com.yundao.tenant.app.view.report.ReportView;
import com.yundao.tenant.app.view.report.Structure1View;

/**
 * Function: Reason: Date: 2017年9月8日 下午5:00:58
 * 
 * @author wucq
 * @version
 */
@Service
public class IndexServiceImpl extends AbstractService implements IndexService {
	@Autowired
	private MsgMessageService msgMessageService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private ProductService productService;
	private String defaultTimeType = "CUR_MONTH";// 默认本月
	private Integer defaultLimit = 3;// 业绩默认值
	private Integer defaultNoticeLIMIT = 2;// 通知默认值

	@Override
	public Result<Structure1View> getByTimeType(String timeType) throws BaseException {
		List<ReportView> achievementList = new ArrayList<>();// 保存预约及报单报表数据
		BeginEndDateDto dateDto=new BeginEndDateDto();
		setBeginAndEndTime(dateDto, timeType);// 设置开始时间与结束时间
		// 组装预约报表数据
		addReservationView(achievementList, dateDto.getBeginDate(), dateDto.getEndDate());
		// 组装报单报表数据
		addDeclarationView(achievementList, dateDto.getBeginDate(), dateDto.getEndDate());
		
		return Result.newSuccessResult(new Structure1View(achievementList, null));
	}

	@Override
	public Result<ItemListDTO> index() throws BaseException {
		ItemListDTO itemListDTO = new ItemListDTO();
		List<ViewItem> viewItems = new ArrayList<>();
		itemListDTO.setViewItems(viewItems);

		// 业绩
		List<NameIdView> timeTypeList = new ArrayList<>();
		timeTypeList
				.add(new NameIdView(ReportTimeTypeEnum.CUR_MONTH.getDesc(), ReportTimeTypeEnum.CUR_MONTH.getType()));
		timeTypeList
				.add(new NameIdView(ReportTimeTypeEnum.LAST_MONTH.getDesc(), ReportTimeTypeEnum.LAST_MONTH.getType()));
		timeTypeList.add(new NameIdView(ReportTimeTypeEnum.CUR_WEEK.getDesc(), ReportTimeTypeEnum.CUR_WEEK.getType()));
		timeTypeList
				.add(new NameIdView(ReportTimeTypeEnum.LAST_WEEK.getDesc(), ReportTimeTypeEnum.LAST_WEEK.getType()));

		List<ReportView> achievementList = new ArrayList<>();// 保存预约及报单报表数据

		BeginEndDateDto dateDto=new BeginEndDateDto();
		setBeginAndEndTime(dateDto, defaultTimeType);// 设置开始时间与结束时间
		// 组装预约报表数据
		addReservationView(achievementList, dateDto.getBeginDate(), dateDto.getEndDate());
		// 组装报单报表数据
		addDeclarationView(achievementList, dateDto.getBeginDate(), dateDto.getEndDate());

		viewItems.add(new ViewItem(1, new Structure1View(achievementList, timeTypeList)));

		// 通知
		NoticMessageView noticView = new NoticMessageView();
		noticView.setName("通知");
		List<TitleDateView> noticList = new ArrayList<>();
		noticView.setNoticeList(noticList);
		Date today = new Date();
		MsgMessageQueryReqDto noticeDto = new MsgMessageQueryReqDto();
		noticeDto.setBeginDate(CalendarUtils.getDayBegin(today));
		noticeDto.setEndDate(CalendarUtils.getDayEnd(today));
		noticeDto.setType("notice");
		noticeDto.setPageSize(defaultNoticeLIMIT);
		Result<List<MsgMessagePageResDto>> messageList = msgMessageService.getListByLimit(noticeDto);
		if (messageList.getResult() != null && !messageList.getResult().isEmpty()) {
			List<MsgMessagePageResDto> messages = messageList.getResult();
			for (MsgMessagePageResDto message : messages) {
				noticList.add(new TitleDateView(message.getAppContent(),
						DateFormatUtils.format(message.getCreateDate(), "MM-dd")));
			}
		}
		viewItems.add(new ViewItem(2, noticView));

		viewItems.add(new ViewItem(2001, new DividingLineView()));
		// 今日待办
		TaskMessageView taskMessageView = new TaskMessageView();
		List<MsgMessageView> pendingList = new ArrayList<>();
		taskMessageView.setPendingList(pendingList);
		Result<Integer> countResult = msgMessageService.getTodoCount();
		if (countResult != null && countResult.getResult() != null) {
			taskMessageView.setPendingCount(countResult.getResult());
		}
		MsgMessageQueryReqDto taskDto = new MsgMessageQueryReqDto();
		taskDto.setBeginDate(CalendarUtils.getDayBegin(today));
		taskDto.setEndDate(CalendarUtils.getDayEnd(today));
		taskDto.setType("pending");
		taskDto.setPageSize(defaultNoticeLIMIT);
		Result<List<MsgMessagePageResDto>> taskList = msgMessageService.getListByLimit(taskDto);
		if (taskList.getResult() != null && !taskList.getResult().isEmpty()) {
			List<MsgMessagePageResDto> messages = taskList.getResult();
			for (MsgMessagePageResDto message : messages) {
				MsgMessageView messageView = new MsgMessageView();
				messageView.setTitle(message.getTitle());
				messageView.setContent(message.getIndexContent());
				messageView.setMessageId(String.valueOf(message.getId()));
				messageView.setReaded(message.getReadStatus() == 2 ? true : false);
				if (message.getCreateDate() != null) {
					messageView.setTimeText(DateFormatUtils.format(message.getCreateDate(), "HH:mm"));
				}
				messageView.setAction(new ActionDTO(message.getAppAction()));
				pendingList.add(messageView);
			}
		}
		viewItems.add(new ViewItem(3, taskMessageView));
		viewItems.add(new ViewItem(2001, new DividingLineView()));
		// 推荐产品
		viewItems.add(new ViewItem(2003, new TitleImageView("推荐产品", ConfigUtils.getValue("product.recommond.url"))));
		List<ViewItem> recommendedViews = productService.getRecommendedList(3);
		if(recommendedViews==null || recommendedViews.isEmpty()){
			viewItems.add(new ViewItem(2002, new Structure2002View("暂无推荐产品")));
		}else{
			viewItems.addAll(recommendedViews);
		}
		
		
		return Result.newSuccessResult(itemListDTO);

	}

	private void addDeclarationView(List<ReportView> achievementList, Date beginDate, Date endDate)
			throws BaseException {
		Result<Double> declarationTotal = reportService.getDeclarationTotal(beginDate, endDate);
		Result<List<AfpReportDto>> declarationRanks = reportService.getDeclarationRank(beginDate, endDate,
				defaultLimit);
		ReportView declaration = new ReportView();
		declaration.setTitle("报单总额");
		if (declarationTotal.getResult() != null) {
			Double money = declarationTotal.getResult();
			declaration.setTotalAmount((money == null || money == 0) ? 0 + "" : money.intValue() + "万");
		}
		if (declarationRanks.getResult() != null && !declarationRanks.getResult().isEmpty()) {
			List<AfpReportDto> afpReportDtos = declarationRanks.getResult();
			for (int i = 0; i < afpReportDtos.size(); i++) {
				AfpReportDto dto = afpReportDtos.get(i);
				if (0 == i) {
					// 第一名
					declaration.setFirst(new NameTextView(dto.getUserName(),
							dto.getMoney() == null ? 0 + "" : dto.getMoney().intValue() + "万"));
				} else if (1 == i) {
					// 第二名
					declaration.setSecond(new NameTextView(dto.getUserName(),
							dto.getMoney() == null ? 0 + "" : dto.getMoney().intValue() + "万"));
				} else if (2 == i) {
					// 第三名
					declaration.setThird(new NameTextView(dto.getUserName(),
							dto.getMoney() == null ? 0 + "" : dto.getMoney().intValue() + "万"));
				}
			}
		}
		achievementList.add(declaration);
	}

	private void addReservationView(List<ReportView> achievementList, Date beginDate, Date endDate)
			throws BaseException {
		Result<Double> reservationTotal = reportService.getReservationTotal(beginDate, endDate);
		Result<List<AfpReportDto>> reservationRanks = reportService.getReservationRank(beginDate, endDate,
				defaultLimit);
		ReportView reservation = new ReportView();
		reservation.setTitle("预约总额");
		if (reservationTotal.getResult() != null) {
			Double money = reservationTotal.getResult();
			reservation.setTotalAmount((money == null || money == 0) ? 0 + "" : money.intValue() + "万");
		}
		if (reservationRanks.getResult() != null && !reservationRanks.getResult().isEmpty()) {
			List<AfpReportDto> afpReportDtos = reservationRanks.getResult();
			for (int i = 0; i < afpReportDtos.size(); i++) {
				AfpReportDto dto = afpReportDtos.get(i);
				if (0 == i) {
					// 第一名
					reservation.setFirst(new NameTextView(dto.getUserName(),
							dto.getMoney() == null ? 0 + "" : dto.getMoney().intValue() + "万"));
				} else if (1 == i) {
					// 第二名
					reservation.setSecond(new NameTextView(dto.getUserName(),
							dto.getMoney() == null ? 0 + "" : dto.getMoney().intValue() + "万"));
				} else if (2 == i) {
					// 第三名
					reservation.setThird(new NameTextView(dto.getUserName(),
							dto.getMoney() == null ? 0 + "" : dto.getMoney().intValue() + "万"));
				}
			}
		}
		achievementList.add(reservation);

	}

	private void setBeginAndEndTime(BeginEndDateDto dateDto, String timeType) throws BaseException {
		Date today = new Date();
		Date beginDate=new Date();
		Date endDate=new Date();
		if (ReportTimeTypeEnum.LAST_MONTH.getType().equals(timeType)) {
			// 上个月
			beginDate = CalendarUtils.getDayBegin(today, NumberEnum.TWO.getValue(), -1);
			endDate = CalendarUtils.getDayEnd(today, NumberEnum.TWO.getValue(), -1);// 上个月的结束时间
		} else if (ReportTimeTypeEnum.LAST_WEEK.getType().equals(timeType)) {
			// 上周
			beginDate = CalendarUtils.getDayBegin(today, NumberEnum.ONE.getValue(), -1);// 上一周的开始时间
			endDate = CalendarUtils.getDayEnd(today, NumberEnum.ONE.getValue(), -1);// 上一周的结束时间
		} else if (ReportTimeTypeEnum.CUR_WEEK.getType().equals(timeType)) {
			// 本周
			beginDate = CalendarUtils.getDayBegin(today, NumberEnum.ONE.getValue(), 0);// 本周的开始时间
			endDate = today;// 当前时间
		} else {
			// 默认本月
			beginDate = CalendarUtils.getDayBegin(today, NumberEnum.TWO.getValue(), 0);// 本月的开始时间
			endDate = today;// 当前时间
		}
		dateDto.setBeginDate(beginDate);
		dateDto.setEndDate(endDate);
	}

}
