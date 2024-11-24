package com.choco.pet.dao;

import org.apache.ibatis.annotations.Mapper;

import com.choco.pet.model.Pet;

@Mapper
public interface PetRepository {
	void insertPet(Pet pet);
	int createPetId();
}
