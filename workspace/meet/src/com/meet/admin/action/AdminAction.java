package com.meet.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.meet.admin.domain.Info;
import com.meet.admin.domain.Reserve;
import com.meet.admin.domain.ReserveSum;
import com.meet.admin.domain.Room;
import com.meet.admin.domain.User;
import com.meet.admin.manager.AdminManager;
import com.meet.common.action.BaseAction;
import com.meet.common.util.BeanLocator;
import com.meet.common.util.DateUtil;
import com.meet.common.util.Param;

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = (AdminManager)BeanLocator.getInstance().getBean("adminManager");
	
	//抓取页面参数
	User paramsUser;
	Room paramsRoom;
	Reserve paramsReserve;
	Info paramsInfo;
	
	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUserId(paramsUser.getUserId());
			admin = adminManager.queryUser(admin);
			Param.setSession("admin", admin);
			
			setSuccessTip("编辑成功", "modifyInfo.jsp");
			
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUserPass(paramsUser.getUserPass());
				Param.setSession("admin", admin);
			}
			
			setSuccessTip("修改成功", "main.jsp");
		} catch (Exception e) {
			setErrorTip("修改异常", "main.jsp");
		}
		
		return "infoTip";
	}
	
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查看用户信息
			paramsUser.setUserType(1);
			//设置分页信息
			setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询用户列表
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加用户页面
	 * @return String
	 */
	public String addUserShow(){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @return String
	 */
	public String addUser(){
		try {
			//检查用户是否存在
			User user = new User();
			user.setUserName(paramsUser.getUserName());
			user = adminManager.queryUser(user);
			if (user!=null) {
				tip="失败，该用户名已经存在！";
				Param.setAttribute("user", paramsUser);
				return "userEdit";
			}
			
			 //添加用户
			paramsUser.setUserType(1);
			paramsUser.setRegDate(new Date());
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("添加用户异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到用户
			User user = adminManager.queryUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
			//更新会议室预约
			adminManager.updateReserveByUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listUsers.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除用户成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("删除用户异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listRooms
	 * @Description: 查询会议室
	 * @return String
	 */
	public String listRooms(){
		try {
			if (paramsRoom==null) {
				paramsRoom = new Room();
			}
			//设置分页信息
			setPagination(paramsRoom);
			//总的条数
			int[] sum={0};
			//查询会议室列表
			List<Room> rooms = adminManager.listRooms(paramsRoom,sum); 
			
			Param.setAttribute("rooms", rooms);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询会议室异常", "main.jsp");
			return "infoTip";
		}
		
		return "roomShow";
	}
	
	/**
	 * @Title: addRoomShow
	 * @Description: 显示添加会议室页面
	 * @return String
	 */
	public String addRoomShow(){
		return "roomEdit";
	}
	
	/**
	 * @Title: addRoom
	 * @Description: 添加会议室
	 * @return String
	 */
	public String addRoom(){
		try {
			//检测是否存在
			Room room = adminManager.queryRoom(paramsRoom);
			 if (room!=null) {
				 setErrorTip("该会议室已经存在！", "Admin_listRooms.action");
				 return "infoTip";
			 }
			 //添加会议室
			adminManager.addRoom(paramsRoom);
			
			setSuccessTip("添加成功", "Admin_listRooms.action");
		} catch (Exception e) {
			setErrorTip("添加会议室异常", "Admin_listRooms.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editRoom
	 * @Description: 编辑会议室
	 * @return String
	 */
	public String editRoom(){
		try {
			 //得到会议室
			Room room = adminManager.queryRoom(paramsRoom);
			Param.setAttribute("room", room);
		} catch (Exception e) {
			setErrorTip("查询会议室异常", "Admin_listRooms.action");
			return "infoTip";
		}
		
		return "roomEdit";
	}
	
	/**
	 * @Title: saveRoom
	 * @Description: 保存编辑会议室
	 * @return String
	 */
	public String saveRoom(){
		try {
			 //检测是否存在
			 Room room = new Room();
			 room.setRoomNo(paramsRoom.getRoomNo());
			 room.setRoomName(paramsRoom.getRoomName());
			 room = adminManager.queryRoom(room);
			 if (room!=null && !room.getRoomId().equals(paramsRoom.getRoomId())) {
				 setErrorTip("该会议室已经存在！", "Admin_listRooms.action");
				 return "infoTip";
			 }
			
			 //保存编辑会议室
			adminManager.updateRoom(paramsRoom);
			
			setSuccessTip("编辑成功", "Admin_listRooms.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("room", paramsRoom);
			return "roomEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delRooms
	 * @Description: 删除会议室
	 * @return String
	 */
	public String delRooms(){
		try {
			 //删除会议室
			adminManager.delRooms(paramsRoom);
			
			setSuccessTip("删除会议室成功", "Admin_listRooms.action");
		} catch (Exception e) {
			setErrorTip("删除会议室异常", "Admin_listRooms.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listReserves
	 * @Description: 查询会议室预约
	 * @return String
	 */
	public String listReserves(){
		try {
			if (paramsReserve==null) {
				paramsReserve = new Reserve();
			}
			//设置分页信息
			setPagination(paramsReserve);
			//总的条数
			int[] sum={0};
			//用户身份限制、
			User admin = (User)Param.getSession("admin");
			if (admin.getUserType()==1) {
				paramsReserve.setReserveDateMin(DateUtil.getDate(DateUtil.dateToDateString(new Date(),"yyyy-MM-dd")));
			}
			
			//查询会议室预约列表
			List<Reserve> reserves = adminManager.listReserves(paramsReserve,sum); 
			
			Param.setAttribute("reserves", reserves);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询会议室预约异常", "main.jsp");
			return "infoTip";
		}
		
		return "reserveShow";
	}
	
	/**
	 * @Title: listReservesSum
	 * @Description: 查询会议室预约统计
	 * @return String
	 */
	public String listReservesSum(){
		try {
			if (paramsReserve==null) {
				paramsReserve = new Reserve();
			}
			//设置分页信息
			setPagination(paramsReserve);
			//总的条数
			int[] sum={0};
			//查询会议室预约列表
			List<Reserve> reserves = adminManager.listReservesSum(paramsReserve,sum); 
			
			Param.setAttribute("reserves", reserves);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询会议室预约统计异常", "main.jsp");
			return "infoTip";
		}
		
		return "reserveSumShow";
	}
	
	/**
	 * @Title: listReservesSum2
	 * @Description: 查询会议室预约统计
	 * @return String
	 */
	public String listReservesSum2(){
		try {
			if (paramsReserve==null || paramsReserve.getRoom().getRoomId().intValue()==0) {
				//设置默认日期段为一周
				paramsReserve = new Reserve();
				String reserveDateMin = DateUtil.dateToDateString(new Date(),"yyyy-MM-dd");
				paramsReserve.setReserveDateMin(DateUtil.getDate(reserveDateMin));
				paramsReserve.setReserveDateMax(DateUtil.getOtherDate(DateUtil.getDate(reserveDateMin), 0, 0, 6));
				
				//查询会议室字典
				Room room = new Room();
				room.setStart(-1);
				List<Room> rooms = adminManager.listRooms(room, null);
				if (rooms==null) {
					rooms = new ArrayList<Room>();
				}
				Param.setAttribute("rooms", rooms);
				Param.setAttribute("tip", "请选择会议室后进行统计");
				return "reserveSum2Show";
			}
			//设置分页信息
			paramsReserve.setStart(-1);
			//审核标志
			paramsReserve.setReserveFlags("1,2");
			//查询会议室预约列表
			List<ReserveSum> reserves = adminManager.listReservesSum2(paramsReserve); 
			Param.setAttribute("reserves", reserves);
			
			//查询会议室字典
			Room room = new Room();
			room.setStart(-1);
			List<Room> rooms = adminManager.listRooms(room, null);
			if (rooms==null) {
				rooms = new ArrayList<Room>();
			}
			Param.setAttribute("rooms", rooms);
			
			//重新格式化结束日期
			paramsReserve.setReserveDateMax(DateUtil.getDate(DateUtil.dateToDateString(paramsReserve.getReserveDateMax(), "yyyy-MM-dd")));
			
		} catch (Exception e) {
			setErrorTip("查询会议室预约统计异常", "main.jsp");
			return "infoTip";
		}
		
		return "reserveSum2Show";
	}
	
	/**
	 * @Title: addReserveShow
	 * @Description: 显示添加会议室预约页面
	 * @return String
	 */
	public String addReserveShow(){
		//查询会议室字典
		Room room = new Room();
		room.setStart(-1);
		List<Room> rooms = adminManager.listRooms(room, null);
		if (rooms==null) {
			rooms = new ArrayList<Room>();
		}
		Param.setAttribute("rooms", rooms);
		return "reserveEdit";
	}
	
	/**
	 * @Title: addReserve2Show
	 * @Description: 显示添加会议室预约页面-管理员
	 * @return String
	 */
	public String addReserveShow2(){
		//查询会议室字典
		Room room = new Room();
		room.setStart(-1);
		List<Room> rooms = adminManager.listRooms(room, null);
		if (rooms==null) {
			rooms = new ArrayList<Room>();
		}
		Param.setAttribute("rooms", rooms);
		
		//查询用户字典
		User user = new User();
		user.setStart(-1);
		user.setUserType(1);
		List<User> users = adminManager.listUsers(user, null);
		if (users==null) {
			users = new ArrayList<User>();
		}
		Param.setAttribute("users", users);
		return "reserveEdit2";
	}
	
	/**
	 * @Title: addReserve
	 * @Description: 添加会议室预约
	 * @return String
	 */
	public String addReserve(){
		try {
			//检测是否存在
			Reserve reserve = new Reserve();
			reserve.setRoom(paramsReserve.getRoom());
			reserve.setReserveDate1(paramsReserve.getReserveDate1());
			reserve.setReserveDate2(paramsReserve.getReserveDate2());
			reserve.setReserveFlags("1,2");
			List<Reserve> reserves = adminManager.listReserves(reserve, null);
			 if (reserves!=null && reserves.size()>0) {
				 setErrorTip("预约时间段与该会议室已有预约冲突！", "Admin_listReserves.action");
				 return "infoTip";
			 }
			 //添加会议室预约
			adminManager.addReserve(paramsReserve);
			
			setSuccessTip("添加成功", "Admin_listReserves.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("添加会议室预约异常", "Admin_listReserves.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: editReserve
	 * @Description: 编辑会议室预约
	 * @return String
	 */
	public String editReserve(){
		try {
			 //得到会议室预约
			Reserve reserve = adminManager.queryReserve(paramsReserve);
			Param.setAttribute("reserve", reserve);
			
			//查询会议室字典
			Room room = new Room();
			room.setStart(-1);
			List<Room> rooms = adminManager.listRooms(room, null);
			if (rooms==null) {
				rooms = new ArrayList<Room>();
			}
			Param.setAttribute("rooms", rooms);
			
			//查询用户字典
			User user = new User();
			user.setStart(-1);
			user.setUserType(1);
			List<User> users = adminManager.listUsers(user, null);
			if (users==null) {
				users = new ArrayList<User>();
			}
			Param.setAttribute("users", users);
		} catch (Exception e) {
			setErrorTip("查询会议室预约异常", "Admin_listReserves.action");
			return "infoTip";
		}
		
		return "reserveEdit2";
	}
	
	/**
	 * @Title: saveReserve
	 * @Description: 保存编辑会议室预约
	 * @return String
	 */
	public String saveReserve(){
		try {
			//检测是否存在
			Reserve reserve = new Reserve();
			reserve.setRoom(paramsReserve.getRoom());
			reserve.setReserveDate1(paramsReserve.getReserveDate1());
			reserve.setReserveDate2(paramsReserve.getReserveDate2());
			reserve.setReserveFlags("1,2");
			List<Reserve> reserves = adminManager.listReserves(reserve, null);
		    if (reserves!=null && reserves.size()>0) {
		    	for (Reserve _reserve : reserves) {
					if (_reserve.getReserveId().intValue()!=paramsReserve.getReserveId().intValue()) {
						setErrorTip("预约时间段与该会议室已有预约冲突！", "Admin_listReserves.action");
				    	return "infoTip";
					}
				}
		    }
			
			 //保存编辑会议室预约
			adminManager.updateReserve(paramsReserve);
			
			setSuccessTip("编辑成功", "Admin_listReserves.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("reserve", paramsReserve);
			return "reserveEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delReserves
	 * @Description: 删除会议室预约
	 * @return String
	 */
	public String delReserves(){
		try {
			 //删除会议室预约
			adminManager.delReserves(paramsReserve);
			
			setSuccessTip("删除会议室预约成功", "Admin_listReserves.action");
		} catch (Exception e) {
			setErrorTip("删除会议室预约异常", "Admin_listReserves.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: assessReserve
	 * @Description: 审核会议室预约
	 * @return String
	 */
	public String assessReserve(){
		try {
			 //审核会议室预约
			adminManager.updateReserveFlag(paramsReserve);
			
			setSuccessTip("审核成功", "Admin_listReserves.action");
		} catch (Exception e) {
			tip="审核失败";
			Param.setAttribute("reserve", paramsReserve);
			return "reserveEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 查询网站公告
	 * @return String
	 */
	public String listInfos(){
		try {
			if (paramsInfo==null) {
				paramsInfo = new Info();
			}
			
			//设置分页信息
			setPagination(paramsInfo);
			//总的条数
			int[] sum={0};
			//查询网站公告列表
			List<Info> infos = adminManager.listInfos(paramsInfo,sum); 
			
			Param.setAttribute("infos", infos);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询网站公告异常", "main.jsp");
			return "infoTip";
		}
		
		return "infoShow";
	}
	
	/**
	 * @Title: queryInfo
	 * @Description: 查询网站公告
	 * @return String
	 */
	public String queryInfo(){
		try {
			 //得到网站公告
			Info info = adminManager.queryInfo(paramsInfo);
			Param.setAttribute("info", info);
			
		} catch (Exception e) {
			setErrorTip("查询网站公告异常", "Admin_listInfos.action");
			return "infoTip";
		}
		
		return "infoDetail";
	}
	
	/**
	 * @Title: addInfoShow
	 * @Description: 显示添加网站公告页面
	 * @return String
	 */
	public String addInfoShow(){
		return "infoEdit";
	}
	
	/**
	 * @Title: addInfo
	 * @Description: 添加网站公告
	 * @return String
	 */
	public String addInfo(){
		try {
			 //添加网站公告
			adminManager.addInfo(paramsInfo);
			
			setSuccessTip("添加成功", "Admin_listInfos.action");
		} catch (Exception e) {
			setErrorTip("添加网站公告异常", "Admin_listInfos.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editInfo
	 * @Description: 编辑网站公告
	 * @return String
	 */
	public String editInfo(){
		try {
			 //得到网站公告
			Info info = adminManager.queryInfo(paramsInfo);
			Param.setAttribute("info", info);
			
		} catch (Exception e) {
			setErrorTip("查询网站公告异常", "Admin_listInfos.action");
			return "infoTip";
		}
		
		return "infoEdit";
	}
	
	/**
	 * @Title: saveInfo
	 * @Description: 保存编辑网站公告
	 * @return String
	 */
	public String saveInfo(){
		try {
			 //保存编辑网站公告
			adminManager.updateInfo(paramsInfo);
			
			setSuccessTip("编辑成功", "Admin_listInfos.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("info", paramsInfo);
			return "infoEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delInfos
	 * @Description: 删除网站公告
	 * @return String
	 */
	public String delInfos(){
		try {
			 //删除网站公告
			adminManager.delInfos(paramsInfo);
			
			setSuccessTip("删除网站公告成功", "Admin_listInfos.action");
		} catch (Exception e) {
			setErrorTip("删除网站公告异常", "Admin_listInfos.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: validateAdmin
	 * @Description: admin登录验证
	 * @return boolean
	 */
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Room getParamsRoom() {
		return paramsRoom;
	}

	public void setParamsRoom(Room paramsRoom) {
		this.paramsRoom = paramsRoom;
	}

	public Reserve getParamsReserve() {
		return paramsReserve;
	}

	public void setParamsReserve(Reserve paramsReserve) {
		this.paramsReserve = paramsReserve;
	}

	public Info getParamsInfo() {
		return paramsInfo;
	}

	public void setParamsInfo(Info paramsInfo) {
		this.paramsInfo = paramsInfo;
	}

}
