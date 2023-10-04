package com.api.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.hotel.model.Room;
import com.api.hotel.model.RoomType;
import com.api.hotel.service.EmailService;
import com.api.hotel.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@GetMapping("/findbytype/{type}")
	public List<Room> findByType(@PathVariable(name = "type") String type) {
		List<Room> rooms = new ArrayList<>();
		
		if (type == null || type.isBlank()) {
			return rooms;
		}
		else {
			type = type.trim().toUpperCase();
		}
		
		try {
			RoomType typeEnum = RoomType.valueOf(type);
			rooms = roomService.getRoomByRoomType(typeEnum);
		}
		catch (IllegalArgumentException e) {
			System.out.println("Invalid Room Type - " + e.getMessage());
		}
		catch (Exception ex) {
			System.out.println("RoomController : findByType - " + ex.getMessage());
		}
		
		return rooms;
	}
	
	@GetMapping("/findall")
	public List<Room> getAllRooms() {
		return roomService.getAllRooms();
	}
	
	@GetMapping("/findbyid/{id}")
	public Room findById(@PathVariable(name = "id", required = false) int id) {
		Room room = null;	
		try {
			room = roomService.getRoomById(id);
		}
		catch (Exception ex) {
			System.out.println("RoomController : findById - " + ex.getMessage());
		}
		
		return room;
	}

	
}
