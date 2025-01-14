package com.service.hotel.services;

import java.util.List;
import java.util.Optional;
import com.service.hotel.entities.Hotel;

public interface HotelService {

	// create hotel
	Hotel create(Hotel hotel);

	// get all hotels
	List<Hotel> getAll();

	// get single hotel
	Optional<Hotel> get(String id);
}
