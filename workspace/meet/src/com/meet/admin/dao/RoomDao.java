package com.meet.admin.dao;

import java.util.ArrayList;
import java.util.List;

import com.meet.admin.domain.Room;
import com.meet.common.dao.BaseDao;
import com.meet.common.util.StringUtil;

public class RoomDao extends BaseDao{
	
	public void addRoom(Room room){
		super.add(room);
	}
	
	public void delRoom(Integer roomId){
		super.del(Room.class, roomId);
	}
	
	public void delRoom(String[] roomIds){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <roomIds.length; i++) {
			sBuilder.append(roomIds[i]);
			if (i !=roomIds.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Room d WHERE d.roomId IN (" +sBuilder.toString()+")";

		Object[] params = null;
		
		super.executeUpdateHql(hql, params);
	}
	
	public void updateRoom(Room room){
		Room _room = (Room)super.get(Room.class, room.getRoomId());
		if (!StringUtil.isEmptyString(room.getRoomNo())) {
			_room.setRoomNo(room.getRoomNo());
		}
		if (!StringUtil.isEmptyString(room.getRoomName())) {
			_room.setRoomName(room.getRoomName());
		}
		if (room.getRoomPerson()!=null) {
			_room.setRoomPerson(room.getRoomPerson());
		}
		if (!StringUtil.isEmptyString(room.getRoomNote())) {
			_room.setRoomNote(room.getRoomNote());
		}
	    //super.update(room);
	}
	
	@SuppressWarnings("rawtypes")
	public Room getRoom(Room room){
		Room _room = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Room a WHERE 1=1");
		
		List<Object> paramsList = new ArrayList<Object>();
		if (room.getRoomId()!=null && room.getRoomId()!=0) {
			sBuilder.append(" and a.roomId = ? ");
			paramsList.add(room.getRoomId());
		}
		if (!StringUtil.isEmptyString(room.getRoomNo())) {
			sBuilder.append(" and a.roomNo = ? ");
			paramsList.add(room.getRoomNo());
		}
		if (!StringUtil.isEmptyString(room.getRoomName())) {
			sBuilder.append(" and a.roomName = ? ");
			paramsList.add(room.getRoomName());
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
			_room = (Room)list.get(0);
		}
		return _room;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Room>  listRooms(Room room){
		List<Room> rooms = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Room a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (room.getRoomId()!=null && room.getRoomId()!=0) {
			sBuilder.append(" and a.roomId = ? ");
			paramsList.add(room.getRoomId());
		}
		if (!StringUtil.isEmptyString(room.getRoomNo())) {
			sBuilder.append(" and a.roomNo like ? ");
			paramsList.add("%"+room.getRoomNo()+"%");
		}
		if (!StringUtil.isEmptyString(room.getRoomName())) {
			sBuilder.append(" and a.roomName like ? ");
			paramsList.add("%"+room.getRoomName()+"%");
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		
		sBuilder.append(" order by a.roomId asc");

		List list = null;
		if (room.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, room.getStart(), room.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			rooms = new ArrayList<Room>();
			for (Object object : list) {
				rooms.add((Room)object);
			}
		}
		return rooms;
	}
	
	
	public int listRoomsCount(Room room){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Room a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (room.getRoomId()!=null && room.getRoomId()!=0) {
			sBuilder.append(" and a.roomId = ? ");
			paramsList.add(room.getRoomId());
		}
		if (!StringUtil.isEmptyString(room.getRoomNo())) {
			sBuilder.append(" and a.roomNo like ? ");
			paramsList.add("%"+room.getRoomNo()+"%");
		}
		if (!StringUtil.isEmptyString(room.getRoomName())) {
			sBuilder.append(" and a.roomName like ? ");
			paramsList.add("%"+room.getRoomName()+"%");
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
	
	
	
	
}
