package com.meet.admin.domain;

import com.meet.common.domain.BaseDomain;

/**
 * Reserve entity. @author MyEclipse Persistence Tools
 */

public class ReserveItem extends BaseDomain {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1871309788915594475L;
	private String reserve_date;
	private int reserve_week;//0-6
	private double reserve_length;//3600*4 3600*5 3600*3
	private int reserve_sec;//1-上午 2-下午 3-晚上
	private String user_dept;// 
	private String reserve_flag;// 
	private String real_name;// 
	private String meet_content;// 
	private String reserve_time;// 
	
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

	public double getReserve_length() {
		return reserve_length;
	}

	public int getReserve_sec() {
		return reserve_sec;
	}

	public String getUser_dept() {
		return user_dept;
	}

	public String getReserve_flag() {
		return reserve_flag;
	}

	public String getReal_name() {
		return real_name;
	}

	public String getMeet_content() {
		return meet_content;
	}

	public String getReserve_time() {
		return reserve_time;
	}

	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}

	public void setReserve_week(int reserve_week) {
		this.reserve_week = reserve_week;
	}

	public void setReserve_length(double reserve_length) {
		this.reserve_length = reserve_length;
	}

	public void setReserve_sec(int reserve_sec) {
		this.reserve_sec = reserve_sec;
	}

	public void setUser_dept(String user_dept) {
		this.user_dept = user_dept;
	}

	public void setReserve_flag(String reserve_flag) {
		this.reserve_flag = reserve_flag;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public void setMeet_content(String meet_content) {
		this.meet_content = meet_content;
	}

	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}


}