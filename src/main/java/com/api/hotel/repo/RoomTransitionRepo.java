package com.api.hotel.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hotel.model.RoomTransition;

public interface RoomTransitionRepo extends 
	JpaRepository<RoomTransition, Integer> {
	
	public List<RoomTransition> findRoomTransitionsByVisitorId(int id);
}
