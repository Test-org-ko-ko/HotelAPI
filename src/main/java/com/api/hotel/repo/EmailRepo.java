package com.api.hotel.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hotel.model.Email;

public interface EmailRepo extends JpaRepository<Email, Integer> {

}
