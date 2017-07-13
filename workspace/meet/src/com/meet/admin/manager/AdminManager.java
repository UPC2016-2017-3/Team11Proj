package com.meet.admin.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meet.admin.dao.InfoDao;
import com.meet.admin.dao.ReserveDao;
import com.meet.admin.dao.RoomDao;
import com.meet.admin.dao.UserDao;
import com.meet.admin.domain.Info;
import com.meet.admin.domain.Reserve;
import com.meet.admin.domain.ReserveItem;
import com.meet.admin.domain.ReserveSum;
import com.meet.admin.domain.Room;
import com.meet.admin.domain.User;
import com.meet.common.util.DateUtil;
import com.meet.common.util.Md5;
import com.meet.common.util.Param;
import com.meet.common.util.StringUtil;
import com.meet.common.util.Transcode;

public class AdminManager {
	
	//所有待注入Dao类
	RoomDao roomDao;
	ReserveDao reserveDao;
	InfoDao infoDao;
	UserDao userDao;

	/**
	 * @Title: listUsers
	 * @Description: 查询用户集合
	 * @param user
	 * @return List<User>
	 */
	public List<User> listUsers(User user, int[] sum) {
		if (sum != null) {
			sum[0] = userDao.listUsersCount(user);
		}
		List<User> users = userDao.listUsers(user);
		return users;
	}

	/**
	 * @Title: queryUser
	 * @Description: 用户单个查询
	 * @param user
	 * @return User
	 */
	public User queryUser(User user) {
		User _user = userDao.getUser(user);
		return _user;
	}

	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void addUser(User user) {
		//密码MD5加密
		if (!StringUtil.isEmptyString(user.getUserPass())) {
			user.setUserPass(Md5.makeMd5(user.getUserPass()));
		}
		//添加用户
		userDao.addUser(user);
	}

	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void updateUser(User user) {
		//密码MD5加密
		if (!StringUtil.isEmptyString(user.getUserPass())) {
			user.setUserPass(Md5.makeMd5(user.getUserPass()));
		}
		userDao.updateUser(user);
	}

	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void delUsers(User user) {
		userDao.delUser(user.getIds().split(","));
	}
	
	/**
	 * @Title: listRooms
	 * @Description: 会议室集合查询
	 * @param room
	 * @return List<Room>
	 */
	public List<Room> listRooms(Room room, int[] sum) {
		if (sum != null) {
			sum[0] = roomDao.listRoomsCount(room);
		}
		List<Room> rooms = roomDao.listRooms(room);

		return rooms;
	}
	
	/**
	 * @Title: queryRoom
	 * @Description: 会议室单体查询
	 * @param room
	 * @return Room
	 */
	public Room queryRoom(Room room) {
		Room _room = roomDao.getRoom(room);
		return _room;
	}

	/**
	 * @Title: addRoom
	 * @Description: 添加会议室
	 * @param room
	 * @return void
	 */
	public void addRoom(Room room) {
		roomDao.addRoom(room);
	}

	/**
	 * @Title: updateRoom
	 * @Description: 更新会议室信息
	 * @param room
	 * @return void
	 */
	public void updateRoom(Room room) {
		roomDao.updateRoom(room);
	}

	/**
	 * @Title: delRoom
	 * @Description: 删除会议室信息
	 * @param room
	 * @return void
	 */
	public void delRooms(Room room) {
		roomDao.delRoom(room.getIds().split(","));
	} 
	
	/**
	 * @Title: listReserves
	 * @Description: 会议室预约集合查询
	 * @param reserve
	 * @return List<Reserve>
	 */
	public List<Reserve> listReserves(Reserve reserve, int[] sum) {
		//预约日期格式设置
		if (reserve.getReserveDateMax()!=null) {
			String reserveDateMax = DateUtil.dateToDateString(reserve.getReserveDateMax()) + " 23:59:59";
			reserve.setReserveDateMax(DateUtil.getDate(reserveDateMax));
		}
		if (sum != null) {
			sum[0] = reserveDao.listReservesCount(reserve);
		}
		List<Reserve> reserves = reserveDao.listReserves(reserve);

		return reserves;
	}
	
