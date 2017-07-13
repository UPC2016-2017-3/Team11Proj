package com.meet.admin.domain;

import java.util.Date;

import com.meet.common.domain.BaseDomain;
import com.meet.common.util.DateUtil;
import com.meet.common.util.StringUtil;
import com.meet.common.util.Transcode;

public class Info extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -674161960515333295L;
	private Integer info_id; // 
	private String info_title; // 
	private String info_content; //
	private String info_admin; // 
	private Date info_date; // 
	
	private String random; // 
	private String ids; // 
	
	public String getInfo_dateDesc(){
		if (info_date!=null) {
			return DateUtil.dateToDateString(info_date);
		}else {
			return null;
		}
	}

	public void setInfo_id(Integer info_id){
		this.info_id=info_id;
	}

	public Integer getInfo_id(){
		return info_id;
	}

	public void setInfo_title(String info_title){
		this.info_title=info_title;
	}

	public String getInfo_title(){
		return info_title;
	}

	public void setInfo_content(String info_content){
		this.info_content=info_content;
	}

	public String getInfo_contentShow(){
		if (!StringUtil.isEmptyString(info_content)) {
			return Transcode.htmlDiscode(info_content);
		}
		return info_content;
	}
	
	public String getInfo_content(){
		return info_content;
	}

	public void setInfo_date(Date info_date){
		this.info_date=info_date;
	}

	public Date getInfo_date(){
		return info_date;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getInfo_admin() {
		return info_admin;
	}

	public void setInfo_admin(String info_admin) {
		this.info_admin = info_admin;
	}

}
