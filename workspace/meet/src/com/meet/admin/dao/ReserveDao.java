package com.meet.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.meet.admin.domain.Reserve;
import com.meet.admin.domain.ReserveItem;
import com.meet.admin.domain.User;
import com.meet.common.dao.BaseDao;
import com.meet.common.util.StringUtil;

public class ReserveDao extends BaseDao{
	
	public void addReserve(Reserve reserve){
		super.add(reserve);
	}
	
	public void delReserve(Integer reserveId){
		super.del(Reserve.class, reserveId);
	}
	
	public void delReserve(String[] reserveIds){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <reserveIds.length; i++) {
			sBuilder.append(reserveIds[i]);
			if (i !=reserveIds.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Reserve d WHERE d.reserveId IN (" +sBuilder.toString()+")";

		Object[] params = null;
		
		super.executeUpdateHql(hql, params);
	}
	
	public void updateReserveByUser(User user){
		String hql = "UPDATE Reserve d SET d.realName = '"+user.getRealName()+"',d.userDept='"+user.getUserDept()+"' WHERE d.userId =  " +user.getUserId()+" ";

		Object[] params = null;
		
		super.executeUpdateHql(hql, params);
	}
	
	public void updateReserve(Reserve reserve){
		Reserve _reserve = (Reserve)super.get(Reserve.class, reserve.getReserveId());
		if (reserve.getRoom()!=null && reserve.getRoom().getRoomId()!=null) {
			_reserve.setRoom(reserve.getRoom());
		}
		if (reserve.getUserId()!=null) {
			_reserve.setUserId(reserve.getUserId());
		}
		if (!StringUtil.isEmptyString(reserve.getRealName())) {
			_reserve.setRealName(reserve.getRealName());
		}
		if (!StringUtil.isEmptyString(reserve.getUserDept())) {
			_reserve.setUserDept(reserve.getUserDept());
		}
		if (reserve.getReserveDate1()!=null) {
			_reserve.setReserveDate1(reserve.getReserveDate1());
		}
		if (reserve.getReserveDate2()!=null) {
			_reserve.setReserveDate2(reserve.getReserveDate2());
		}
		if (reserve.getReserveLength()!=null) {
			_reserve.setReserveLength(reserve.getReserveLength());
		}
		if (!StringUtil.isEmptyString(reserve.getMeetContent())) {
			_reserve.setMeetContent(reserve.getMeetContent());
		}
		if (reserve.getReserveEquip()!=null) {
			_reserve.setReserveEquip(reserve.getReserveEquip());
		}
		if (reserve.getReserveFlag()!=null) {
			_reserve.setReserveFlag(reserve.getReserveFlag());
		}
	    //super.update(reserve);
	}
	
	@SuppressWarnings("rawtypes")
	public Reserve getReserve(Reserve reserve){
		Reserve _reserve = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Reserve a join fetch a.room WHERE 1=1");
		
		List<Object> paramsList = new ArrayList<Object>();
		if (reserve.getReserveId()!=null && reserve.getReserveId()!=0) {
			sBuilder.append(" and a.reserveId = ? ");
			paramsList.add(reserve.getReserveId());
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		
		List list = super.executeQueryHql(sBuilder.toString(), params);
		if (list != null && list.size() > 0) {
			_reserve = (Reserve)list.get(0);
		}
		return _reserve;
	}
	
	@SuppressWarnings("rawtypes")
	//t1<T1 and t2>t1;t1>=T1 and t1<T2
	public List<Reserve>  listReserves(Reserve reserve){
		List<Reserve> reserves = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Reserve a join fetch a.room WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (reserve.getReserveDate1()!=null && reserve.getReserveDate2()!=null) {
			sBuilder.append(" and ((? < a.reserveDate1 and ? > a.reserveDate1) or (? >= a.reserveDate1 and ? < a.reserveDate2)) ");
			paramsList.add(reserve.getReserveDate1());
			paramsList.add(reserve.getReserveDate2());
			paramsList.add(reserve.getReserveDate1());
			paramsList.add(reserve.getReserveDate1());
		}
		if (reserve.getReserveId()!=null && reserve.getReserveId()!=0) {
			sBuilder.append(" and a.reserveId = ? ");
			paramsList.add(reserve.getReserveId());
		}
		if (reserve.getRoom()!=null && reserve.getRoom().getRoomId()!=null) {
			sBuilder.append(" and a.room.roomId = ? ");
			paramsList.add(reserve.getRoom().getRoomId());
		}
		if (reserve.getRoom()!=null && !StringUtil.isEmptyString(reserve.getRoom().getRoomName())) {
			sBuilder.append(" and a.room.roomName like ? ");
			paramsList.add("%"+reserve.getRoom().getRoomName()+"%");
		}
		if (reserve.getUserId()!=null) {
			sBuilder.append(" and a.userId = ? ");
			paramsList.add(reserve.getUserId());
		}
		if (!StringUtil.isEmptyString(reserve.getRealName())) {
			sBuilder.append(" and a.realName like ? ");
			paramsList.add("%"+reserve.getRealName()+"%");
		}
		if (!StringUtil.isEmptyString(reserve.getUserDept())) {
			sBuilder.append(" and a.userDept like ? ");
			paramsList.add("%"+reserve.getUserDept()+"%");
		}
		if (reserve.getReserveDateMin()!=null) {
			sBuilder.append(" and a.reserveDate1 >= ? ");
			paramsList.add(reserve.getReserveDateMin());
		}
		if (reserve.getReserveDateMax()!=null) {
			sBuilder.append(" and a.reserveDate1 <= ? ");
			paramsList.add(reserve.getReserveDateMax());
		}
		if (reserve.getReserveFlag()!=null && reserve.getReserveFlag()!=0) {
			sBuilder.append(" and a.reserveFlag = ? ");
			paramsList.add(reserve.getReserveFlag());
		}
		if (reserve.getReserveFlags()!=null && !"".equals(reserve.getReserveFlags())) {
			sBuilder.append(" and a.reserveFlag in ("+reserve.getReserveFlags()+") ");
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		
		sBuilder.append(" order by a.reserveDate1 desc,a.reserveId asc");

		List list = null;
		if (reserve.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, reserve.getStart(), reserve.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			reserves = new ArrayList<Reserve>();
			for (Object object : list) {
				reserves.add((Reserve)object);
			}
		}
		return reserves;
	}
	
	
	public int listReservesCount(Reserve reserve){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Reserve a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (reserve.getReserveDate1()!=null && reserve.getReserveDate2()!=null) {
			sBuilder.append(" and ((? < a.reserveDate1 and ? > a.reserveDate1) or (? >= a.reserveDate1 and ? < a.reserveDate2)) ");
			paramsList.add(reserve.getReserveDate1());
			paramsList.add(reserve.getReserveDate2());
			paramsList.add(reserve.getReserveDate1());
			paramsList.add(reserve.getReserveDate1());
		}
		if (reserve.getReserveId()!=null && reserve.getReserveId()!=0) {
			sBuilder.append(" and a.reserveId = ? ");
			paramsList.add(reserve.getReserveId());
		}
		if (reserve.getRoom()!=null && reserve.getRoom().getRoomId()!=null) {
			sBuilder.append(" and a.room.roomId = ? ");
			paramsList.add(reserve.getRoom().getRoomId());
		}
		if (reserve.getRoom()!=null && !StringUtil.isEmptyString(reserve.getRoom().getRoomName())) {
			sBuilder.append(" and a.room.roomName like ? ");
			paramsList.add("%"+reserve.getRoom().getRoomName()+"%");
		}
		if (reserve.getUserId()!=null) {
			sBuilder.append(" and a.userId = ? ");
			paramsList.add(reserve.getUserId());
		}
		if (!StringUtil.isEmptyString(reserve.getRealName())) {
			sBuilder.append(" and a.realName like ? ");
			paramsList.add("%"+reserve.getRealName()+"%");
		}
		if (!StringUtil.isEmptyString(reserve.getUserDept())) {
			sBuilder.append(" and a.userDept like ? ");
			paramsList.add("%"+reserve.getUserDept()+"%");
		}
		if (reserve.getReserveDateMin()!=null) {
			sBuilder.append(" and a.reserveDate1 >= ? ");
			paramsList.add(reserve.getReserveDateMin());
		}
		if (reserve.getReserveDateMax()!=null) {
			sBuilder.append(" and a.reserveDate1 <= ? ");
			paramsList.add(reserve.getReserveDateMax());
		}
		if (reserve.getReserveFlag()!=null && reserve.getReserveFlag()!=0) {
			sBuilder.append(" and a.reserveFlag = ? ");
			paramsList.add(reserve.getReserveFlag());
		}
		if (reserve.getReserveFlags()!=null && !"".equals(reserve.getReserveFlags())) {
			sBuilder.append(" and a.reserveFlag in ("+reserve.getReserveFlags()+") ");
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), params);
		sum = (int)count;
		return sum;
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public List<Reserve>  listReservesSum(Reserve reserve){
		List<Reserve> reserves = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT r.room_name roomName,count(*) reserveCount,sum(a.reserve_length) reserveLength FROM reserve a join room r on a.room_id=r.room_id WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (reserve.getRoom()!=null && reserve.getRoom().getRoomId()!=null) {
			sBuilder.append(" and a.room_id = ? ");
			paramsList.add(reserve.getRoom().getRoomId());
		}
		if (reserve.getRoom()!=null && !StringUtil.isEmptyString(reserve.getRoom().getRoomName())) {
			sBuilder.append(" and r.room_name like ? ");
			paramsList.add("%"+reserve.getRoom().getRoomName()+"%");
		}
		if (reserve.getUserId()!=null) {
			sBuilder.append(" and a.user_id = ? ");
			paramsList.add(reserve.getUserId());
		}
		if (!StringUtil.isEmptyString(reserve.getRealName())) {
			sBuilder.append(" and a.real_name like ? ");
			paramsList.add("%"+reserve.getRealName()+"%");
		}
		if (!StringUtil.isEmptyString(reserve.getUserDept())) {
			sBuilder.append(" and a.user_dept like ? ");
			paramsList.add("%"+reserve.getUserDept()+"%");
		}
		if (reserve.getReserveDateMin()!=null) {
			sBuilder.append(" and a.reserve_date1 >= ? ");
			paramsList.add(reserve.getReserveDateMin());
		}
		if (reserve.getReserveDateMax()!=null) {
			sBuilder.append(" and a.reserve_date1 <= ? ");
			paramsList.add(reserve.getReserveDateMax());
		}
		if (reserve.getReserveFlag()!=null && reserve.getReserveFlag()!=0) {
			sBuilder.append(" and a.reserveFlag = ? ");
			paramsList.add(reserve.getReserveFlag());
		}
		if (reserve.getReserveFlags()!=null && !"".equals(reserve.getReserveFlags())) {
			sBuilder.append(" and a.reserveFlag in (?) ");
			paramsList.add(reserve.getReserveFlags());
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		sBuilder.append(" group by r.room_name");
		sBuilder.append(" order by r.room_name asc");

		List<Map<String, Object>> list = null;
		if (reserve.getStart()!=-1) {
			list = super.executeQueryMapSql(sBuilder.toString(), params, reserve.getStart(), reserve.getLimit());
		}else {
			list = super.executeQueryMapSql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			reserves = new ArrayList<Reserve>();
			for (int i=0;i<list.size();i++ ) {
				Reserve _reserve = new Reserve();
				Map<String, Object> map = list.get(i);
				_reserve.setRoomName(map.get("roomName").toString());
				_reserve.setReserveCount(Integer.parseInt(map.get("reserveCount").toString()));
				_reserve.setReserveLength(Double.parseDouble(map.get("reserveLength").toString()));
				reserves.add(_reserve);
			}
		}
		return reserves;
	}
	
	public int listReservesSumCount(Reserve reserve){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(distinct a.room.roomName) FROM Reserve a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (reserve.getRoom()!=null && reserve.getRoom().getRoomId()!=null) {
			sBuilder.append(" and a.room.roomId = ? ");
			paramsList.add(reserve.getRoom().getRoomId());
		}
		if (reserve.getRoom()!=null && !StringUtil.isEmptyString(reserve.getRoom().getRoomName())) {
			sBuilder.append(" and a.room.roomName like ? ");
			paramsList.add("%"+reserve.getRoom().getRoomName()+"%");
		}
		if (reserve.getUserId()!=null) {
			sBuilder.append(" and a.userId = ? ");
			paramsList.add(reserve.getUserId());
		}
		if (!StringUtil.isEmptyString(reserve.getRealName())) {
			sBuilder.append(" and a.realName like ? ");
			paramsList.add("%"+reserve.getRealName()+"%");
		}
		if (!StringUtil.isEmptyString(reserve.getUserDept())) {
			sBuilder.append(" and a.userDept like ? ");
			paramsList.add("%"+reserve.getUserDept()+"%");
		}
		if (reserve.getReserveDateMin()!=null) {
			sBuilder.append(" and a.reserveDate1 >= ? ");
			paramsList.add(reserve.getReserveDateMin());
		}
		if (reserve.getReserveDateMax()!=null) {
			sBuilder.append(" and a.reserveDate1 <= ? ");
			paramsList.add(reserve.getReserveDateMax());
		}
		if (reserve.getReserveFlag()!=null && reserve.getReserveFlag()!=0) {
			sBuilder.append(" and a.reserveFlag = ? ");
			paramsList.add(reserve.getReserveFlag());
		}
		if (reserve.getReserveFlags()!=null && !"".equals(reserve.getReserveFlags())) {
			sBuilder.append(" and a.reserveFlag in (?) ");
			paramsList.add(reserve.getReserveFlags());
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), params);
		sum = (int)count;
		return sum;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<ReserveItem>  listReservesSum2(Reserve reserve){
		List<ReserveItem> reserves = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT	date_format(a.reserve_date1,'%Y-%m-%d') reserve_date, ");
		sBuilder.append("		date_format(a.reserve_date1,'%w') reserve_week, ");
		sBuilder.append("		a.reserve_length, ");
		sBuilder.append("		(case when date_format(a.reserve_date1,'%H:%i:%s') BETWEEN '08:00:00' AND '12:00:00' then 1  ");
		sBuilder.append("			  when date_format(a.reserve_date1,'%H:%i:%s') BETWEEN '13:00:00' AND '18:00:00' then 2 ");
		sBuilder.append("			  else 3 end) reserve_sec, ");
		sBuilder.append("		a.user_dept,(case when a.reserve_flag=1 then '已预约' else '已分配' end) reserve_flag, ");
		sBuilder.append("		a.real_name,a.meet_content, ");
		sBuilder.append("		concat(date_format(a.reserve_date1,'%H:%i'),'-',date_format(a.reserve_date2,'%H:%i')) reserve_time ");
		sBuilder.append("  FROM reserve a JOIN room r ON a.room_id = r.room_id ");
		sBuilder.append(" WHERE 1 = 1 and a.reserve_flag in (1,2) ");
		List<Object> paramsList = new ArrayList<Object>();
		if (reserve.getRoom()!=null && reserve.getRoom().getRoomId()!=null) {
			sBuilder.append(" and a.room_id = ? ");
			paramsList.add(reserve.getRoom().getRoomId());
		}
		if (reserve.getRoom()!=null && !StringUtil.isEmptyString(reserve.getRoom().getRoomName())) {
			sBuilder.append(" and concat(r.room_name,'(',r.room_no,')') like ? ");
			paramsList.add("%"+reserve.getRoom().getRoomName()+"%");
		}
		if (reserve.getReserveDateMin()!=null) {
			sBuilder.append(" and a.reserve_date1 >= ? ");
			paramsList.add(reserve.getReserveDateMin());
		}
		if (reserve.getReserveDateMax()!=null) {
			sBuilder.append(" and a.reserve_date1 <= ? ");
			paramsList.add(reserve.getReserveDateMax());
		}
		if (reserve.getReserveFlag()!=null && reserve.getReserveFlag()!=0) {
			sBuilder.append(" and a.reserve_flag = ? ");
			paramsList.add(reserve.getReserveFlag());
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		sBuilder.append(" order by a.reserve_date1 asc,date_format(a.reserve_date1,'%w') asc");

		List<Map<String, Object>> list = null;
		if (reserve.getStart()!=-1) {
			list = super.executeQueryMapSql(sBuilder.toString(), params, reserve.getStart(), reserve.getLimit());
		}else {
			list = super.executeQueryMapSql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			reserves = new ArrayList<ReserveItem>();
			for (int i=0;i<list.size();i++ ) {
				ReserveItem _reserve = new ReserveItem();
				Map<String, Object> map = list.get(i);
				_reserve.setReserve_date(map.get("reserve_date").toString());
				_reserve.setReserve_week(Integer.parseInt(map.get("reserve_week").toString()));
				_reserve.setReserve_length(Double.parseDouble(map.get("reserve_length").toString()));
				_reserve.setReserve_sec(Integer.parseInt(map.get("reserve_sec").toString()));
				_reserve.setUser_dept(map.get("user_dept").toString());
				_reserve.setReserve_flag(map.get("reserve_flag").toString());
				_reserve.setReal_name(map.get("real_name").toString());
				_reserve.setMeet_content(map.get("meet_content").toString());
				_reserve.setReserve_time(map.get("reserve_time").toString());
				reserves.add(_reserve);
			}
		}
		return reserves;
	}
	
}