	/**
	 * @Title: listReservesSum
	 * @Description: 会议室预约统计查询
	 * @param reserve
	 * @return List<Reserve>
	 */
	public List<Reserve> listReservesSum(Reserve reserve, int[] sum) {
		//预约日期格式设置
		if (reserve.getReserveDateMax()!=null) {
			String reserveDateMax = DateUtil.dateToDateString(reserve.getReserveDateMax()) + " 23:59:59";
			reserve.setReserveDateMax(DateUtil.getDate(reserveDateMax));
		}
		if (sum != null) {
			sum[0] = reserveDao.listReservesSumCount(reserve);
		}
		List<Reserve> reserves = reserveDao.listReservesSum(reserve);

		return reserves;
	}
	
	/**
	 * @Title: listReservesSum2
	 * @Description: 会议室预约统计查询
	 * @param reserve
	 * @return List<ReserveSum>
	 */
	public List<ReserveSum> listReservesSum2(Reserve reserve) {
		List<ReserveSum> reserves = new ArrayList<ReserveSum>();
		//会议室预约统计明细查询
		List<ReserveItem> items = listReserveItems(reserve);
		//会议室预约统计梳理
		ReserveSum reserveSum = new ReserveSum();
		List<ReserveItem> items1 = new ArrayList<ReserveItem>(); 
		List<ReserveItem> items2 = new ArrayList<ReserveItem>(); 
		List<ReserveItem> items3 = new ArrayList<ReserveItem>(); 
		
		int k=0;
		Map<String,ReserveSum> reserveDays = new HashMap<String,ReserveSum>();
		if (items!=null && items.size()>0) {
			for (ReserveItem reserveItem : items) {
				if (reserveSum.getReserve_date()==null) {
					 //初始化
					 reserveSum = new ReserveSum();
					 reserveSum.setReserve_date(reserveItem.getReserve_date());
					 reserveSum.setReserve_week(reserveItem.getReserve_week());
				 }
				
				//同一天处理
				if(reserveSum.getReserve_date().equals(reserveItem.getReserve_date())){
					 if (reserveItem.getReserve_sec()==1) {
						 items1.add(reserveItem);
					 }else if (reserveItem.getReserve_sec()==2) {
						 items2.add(reserveItem);
					 }else {
						 items3.add(reserveItem);
					 }
				}else {
					//上一天颜色标示
					 if (reserveSum.getReserve_date()!=null) {
						double items1_lens = 0;
						double items2_lens = 0;
						double items3_lens = 0;
						for (ReserveItem itemLen : items1) {
							items1_lens+=itemLen.getReserve_length();
						}
						for (ReserveItem itemLen : items2) {
							items2_lens+=itemLen.getReserve_length();
						}
						for (ReserveItem itemLen : items3) {
							items3_lens+=itemLen.getReserve_length();
						}
						if (items1_lens==3600*4) {
							reserveSum.setItems1_color(3);
						}else if (items1_lens==0) {
							reserveSum.setItems1_color(1);
						}else {
							reserveSum.setItems1_color(2);
						}
						if (items2_lens==3600*5) {
							reserveSum.setItems2_color(3);
						}else if (items2_lens==0) {
							reserveSum.setItems2_color(1);
						}else {
							reserveSum.setItems2_color(2);
						}
						if (items3_lens==3600*3) {
							reserveSum.setItems3_color(3);
						}else if (items3_lens==0) {
							reserveSum.setItems3_color(1);
						}else {
							reserveSum.setItems3_color(2);
						}
						reserveSum.setItems1(items1);
						reserveSum.setItems2(items2);
						reserveSum.setItems3(items3);
						//reserves.add(reserveSum);
						reserveDays.put(reserveSum.getReserve_date(),reserveSum);
					 }
					 
					//重新初始化
					 reserveSum = new ReserveSum();
					 reserveSum.setReserve_date(reserveItem.getReserve_date());
					 reserveSum.setReserve_week(reserveItem.getReserve_week());
					 items1 = new ArrayList<ReserveItem>(); 
					 items2 = new ArrayList<ReserveItem>(); 
 					 items3 = new ArrayList<ReserveItem>(); 
 					 
 					 reserveSum = new ReserveSum();
					 reserveSum.setReserve_date(reserveItem.getReserve_date());
					 reserveSum.setReserve_week(reserveItem.getReserve_week());
					 
					 if (reserveItem.getReserve_sec()==1) {
						 items1.add(reserveItem);
					 }else if (reserveItem.getReserve_sec()==2) {
						 items2.add(reserveItem);
					 }else {
						 items3.add(reserveItem);
					 }
				}
				
				 //最后一天处理
				 if(k==items.size()-1){
					 if (reserveSum.getReserve_date()!=null) {
						double items1_lens = 0;
						double items2_lens = 0;
						double items3_lens = 0;
						for (ReserveItem itemLen : items1) {
							items1_lens+=itemLen.getReserve_length();
						}
						for (ReserveItem itemLen : items2) {
							items2_lens+=itemLen.getReserve_length();
						}
						for (ReserveItem itemLen : items3) {
							items3_lens+=itemLen.getReserve_length();
						}
						if (items1_lens==3600*4) {
							reserveSum.setItems1_color(3);
						}else if (items1_lens==0) {
							reserveSum.setItems1_color(1);
						}else {
							reserveSum.setItems1_color(2);
						}
						if (items2_lens==3600*5) {
							reserveSum.setItems2_color(3);
						}else if (items2_lens==0) {
							reserveSum.setItems2_color(1);
						}else {
							reserveSum.setItems2_color(2);
						}
						if (items3_lens==3600*3) {
							reserveSum.setItems3_color(3);
						}else if (items3_lens==0) {
							reserveSum.setItems3_color(1);
						}else {
							reserveSum.setItems3_color(2);
						}
						reserveSum.setItems1(items1);
						reserveSum.setItems2(items2);
						reserveSum.setItems3(items3);
						//reserves.add(reserveSum);
						reserveDays.put(reserveSum.getReserve_date(),reserveSum);
					 }  
				 }
				 
				 k++;
			}
		}
		
		//处理选择日期段
		List<ReserveSum> reserveAlls = new ArrayList<ReserveSum>();
		Date startDate = reserve.getReserveDateMin();
		Date endDate = reserve.getReserveDateMax();
		for (Date i = startDate; DateUtil.compareDateStr(i, endDate)>=0; i = DateUtil.getOtherDate(i, 0, 0, 1)) {
			if (reserveDays.containsKey(DateUtil.dateToDateString(i))) {
				reserveSum = reserveDays.get(DateUtil.dateToDateString(i));
				if (DateUtil.compareDateStr(startDate,i)==0) {
					int nowHourMinute = Integer.parseInt(DateUtil.dateToDateString(new Date(),"HHmm"));
					if (nowHourMinute>1200 && reserveSum.getItems1().size()==0) {
						reserveSum.setItems1_color(4);
					}
					if (nowHourMinute>1800 && reserveSum.getItems2().size()==0) {
						reserveSum.setItems2_color(4);
					}
					if (nowHourMinute>2200 && reserveSum.getItems3().size()==0) {
						reserveSum.setItems3_color(4);
					}
				}
				reserveAlls.add(reserveSum);
			}else{
				reserveSum = new ReserveSum();
				reserveSum.setReserve_date(DateUtil.dateToDateString(i));
				reserveSum.setReserve_week(DateUtil.getWeek(i)-1);
				reserveSum.setItems1_color(1);
				reserveSum.setItems2_color(1);
				reserveSum.setItems3_color(1);
				if (DateUtil.compareDateStr(startDate,i)==0) {
					int nowHourMinute = Integer.parseInt(DateUtil.dateToDateString(new Date(),"HHmm"));
					if (nowHourMinute>1200 && reserveSum.getItems1().size()==0) {
						reserveSum.setItems1_color(4);
					}
					if (nowHourMinute>1800 && reserveSum.getItems2().size()==0) {
						reserveSum.setItems2_color(4);
					}
					if (nowHourMinute>2200 && reserveSum.getItems3().size()==0) {
						reserveSum.setItems3_color(4);
					}
				}
				reserveAlls.add(reserveSum);
			}
		}
		
		return reserveAlls;
	}
	
