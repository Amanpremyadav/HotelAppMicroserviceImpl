package com.user.service.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.external.services.HotelService;
import com.user.service.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// Implement RATINGSERVICE Call using : RestTemplate
		List<User> allUsers = userRepository.findAll();

		// Iterate over each user to fetch their ratings
		for (User user : allUsers) {

			// Fetch the ratings for this user
			Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/user/" + user.getUserId(),
					Rating[].class);

			List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

			List<Rating> ratingList = ratings.stream().map(rating -> {
				// Api call to hotel service to get the hotel
				// http://localhost:8082/hotels/2e092957-3425-4401-afdf-d55572422c73

				ResponseEntity<Hotel> forEntity = restTemplate
						.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
				Hotel hotel = forEntity.getBody();

				// set the hotel to rating
				rating.setHotel(hotel);

				return rating;
			}).collect(Collectors.toList());

			// Set the ratings for this user (assuming User has a setRatings method)
			user.setRatings(ratingList);
		}

		return allUsers;
	}

	@Override
	public Optional<User> getUser(String userId) {
		Optional<User> user = userRepository.findById(userId);

		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/user/" + userId,
				Rating[].class);

		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {
			// api call to hotel service to get the hotel
			// http://localhost:8082/hotels/2e092957-3425-4401-afdf-d55572422c73

			/***
			 * ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" +rating.getHotelId(), Hotel.class); 
			 * Hotel hotel = forEntity.getBody();
			 */

			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
			// set the hotel to rating
			rating.setHotel(hotel);

			return rating;
		}).collect(Collectors.toList());

		if (user.isPresent()) {
			User existingUser = user.get();
			existingUser.setRatings(ratingList); // Assuming the ratings field can accept a list of Rating objects
			userRepository.save(existingUser);
		}

		return user;
	}
}
