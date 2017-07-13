package com.meet.admin.domain;

import com.meet.common.domain.BaseDomain;

/**
 * Room entity. @author MyEclipse Persistence Tools
 */

public class Room extends BaseDomain {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 7179232641484413801L;
	private Integer roomId;
	private String roomNo;
	private String roomName;
	private Integer roomPerson;
	private String roomNote;

	private String ids;
	// Constructors

	/** default constructor */
	public Room() {
	}

	/** full constructor */
	public Room(String roomNo, String roomName, Integer roomPerson,
			String roomNote) {
		this.roomNo = roomNo;
		this.roomName = roomName;
		this.roomPerson = roomPerson;
		this.roomNote = roomNote;
	}

	// Property accessors

	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomNo() {
		return this.roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getRoomPerson() {
		return this.roomPerson;
	}

	public void setRoomPerson(Integer roomPerson) {
		this.roomPerson = roomPerson;
	}

	public String getRoomNote() {
		return this.roomNote;
	}

	public void setRoomNote(String roomNote) {
		this.roomNote = roomNote;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}