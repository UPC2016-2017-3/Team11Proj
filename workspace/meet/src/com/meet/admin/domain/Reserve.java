package com.meet.admin.domain;

import java.util.Date;

import com.meet.common.domain.BaseDomain;
import com.meet.common.util.DateUtil;

/**
 * Reserve entity. @author MyEclipse Persistence Tools
 */

public class Reserve extends BaseDomain {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1871309788915594475L;
	private Integer reserveId;
	private Room room;
	private Integer userId;
	private String realName;
	private String userDept;
	private Date reserveDate1;
	private Date reserveDate2;
	private Double reserveLength;
	private String meetContent;
	private Integer reserveEquip;//1-否 2-是
	private Integer reserveFlag;//1:待审核 2:审核通过 3:审核驳回
	
	private Date reserveDateMin;
	private Date reserveDateMax;
	private String roomName;
	private int reserveCount;
	private String reserveFlags;
	
	private String ids;

	public String getReserveEquipDesc() {
		switch (reserveEquip) {
		case 1:
			return "否";
		case 2:
			return "是"; 
		default:
			return "";
		}
	}
	
	public String getReserveFlagDesc() {
		switch (reserveFlag) {
		case 1:
			return "待审核";
		case 2:
			return "审核通过"; 
		case 3:
			return "审核驳回"; 
		default:
			return "";
		}
	}
	
	public String getReserveDate1Desc(){
		if (reserveDate1!=null) {
			return DateUtil.dateToDateString(reserveDate1);
		}else {
			return null;
		}
	}
	
	public String getReserveDate2Desc(){
		if (reserveDate2!=null) {
			return DateUtil.dateToDateString(reserveDate2);
		}else {
			return null;
		}
	}
	
	public String getReserveDateMinDesc(){
		if (reserveDateMin!=null) {
			return DateUtil.dateToDateString(reserveDateMin);
		}else {
			return null;
		}
	}
	
	public String getReserveDateMaxDesc(){
		if (reserveDateMax!=null) {
			return DateUtil.dateToDateString(reserveDateMax);
		}else {
			return null;
		}
	}
	
	public double getReserveLengthH() {
		return Math.round(reserveLength*100/3600.0)/100.0;
	}
	
	// Constructors

	/** default constructor */
	public Reserve() {
	}

	/** full constructor */
	public Reserve(Room room, Integer userId, String realName, String userDept,
			Date reserveDate1,Date reserveDate2,Double reserveLength, Integer reserveAp, String meetContent,
			Integer reserveEquip) {
		this.room = room;
		this.userId = userId;
		this.realName = realName;
		this.userDept = userDept;
		this.reserveDate1 = reserveDate1;
		this.reserveDate2 = reserveDate2;
		this.reserveLength = reserveLength;
		this.meetContent = meetContent;
		this.reserveEquip = reserveEquip;
	}

	// Property accessors

	public Integer getReserveId() {
		return this.reserveId;
	}

	public void setReserveId(Integer reserveId) {
		this.reserveId = reserveId;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserDept() {
		return this.userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public String getMeetContent() {
		return this.meetContent;
	}

	public void setMeetContent(String meetContent) {
		this.meetContent = meetContent;
	}

	public Integer getReserveEquip() {
		return this.reserveEquip;
	}

	public void setReserveEquip(Integer reserveEquip) {
		this.reserveEquip = reserveEquip;
	}

	public Date getReserveDateMin() {
		return reserveDateMin;
	}

	public void setReserveDateMin(Date reserveDateMin) {
		this.reserveDateMin = reserveDateMin;
	}

	public Date getReserveDateMax() {
		return reserveDateMax;
	}

	public void setReserveDateMax(Date reserveDateMax) {
		this.reserveDateMax = reserveDateMax;
	}

	public int getReserveCount() {
		return reserveCount;
	}

	public void setReserveCount(int reserveCount) {
		this.reserveCount = reserveCount;
	}

	public Double getReserveLength() {
		return reserveLength;
	}

	public Date getReserveDate1() {
		return reserveDate1;
	}

	public void setReserveDate1(Date reserveDate1) {
		this.reserveDate1 = reserveDate1;
	}

	public Date getReserveDate2() {
		return reserveDate2;
	}

	public void setReserveDate2(Date reserveDate2) {
		this.reserveDate2 = reserveDate2;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public void setReserveLength(Double reserveLength) {
		this.reserveLength = reserveLength;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getReserveFlag() {
		return reserveFlag;
	}

	public void setReserveFlag(Integer reserveFlag) {
		this.reserveFlag = reserveFlag;
	}

	public String getReserveFlags() {
		return reserveFlags;
	}

	public void setReserveFlags(String reserveFlags) {
		this.reserveFlags = reserveFlags;
	}

}