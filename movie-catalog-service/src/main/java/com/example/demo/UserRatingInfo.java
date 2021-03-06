package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.Model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		return restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
	}

	public UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		// userRating.setUserId(userId);
		// userRating.setRatings(Arrays.asList(new Rating("0",0)));

		return userRating;
	}

}
