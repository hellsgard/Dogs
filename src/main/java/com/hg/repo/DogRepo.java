package com.hg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hg.domain.Dog;

@Repository
public interface DogRepo extends JpaRepository<Dog, Integer> {

	// Spring will auto-generate all of the basic DRUD functionality

	List<Dog> findByNameIgnoreCase(String name);

	List<Dog> findByBreedIgnoreCase(String breed);
}
