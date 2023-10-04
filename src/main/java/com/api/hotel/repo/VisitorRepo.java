package com.api.hotel.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hotel.model.Visitor;

public interface VisitorRepo extends JpaRepository<Visitor, Integer> {

}
