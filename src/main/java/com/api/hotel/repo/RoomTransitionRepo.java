package com.api.hotel.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hotel.model.RoomTransition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomTransitionRepo extends 
	JpaRepository<RoomTransition, Integer> {
	
	public List<RoomTransition> findRoomTransitionsByVisitorId(int id);

	@Query("SELECT t FROM RoomTransition t WHERE t.checkInDate >= :from AND t.checkOutDate <= :to")
	public List<RoomTransition> getTransitionsByDateRange(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
