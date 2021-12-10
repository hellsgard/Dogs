package com.hg.service;

import java.util.List;

import com.hg.domain.Dog;

public interface DogService {
	// private List<Dog> dogs = new ArrayList<>();

	Dog createDog(Dog dogg);

	List<Dog> getAllDogs();

	List<Dog> getDogByName(String name);

	List<Dog> getDogByBreed(String breed);

//		@GetMapping("/getAll")
//		public ResponseEntity<Dog> getAllDogs() {
//			ResponseEntity<Dog> response = new ResponseEntity<Dog>(return, HttpStatus.OK)
//			return this.dogs;
//		}
//			

	Dog getDog(Integer id);

	Dog replaceDog(Integer id, Dog newDog);

	void removeDog(Integer id);

}

