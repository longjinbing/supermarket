package com.ljb.viewmodel;


public class MenuModel {
	private Long menuId;
	
	private String section;
	
	private String select;
	
	private String text;
	
	public MenuModel() {
		
	}
	
	public MenuModel(Long menuId,String section, String select, String text) {
		this.menuId=menuId;
		this.section=section;
		this.select=select;
		this.text=text;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
