package com.rating.service.services;

import java.util.List;
import java.util.Optional;

import com.rating.service.entities.Rating;

public interface RatingService {
	// create
	Rating create(Rating rating);

	// get all ratings
	List<Rating> getRatings();

	// get all ratings using userId
	Optional<List<Rating>> getRatingsByUserId(String userId);

	// get all ratings using hotelId
	Optional<List<Rating>> getRatingsByHotelId(String hotelId);
}
