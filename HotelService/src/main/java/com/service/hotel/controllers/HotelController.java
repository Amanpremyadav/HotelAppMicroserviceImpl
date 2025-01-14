package com.service.hotel.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.hotel.entities.Hotel;
import com.service.hotel.exceptions.ResourceNotFoundException;
import com.service.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
	private HotelService hotelService;

	// create hotel
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		Hotel hotel1 = hotelService.create(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
	}

	// get single hotel
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId) {
		Optional<Hotel> outHotel = hotelService.get(hotelId);

		Hotel hotel = null;
		if (outHotel.isPresent()) {
			hotel = outHotel.get();
			return ResponseEntity.ok(hotel);
		} else {
			throw new ResourceNotFoundException("hotel not found with hotelId : " + hotelId);
		}
	}

	// get all hotels
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels() {
		List<Hotel> allHotels = hotelService.getAll();
		return ResponseEntity.ok(allHotels);
	}
}
