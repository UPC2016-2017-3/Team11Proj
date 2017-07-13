package com.meet.admin.manager;

import com.meet.admin.dao.UserDao;
import com.meet.admin.domain.User;
import com.meet.common.util.Md5;
import com.meet.common.util.StringUtil;

public class LoginManager {
	
	UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * @Title: getUser
	 * @Description: 查询用户
	 * @param user
	 * @return User
	 */
	public User getUser(User user){
		if (!StringUtil.isEmptyString(user.getUserPass())) {
			user.setUserPass(Md5.makeMd5(user.getUserPass()));
		}
		User _user = userDao.getUser(user);
		return _user;
	}
	
	/**
	 * @Title: addUser
	 * @Description: 用户注册
	 * @return void
	 */
	public void addUser(User user) {
		//密码加密
		if (!StringUtil.isEmptyString(user.getUserPass())) {
			user.setUserPass(Md5.makeMd5(user.getUserPass()));
		}
		userDao.addUser(user);
		
	}  
}
