package com.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.user.service.entities.Rating;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {
	// get

	// post

	@PostMapping("/ratings")
	Rating createRating(Rating rating);

	// PUT
	@PutMapping("/ratings/{ratingId}")
	Rating updateRating(Rating rating, @PathVariable String ratingId);

	// Delete
	@DeleteMapping("/ratings/{ratingId}")
	void deleteRating(String ratingId);

}
