package com.api.hotel.service;

import java.util.List;

import com.api.hotel.model.Room;
import com.api.hotel.model.RoomTransition;
import com.api.hotel.model.RoomType;

public interface RoomService {
	
	public List<Room> getRoomByRoomType(RoomType type);
	public List<Room> getAllRooms();
	public Room getRoomById(int id);
	public List<RoomTransition> getAllTransitions();
	public RoomTransition getTransitionById(int id);
	public List<RoomTransition> getTransitionsByVisitorId(int id);

}