	public List<ReserveItem> listReserveItems(Reserve reserve) {
		//预约日期格式设置
		if (reserve.getReserveDateMax()!=null) {
			String reserveDateMax = DateUtil.dateToDateString(reserve.getReserveDateMax()) + " 23:59:59";
			reserve.setReserveDateMax(DateUtil.getDate(reserveDateMax));
		}
		List<ReserveItem> reserves = reserveDao.listReservesSum2(reserve);

		return reserves;
	}
	
	/**
	 * @Title: queryReserve
	 * @Description: 会议室预约单体查询
	 * @param reserve
	 * @return Reserve
	 */
	public Reserve queryReserve(Reserve reserve) {
		Reserve _reserve = reserveDao.getReserve(reserve);
		return _reserve;
	}

	/**
	 * @Title: addReserve
	 * @Description: 添加会议室预约
	 * @param reserve
	 * @return void
	 */
	public void addReserve(Reserve reserve) {
		//计算预约时长
		reserve.setReserveLength((reserve.getReserveDate2().getTime()-reserve.getReserveDate1().getTime())/1000.0);
		//审核标志
		User admin = (User)Param.getSession("admin");
		if (admin.getUserType().intValue()==1) {
			reserve.setReserveFlag(1);//待审核
		}else{
			reserve.setReserveFlag(2);//审核通过
		}
		
		reserveDao.addReserve(reserve);
	}

