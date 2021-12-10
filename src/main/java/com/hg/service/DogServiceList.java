package com.hg.service;

import java.util.ArrayList;
import java.util.List;

import com.hg.domain.Dog;


public class DogServiceList implements DogService {

	private List<Dog> dogs = new ArrayList<>();


	@Override
	public Dog createDog(Dog dogg) {
		this.dogs.add(dogg);
		Dog created = this.dogs.get(this.dogs.size() - 1);
		return created;
	}

	@Override
	public List<Dog> getAllDogs() {
		return this.dogs;
	}


	@Override
	public Dog getDog(Integer id) {
		System.out.println("ID: " + id);
		return this.dogs.get(id);

	}

	@Override
	public List<Dog> getDogByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dog replaceDog(Integer id, Dog newDog) {
		Dog found = this.dogs.set(id, newDog);
		System.out.println("ID: " + id);
		System.out.println("Dog: " + newDog);
		return found;
//			return dogs.get(id);

	}

	@Override
	public void removeDog(Integer id) {
		System.out.println("ID: " + id);
		this.dogs.remove(id.intValue());

	}

	@Override
	public List<Dog> getDogByBreed(String breed) {
		// TODO Auto-generated method stub
		return null;
	}

//	@GetMapping("/getAll")
//	public ResponseEntity<Dog> getAllDogs() {
//		ResponseEntity<Dog> response = new ResponseEntity<Dog>(return, HttpStatus.OK)
//		return this.dogs;
//	}
//

}

