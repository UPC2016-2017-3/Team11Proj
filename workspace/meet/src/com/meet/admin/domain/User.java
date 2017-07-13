package com.meet.admin.domain;

import java.util.Date;

import com.meet.common.domain.BaseDomain;
import com.meet.common.util.DateUtil;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User extends BaseDomain{

	// Fields

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -1413463156658872255L;
	private Integer userId;
	private String userName;
	private String userPass;
	private String userCode;
	private String realName;
	private Integer userSex;
	private Integer userAge;
	private String userDept;
	private String userPhone;
	private Date regDate;
	private Integer userType;//1：用户 2：管理员
	
	private String random;
	private String ids;

	public String getUserTypeDesc() {
		switch (userType) {
		case 1:
			return "用户";
		case 2:
			return "管理员"; 
		default:
			return "";
		}
	}
	
	public String getUserSexDesc() {
		switch (userSex) {
		case 1:
			return "男";
		case 2:
			return "女";
		default:
			return "";
		}
	}
	
	public String getRegDateDesc(){
		if (regDate!=null) {
			return DateUtil.dateToDateString(regDate);
		}else {
			return null;
		}
	}
	
	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userName, String userPass) {
		this.userName = userName;
		this.userPass = userPass;
	}

	/** full constructor */
	public User(String userName, String userPass,String userCode,
			String realName, Integer userSex, Integer userAge,String userDept,String userPhone,
			Date regDate, Integer userType) {
		this.userName = userName;
		this.userPass = userPass;
		this.userCode = userCode;
		this.realName = realName;
		this.userSex = userSex;
		this.userAge = userAge;
		this.userDept = userDept;
		this.userPhone = userPhone;
		this.regDate = regDate;
		this.userType = userType;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getUserSex() {
		return this.userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public Integer getUserAge() {
		return this.userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

}