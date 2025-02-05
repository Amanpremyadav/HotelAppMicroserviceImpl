package com.rating.service.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rating.service.entities.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {

	// custom finder methods

	Optional<List<Rating>> findByUserId(String userId);

	Optional<List<Rating>> findByHotelId(String hotelId);

}
