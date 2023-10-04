package com.api.hotel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hotel.model.Room;
import com.api.hotel.model.RoomTransition;
import com.api.hotel.model.RoomType;
import com.api.hotel.model.Visitor;
import com.api.hotel.repo.RoomRepo;
import com.api.hotel.repo.RoomTransitionRepo;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private RoomTransitionRepo transRepo;
	
	@Override
	public List<Room> getRoomByRoomType(RoomType type) {
		List<Room> rooms = new ArrayList<>();
		try {
			rooms = roomRepo.findRoomsByType(type.toString());
		}
		catch (Exception e) {
			System.out.println("getRoomByRoomType: DB RETRIEVAL FAILED - " + e.getMessage());
		}
		
		return rooms;
	}

	@Override
	public List<Room> getAllRooms() {
		List<Room> rooms = new ArrayList<>();
		try {
			rooms = roomRepo.findAll();
		}
		catch (Exception e) {
			System.out.println("getAllRooms : DB RETRIEVAL FAILED - " + e.getMessage());
		}
		
		return rooms;
	}

	@Override
	public Room getRoomById(int id) {
		Room room = null;
		try {
			room = roomRepo.findById(id).orElse(new Room());
		}
		catch (Exception e) {
			System.out.println("getAllRooms : DB RETRIEVAL FAILED - " + e.getMessage());
		}
		
		return room;
	}

	@Override
	public List<RoomTransition> getAllTransitions() {
		try {
			return transRepo.findAll();
		}
		catch (Exception e) {
			System.out.println("getAllTransitions : DB RETRIEVAL FAILED - " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public RoomTransition getTransitionById(int id) {
		try {
			return transRepo.findById(id).orElse(new RoomTransition());
		}
		catch (Exception e) {
			System.out.println("getAllTransitions : DB RETRIEVAL FAILED - " + e.getMessage());
		}
		
		return null;
	}

	// test
	@Override
	public List<RoomTransition> getTransitionsByVisitorId(int id) {
		try {
			List<RoomTransition> transitions = transRepo.findRoomTransitionsByVisitorId(id);
			
		}
		catch (Exception e) {
			System.out.println("getTransitionsByVisitorId : DB RETRIEVAL FAILED - " + e.getMessage());
		}
		
		return null;
	}
	
}
