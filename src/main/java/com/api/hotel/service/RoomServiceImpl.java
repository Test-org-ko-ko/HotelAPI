package com.api.hotel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.api.hotel.constant.Constant;
import com.api.hotel.model.*;
import com.api.hotel.repo.VisitorFineRepo;
import com.api.hotel.repo.VisitorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hotel.repo.RoomRepo;
import com.api.hotel.repo.RoomTransitionRepo;

@Service
public class RoomServiceImpl implements RoomService {

	private static final Logger LOG = LoggerFactory.getLogger(RoomServiceImpl.class);
	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private RoomTransitionRepo transRepo;

	@Autowired
	private VisitorRepo visitorRepo;

	@Autowired
	private VisitorFineRepo visitorFineRepo;
	
	@Override
	public List<Room> getRoomByRoomType(RoomType type) {
		List<Room> rooms = new ArrayList<>();
		try {
			rooms = roomRepo.findRoomsByType(type.toString());
		}
		catch (Exception e) {
			LOG.error(Constant.DB_FAILED + e.getMessage(), e);
		}
		return rooms;
	}

	@Override
	public List<Room> getAllRooms() {
		try {
			List<Room> rooms = roomRepo.findAll();
			if (rooms != null && rooms.size() > 0) {
				return rooms.stream()
						.filter(room -> room.isAvailable())
						.toList();
			}
		}
		catch (Exception e) {
			LOG.error(Constant.DB_FAILED + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Room getRoomById(int id) {
		Room room = null;
		try {
			room = roomRepo.findById(id).orElse(new Room());
		}
		catch (Exception e) {
			LOG.error(Constant.DB_FAILED + e.getMessage(), e);
		}
		return room;
	}

	@Override
	public List<RoomTransition> getAllTransitions() {
		try {
			return transRepo.findAll();
		}
		catch (Exception e) {
			LOG.error(Constant.DB_FAILED + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public RoomTransition getTransitionById(int id) {
		try {
			return transRepo.findById(id).orElse(new RoomTransition());
		}
		catch (Exception e) {
			LOG.error(Constant.DB_FAILED + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<RoomTransition> getTransitionsByVisitorId(int id) {
		try {
			return transRepo.findRoomTransitionsByVisitorId(id);
		}
		catch (Exception e) {
			LOG.error(Constant.DB_FAILED + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<RoomTransition> getTransitionsByDateRange(RoomTransition transition) {
		try {
			List<RoomTransition> transitions = transRepo.getTransitionsByDateRange(transition.getCheckInDate(),
					transition.getCheckOutDate());
			if (transitions != null && transitions.size() > 0) {
				for (int i = 0; i < transitions.size(); i++) {
					RoomTransition trn = transitions.get(i);
					Optional<Visitor> visitor = visitorRepo.findById(trn.getVisitorId());
					visitor.ifPresent(v -> trn.setVisitor(v));
				}
			}
			return transitions;
		}
		catch (Exception e) {
			LOG.error(Constant.DB_FAILED + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public boolean createVisitorFine(VisitorFine visitorFine) {
		try {
			VisitorFine visitorFineCreated = visitorFineRepo.save(visitorFine);
			return visitorFineCreated != null && visitorFineCreated.getVisitorId() != 0;
		} catch (Exception e) {
			LOG.error(Constant.DB_FAILED + e.getMessage(), e);
		}
		return false;
	}
}
