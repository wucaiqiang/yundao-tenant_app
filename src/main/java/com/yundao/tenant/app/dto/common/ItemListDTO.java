package com.yundao.tenant.app.dto.common;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.yundao.core.pagination.PaginationSupport;

/**
 * 
 * date: 2017年8月1日 下午4:09:03
 * 
 * @author:wucq
 * @description:
 */
public class ItemListDTO implements Serializable {

	private static final long serialVersionUID = -1606991347539089314L;
	protected int currentPage = 1;
	protected boolean hasNext;
	protected Integer totalCount;
	private String permissionText;
	protected List<ViewItem> viewItems;

	public String getPermissionText() {
	
		return permissionText;
	}

	public void setPermissionText(String permissionText) {
	
		this.permissionText = permissionText;
	}

	public ItemListDTO(List<ViewItem> viewItems) {
		super();
		this.viewItems = viewItems;
	}

	public ItemListDTO() {

	}

	public ItemListDTO(PaginationSupport pager) {
		if (pager != null) {
			this.setCurrentPage(pager.getCurrentPage());
			this.setHasNext(pager.getCurrentPage() < pager.getTotalPage());
			this.totalCount = pager.getTotalCount();
		}
	}

	public ItemListDTO(int currentIndex, boolean hasNext, List<ViewItem> viewItems) {
		super();
		constructor(currentIndex, hasNext, viewItems);
	}

	protected void constructor(int currentIndex, boolean hasNext, List<ViewItem> viewItems) {
		this.currentPage = currentIndex;
		this.hasNext = hasNext;
		this.viewItems = viewItems;
	}

	public void setPaginaton(PaginationSupport pager) {
		if (pager != null) {
			this.setCurrentPage(pager.getCurrentPage());
			this.setHasNext(pager.getCurrentPage() < pager.getTotalPage());
			this.totalCount = pager.getTotalCount();
		}
	}

	public int getCurrentPage() {

		return currentPage;
	}

	public void setCurrentPage(int currentPage) {

		this.currentPage = currentPage;
	}

	public boolean isHasNext() {

		return hasNext;
	}

	public void setHasNext(boolean hasNext) {

		this.hasNext = hasNext;
	}

	public Integer getTotalCount() {

		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {

		this.totalCount = totalCount;
	}

	public List<ViewItem> getViewItems() {

		return viewItems;
	}

	public void setViewItems(List<ViewItem> viewItems) {

		this.viewItems = viewItems;
	}

}
