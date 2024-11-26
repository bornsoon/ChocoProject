package com.choco.pet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choco.pet.dao.PetRepository;
import com.choco.pet.model.Pet;


@Service
public class BasicPetService implements PetService {
	
	@Autowired
	PetRepository petRepository;
	
	@Override
	public int createPetId() {
		return petRepository.createPetId();
	}
	
	@Override
	public Pet getPetInfo(String usersId) {
		return petRepository.getPetInfo(usersId);
	}
	
}
