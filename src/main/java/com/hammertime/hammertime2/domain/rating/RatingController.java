package com.hammertime.hammertime2.domain.rating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hammertime.hammertime2.exceptions.ProfessionalNotFoundException;
import com.hammertime.hammertime2.exceptions.RatingNotFoundException;
import com.hammertime.hammertime2.service.IBackendService;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class RatingController {
	
	@Autowired
	IBackendService backendService;
	
	//use case: get all ratings.
	@GetMapping("/dataRequest/ratings")
	List<Rating> all() {
		return backendService.getRatings();
	}

	//use case: get all rating ids.
	@GetMapping("/dataRequest/ratings/ids")
	List<Long> getRatingIds(){
		return backendService.getRatingIds();
	}
	
	//use case: create rating
	@PostMapping("/dataRequest/rating")
	Rating newRating(@RequestBody Rating rating) throws ProfessionalNotFoundException {
		return backendService.createRating(rating);
	}

	//use case: update rating
	@PutMapping("/dataRequest/rating/{id}")
	Rating replaceRating(@RequestBody Rating newRating, @PathVariable Long id) {
		return backendService.updateRating(newRating, id);
	}
	
	//use case: delete rating
	@DeleteMapping("/dataRequest/rating/{id}")
	void deleteRating(@PathVariable Long id) {
		backendService.deleteRating(id);
	}
	
	//use case: get rating by id
	@GetMapping("/dataRequest/rating/{id}")
	Rating getRatingById(@PathVariable Long id) throws RatingNotFoundException {
		return backendService.getRating(id);
	}
}
