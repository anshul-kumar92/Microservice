package com.example.demo;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Model.CatalogItem;
import com.example.Model.Movie;
import com.example.Model.Rating;
import com.example.Model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {


	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	MovieInfo movieInfo;
	
	
	@Autowired
	UserRatingInfo userRatingInfo;

	@RequestMapping("/{userId}")

	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating userratings = userRatingInfo.getUserRating(userId);
		return UserRating.getRating.stream().map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
	}

	

	

	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
		return Arrays.asList(new CatalogItem("No Movie", "", 0));
	}

}