	/**
	 * @Title: updateReserve
	 * @Description: 更新会议室预约信息
	 * @param reserve
	 * @return void
	 */
	public void updateReserve(Reserve reserve) {
		//计算预约时长
		reserve.setReserveLength((reserve.getReserveDate2().getTime()-reserve.getReserveDate1().getTime())/1000.0);
		reserveDao.updateReserve(reserve);
	}
	
	/**
	 * @Title: updateReserveFlag
	 * @Description: 更新会议室预约审核
	 * @param reserve
	 * @return void
	 */
	public void updateReserveFlag(Reserve reserve) {
		//更新会议室预约审核
		reserveDao.updateReserve(reserve);
	}
	
	
	/**
	 * @Title: updateReserveByUser
	 * @Description: 根据用户信息更新会议室预约信息
	 * @param reserve
	 * @return void
	 */
	public void updateReserveByUser(User user) {
		//更新会议室预约信息
		reserveDao.updateReserveByUser(user);
	}

	/**
	 * @Title: delReserve
	 * @Description: 删除会议室预约信息
	 * @param reserve
	 * @return void
	 */
	public void delReserves(Reserve reserve) {
		reserveDao.delReserve(reserve.getIds().split(","));
	} 
	
	/**
	 * @Title: listInfos
	 * @Description: 网站公告查询
	 * @param info
	 * @return List<Info>
	 */
	public List<Info> listInfos(Info info, int[] sum) {
		if (sum != null) {
			sum[0] = infoDao.listInfosCount(info);
		}
		List<Info> infos = infoDao.listInfos(info);

		return infos;
	}

	/**
	 * @Title: queryInfo
	 * @Description: 网站公告查询
	 * @param info
	 * @return Info
	 */
	public Info queryInfo(Info info) {
		Info _info = infoDao.getInfo(info);
		return _info;
	}

	/**
	 * @Title: addInfo
	 * @Description: 添加网站公告
	 * @param info
	 * @return void
	 */
	public void addInfo(Info info) {
		if (!StringUtil.isEmptyString(info.getInfo_content())) {
			info.setInfo_content(Transcode.htmlEncode(info.getInfo_content()));
		}
		info.setInfo_date(new Date());
		infoDao.addInfo(info);
	}

	/**
	 * @Title: updateInfo
	 * @Description: 更新网站公告信息
	 * @param info
	 * @return void
	 */
	public void updateInfo(Info info) {
		if (!StringUtil.isEmptyString(info.getInfo_content())) {
			info.setInfo_content(Transcode.htmlEncode(info.getInfo_content()));
		}
		infoDao.updateInfo(info);
	}

	/**
	 * @Title: delInfo
	 * @Description: 删除网站公告信息
	 * @param info
	 * @return void
	 */
	public void delInfos(Info info) {
		infoDao.delInfo(info.getIds().split(","));
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RoomDao getRoomDao() {
		return roomDao;
	}

	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	public ReserveDao getReserveDao() {
		return reserveDao;
	}

	public void setReserveDao(ReserveDao reserveDao) {
		this.reserveDao = reserveDao;
	}

	public InfoDao getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(InfoDao infoDao) {
		this.infoDao = infoDao;
	}
	
}
