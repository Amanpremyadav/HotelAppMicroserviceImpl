package com.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import com.user.service.entities.Rating;
import com.user.service.external.services.RatingService;

@SpringBootTest
@Service
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating() {
		Rating rating = new Rating("", "", 4, "This is created using feign client", null);

		Rating savedRating = ratingService.createRating(rating);
		System.out.println("\n new rating created : " + savedRating);
	}

}
