package com.api.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.hotel.model.Room;
import com.api.hotel.model.RoomTransition;
import com.api.hotel.service.RoomService;

@RestController
@RequestMapping("/transition")
public class TransitionController {

	@Autowired
	private RoomService roomService;
	
	@GetMapping("/findall")
	public List<RoomTransition> getAllTransitions() {
		return roomService.getAllTransitions();
	}
	
	@GetMapping("/findbyid/{id}")
	public RoomTransition findById(@PathVariable(name = "id") int id) {
		try {
			return roomService.getTransitionById(id);
		}
		catch (Exception ex) {
			System.out.println("TransitionController : findById - " + ex.getMessage());
		}
		
		return null;
	}
	
	
	@GetMapping("/findbyvisitorid/{id}")
	public List<RoomTransition> getAllTransitions(@PathVariable(name = "id") int id) {
		return roomService.getTransitionsByVisitorId(id);
	}
	
	
	// check in
	public RoomTransition doCheckIn() {
		// validate input
		
		// call service method to check in 
		// and return transition record created upon check in success
		return null;
		
	}
	
	
	// checkout
	
	
	
	// payment for checkout via payment service
	
	
	
	// rating/feedback 
	
	
}
