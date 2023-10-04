package com.api.hotel.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.hotel.model.Room;
import com.api.hotel.model.RoomType;

public interface RoomRepo extends JpaRepository<Room, Integer> {

	@Query("select r from Room r where type = :type")
	public List<Room> findRoomsByType(@Param("type") String type);
	
//	public List<Room> findRoomsByType(String type);
}
