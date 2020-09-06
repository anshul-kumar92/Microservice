package com.ratings.data;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratings.model.Rating;
import com.ratings.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId)
	{
		return new Rating(movieId,4);
	}
	
	@RequestMapping("users/{userId}")
	public  UserRating getuserRating(@PathVariable("userId") String userId)
	{
		 List<Rating> ratings=Arrays.asList(
				  
				  new Rating("12345",4),
				  new Rating("5678",4)
				  );
		 
		 
		 UserRating userRating=new UserRating();
		 userRating.setUserRating(ratings);
		 return userRating;
	}
	
	
}
