package com.hg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hg.domain.Dog;
import com.hg.repo.DogRepo;

@Service
public class DogServiceDB implements DogService {

	private DogRepo repo;

	@Autowired
	public DogServiceDB(DogRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Dog createDog(Dog dogg) {
		Dog created = this.repo.save(dogg);
		return created;
	}

	@Override
	public List<Dog> getAllDogs() {
		return this.repo.findAll(); // SELECT * FROM DOG
	}

	@Override
	public List<Dog> getDogByName(String name) {
		List<Dog> found = this.repo.findByNameIgnoreCase(name);
		return found;
	}

	@Override
	public List<Dog> getDogByBreed(String breed) {
		List<Dog> found = this.repo.findByBreedIgnoreCase(breed);
		return found;
	}


	@Override
	public Dog getDog(Integer id) {
		Optional<Dog> found = this.repo.findById(id); // SELECT * FROM DOG ...WHERE ID=
		return found.get();
	}

	@Override
	public Dog replaceDog(Integer id, Dog newDog) {
		Dog existing = this.repo.findById(id).get();

		existing.setAge(newDog.getAge());
		existing.setBreed(newDog.getBreed());
		existing.setName(newDog.getName());

		Dog updated = this.repo.save(existing);
		return updated;
	}

	@Override
	public void removeDog(Integer id) {
		this.repo.deleteById(id); // DELETE FROM DOG WHERE ID=

	}




}
