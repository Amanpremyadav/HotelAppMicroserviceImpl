package com.rating.service.controllers;

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
import com.rating.service.entities.Rating;
import com.rating.service.exceptions.ResourceNotFoundException;
import com.rating.service.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	// create rating
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		Rating rating1 = ratingService.create(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
	}

	// get all ratings
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings() {
		List<Rating> allRatings = ratingService.getRatings();
		return ResponseEntity.ok(allRatings);
	}

	// get all ratings using using userId
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
		Optional<List<Rating>> outRatings = ratingService.getRatingsByUserId(userId);

		if (outRatings.isPresent()) {
			List<Rating> ratingsByUserId = outRatings.get();
			return ResponseEntity.ok(ratingsByUserId); // Will return empty list if no ratings found
		} else {
			throw new ResourceNotFoundException("User not found with userId : " + userId);
		}

	}

	// get all ratings using using hotelId
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
		Optional<List<Rating>> outRatings = ratingService.getRatingsByHotelId(hotelId);

		if (outRatings.isPresent()) {
			List<Rating> ratingsByHotelId = outRatings.get();
			return ResponseEntity.ok(ratingsByHotelId);
		} else {
			throw new ResourceNotFoundException("hotel not found with hotelId : " + hotelId);
		}
	}
}
