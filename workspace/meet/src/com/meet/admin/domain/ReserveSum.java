package com.meet.admin.domain;

import java.util.ArrayList;
import java.util.List;

import com.meet.common.domain.BaseDomain;

/**
 * Reserve entity. @author MyEclipse Persistence Tools
 */

public class ReserveSum extends BaseDomain {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1871309788915594475L;
	private String reserve_date;
	private int reserve_week;//0-6
	private List<ReserveItem> items1 = new ArrayList<ReserveItem>();//上午
	private List<ReserveItem> items2 = new ArrayList<ReserveItem>();//下午
	private List<ReserveItem> items3 = new ArrayList<ReserveItem>();//晚上
	private int items1_color;//1-可预约 2-黄色 3-红色 4-已过期
	private int items2_color;//1-可预约 2-黄色 3-红色 4-已过期
	private int items3_color;//1-可预约 2-黄色 3-红色 4-已过期
	
	public String getReserve_weekDesc() {
		String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		return weeks[reserve_week];
	}

	public String getReserve_date() {
		return reserve_date;
	}

	public int getReserve_week() {
		return reserve_week;
	}

	public List<ReserveItem> getItems1() {
		return items1;
	}

	public List<ReserveItem> getItems2() {
		return items2;
	}

	public List<ReserveItem> getItems3() {
		return items3;
	}

	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}

	public void setReserve_week(int reserve_week) {
		this.reserve_week = reserve_week;
	}

	public void setItems1(List<ReserveItem> items1) {
		this.items1 = items1;
	}

	public void setItems2(List<ReserveItem> items2) {
		this.items2 = items2;
	}

	public void setItems3(List<ReserveItem> items3) {
		this.items3 = items3;
	}

	public int getItems1_color() {
		return items1_color;
	}

	public int getItems2_color() {
		return items2_color;
	}

	public int getItems3_color() {
		return items3_color;
	}

	public void setItems1_color(int items1_color) {
		this.items1_color = items1_color;
	}

	public void setItems2_color(int items2_color) {
		this.items2_color = items2_color;
	}

	public void setItems3_color(int items3_color) {
		this.items3_color = items3_color;
	}
	
	 

}